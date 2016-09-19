package com.j2fx.auglogindriver;

import android.content.Context;
import android.content.Intent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.IOException;
import java.net.URL;

/**
 * Created by J2FX on 12/08/16.
 */
public class JobListener {
    private static Firebase dRef = new Firebase("https://amber-inferno-8546.firebaseio.com/Bookings/Dispatched");

    public static void start(UserAreaActivity userAreaActivity){
        final Context uaa = userAreaActivity.getApplicationContext();
        dRef.addChildEventListener(new ChildEventListener()  {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    URL url = new URL("https://amber-inferno-8546.firebaseio.com/Bookings/Dispatched.json");
                    JsonNode bookings = objectMapper.readValue(url, JsonNode.class);
                    for (JsonNode booking : bookings) {
                        System.out.println(dataSnapshot.getKey());
                        if (dataSnapshot.getKey().equals(UserActive.d.getId())) {
                            Booking b = Booking.getInstance();
                            JsonNode comment = booking.get("comment");
                            if(comment.asText() != null) {
                                b.setNotes(comment.asText());
                            }
                            JsonNode dropoff = booking.get("dropoff");
                            if(dropoff.asText() != null) {
                                b.setDrop(dropoff.asText());
                            }
                            JsonNode name = booking.get("name");
                            if(name.asText() != null) {
                                b.setClient(name.asText());
                            }
                            JsonNode pickup = booking.get("pickup");
                            if(pickup.asText() != null) {
                                b.setPickup(pickup.asText());
                            }
                            JsonNode time = booking.get("time");
                            if(time.asText() != null) {
                                b.setDue(time.asText());
                            }
                            JsonNode price = booking.get("price");
                            if(price.asText() != null) {
                                b.setPrice(price.asDouble());
                            }
                            JsonNode bookingNumber = booking.get("booking_number");
                            if(bookingNumber.asText() != null) {
                                b.setBookingNumber(bookingNumber.asText());
                            }
                            JsonNode account = booking.get("account");
                            if(account.asText() != null) {
                                b.setAccount(account.asText());
                            }
                            JsonNode date = booking.get("date");
                            if(date.asText() != null) {
                                b.setAccount(date.asText());
                            }
                            JsonNode email = booking.get("email");
                            if(email.asText() != null) {
                                b.setEmail(email.asText());
                            }
                            JsonNode noPassengers = booking.get("no_passengers");
                            if(noPassengers.asText() != null) {
                                b.setNoPassengers(noPassengers.asText());
                            }
                            JsonNode tel = booking.get("tel");
                            if(tel.asText() != null) {
                                b.setTel(tel.asText());
                            }
                            JsonNode vehicleType = booking.get("vehicle_type");
                            if(vehicleType.asText() != null) {
                                b.setVehicleType(vehicleType.asText());
                            }

                            if(UpdateDriver.status.equals("Awaiting Job")) { //prevents this happening twice
                                UpdateDriver.statusUpdate("Job Offered");
                                Intent jobOfferIntent = new Intent(uaa.getApplicationContext(), UserJobOffer.class);
                                jobOfferIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                uaa.startActivity(jobOfferIntent);
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}