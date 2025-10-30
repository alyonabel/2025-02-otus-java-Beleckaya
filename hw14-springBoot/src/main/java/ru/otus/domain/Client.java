package ru.otus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("clients")
public class Client  {

    @Id
    private Long id;

    private String name;
    private String login;
    private String password;

    @MappedCollection(idColumn = "client_id")
    private Set<AddressDataSet> addressDataSets;

    @MappedCollection(idColumn = "client_id")
    private Set<PhoneDataSet> phoneDataSets;

     public Client(Long id, String name, String login, String password, Set<AddressDataSet> addressDataSets, Set<PhoneDataSet> phoneDataSets) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.addressDataSets = addressDataSets;
        this.phoneDataSets = phoneDataSets;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<PhoneDataSet> getPhoneDataSets() {
        return phoneDataSets;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneDataSets(Set<PhoneDataSet> phoneDataSets) {
        this.phoneDataSets = phoneDataSets;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public Set<AddressDataSet> getAddressDataSet() {
        return addressDataSets;
    }

    public void setAddressDataSet(Set<AddressDataSet> addressDataSet) {
        this.addressDataSets = addressDataSet;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
