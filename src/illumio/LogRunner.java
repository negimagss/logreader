package illumio;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Main function to run the code 
 * @author negimags
 *
 */
public class LogRunner {
	 // file path locations
	  static String lookupDirectory = "./resources/inputCsvFiles";
	  static String flowDirectory = "./resources/inputFiles"; 
	  static String outputFile = "./resources/outputFiles/output " + getCurrentDateTimeAsString()+ ".txt";
	
	// Main function
    public static void main(String[] args) {
    	// input for sample files from resource folder      
        Map<String, String> tagMap;
        Map<String, Integer> tagCounts = new HashMap<>();
        Map<String, Integer> portProtocolCounts = new HashMap<>();
        try {
        	// calling function to handle multiple csv files 
            tagMap = LogCsvReader.loadLookupTablesFromDirectory(lookupDirectory);
            List<String> flowFiles = LogParser.loadFlowLogsFromDirectory(flowDirectory); // Load all flow log files
            
            // calling function to process all log files 
            LogParser.processFlowLogs(flowFiles, tagMap, tagCounts, portProtocolCounts);
            // writing the output
            OutputWriter.writeOutput(outputFile, tagCounts, portProtocolCounts);
            System.out.println("Finished processing the files. The output will be in output Folder");
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }
    
    
    // function to create a new output file for each logs
    public static String getCurrentDateTimeAsString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
}
