@REM Maven wrapper script for Windows
@REM Download and run Maven if not present

@echo off
setlocal

set MAVEN_VERSION=3.9.6
set MAVEN_URL=https://dlcdn.apache.org/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_HOME=%USERPROFILE%\.m2\wrapper\maven
set MAVEN_PATH=%MAVEN_HOME%\apache-maven-%MAVEN_VERSION%
set MAVEN_CMD=%MAVEN_PATH%\bin\mvn.cmd

if not exist "%MAVEN_CMD%" (
    echo Downloading Maven %MAVEN_VERSION%...
    powershell -Command "New-Item -ItemType Directory -Force -Path '%MAVEN_HOME%'; Invoke-WebRequest '%MAVEN_URL%' -OutFile '%MAVEN_HOME%\maven.zip'; Expand-Archive '%MAVEN_HOME%\maven.zip' -DestinationPath '%MAVEN_HOME%' -Force; Remove-Item '%MAVEN_HOME%\maven.zip'"
)

"%MAVEN_CMD%" %* 