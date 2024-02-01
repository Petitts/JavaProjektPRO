package org.example.javaprojektpro;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class SlideViewerSlideModel {
    private List<File> slides;
    public SlideViewerSlideModel(String buttonName){
        String directoryName = prepareDirectoryName(buttonName);
        String desiredExtension = "jpg";

        File folder = new File("src/main/resources/org/example/javaprojektpro/slides/"+directoryName);
        FilenameFilter filter = (dir, name) -> name.toLowerCase().endsWith("." + desiredExtension.toLowerCase());
        this.slides = Arrays.stream(folder.listFiles(filter)).sorted(Comparator.comparing(File::getName)).toList();
    }
    private String prepareDirectoryName(String buttonName){
        return buttonName.replaceAll("\\s", "_").toLowerCase().replaceAll("ą","a").replaceAll("ć", "c").replaceAll("ę", "e").replaceAll("ł", "l").replaceAll("ń", "n").replaceAll("ó", "o").replaceAll("ś", "s").replaceAll("ż", "z").replaceAll("ź", "z");
    }

    public List<File> getSlides() {
        return slides;
    }
}
