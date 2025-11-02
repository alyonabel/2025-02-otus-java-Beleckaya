package ru.otus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("address_data_set")
public class AddressDataSet {

    @Id
    private Long id;

    @Column("street")
    private String street;

    @Column("client_id")
    private Long clientId;

     public AddressDataSet(Long id, String street,Long clientId) {
        this.id = id;
        this.street = street;
        this.clientId = clientId;
    }
    public AddressDataSet(String street) {
        this(null, street,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                '}';
    }

}
