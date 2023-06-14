//package com.nonit.personalproject.rest;
//
//import com.nonit.personalproject.dto.*;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@RequestMapping(value = "/incomingsdetails")
//public interface IncomingsDetailAPI {
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping
//    ResponseEntity<List<IncomingsDetailDTO>> getAllIncomingsDetail();
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/{incomingsId}")
//    ResponseEntity<IncomingsDetailDTO> findIncomingsDetailById(@PathVariable("incomingsId") Long incomingsId);
//
//    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
//    @DeleteMapping("/{incomingsId}")
//    ResponseEntity<Void> deleteIncomingsDetail(@PathVariable("incomingsId") Long incomingsId);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/product-incoming") // localhost:8080/incomingsdetails/product-incoming?date=2024-02-27
//    ResponseEntity<List<IncomingsAmountStatsDTO>> getNumberOfProductIncomings(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/incoming-purchasetime-amount") // localhost:8080/incomingsdetails/incoming-purchasetime-amount
//    ResponseEntity<List<PurchaseTimeStatDTO>> getNumberOfPurchaseTimeAndAmount();
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/product-purchasetime-amount") // localhost:8080/incomingsdetails/product-purchasetime-amount?inputId=30
//    ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProduct(@RequestParam("inputId") Long inputId);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/inputproduct-purchasetime-amount") // localhost:8080/incomingsdetails/inputproduct-purchasetime-amount?inputId=15&inputDate=2022-08-27
//    ResponseEntity<PurchaseTimeStatDTO> getPurchaseTimeAndAmountOfSpecificProductAndDate(@RequestParam("inputId") Long inputId,
//                                                                                         @RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/purchaseamount-fromdate-todate") // localhost:8080/incomingsdetails/purchaseamount-fromdate-todate?fromDate=2022-01-21&toDate=2022-07-21
//    ResponseEntity<List<PurchaseTimeStatDTO>> getPurchaseTimeAndAmountBetweenDates(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fromDate,
//                                                                                   @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/stock-nearlyexpiring") // localhost:8080/incomingsdetails/stock-nearlyexpiring?inputCountDays=30
//    ResponseEntity<List<Object[]>> getCountDaysAndAmountBeforeExpire(@RequestParam("inputCountDays") Long inputCountDays);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/products-nearlyoutofstock") // localhost:8080/incomingsdetails/products-nearlyoutofstock?inputAmount=15000
//    ResponseEntity<List<ProductNearlyOutOfStockStatDTO>> getProductNearlyOutOfStock(@RequestParam("inputAmount") Double inputAmount);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/totalstock-rawmaterial") // localhost:8080/incomingsdetails/totalstock-rawmaterial
//    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterial();
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/totalstock-finishedgood") // localhost:8080/incomingsdetails/totalstock-finishedgood
//    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGood();
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/totalstock-rawmaterial-fromdate-todate") // localhost:8080/incomingsdetails/totalstock-rawmaterial-fromdate-todate?inputDate=2022-07-21
//    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfRawMaterialBeforeDate(@RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/totalstock-finishedgood-fromdate-todate") // localhost:8080/incomingsdetails/totalstock-finishedgood-fromdate-todate?inputDate=2022-07-21
//    ResponseEntity<List<StockAmountOfCategoryStatDTO>> getTotalStockAmountOfFinishedGoodBeforeDate(@RequestParam("inputDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inputDate);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/totalproductcost") // localhost:8080/incomingsdetails/totalproductcost
//    ResponseEntity<List<CostStatsDTO>> getProductsTotalCost();
//
//    @PreAuthorize("hasRole('WAREHOUSE_STAFF')")
//    @PutMapping("/{incomingsId}")
//    ResponseEntity<IncomingsDetailDTO> updateIncomingsDetail(@PathVariable("incomingsId") Long incomingsId,
//                                                             @RequestBody IncomingsDetailUpdateDTO incomingsDetailUpdateDTO);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/incomings-product")
//    ResponseEntity<List<IncomingsProductStatDTO>> getIncomingsAmountOfProduct (@RequestParam("inputProductId") Long inputProductId);
//
//    @PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//    @GetMapping("/product-stock") // localhost:8080/incomingsdetails/product-stock?inputProductId=13
//    ResponseEntity<TotalStockOfProductStatDTO> getTotalStockAmountOfProduct(@RequestParam("inputProductId") Long inputProductId);
//}
