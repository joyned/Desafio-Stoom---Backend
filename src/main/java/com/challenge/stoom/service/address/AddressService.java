package com.challenge.stoom.service.address;

import com.challenge.stoom.core.StoomException;
import com.challenge.stoom.model.StoomAddress;
import com.challenge.stoom.repository.address.AddressRepository;
import com.challenge.stoom.service.google.geocode.proxy.GeocodeProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    public int create(StoomAddress address) {
        setLatatitudeAndLongitude(address);
        return new AddressRepository().create(address);
    }

    private void setLatatitudeAndLongitude(StoomAddress address) {
        try {
            if (StringUtils.isBlank(address.getLongitude()) || StringUtils.isBlank(address.getLatitude())) {
                JSONObject result = GeocodeProxy.getLocation(address);

                address.setLongitude(String.valueOf(result.get("lng")));
                address.setLatitude(String.valueOf(result.get("lat")));
            }
        } catch (JSONException e) {
            throw new StoomException("Falha ao pegar o valor 'lat' e 'lng' do JSON.", e);
        }
    }

    public List<StoomAddress> read() {
        return new AddressRepository().read();
    }

    public void update(StoomAddress address) {
        if (address.getId() == 0) {
            throw new StoomException("Não é possivel fazer update pois o código é 0.");
        }
        setLatatitudeAndLongitude(address);
        new AddressRepository().update(address);
    }

    public void delete(int addressId) {
        if (addressId == 0) {
            throw new StoomException("Não é possivel fazer delete pois o código é 0.");
        }
        new AddressRepository().delete(addressId);
    }


}
