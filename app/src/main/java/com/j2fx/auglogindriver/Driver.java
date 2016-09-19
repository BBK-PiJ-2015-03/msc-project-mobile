package com.j2fx.auglogindriver;

/**
 * Created by J2FX on 10/08/16.
 * driver class
 */
public final class Driver {
    public String address;
    public String id;
    public String name;
    public String tel;

    public Driver(){
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
