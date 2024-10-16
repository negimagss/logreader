#!/bin/bash

# Path to the JAR file
JAR_PATH="./illumio_logger.jar"

# Check if the JAR file exists
if [ -f "$JAR_PATH" ]; then
    echo "Running the illumio_logger.jar..."
    java -jar "$JAR_PATH"
else
    echo "Error: $JAR_PATH not found!"
    exit 1
fi

