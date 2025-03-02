# Find Java installation
$javaPath = Get-Command java | Select-Object -ExpandProperty Source
if ($javaPath) {
    $javaHome = (Get-Item $javaPath).Directory.Parent.Parent.FullName
    
    # Set JAVA_HOME for the current session
    $env:JAVA_HOME = $javaHome
    
    # Set JAVA_HOME permanently for the user
    [System.Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "User")
    
    Write-Host "JAVA_HOME has been set to: $javaHome"
    Write-Host "Please restart your terminal after running this script."
} else {
    Write-Host "Java installation not found. Please make sure Java is installed."
} 