# Create a directory for Maven in user's home directory
$mavenPath = "$env:USERPROFILE\Maven"
New-Item -ItemType Directory -Force -Path $mavenPath

# Download Maven
$mavenUrl = "https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
$zipFile = "$mavenPath\maven.zip"
Invoke-WebRequest -Uri $mavenUrl -OutFile $zipFile

# Extract Maven
Expand-Archive -Path $zipFile -DestinationPath $mavenPath -Force
Remove-Item $zipFile

# Add Maven to PATH for current user
$mavenBinPath = (Get-ChildItem -Path $mavenPath -Filter "apache-maven-*" | Select-Object -First 1).FullName + "\bin"
$currentPath = [Environment]::GetEnvironmentVariable("Path", "User")
if (-not $currentPath.Contains($mavenBinPath)) {
    [Environment]::SetEnvironmentVariable("Path", $currentPath + ";" + $mavenBinPath, "User")
}

# Set JAVA_HOME if not set
$javaHome = [Environment]::GetEnvironmentVariable("JAVA_HOME", "User")
if (-not $javaHome) {
    $javaPath = (Get-Command java).Source
    $javaHome = (Get-Item $javaPath).Directory.Parent.Parent.FullName
    [Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "User")
}

Write-Host "Maven has been installed. Please restart your terminal for the changes to take effect." 