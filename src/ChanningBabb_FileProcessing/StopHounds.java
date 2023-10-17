package ChanningBabb_FileProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StopHounds {

    public static void runStopHounds() throws FileNotFoundException {
        String removed = "";
        ArrayList<String> stopWords = new ArrayList();
        String[] files = {"Melville/MobyDick.txt"};

//        , "Melville/Bartleby.txt",
//                "Melville/IAndMyChimney.txt", "Melville/Omoo.txt", "Melville/Pierre.txt",
//                "Melville/Redburn.txt", "Melville/TheConfidenceMan.txt", "Melville/ThePiazzaTales.txt",
//                "Melville/Typee.txt", "Melville/WhiteJacket.txt"

        //parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File("src/ChanningBabb_FileProcessing/stopwords.txt"));
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            stopWords.add(sc.next());
        }
        sc.close();


        ArrayList<readers.WordCounter> counters = new ArrayList();


        for (int i = 0; i < files.length; i++) {
            String line;
            Scanner fileScan;
            fileScan = new Scanner(new File("src/ChanningBabb_FileProcessing/" + files[i]));

            while (fileScan.hasNext()) {
                line = fileScan.nextLine();


                    for (int j = 0; j < stopWords.size(); j++) {
                        String replaced = line.replaceAll(stopWords.get(j), " ");
                        removed += replaced;
                    }
                    System.out.println(removed + "\n");
            }
            DataFileWriter dfw = new DataFileWriter();
            dfw.writeData(removed, files[i] + "stopped");
        }


    }
}

