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

public class CodeViewerModel {
    private List<Code> codes;

    public CodeViewerModel(String title, String language, String laboratory, String exercise){
        codes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            URL url = null;
            if(language != null && laboratory != null && exercise != null) {
                 url = new URL("http://localhost:8080/api/v1/code?title=" + title + "&language="+language+"&laboratory="+laboratory+"&exercise="+exercise);
            } else if (language != null && laboratory != null && exercise == null) {
                url = new URL("http://localhost:8080/api/v1/code?title=" + title + "&language="+language+"&laboratory="+laboratory);
            } else if (language != null && laboratory == null && exercise == null) {
                url = new URL("http://localhost:8080/api/v1/code?title=" + title + "&language="+language);
            } else if (language == null && laboratory == null && exercise == null) {
                url = new URL("http://localhost:8080/api/v1/code?title=" + title);
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            Code[] codeArray = objectMapper.readValue(response.toString(), Code[].class);
            for (Code code : codeArray){
                codes.add(code);
            }
            connection.disconnect();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Code getCode() {
        if(codes.size()>0) {
            return codes.getFirst();
        }
        return null;
    }
}
