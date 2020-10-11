package com.challenge.stoom.service.google.geocode.proxy;

import com.challenge.stoom.core.StoomException;
import com.challenge.stoom.model.StoomAddress;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class GeocodeProxy {

    private static final String API_KEY = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";
    private static String geoCodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    public static JSONObject getLocation(StoomAddress address) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return getLocationObjectFromJson(restTemplate.getForObject(buildUrlParam(address), String.class));
        } catch (Exception e) {
            throw new StoomException("Falha ao fazer parse do JSON.", e);
        }
    }

    static String buildUrlParam(StoomAddress address) {
        String addressString = address.getStreetName() + " " + address.getNumber() + " " + address.getNeighbourhood() + " " + address.getZipCode() + " " + address.getCity() + " " + address.getState() + " "
                + address.getCountry();
        addressString = addressString.replaceAll("\\s+", "+");
        return geoCodeUrl + addressString + "&key=" + API_KEY;
    }

    private static JSONObject getLocationObjectFromJson(String result) throws JSONException {
        return new JSONObject(result).getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
    }
}
