package com.kyokyu.backend.models;


public class UserInfos {

    private String lastname = null;

    private String firstname = null;

    private String sexe = null;

    private Address address;


    public UserInfos() {
    }

    public UserInfos(String lastname, String firstname, String sexe, Address address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.sexe = sexe;
        this.address = address;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
