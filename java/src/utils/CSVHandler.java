package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import modules.*;

public class CSVHandler {

    private CSVHandler() {
        // Private constructor to prevent instantiation
    }

    public static void saveToCSV(Item obj, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, true); //Set true for append mode
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(obj.getName() + "," + obj.getId() + "," + obj.getState().getName() + "," +
                    obj.getValue() + "," + obj.getPurchaseDate() + "," + String.join("|", obj.getKeyWord()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}