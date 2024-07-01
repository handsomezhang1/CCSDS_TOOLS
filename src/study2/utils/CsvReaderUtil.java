package study2.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvReaderUtil {

    /**
     * Reads a CSV file and returns a list of records.
     *
     * @param filePath the path to the CSV file
     * @return a List of String arrays, each array representing a row in the CSV file
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
     */
    public static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the CSV file has a header row and using ',' as delimiter
                String[] values = line.split("	");
                records.add(values);
            }
        }
        return records;
    }
    
    // Test the readCsv method
    /***
     * ***/
    public static void main(String[] args) {
        String csvFilePath = "config/tmcsv.csv"; // Replace with the actual path to the CSV file
        try {
            List<String[]> csvData = readCsv(csvFilePath);
            for (String[] row : csvData) {
                for (String column : row) {
                    System.out.print(column + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}