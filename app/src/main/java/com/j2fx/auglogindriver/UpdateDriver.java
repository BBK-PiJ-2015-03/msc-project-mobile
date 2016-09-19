package com.j2fx.auglogindriver;

import com.firebase.client.Firebase;

/**
 * Created by J2FX on 10/08/16.
 */
public class UpdateDriver {
    public static String status = "Awaiting Job";
    private static Firebase bRef = new Firebase("https://amber-inferno-8546.firebaseio.com/Drivers");

    public static void active(String id){
        Firebase bRefChild = bRef.child(UserActive.d.getId()); //Must repeat this incase a different driver logs into same device.
        bRefChild.child("Active").setValue("True");
    }
    public static void inactive(String id){
        Firebase bRefChild = bRef.child(UserActive.d.getId());
        bRefChild.child("Active").setValue("False");
    }
    public static void statusUpdate(String status){
        Firebase bRefChild = bRef.child(UserActive.d.getId());
        bRefChild.child("status").setValue(status);
        UpdateDriver.status = status;
    }
}
