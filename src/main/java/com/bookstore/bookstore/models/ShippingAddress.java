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
    private String userShippingName;
    private String userShippingStreet1;
    private String userShippingStreet2;
    private String userShippingCity;

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

    public String getUserShippingName() {
        return userShippingName;
    }

    public void setUserShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
    }

    public String getUserShippingStreet1() {
        return userShippingStreet1;
    }

    public void setUserShippingStreet1(String userShippingStreet1) {
        this.userShippingStreet1 = userShippingStreet1;
    }

    public String getUserShippingStreet2() {
        return userShippingStreet2;
    }

    public void setUserShippingStreet2(String userShippingStreet2) {
        this.userShippingStreet2 = userShippingStreet2;
    }

    public String getUserShippingCity() {
        return userShippingCity;
    }

    public void setUserShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
    }

    public String getUserShippingState() {
        return userShippingState;
    }

    public void setUserShippingState(String userShippingState) {
        this.userShippingState = userShippingState;
    }

    public String getUserShippingCountry() {
        return userShippingCountry;
    }

    public void setUserShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
    }

    public String getUserShippingZipcode() {
        return userShippingZipcode;
    }

    public void setUserShippingZipcode(String userShippingZipcode) {
        this.userShippingZipcode = userShippingZipcode;
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
    private String userShippingState;
    private String userShippingCountry;
    private String userShippingZipcode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
