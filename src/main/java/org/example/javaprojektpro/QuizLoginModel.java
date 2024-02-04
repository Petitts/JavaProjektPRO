package org.example.javaprojektpro;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class QuizLoginModel {
    private String username;
    private  String password;
    public QuizLoginModel(String username, String password){
        this.username = username;
        try{
            this.password = createHash(password);
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
    }
    private String createHash(String toHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] plainBytes = toHash.getBytes();
        byte[] hashedBytes = md.digest(plainBytes);
        StringBuilder hashStringB = new StringBuilder();
        for(byte b : hashedBytes){
            hashStringB.append(String.format("%02x", b));
        }
        return hashStringB.toString();
    }
    public boolean isPasswordCorrect(){
        try{
            URL url = new URL("http://localhost:8080/api/v1/auth");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String requestLoad = "username=" + username + "&password=" + password;
            try(DataOutputStream wr = new DataOutputStream((connection.getOutputStream()))){
                wr.writeBytes(requestLoad);
                wr.flush();
            }
            int responseCode = connection.getResponseCode();
            try(BufferedReader in = new BufferedReader(new InputStreamReader(responseCode == HttpURLConnection.HTTP_OK ? connection.getInputStream() : connection.getErrorStream()))){
                String inputLine;
                StringBuilder responseBuild = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    responseBuild.append(inputLine);
                }
                String response = responseBuild.toString();
                if(response.equals("true")){
                    return true;
                }
            }

            connection.disconnect();

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
