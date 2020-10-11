package com.challenge.stoom.controller;

import com.challenge.stoom.core.ServiceResponse;
import com.challenge.stoom.model.StoomAddress;
import com.challenge.stoom.service.address.AddressService;
import com.challenge.stoom.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> createAddress(@RequestBody @Valid StoomAddress address) {
        try {
            return ResponseBuilder.responseOk(this.addressService.create(address));
        } catch (Exception e) {
            return ResponseBuilder.responseFail(HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("/read")
    public ResponseEntity<ServiceResponse> readAddresses() {
        try {
            return ResponseBuilder.responseOk((Serializable) this.addressService.read());
        } catch (Exception e) {
            return ResponseBuilder.responseFail(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ServiceResponse> updateAddress(@RequestBody @Valid StoomAddress address) {
        try {
            this.addressService.update(address);
            return ResponseBuilder.responseOk();
        } catch (Exception e) {
            return ResponseBuilder.responseFail(HttpStatus.BAD_REQUEST, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> deleteAddress(@PathVariable int id) {
        try {
            this.addressService.delete(id);
            return ResponseBuilder.responseOk();
        } catch (Exception e) {
            return ResponseBuilder.responseFail(HttpStatus.BAD_REQUEST, e);
        }
    }

}
