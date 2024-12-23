package utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import modules.*;

public class CSVHandler {

    private CSVHandler() {
        // Private constructor to prevent instantiation
    }

    public static void saveToCSV(Item obj, File file) {
        try {
            if (!file.exists()) {
                boolean wasFileCreated = file.createNewFile();
                if (!wasFileCreated) {
                    System.out.println("Failed to create the file at " + file.getAbsolutePath());
                    return;
                }
            }

            try (FileWriter fileWriter = new FileWriter(file, true); // Set true for append mode
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {

                printWriter.println(obj.getName() + "," + obj.getId() + "," + obj.getState().getName() + ","
                        + obj.getValue() + "," + obj.getPurchaseDate() + "," + String.join("|", obj.getKeyWord()));
            }

        } catch (IOException e) {
            System.err.println("An error occurred: ");
            e.printStackTrace();
        }
    }

    // Main test function
    public static void main(String[] args) {
        // Create an Item object for testing
        Item testItem = new Item("Item1", new String[]{"key1", "key2"}, "", State.PERFECT, 45, LocalDate.now());

        // Create a File object
        File testFile = new File("test.csv");

        // Call the saveToCSV method
        saveToCSV(testItem, testFile);
    }
}