package com.j2fx.auglogindriver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.net.URL;

/**
 * Created by J2FX on 10/08/16.
 */
public class UserActive {
    public static Driver d = new Driver();
    private Boolean authenticated = false;


    public void authenticateUser(final String username, final String password) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {

                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    URL url = new URL("https://amber-inferno-8546.firebaseio.com/Drivers/"+username+".json");
                    JsonNode driver = objectMapper.readValue(url, JsonNode.class);
                    if (driver != null) {
                        System.out.println("Check "+driver.get("id").asText()+ " and "+driver.get("pin").asText());
                        if (driver.get("id").asText().equals(username) && driver.get("pin").asText().equals(password)) {
                            authenticated = true;
                            System.out.println("USER IS LEGIT!");
                            d.setAddress(driver.get("address").asText());
                            d.setId(driver.get("id").asText());
                            d.setName(driver.get("name").asText());
                            d.setTel(driver.get("tel").asText());
                            }
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//        });
//        thread.start();}

    public boolean getAuthenticated(){
        return authenticated;
    }

    public static void clearDriver(){
        d = new Driver();
    }



}
