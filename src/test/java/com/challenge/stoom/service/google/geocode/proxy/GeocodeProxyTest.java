package com.challenge.stoom.service.google.geocode.proxy;

import com.challenge.stoom.model.GeocodeLocation;
import com.challenge.stoom.model.StoomAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeocodeProxyTest {

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
    public void buildUrlWithGoogleAddress() {
        String expected = "https://maps.googleapis.com/maps/api/geocode/json?address=Av.+Brg.+Faria+Lima+3477+Itaim+Bibi+04538133+São+Paulo+São+Paulo+Brasil&key=AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";

        String result = GeocodeProxy.buildUrlParam(buildAddressObject());

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getGoogleLocationAndGetJsonObjectFromResult() throws JSONException {
        String expectedLat = "-23.5864794";
        String expectedLng = "-46.68207839999999";

        JSONObject location = GeocodeProxy.getLocation(buildAddressObject());

        GeocodeLocation geocodeLocation = new GeocodeLocation();
        geocodeLocation.setLatitude(String.valueOf(location.get("lat")));
        geocodeLocation.setLongitude(String.valueOf(location.get("lng")));

        Assertions.assertEquals(expectedLat, geocodeLocation.getLatitude());
        Assertions.assertEquals(expectedLng, geocodeLocation.getLongitude());
    }


}
