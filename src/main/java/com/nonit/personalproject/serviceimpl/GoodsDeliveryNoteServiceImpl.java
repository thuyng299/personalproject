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

//        goodsDeliveryNote = goodsDeliveryNoteRepository.save(goodsDeliveryNote);

        // Create Outgoing detail
        List<OutgoingDetail> outgoingDetails = new ArrayList<>();

        for (OutgoingDetailsCreateDTO outs : gdnCreateWithDetailsDTO.getOutgoingDetailsCreateDTOList()) {

            Double totalAmount = outs.getTotalAmount();

            Product product = productRepository.findById(outs.getProductId()).orElseThrow(WarehouseException::ProductNotFound);

            List<IncomingDetail> incomingDetails = incomingDetailRepository.findByProductIdOrderByExpirationDate(outs.getProductId());

            Double sumRemainingAmount = incomingDetails.stream()
                    .mapToDouble(IncomingDetail::getRemainingAmount)
                    .sum();
            if (totalAmount > sumRemainingAmount) {
                throw WarehouseException.badRequest("InvalidAmount", "FODs");
            } else {
                for (IncomingDetail ins : incomingDetails) {

                    if (ins.getExpirationDate().isAfter(gdnCreateWithDetailsDTO.getOutgoingDate())) {
                        if (ins.getRemainingAmount() >= totalAmount) {
                            ins.setRemainingAmount(ins.getRemainingAmount() - totalAmount);

                            // An incoming id is set to outgoing detail
                            outs.setIncomingId(ins.getId());
                            outs.setAmount(totalAmount);
                            totalAmount = 0.0;
                        } else {
                        /* if totalAmount > remainingAmount, it will subtract this remainingAmount to 0 and go to next incomingDetail,
                        subtract remainingAmount until totalAmount is 0
                       */
                            for (int i = 0; i < incomingDetails.size(); i++) {
                                Double outgoingAmount = incomingDetails.get(i).getRemainingAmount();
                                totalAmount -= incomingDetails.get(i).getRemainingAmount();
                                incomingDetails.get(i).setRemainingAmount(0.0);
                                OutgoingDetail outgoingDetail = OutgoingDetail.builder()
                                        .incomingDetail(incomingDetails.get(i))
                                        .amount(outgoingAmount)
                                        .price(outs.getPrice())
                                        .discount(outs.getDiscount())
                                        .product(product)
                                        .goodsDeliveryNote(goodsDeliveryNote)
                                        .build();
                                if (totalAmount < incomingDetails.get(i + 1).getRemainingAmount()) {
                                    OutgoingDetail outgoingDetail2 = OutgoingDetail.builder()
                                            .incomingDetail(incomingDetails.get(i + 1))
                                            .amount(outgoingAmount)
                                            .price(outs.getPrice())
                                            .discount(outs.getDiscount())
                                            .product(product)
                                            .goodsDeliveryNote(goodsDeliveryNote)
                                            .build();
                                }
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
