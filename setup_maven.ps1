# Create a directory for Maven in user's home directory
$mavenPath = "$env:USERPROFILE\Maven"
New-Item -ItemType Directory -Force -Path $mavenPath | Out-Null

# Download Maven
$mavenVersion = "3.9.6"
$mavenUrl = "https://dlcdn.apache.org/maven/maven-3/$mavenVersion/binaries/apache-maven-$mavenVersion-bin.zip"
$zipFile = "$mavenPath\maven.zip"

Write-Host "Downloading Maven $mavenVersion..."
Invoke-WebRequest -Uri $mavenUrl -OutFile $zipFile

# Extract Maven
Write-Host "Extracting Maven..."
Expand-Archive -Path $zipFile -DestinationPath $mavenPath -Force
Remove-Item $zipFile

# Add Maven to PATH
$mavenBinPath = "$mavenPath\apache-maven-$mavenVersion\bin"
$currentPath = [Environment]::GetEnvironmentVariable("Path", "User")

if (-not $currentPath.Contains($mavenBinPath)) {
    [Environment]::SetEnvironmentVariable("Path", "$currentPath;$mavenBinPath", "User")
    $env:Path = "$env:Path;$mavenBinPath"
}

Write-Host "Maven has been installed to: $mavenBinPath"
Write-Host "Please restart your terminal for the changes to take effect."

# Test Maven installation
try {
    $env:Path = "$env:Path;$mavenBinPath"
    mvn --version
    Write-Host "Maven installation successful!"
} catch {
    Write-Host "Error testing Maven installation. Please restart your terminal and try again."
} 