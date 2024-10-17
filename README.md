# Log Reader

This Java program parses AWS VPC flow log data and maps each log entry to a tag based on a provided lookup table. The lookup table contains destination ports, protocols, and associated tags. The program processes multiple flow log files and CSV lookup tables, generating a summary of tag matches and port/protocol combinations.

## Features
- **Supports multiple flow log files**: Parses flow log data from multiple `.txt` files located in the input directory.
- **Supports multiple lookup tables**: Loads mapping data from multiple `.csv` files located in the lookup directory.
- **Protocol conversion**: Converts protocol numbers (e.g., 6 for TCP, 17 for UDP) to human-readable names.
- **Output summary**: Generates an output file with a summary of:
  - Tag counts (how often each tag was matched)
  - Port/Protocol combination counts (how often each combination was found)

## Assumptions
1. **Flow log format**: The program supports only AWS VPC flow logs in the default format and version 2, with the following fields considered:
   - Source IP
   - Destination IP
   - Destination port
   - Protocol
2. **Protocol support**: Only TCP, UDP, and ICMP protocols are recognized (protocol numbers 6, 17, and 1 respectively). Other protocols are categorized as `unknown`.
3. **Tag mapping**: Tag mapping is based on destination port and protocol combination. If no match is found in the lookup table, the entry is tagged as `Untagged`.
4. **File format**: The lookup table files should be in CSV format with three columns: `dstport`, `protocol`, and `tag`. Flow log files are expected to be plain text files.
5. **Case insensitivity**: Protocols and destination ports in the lookup table are matched case-insensitively.
6. **Multiple files**: The program assumes that both the flow log files and lookup CSV files are stored in their respective folders and processes all files with the `.txt` and `.csv` extensions in those folders.

## How to Run

### Prerequisites
- Java 8 or above
- Developed on Java 18 

### Steps:
1. Place the flow log `.txt` files in the `resources/inputFiles` directory.
2. Place the lookup `.csv` files in the `resources/inputCsvFiles` directory.
3. Main java file is LogRunner.java under src/illumio/ folder. 
4. Compile and run the program:
   ```bash
   javac -d . LogRunner.java
   java illumio.LogRunner

### Running the package using Runnable Jar:
- Navigate to RunnablePackage folder
- If using mac, run the bas script run_illumio_logger.sh, for windows run run_illumio_logger.bat
- Place your logs in resources/inputFiles folder
- Place the csv file on  resources/inputCsvFiles folder
- Run the program
- Result file will be generated in Output folder

### Tests:
- All test files are in the package
- Tested with single and multiple log files
  
