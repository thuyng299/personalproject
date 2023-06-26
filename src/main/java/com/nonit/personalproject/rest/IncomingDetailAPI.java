package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.*;
import com.nonit.personalproject.dto.customdto.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/incomingdetails")
public interface IncomingDetailAPI {
    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping
    ResponseEntity<List<IncomingDetailDTO>> getAllIncomingDetail();

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/{incomingId}")
    ResponseEntity<IncomingDetailDTO> findIncomingDetailById(@PathVariable("incomingId") Long incomingId);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @DeleteMapping("/{incomingId}")
    ResponseEntity<Void> deleteIncomingDetail(@PathVariable("incomingId") Long incomingId);

    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
    @PutMapping("/{incomingId}")
    ResponseEntity<IncomingDetailDTO> updateIncomingDetail(@PathVariable("incomingId") Long incomingId,
                                                           @RequestBody IncomingDetailUpdateDTO incomingDetailUpdateDTO);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/product-incoming")
        // localhost:8080/incomingdetails/product-incoming?date=2024-02-27
    ResponseEntity<List<IncomingAmountStatsDTO>> getNumberOfProductIncoming(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/incoming-purchasetime-amount")
        // localhost:8080/incomingdetails/incoming-purchasetime-amount
    ResponseEntity<List<PurchaseTimeStatDTO>> getNumberOfPurchaseTimeAndAmount();

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/product-purchasetime-amount")
        // localhost:8080/incomingdetails/product-purchasetime-amount?inputId=30
    ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProduct(@RequestParam("inputId") Long inputId);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/inputproduct-purchasetime-amount")
        // localhost:8080/incomingdetails/inputproduct-purchasetime-amount?inputId=15&inputDate=2022-08-27
    ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProductAndDate(@RequestParam("inputId") Long inputId,
                                                                                         @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/purchaseamount-fromdate-todate")
        // localhost:8080/incomingdetails/purchaseamount-fromdate-todate?fromDate=2022-01-21&toDate=2022-07-21
    ResponseEntity<List<PurchaseTimeStatDTO>> getPurchaseTimeAndAmountBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                                                   @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/stock-nearlyexpiring")
        // localhost:8080/incomingdetails/stock-nearlyexpiring?inputCountDays=30
    ResponseEntity<List<Object[]>> getCountDaysAndAmountBeforeExpire(@RequestParam("inputCountDays") Long inputCountDays);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/products-nearlyoutofstock")
        // localhost:8080/incomingdetails/products-nearlyoutofstock?inputAmount=15000
    ResponseEntity<List<ProductNearlyOutOfStockStatDTO>> getProductNearlyOutOfStock(@RequestParam("inputAmount") Double inputAmount);

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/totalstock-rawmaterial")
    // localhost:8080/incomingdetails/totalstock-rawmaterial
    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterial();

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/totalstock-finishedgood")
    // localhost:8080/incomingdetails/totalstock-finishedgood
    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGood();

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/totalstock-rawmaterial-fromdate-todate")
        // localhost:8080/incomingdetails/totalstock-rawmaterial-fromdate-todate?inputDate=2022-07-21
    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterialBeforeDate(@RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/totalstock-finishedgood-fromdate-todate")
        // localhost:8080/incomingdetails/totalstock-finishedgood-fromdate-todate?inputDate=2022-07-21
    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGoodBeforeDate(@RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/totalproductcost")
        // localhost:8080/incomingdetails/totalproductcost
    ResponseEntity<List<CostStatsDTO>> getProductsTotalCost();


    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/incoming-product")
    ResponseEntity<List<IncomingProductStatDTO>> getIncomingAmountOfProduct(@RequestParam("inputProductId") Long inputProductId);

    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/product-stock")
        // localhost:8080/incomingdetails/product-stock?inputProductId=13
    ResponseEntity<TotalStockOfProductStatDTO> getTotalStockAmountOfProduct(@RequestParam("inputProductId") Long inputProductId);

    @GetMapping("/total-incoming-within-month")
        // localhost:8080/incomingdetails/total-incoming-within-month
    ResponseEntity<Object> getInAmountWithinMonth();

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/total-stock-not-expiration")
    // localhost:8080/incomingdetails/total-stock-not-expiration
    ResponseEntity<Object> getStockAmountNotExpiration();

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/total-monthly-incoming")
    // localhost:8080/incomingdetails/total-monthly-incoming
    ResponseEntity<List<MonthlyAmountDTO>> getMonthLyIncomingAmount();

    //    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
    @GetMapping("/total-annual-incoming")
    // localhost:8080/incomingdetails/total-annual-incoming
    ResponseEntity<List<YearAmountDTO>> getAnnualIncomingAmount();
}
