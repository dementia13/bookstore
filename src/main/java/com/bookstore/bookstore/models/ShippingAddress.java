package com.bookstore.bookstore.models;

import javax.persistence.*;

@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ShippingAddressName;
    private String ShippingAddressStreet1;
    private String ShippingAddressStreet2;
    private String ShippingAddressCity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddressName() {
        return ShippingAddressName;
    }

    public void setShippingAddressName(String ShippingAddressName) {
        this.ShippingAddressName = ShippingAddressName;
    }

    public String getShippingAddressStreet1() {
        return ShippingAddressStreet1;
    }

    public void setShippingAddressStreet1(String ShippingAddressStreet1) {
        this.ShippingAddressStreet1 = ShippingAddressStreet1;
    }

    public String getShippingAddressStreet2() {
        return ShippingAddressStreet2;
    }

    public void setShippingAddressStreet2(String ShippingAddressStreet2) {
        this.ShippingAddressStreet2 = ShippingAddressStreet2;
    }

    public String getShippingAddressCity() {
        return ShippingAddressCity;
    }

    public void setShippingAddressCity(String ShippingAddressCity) {
        this.ShippingAddressCity = ShippingAddressCity;
    }

    public String getShippingAddressState() {
        return ShippingAddressState;
    }

    public void setShippingAddressState(String ShippingAddressState) {
        this.ShippingAddressState = ShippingAddressState;
    }

    public String getShippingAddressCountry() {
        return ShippingAddressCountry;
    }

    public void setShippingAddressCountry(String ShippingAddressCountry) {
        this.ShippingAddressCountry = ShippingAddressCountry;
    }

    public String getShippingAddressZipcode() {
        return ShippingAddressZipcode;
    }

    public void setShippingAddressZipcode(String ShippingAddressZipcode) {
        this.ShippingAddressZipcode = ShippingAddressZipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String ShippingAddressState;
    private String ShippingAddressCountry;
    private String ShippingAddressZipcode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
