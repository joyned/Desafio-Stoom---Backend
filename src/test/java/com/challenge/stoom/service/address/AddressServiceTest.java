package com.challenge.stoom.service.address;

import com.challenge.stoom.model.StoomAddress;
import com.challenge.stoom.util.TransactionalTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    private StoomAddress buildAddressObject() {
        StoomAddress stoomAddress = new StoomAddress();

        stoomAddress.setStreetName("Av. Brg. Faria Lima");
        stoomAddress.setNeighbourhood("Itaim Bibi");
        stoomAddress.setNumber(3477);
        stoomAddress.setZipCode("04538133");
        stoomAddress.setCity("São Paulo");
        stoomAddress.setState("São Paulo");
        stoomAddress.setCountry("Brasil");

        return stoomAddress;
    }

    @Test
    public void createNewAddress() {
        StoomAddress stoomAddress = buildAddressObject();

        TransactionalTest.handle(stoomAddress, t -> {
            this.addressService.create(stoomAddress);
        });
    }

    @Test
    public void createNewAddressAndList() {
        StoomAddress stoomAddress = buildAddressObject();

        TransactionalTest.handle(stoomAddress, t -> {
            this.addressService.create(stoomAddress);

            List<StoomAddress> stoomAddresses = this.addressService.read();

            Assertions.assertEquals(1, stoomAddresses.size());
        });
    }

    @Test
    public void createNewAddressAndListAndUpdateAddressNumber(){
        int expectedNumber = 5000;
        StoomAddress stoomAddress = buildAddressObject();

        TransactionalTest.handle(stoomAddress, t -> {
            this.addressService.create(stoomAddress);

            List<StoomAddress> stoomAddresses = this.addressService.read();

            stoomAddresses.get(0).setNumber(5000);

            this.addressService.update(stoomAddresses.get(0));

            stoomAddresses = this.addressService.read();

            Assertions.assertEquals(expectedNumber, stoomAddresses.get(0).getNumber());
        });
    }

    @Test
    public void createNewAddressAndDeleteIt(){
        StoomAddress stoomAddress = buildAddressObject();

        TransactionalTest.handle(stoomAddress, t -> {
            this.addressService.create(stoomAddress);

            List<StoomAddress> stoomAddresses = this.addressService.read();

            Assertions.assertEquals(1, stoomAddresses.size());

            this.addressService.delete(stoomAddresses.get(0).getId());

            stoomAddresses = this.addressService.read();

            Assertions.assertEquals(0, stoomAddresses.size());
        });
    }

}
