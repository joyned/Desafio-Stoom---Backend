package com.challenge.stoom.util;

import com.challenge.stoom.core.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class ResponseBuilder {

    public static ResponseEntity<ServiceResponse> responseOk(Serializable payload) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setPayload(payload);
        serviceResponse.setDate();
        return ResponseEntity.ok(serviceResponse);
    }

    public static ResponseEntity<ServiceResponse> responseOk() {
        return responseOk("");
    }

    public static ResponseEntity<ServiceResponse> responseFail(String friendlyMessage, HttpStatus status, Exception e) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setDate();

        if (e != null) {
            serviceResponse.setExceptionMessage(e.toString());
        }

        serviceResponse.setFriedlyMessage(friendlyMessage);

        return ResponseEntity.status(status).body(serviceResponse);
    }

    public static ResponseEntity<ServiceResponse> responseFail(HttpStatus status, Exception e) {
        return responseFail(null, status, e);
    }

    public static ResponseEntity<ServiceResponse> responseFail(HttpStatus status) {
        return responseFail(null, status, null);
    }

    public static ResponseEntity<ServiceResponse> responseFail(String friendlyMessage, HttpStatus status) {
        return responseFail(friendlyMessage, status, null);
    }


}
