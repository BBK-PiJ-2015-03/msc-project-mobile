package com.j2fx.auglogindriver;

import java.text.DecimalFormat;

/**
 * Created by J2FX on 12/08/16.
 */
public class Booking {
    private static Booking booking = null;
    private String client;
    private String pickup;
    private String drop;
    private String due;
    private String notes;
    private String price;
    private String bookingNumber;
    private String account;
    private String email;
    private String date;
    private String noPassengers;
    private String tel;
    private String vehicleType;


    public static Booking getInstance(){
        if(booking == null){
            booking = new Booking();
        }
        return booking;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoPassengers() {
        return noPassengers;
    }

    public void setNoPassengers(String noPassengers) {
        this.noPassengers = noPassengers;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.price = ("£"+df.format(price));
    }

    public void clear(){
        booking = null;
    }
}
