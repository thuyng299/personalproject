package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.GDNCreateWithDetailsDTO;
import com.nonit.personalproject.dto.GoodsDeliveryNoteDTO;
import com.nonit.personalproject.dto.OutgoingDetailsCreateDTO;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.GoodsDeliveryNoteMapper;
import com.nonit.personalproject.repository.*;
import com.nonit.personalproject.service.GoodsDeliveryNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodsDeliveryNoteServiceImpl implements GoodsDeliveryNoteService {
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IncomingDetailRepository incomingDetailRepository;
    private final GoodsDeliveryNoteMapper goodsDeliveryNoteMapper = GoodsDeliveryNoteMapper.INSTANCE;

    @Override
    public List<GoodsDeliveryNoteDTO> getAllGoodsDeliveryNote() {
        List<GoodsDeliveryNote> goodsDeliveryNotes = goodsDeliveryNoteRepository.findAll();
        if (goodsDeliveryNotes.isEmpty()) {
            throw WarehouseException.GDNNotFound();
        }
        return goodsDeliveryNoteMapper.toDtos(goodsDeliveryNotes);
    }

    @Override
    public GoodsDeliveryNoteDTO findGoodsDeliveryNoteById(Long gdnId) {
        GoodsDeliveryNote goodsDeliveryNote = goodsDeliveryNoteRepository.findById(gdnId).orElseThrow(WarehouseException::GDNNotFound);
        return goodsDeliveryNoteMapper.toDto(goodsDeliveryNote);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public GoodsDeliveryNoteDTO createGoodsDeliveryNote(GDNCreateWithDetailsDTO gdnCreateWithDetailsDTO) {
        log.info("1");
        Customer customer = customerRepository.findById(gdnCreateWithDetailsDTO.getCustomerId()).orElseThrow(WarehouseException::CustomerNotFound);
        Employee employee = employeeRepository.findByUsername(getCurrentUsername()).get();

        // Create GDN
        GoodsDeliveryNote goodsDeliveryNote = GoodsDeliveryNote.builder()
                .code(gdnCreateWithDetailsDTO.getCode())
                .record(gdnCreateWithDetailsDTO.getRecord())
                .outgoingDate(LocalDateTime.now())
                .customer(customer)
                .employee(employee)
                .build();
        log.info("2");
//        goodsDeliveryNote = goodsDeliveryNoteRepository.save(goodsDeliveryNote);

        // Create Outgoing detail
        List<OutgoingDetail> outgoingDetails = new ArrayList<>();

        for (OutgoingDetailsCreateDTO outs : gdnCreateWithDetailsDTO.getOutgoingDetailsCreateDTOList()) {
//TÂN: Vì total Amount là null nên thế là Amount
            Double totalAmount = outs.getAmount();

            Product product = productRepository.findById(outs.getProductId()).orElseThrow(WarehouseException::ProductNotFound);

            List<IncomingDetail> incomingDetails = incomingDetailRepository.findByProductIdOrderByExpirationDate(outs.getProductId());

            Double sumRemainingAmount = incomingDetails.stream()
                    .mapToDouble(IncomingDetail::getRemainingAmount)
                    .sum();
            log.warn("sumRemainingAmount "+sumRemainingAmount);
            log.warn("totalAmount "+totalAmount);

            log.info("3");
            log.warn("Here ");

            if (totalAmount > sumRemainingAmount) {
                log.info("Here 1 ");

                throw WarehouseException.badRequest("InvalidAmount", "FODs");
            } else {
                log.info("Here 2");

                for (IncomingDetail ins : incomingDetails) {
                    log.info("4");

                    log.info("getExpirationDate: "+ins.getExpirationDate());
                    log.info("getOutgoingDate: "+gdnCreateWithDetailsDTO.getOutgoingDate());
                    // TÂN: Vì gdnCreateWithDetailsDTO không có nhập vào Date nên thay thành LocalDateTime.now()
                    if (ins.getExpirationDate().isAfter(LocalDateTime.now())) {
                        log.warn("IncomingDetailId "+ins.getId());

                        if (ins.getRemainingAmount() >= totalAmount) {
                            ins.setRemainingAmount(ins.getRemainingAmount() - totalAmount);

                            // An incoming id is set to outgoing detail
                            outs.setIncomingId(ins.getId());
                            outs.setAmount(totalAmount);
                            totalAmount = 0.0;
                            log.info("5");

                        } else {
                        /* if totalAmount > remainingAmount, it will subtract this remainingAmount to 0 and go to next incomingDetail,
                        subtract remainingAmount until totalAmount is 0
                       */
                            log.info("6");
                            for (int i = 0; i < incomingDetails.size(); i++) {

                                Double outgoingAmount = incomingDetails.get(i).getRemainingAmount();
                                log.info("incomingDetails ID : "+incomingDetails.get(i).getId());
                                log.info("incomingDetails Amount : "+incomingDetails.get(i).getRemainingAmount());
                                log.info("totalAmount before : " + totalAmount);

                                if (totalAmount-incomingDetails.get(i).getRemainingAmount()>0) {
                                    totalAmount -= incomingDetails.get(i).getRemainingAmount();
                                    log.info("totalAmount after : " + totalAmount);
                                    incomingDetails.get(i).setRemainingAmount(0.0);
                                    log.info("incomingDetails Amount  : " + incomingDetails.get(i).getRemainingAmount());

                                    OutgoingDetail outgoingDetail = OutgoingDetail.builder()
                                            .incomingDetail(incomingDetails.get(i))
                                            .amount(outgoingAmount)
                                            .price(outs.getPrice())
                                            .discount(outs.getDiscount())
                                            .product(product)
                                            .goodsDeliveryNote(goodsDeliveryNote)
                                            .build();
                                    log.info("7");
                                } else {
                                    incomingDetails.get(i).setRemainingAmount(incomingDetails.get(i).getRemainingAmount()-totalAmount);

                                    log.info("incomingDetails Amount : " + incomingDetails.get(i).getRemainingAmount());
                                    totalAmount= (double) 0;
                                    log.info("totalAmount after : " + totalAmount);

                                    OutgoingDetail outgoingDetail = OutgoingDetail.builder()
                                            .incomingDetail(incomingDetails.get(i))
                                            .amount(outgoingAmount)
                                            .price(outs.getPrice())
                                            .discount(outs.getDiscount())
                                            .product(product)
                                            .goodsDeliveryNote(goodsDeliveryNote)
                                            .build();
                                    log.info("8");
                                }
                                if (totalAmount==0) break;
                            }


                        }

                        OutgoingDetail outgoingDetail = new OutgoingDetail();
                        outgoingDetail.setGoodsDeliveryNote(goodsDeliveryNote);
                        outgoingDetail.setProduct(product);
                        outgoingDetail.setAmount(outs.getAmount());
                        outgoingDetail.setPrice(outs.getPrice());
                        outgoingDetail.setDiscount(outs.getDiscount());
                        outgoingDetails.add(outgoingDetail);
                    }


                }


            }

        }

        return goodsDeliveryNoteMapper.toDto(goodsDeliveryNote);
    }

    @Override
    public void deleteGoodsDeliveryNote(Long gdnId) {
        log.info("delete GDN by id {}", gdnId);
        goodsDeliveryNoteRepository.deleteById(gdnId);
    }
}
