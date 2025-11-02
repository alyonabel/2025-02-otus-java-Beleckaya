package ru.otus.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("phone_data_set")
public class PhoneDataSet {

    @Id
    private Long id;

    @Column("number")
    private String number;

    @Column("client_id")
    private Long clientId;


    public PhoneDataSet(String number) {
        this.number = number;
    }

    public PhoneDataSet(Long id, String number,Long clientId) {
        this.id = id;
        this.number = number;
        this.clientId = clientId;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';
    }

}
