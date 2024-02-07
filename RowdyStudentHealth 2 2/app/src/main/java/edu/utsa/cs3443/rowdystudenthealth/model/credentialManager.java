package edu.utsa.cs3443.rowdystudenthealth.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class credentialManager {
        

    private ArrayList<userCredential> userCredentials = new ArrayList<>();
    userCredential adminCredential;
    
    public credentialManager(Context c) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(c.getAssets().open("users.txt")))) {

            String line;
            String[] data;
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                adminCredential = new userCredential(data[0],data[1]);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        createCredential(adminCredential);
    }
    public ArrayList<userCredential> getUserCredentials() {
        return this.userCredentials;
    }
    public static boolean isValid(String username, String password, credentialManager credentialBase) {
        for ( userCredential credential : credentialBase.getUserCredentials()){
            if( credential.getUsername().equals(username) && credential.getPassword().equals(password) ){
                return true;
            }
        }
        return false;
    }
    public void createCredential(userCredential newCredential){
        this.getUserCredentials().add(newCredential);
    }
}
