package illumio;


/*
 * Provided from the documentation 
 * https://docs.aws.amazon.com/vpc/latest/userguide/flow-log-records.html
 * 
 * Log example
 * https://docs.aws.amazon.com/vpc/latest/userguide/flow-logs-records-examples.html
 * Considering tcp, udp and icmp and rest as unknown
 */
public class MapProtocolName {

    // Convert protocol number to name
     static String protocolNumberToName(String protocolNum) {
        switch (protocolNum) {
            case "6":
                return "tcp";
            case "17":
                return "udp";
            case "1":
                return "icmp";
            default:
                return "unknown";
        }
    }
}
