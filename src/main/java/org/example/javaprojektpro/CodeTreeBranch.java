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

public class CodeTreeBranch {
    private List<String> languages;
    private List<String> laboratory;
    private List<String> exercises;
    private List<String> tites;
    public CodeTreeBranch() {
        languages = fetchLanguages();
    }

    private List<String> fetchLanguages(){
        List<String> languages = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL url = new URL("http://localhost:8080/api/v1/code/language");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            languages = objectMapper.readValue(builder.toString(), new TypeReference<List<String>>() {});
            connection.disconnect();
            return languages;
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<String> fetchLaboratories(String language){
        List<String> laboratories = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL url = new URL("http://localhost:8080/api/v1/code/laboratory?language="+language);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            laboratories = objectMapper.readValue(builder.toString(), new TypeReference<List<String>>() {});
            connection.disconnect();
            return laboratories;
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }

    }
    public List<String> fetchExercises(String language, String laboratory){
        List<String> exercises = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL url = new URL("http://localhost:8080/api/v1/code/exercise?language="+language+"&laboratory="+laboratory);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            exercises = objectMapper.readValue(builder.toString(), new TypeReference<List<String>>() {});
            connection.disconnect();
            return exercises;
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<String> fetchTitles(String language, String laboratory, String exercise){
        List<String> tiles = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL url = new URL("http://localhost:8080/api/v1/code/title?language="+language+"&laboratory="+laboratory+"&exercise="+exercise);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            tiles = objectMapper.readValue(builder.toString(), new TypeReference<List<String>>() {});
            connection.disconnect();
            return tiles;
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public List<String> getLaboratory() {
        return laboratory;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
