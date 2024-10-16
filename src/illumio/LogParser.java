package illumio;


import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class LogParser {

    static MapProtocolName mapProtocolName;
     // Load all flow log files from a directory
      static List<String> loadFlowLogsFromDirectory(String directoryPath) throws IOException {
         List<String> flowLogs = new ArrayList<>();
         try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.txt")) {
             for (Path entry : stream) {
                 flowLogs.add(entry.toString());
             }
         }
         System.out.println("Fetching all file location from director path " + directoryPath);
         return flowLogs;
     }
      // function to take in the flow files and the tag count from csv and map it for output
      static void processFlowLogs(List<String> flowFiles, Map<String, String> tagMap,
                                         Map<String, Integer> tagCounts,
                                         Map<String, Integer> portProtocolCounts) throws IOException {
         for (String flowFile : flowFiles) {
        	 System.out.println("Started reading logs from inputFiles folder " + flowFile);
             try (BufferedReader br = new BufferedReader(new FileReader(flowFile))) {
                 String line;
                 while ((line = br.readLine()) != null) {
                     String[] parts = line.split(" ");
                     if (parts.length < 14) continue;  // Skip invalid lines

                     String dstport = parts[5];        // dstport is the 6th field
                     String protocolNum = parts[7];    // protocol is the 8th field
                     String protocol = MapProtocolName.protocolNumberToName(protocolNum);

                     // Increment port/protocol combination count
                     String portProtocolKey = dstport + "," + protocol;
                     portProtocolCounts.put(portProtocolKey, portProtocolCounts.getOrDefault(portProtocolKey, 0) + 1);

                     // Check if the dstport/protocol combination has a tag
                     String tagKey = dstport + "," + protocol;
                     String tag = tagMap.getOrDefault(tagKey, "Untagged");
                     tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                 }
             }
             System.out.println("Finished reading logs from inputFiles folder " + flowFile);
         }
         System.out.println("Exiting function to process all flow logs ");
     }


}