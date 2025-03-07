@echo off
echo Cleaning and building the project...

REM Delete target directory if it exists
if exist "target" (
    echo Cleaning previous build...
    rmdir /s /q "target"
)

REM Clean and package the project
echo Building project...
call mvn clean package

if errorlevel 1 (
    echo Build failed! Check the error messages above.
    pause
    exit /b 1
) else (
    echo Build successful!
    echo You can now run the application using run.bat
    pause
) 