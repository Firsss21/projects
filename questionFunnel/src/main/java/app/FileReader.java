package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class FileReader {
    // read file
    // add to db with mark
    public void readFile(String fileName) {
        try {
            URL res = getClass().getClassLoader().getResource(fileName);

            File file = new File("./target/classes/questionsResources/" + fileName);
            String absolutePath = file.getAbsolutePath();
            File file2 = new File(absolutePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<String> readFileWithBufferedReader(String fileName) {
        String row;
        LinkedList<String> questionsList = new LinkedList<String>();
        try {
            BufferedReader bufReader = new BufferedReader(new java.io.FileReader("./target/classes/questionsResources/" + fileName));
            while ((row = bufReader.readLine()) != null) {
                String[] data = row.split("/");

                for (int i = 0; i < data.length; i++)
                {
                    questionsList.add(data[i]);
                }
            }
            bufReader.close();
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return questionsList;
    }
}
