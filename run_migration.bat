@echo off
echo Compiling and running password migration...

REM Create lib directory if it doesn't exist
if not exist "lib" mkdir lib

REM Download BCrypt library if not present
if not exist "lib\jbcrypt-0.4.jar" (
    echo Downloading BCrypt library...
    powershell -Command "Invoke-WebRequest 'https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar' -OutFile 'lib\jbcrypt-0.4.jar'"
)

REM Download MySQL JDBC driver if not present
if not exist "lib\mysql-connector-j-8.0.33.jar" (
    echo Downloading MySQL JDBC driver...
    powershell -Command "Invoke-WebRequest 'https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar' -OutFile 'lib\mysql-connector-j-8.0.33.jar'"
)

REM Compile the code
javac -cp "lib\*;src\main\java" src\main\java\utils\SecurityUtil.java src\main\java\utils\PasswordMigration.java src\main\java\utils\MigrationRunner.java

REM Run the migration
java -cp "lib\*;src\main\java" utils.MigrationRunner

echo Migration completed. 