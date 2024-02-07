package org.example.javaprojektpro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JavaToolsModel {
    private List<JavaTool> javaTools;
    private List<String> typesList;
    private List<String> titlesList;
    public JavaToolsModel(){}
    public List<String> fetchTypes(){
        List<String> types = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL url = new URL("http://localhost:8080/api/v1/tool/types");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                types = objectMapper.readValue(builder.toString(), new TypeReference<List<String>>() {});
                connection.disconnect();
                return types;
            }else{
                return null;
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<String> fetchTitles(String type){
        List<String> titles = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL url = new URL("http://localhost:8080/api/v1/tool/titles?type="+type);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                titles = objectMapper.readValue(builder.toString(), new TypeReference<List<String>>() {});
                connection.disconnect();
                return titles;
            }else{
                    return null;}
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public String fetchWebURL(String type, String title){
        String resultUrl;
        ObjectMapper objectMapper = new ObjectMapper();
        if(type != null && title != null){
            try{
                URL url = new URL("http://localhost:8080/api/v1/tool?type="+type+"&title="+title);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if(responseCode == 200){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder responseB = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseB.append(line);
                    }
                    String response = responseB.toString();
                    reader.close();
                    JavaTool toolObjects = objectMapper.readValue(response, JavaTool.class);
                    connection.disconnect();
                    resultUrl = toolObjects.getUrl();
                    return resultUrl;
                }else{
                    return null;
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
}
