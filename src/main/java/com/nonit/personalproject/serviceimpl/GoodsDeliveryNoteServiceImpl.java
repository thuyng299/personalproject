package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.entity.*;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.GoodsDeliveryNoteMapper;
import com.nonit.personalproject.mapper.OutgoingDetailMapper;
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
    private final OutgoingDetailRepository outgoingDetailRepository;

    @Override
    public List<GDNWithDetailsDTO> getAllGoodsDeliveryNoteWithDetails() {
        List<GoodsDeliveryNote> goodsDeliveryNotes = goodsDeliveryNoteRepository.findAll();

        if (goodsDeliveryNotes.isEmpty()) {
            throw WarehouseException.GDNNotFound();
        }

        List<GDNWithDetailsDTO> gdnCreateWithDetailsDTOS = new ArrayList<>();

        for (GoodsDeliveryNote gdn : goodsDeliveryNotes) {
            GDNWithDetailsDTO gdnCreateWithDetailsDTO = new GDNWithDetailsDTO();

            gdnCreateWithDetailsDTO.setGdnId(gdn.getId());
            gdnCreateWithDetailsDTO.setCode(gdn.getCode());
            gdnCreateWithDetailsDTO.setCustomerName(gdn.getCustomer().getName());
            gdnCreateWithDetailsDTO.setOutgoingDate(gdn.getOutgoingDate());
            gdnCreateWithDetailsDTO.setEmployeeName(gdn.getEmployee().getFirstName() + " " + gdn.getEmployee().getLastName());
            gdnCreateWithDetailsDTO.setRecord(gdn.getRecord());

            List<OutgoingDetailsDTO> outgoingDetailsCreateDTOS = new ArrayList<>();

            for (OutgoingDetail outgoingDetail : gdn.getOutgoingDetail()) {

                OutgoingDetailsDTO outgoingDetailsCreateDTO = new OutgoingDetailsDTO();

                outgoingDetailsCreateDTO.setProductName(outgoingDetail.getProduct().getName());
                outgoingDetailsCreateDTO.setAmount(outgoingDetail.getAmount());
                outgoingDetailsCreateDTO.setPrice(outgoingDetail.getPrice());
                outgoingDetailsCreateDTO.setDiscount(outgoingDetail.getDiscount());
                outgoingDetailsCreateDTO.setIncomingDate(outgoingDetail.getIncomingDetail().getGoodsReceivedNote().getIncomingDate());
                outgoingDetailsCreateDTO.setAreaName(outgoingDetail.getIncomingDetail().getWarehouseArea().getName());
                outgoingDetailsCreateDTOS.add(outgoingDetailsCreateDTO);

                gdnCreateWithDetailsDTO.setOutgoingDetailsCreateDTOList(outgoingDetailsCreateDTOS);

            }

            gdnCreateWithDetailsDTOS.add(gdnCreateWithDetailsDTO);
        }

        return gdnCreateWithDetailsDTOS;
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
    public GDNCreateWithDetailsDTO createGoodsDeliveryNote(GDNCreateWithDetailsDTO gdnCreateWithDetailsDTO) {
        GDNCreateWithDetailsDTO finalGoodsDeliveryNoteDTO = new GDNCreateWithDetailsDTO();
        log.info("1");
        Customer customer = customerRepository.findById(gdnCreateWithDetailsDTO.getCustomerId()).orElseThrow(WarehouseException::CustomerNotFound);
        Employee employee = employeeRepository.findByUsername(getCurrentUsername()).get();
        log.info("Employee : " + employee.getId());


        // Create GDN
        GoodsDeliveryNote goodsDeliveryNote = GoodsDeliveryNote.builder()
                .code(gdnCreateWithDetailsDTO.getCode())
                .record(gdnCreateWithDetailsDTO.getRecord())
                .outgoingDate(LocalDateTime.now())
                .customer(customer)
                .employee(employee)
                .build();
        goodsDeliveryNoteRepository.save(goodsDeliveryNote);
        log.info("2");

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
            log.warn("sumRemainingAmount " + sumRemainingAmount);
            log.warn("totalAmount " + totalAmount);


            if (totalAmount > sumRemainingAmount) {
                log.info(" KHÔNG HỢP LỆ  totalAmount: " + totalAmount + "& sumRemainingAmount " + sumRemainingAmount);

                throw WarehouseException.badRequest("InvalidAmount", "FODs");
            } else {
                log.info(" HỢP LỆ  totalAmount: " + totalAmount + "& sumRemainingAmount " + sumRemainingAmount);
                log.info("--------------------------");


                for (IncomingDetail ins : incomingDetails) {

                    if (totalAmount == 0) {
                        log.info("Total amount : " + totalAmount);
                        break;
                    }

                    log.info("ID: " + ins.getId());

                    log.info("getExpirationDate: " + ins.getExpirationDate());
                    // TÂN: Vì gdnCreateWithDetailsDTO không có nhập vào Date nên thay thành LocalDateTime.now()
                    if (ins.getExpirationDate().isAfter(LocalDateTime.now())) {
                        log.warn("CÒN HẠN ");

                        if (ins.getRemainingAmount() >= totalAmount) {
                            log.info("Đủ số lượng remain>total");
                            log.info("getRemainingAmount before: " + ins.getRemainingAmount());
                            log.info("totalAmount before: " + totalAmount);

                            ins.setRemainingAmount(ins.getRemainingAmount() - totalAmount);

                            // An incoming id is set to outgoing detail
                            outs.setIncomingId(ins.getId());
                            outs.setAmount(totalAmount);
                            totalAmount = 0.0;
                            log.info("getRemainingAmount after: " + ins.getRemainingAmount());
                            log.info("totalAmount after: " + totalAmount);

                        } else {
                        /* if totalAmount > remainingAmount, it will subtract this remainingAmount to 0 and go to next incomingDetail,
                        subtract remainingAmount until totalAmount is 0
                       */

                            log.info("Không Đủ số lượng remain<total");
                            log.info("getRemainingAmount before: " + ins.getRemainingAmount());
                            log.info("totalAmount before: " + totalAmount);
                            outs.setIncomingId(ins.getId());
                            outs.setAmount(ins.getRemainingAmount());
                            totalAmount -= ins.getRemainingAmount();
                            ins.setRemainingAmount(0.0);
                            log.info("getRemainingAmount after: " + ins.getRemainingAmount());
                            log.info("totalAmount after: " + totalAmount);
//i

                        }


                    }
                    incomingDetailRepository.save(ins);

                    log.info("HERE1");

                    OutgoingDetail outgoingDetail = new OutgoingDetail();
                    log.info("HERE2");

                    //Create Out Going Detail
                    if (outs.getAmount()!=0) {
                        outgoingDetail.setGoodsDeliveryNote(goodsDeliveryNote);
                        outgoingDetail.setProduct(product);
                        outgoingDetail.setAmount(outs.getAmount());
                        outgoingDetail.setPrice(outs.getPrice());
                        outgoingDetail.setDiscount(outs.getDiscount());
                        outgoingDetail.setIncomingDetail(ins);
                        outgoingDetailRepository.save(outgoingDetail);
                        outgoingDetails.add(outgoingDetail);
                    }

                    log.info("HERE3");

                    log.info("END");

                }


            }

        }

        // GDN show out
        finalGoodsDeliveryNoteDTO.setGdnId(goodsDeliveryNote.getId());
        finalGoodsDeliveryNoteDTO.setCode(goodsDeliveryNote.getCode());
        finalGoodsDeliveryNoteDTO.setCustomerId(goodsDeliveryNote.getCustomer().getId());
        finalGoodsDeliveryNoteDTO.setOutgoingDate(LocalDateTime.now());
        finalGoodsDeliveryNoteDTO.setEmployeeId(goodsDeliveryNote.getEmployee().getId());
        finalGoodsDeliveryNoteDTO.setOutgoingDetailsCreateDTOList(OutgoingDetailMapper.INSTANCE.toDtos(outgoingDetails));

        return finalGoodsDeliveryNoteDTO;
    }

    @Override
    public void deleteGoodsDeliveryNote(Long gdnId) {
        log.info("delete GDN by id {}", gdnId);
        goodsDeliveryNoteRepository.deleteById(gdnId);
    }
}
