@echo off
set JAR_PATH=illumio_logger.jar

rem Check if the JAR file exists
if exist %JAR_PATH% (
    echo Running %JAR_PATH%...
    java -jar %JAR_PATH%
) else (
    echo Error: %JAR_PATH% not found!
    exit /b 1
)

