package com.j2fx.auglogindriver;

import com.firebase.client.Firebase;

/**
 * Created by J2FX on 12/09/16.
 */
public class JobUpdater {
    private static Firebase dispatched = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Dispatched");
    private static Firebase awaitingDispatch = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Awaiting_Dispatch");
    private static Firebase inProgress = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/In_Progress");
    private static Firebase completed = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Completed");

    public void jobAccepted(String bookingNumber){
        Firebase progressChild = inProgress.child(bookingNumber);
        dispatched.child(UserActive.d.getId()).removeValue();
        updateValues(progressChild);
    }

    public void jobDeclined(String bookingNumber){
        Firebase progressChild = awaitingDispatch.child(bookingNumber);
        dispatched.child(UserActive.d.getId()).removeValue();
        updateValues(progressChild);
    }

    public void jobCompleted(String bookingNumber){
        Firebase progressChild = completed.child(bookingNumber);
        inProgress.child(UserActive.d.getId()).removeValue();
        updateValues(progressChild);
    }

    public void jobNoshow(String bookingNumber){
//        Firebase progressChild = awaitingDispatch.child(bookingNumber);
        inProgress.child(UserActive.d.getId()).removeValue();
//        updateValues(progressChild);
//        removing job completly if driver clicks No Show
    }

    public void updateValues(Firebase progressChild){
        progressChild.child("pickup").setValue(Booking.getInstance().getPickup());//
        progressChild.child("dropoff").setValue(Booking.getInstance().getDrop());//
        progressChild.child("name").setValue(Booking.getInstance().getClient());//
        progressChild.child("time").setValue(Booking.getInstance().getDue());
        progressChild.child("comment").setValue(Booking.getInstance().getNotes());//
        progressChild.child("tel").setValue(Booking.getInstance().getTel());//
        progressChild.child("email").setValue(Booking.getInstance().getEmail());//
        progressChild.child("booking_number").setValue(Booking.getInstance().getBookingNumber());//
        progressChild.child("account").setValue(Booking.getInstance().getAccount());
        progressChild.child("vehicle_type").setValue(Booking.getInstance().getVehicleType());
        progressChild.child("no_passengers").setValue(Booking.getInstance().getNoPassengers());
        progressChild.child("date").setValue(Booking.getInstance().getDate());
    }
}
