package illumio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LogCsvReader {

	
	
	 // Load the tag lookup table
    static Map<String, String> loadLookupTable(String lookupFile) throws IOException {
        Map<String, String> tagMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(lookupFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("dstport")) continue; // Skip header
                String[] parts = line.split(",");
                String key = parts[0] + "," + parts[1].toLowerCase(); // dstport,protocol as key
                tagMap.put(key, parts[2]);
            }
        }
        return tagMap;
    }
    
 // Load the tag lookup tables from all CSV files in the specified directory
     static Map<String, String> loadLookupTablesFromDirectory(String directoryPath) throws IOException {
        Map<String, String> tagMap = new HashMap<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.csv")) 
        {
            for (Path entry : stream) {
                // Load each CSV file into the tagMap
                tagMap.putAll(loadLookupTable(entry.toString()));
            }
        }
        return tagMap;
    }
}
