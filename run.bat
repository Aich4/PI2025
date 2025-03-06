@echo off
echo Running TrekSwap application...

REM Check if target directory exists
if not exist "target" (
    echo Building the application...
    call mvn clean package
)

REM Run the application
mvn javafx:run 