package bookrecommender.elaborazione.entities.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;

/**
 * This class contains utils for the CSVReader, made in order not to repeat code
 *
 * @author Leonardo Basso
 */
public class CSVUtils {
    /**
     * This method, given an input, returns the input if it exists in a given header, else returns null
     * @param toFind The string to find
     * @param header The header where the string should be searched
     * @param path the location of the .csv file
     * @return toFind if it matches in the file, else returns null
     */
    public static String find(String toFind, String header, String path) {
        try {
            FileReader reader = new FileReader(path);
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, csvFormat);
            for (CSVRecord record : parser) {
                if (record.get(header).equals(toFind)) return record.get(header);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
