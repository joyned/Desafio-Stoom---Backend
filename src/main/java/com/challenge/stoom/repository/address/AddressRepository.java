package com.challenge.stoom.repository.address;

import com.challenge.stoom.model.StoomAddress;
import com.challenge.stoom.repository.dao.RepositoryCommand;
import com.challenge.stoom.repository.dao.RepositoryQuery;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository {

    public int create(StoomAddress address) {
        StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO Address (Street_Name, Address_Number, Complement, Neighbourhood, City, [State], Country, Zip_Code, Latitude, Longitude) ");
        sql.append("     VALUES (?,?,?,?,?,?,?,?,?,?)");

        RepositoryCommand command = new RepositoryCommand(sql);
        command.setParameter(address.getStreetName());
        command.setParameter(address.getNumber());
        command.setNullableString(address.getComplement());
        command.setParameter(address.getNeighbourhood());
        command.setParameter(address.getCity());
        command.setParameter(address.getState());
        command.setParameter(address.getCountry());
        command.setParameter(address.getZipCode());
        command.setNullableString(address.getLatitude());
        command.setNullableString(address.getLongitude());

        return command.execute();
    }

    public List<StoomAddress> read() {
        StringBuilder sql = new StringBuilder();
        List<StoomAddress> stoomAddresses = new ArrayList<>();

        sql.append(" SELECT Id");
        sql.append("      , Street_Name");
        sql.append("      , Address_Number");
        sql.append("      , Complement");
        sql.append("      , Neighbourhood");
        sql.append("      , City");
        sql.append("      , State");
        sql.append("      , Country");
        sql.append("      , Zip_Code");
        sql.append("      , Latitude");
        sql.append("      , Longitude");
        sql.append(" FROM Address");

        RepositoryQuery query = new RepositoryQuery(sql);
        query.execute();

        while (query.next()) {
            StoomAddress stoomAddress = new StoomAddress();

            stoomAddress.setId(query.getInt("Id"));
            stoomAddress.setStreetName(query.getString("Street_Name"));
            stoomAddress.setNumber(query.getInt("Address_Number"));
            stoomAddress.setComplement(query.getString("Complement"));
            stoomAddress.setNeighbourhood(query.getString("Neighbourhood"));
            stoomAddress.setCity(query.getString("City"));
            stoomAddress.setState(query.getString("State"));
            stoomAddress.setCountry(query.getString("Country"));
            stoomAddress.setZipCode(query.getString("Zip_Code"));
            stoomAddress.setLatitude(query.getString("Latitude"));
            stoomAddress.setLongitude(query.getString("Longitude"));

            stoomAddresses.add(stoomAddress);
        }

        return stoomAddresses;
    }

    public void update(StoomAddress address) {
        StringBuilder sql = new StringBuilder();

        sql.append(" UPDATE Address");
        sql.append("    SET Street_Name = ?,");
        sql.append("        Address_Number = ?,");
        sql.append("        Complement = ?,");
        sql.append("        Neighbourhood = ?,");
        sql.append("        City = ?,");
        sql.append("        [State] = ?,");
        sql.append("        Country = ?,");
        sql.append("        Zip_Code = ?,");
        sql.append("        Latitude = ?,");
        sql.append("        Longitude = ?");
        sql.append(" WHERE Id = ?");

        RepositoryCommand command = new RepositoryCommand(sql);
        command.setParameter(address.getStreetName());
        command.setParameter(address.getNumber());
        command.setNullableString(address.getComplement());
        command.setParameter(address.getNeighbourhood());
        command.setParameter(address.getCity());
        command.setParameter(address.getState());
        command.setParameter(address.getCountry());
        command.setParameter(address.getZipCode());
        command.setNullableString(address.getLatitude());
        command.setNullableString(address.getLongitude());
        command.setParameter(address.getId());

        command.execute();
    }

    public void delete(int addressId) {
        StringBuilder sql = new StringBuilder();

        sql.append(" DELETE FROM Address");
        sql.append("    WHERE Id = ?");

        RepositoryCommand command = new RepositoryCommand(sql);
        command.setParameter(addressId);

        command.execute();
    }


}
