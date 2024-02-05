package org.example.javaprojektpro;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class SlideViewerSlideModel {
    private final List<Image> slides;
    public static  String subjectName = "";
    public static  String lectureName = "";
    public static Boolean logged = false;

    public SlideViewerSlideModel(String buttonName){
        lectureName = prepareDirectoryName(buttonName);
        ObjectMapper objectMapper = new ObjectMapper();
        slides = new ArrayList<>();
        try{
            URL url = new URL("http://localhost:8080/api/v1/images?subject="+subjectName+"&lecture="+lectureName);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            int responseCode = conection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            Image[] imageArray = objectMapper.readValue(response.toString(), Image[].class);
            for(Image image : imageArray){
                slides.add(image);
            }
            conection.disconnect();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        /*
        String directoryName = prepareDirectoryName(buttonName);
        String desiredExtension = "jpg";

        File folder = new File("src/main/resources/org/example/javaprojektpro/slides/"+directoryName);
        FilenameFilter filter = (dir, name) -> name.toLowerCase().endsWith("." + desiredExtension.toLowerCase());
        this.slides = Arrays.stream(folder.listFiles(filter)).sorted(Comparator.comparing(File::getName)).toList();

         */
    }
    private String prepareDirectoryName(String buttonName){
        return buttonName.replaceAll("\\s", "_").toLowerCase().replaceAll("ą","a").replaceAll("ć", "c").replaceAll("ę", "e").replaceAll("ł", "l").replaceAll("ń", "n").replaceAll("ó", "o").replaceAll("ś", "s").replaceAll("ż", "z").replaceAll("ź", "z");
    }

    public List<Image> getSlides() {
        return slides;
    }

}
