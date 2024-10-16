package illumio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Function to write the output to file in resource folder
 * @author negimags
 *
 */
public class OutputWriter {
	

	// Write output to file
	 static void writeOutput(String outputFile, Map<String, Integer> tagCounts,
	                                Map<String, Integer> portProtocolCounts) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
	        // Write port/protocol counts
	        writer.write("Port,Protocol,Count\n");
	        for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
	            String[] keys = entry.getKey().split(","); // Split the key into port and protocol
	            String port = keys[0];
	            String protocol = keys[1];
	            writer.write(port + "," + protocol + "," + entry.getValue() + "\n");
	        }
	        
	        // Write tag counts
	        writer.write("\nTag Counts:\nTag,Count\n");
	        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
	            writer.write(entry.getKey() + "," + entry.getValue() + "\n");
	        }
	    }
	}
}
