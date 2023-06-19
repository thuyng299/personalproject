package com.nonit.personalproject.exception;

import org.springframework.http.HttpStatus;
public class WarehouseException {
    private static final String REGION_NOT_FOUND_MSG_KEY = "RegionNotExisted";
    private static final String REGION_NOT_FOUND_MSG = "Region Not Found";
    private static final String COUNTRY_NOT_FOUND_MSG_KEY = "CountryNotExisted";
    private static final String COUNTRY_NOT_FOUND_MSG = "Country Not Found";
    private static final String SUPPLIER_NOT_FOUND_MSG_KEY = "SupplierNotExisted";
    private static final String SUPPLIER_NOT_FOUND_MSG = "Supplier Not Found";
    private static final String CUSTOMER_NOT_FOUND_MSG_KEY = "CustomerNotExisted";
    private static final String CUSTOMER_NOT_FOUND_MSG = "Customer Not Found";
    private static final String AREA_NOT_FOUND_MSG_KEY = "WarehouseAreaNotExisted";
    private static final String AREA_NOT_FOUND_MSG = "Warehouse Area Not Found";
    private static final String PRODUCT_NOT_FOUND_MSG_KEY = "ProductNotExisted";
    private static final String PRODUCT_NOT_FOUND_MSG = "Product Not Found";
    private static final String EMPLOYEE_NOT_FOUND_MSG_KEY = "EmployeeNotExisted";
    private static final String EMPLOYEE_NOT_FOUND_MSG = "Employee Not Found";
    private static final String GRN_NOT_FOUND_MSG_KEY = "GoodsReceivedNoteNotExisted";
    private static final String GRN_NOT_FOUND_MSG = "Goods Received Note Not Found";
    private static final String INCOMINGSDETAIL_NOT_FOUND_MSG_KEY = "IncomingsDetailNotExisted";
    private static final String INCOMINGSDETAIL_NOT_FOUND_MSG = "Incomings Detail Not Found";
    private static final String GDN_NOT_FOUND_MSG_KEY = "GoodsDeliveryNoteNotExisted";
    private static final String GDN_NOT_FOUND_MSG = "Goods Delivery Note Not Found";
    private static final String OUTCOMINGSDETAIL_NOT_FOUND_MSG_KEY = "OutcomingsDetailNotExisted";
    private static final String OUTCOMINGSDETAIL_NOT_FOUND_MSG = "Outcomings Detail Not Found";
    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public static ResponseException RegionNotFound(){
        return notFound(REGION_NOT_FOUND_MSG_KEY, REGION_NOT_FOUND_MSG);
    }
    public static ResponseException CountryNotFound(){
        return notFound(COUNTRY_NOT_FOUND_MSG_KEY, COUNTRY_NOT_FOUND_MSG);
    }
    public static ResponseException SupplierNotFound(){
        return notFound(SUPPLIER_NOT_FOUND_MSG_KEY, SUPPLIER_NOT_FOUND_MSG);
    }
    public static ResponseException CustomerNotFound(){
        return notFound(CUSTOMER_NOT_FOUND_MSG_KEY, CUSTOMER_NOT_FOUND_MSG);
    }
    public static ResponseException WarehouseAreaNotFound(){
        return notFound(AREA_NOT_FOUND_MSG_KEY, AREA_NOT_FOUND_MSG);
    }
    public static ResponseException ProductNotFound(){
        return notFound(PRODUCT_NOT_FOUND_MSG_KEY, PRODUCT_NOT_FOUND_MSG);
    }
    public static ResponseException EmployeeNotFound(){
        return notFound(EMPLOYEE_NOT_FOUND_MSG_KEY, EMPLOYEE_NOT_FOUND_MSG);
    }
    public static ResponseException GRNNotFound(){
        return notFound(GRN_NOT_FOUND_MSG_KEY, GRN_NOT_FOUND_MSG);
    }
    public static ResponseException IncomingDetailNotFound(){
        return notFound(INCOMINGSDETAIL_NOT_FOUND_MSG_KEY, INCOMINGSDETAIL_NOT_FOUND_MSG);
    }
    public static ResponseException GDNNotFound(){
        return notFound(GDN_NOT_FOUND_MSG_KEY, GDN_NOT_FOUND_MSG);
    }
    public static ResponseException OutgoingDetailNotFound(){
        return notFound(OUTCOMINGSDETAIL_NOT_FOUND_MSG_KEY, OUTCOMINGSDETAIL_NOT_FOUND_MSG);
    }

}
