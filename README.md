Flow Log Parser

This program parses AWS flow log data from text files, maps destination port and protocol combinations to tags based on a provided lookup table, and generates output summarizing the matched tags and port/protocol counts.
Features

    Reads flow logs from multiple text files.
    Reads tag mapping from multiple CSV lookup files.
    Supports protocol conversion for standard TCP, UDP, and ICMP.
    Generates an output file with counts for matched tags and port/protocol combinations.
    Skips invalid flow log lines and handles untagged entries.

Assumptions

    Flow Log File Format:
        The flow log files contain at least 14 space-separated fields per line:
            The 6th field represents the destination port (dstport).
            The 8th field represents the protocol number.
        Any line with fewer than 14 fields is considered invalid and skipped.

    Protocol Mapping:
        Protocol numbers are mapped to protocol names as follows:
            6 → tcp
            17 → udp
            1 → icmp
            Any other protocol number is mapped to unknown.

    Tag Lookup Table:
        The tag lookup files are in CSV format with the following columns:
            dstport: Destination port.
            protocol: Protocol name (case-insensitive).
            tag: Tag associated with the destination port and protocol combination.
        The lookup table's first row is treated as a header and skipped during processing.

    Directory Structure:
        Flow log files are located in the ./resources/inputFiles/ directory and must have a .txt extension.
        Lookup table files are located in the ./resources/inputCsvFiles/ directory and must have a .csv extension.
        The program processes all valid .txt and .csv files in these directories.

    Handling Unknown Tags:
        If a destination port and protocol combination does not exist in the lookup table, it is tagged as "Untagged".

    Counting Port/Protocol Combinations:
        Only valid (non-unknown) port/protocol combinations are included in the portProtocolCounts map.

    Output File Naming:
        The output file is saved in the ./resources/outputFiles/ directory.
        The filename is generated dynamically in the format: output_yyyyMMdd_HHmmss.txt, where yyyyMMdd_HHmmss is the current timestamp.

    Invalid/Empty Lines:
        Any line in the flow logs with fewer than 14 fields is ignored.

    Case Insensitive Protocol Matching:
        Protocol names in the lookup table are case-insensitive (they are converted to lowercase during the matching process).
