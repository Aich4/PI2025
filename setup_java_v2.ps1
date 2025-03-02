# Function to find Java installation
function Find-JavaHome {
    $javaExe = Get-Command java -ErrorAction SilentlyContinue
    if ($javaExe) {
        return (Get-Item $javaExe.Source).Directory.Parent.Parent.FullName
    }
    
    # Check common installation paths
    $commonPaths = @(
        "${env:ProgramFiles}\Java\*",
        "${env:ProgramFiles(x86)}\Java\*",
        "${env:ProgramFiles}\Eclipse Adoptium\*",
        "${env:ProgramFiles}\Eclipse Foundation\*",
        "${env:ProgramFiles}\Common Files\Oracle\Java\javapath"
    )
    
    foreach ($path in $commonPaths) {
        $javaInstalls = Get-Item $path -ErrorAction SilentlyContinue
        if ($javaInstalls) {
            foreach ($install in $javaInstalls) {
                if (Test-Path "$install\bin\java.exe") {
                    return $install.FullName
                }
            }
        }
    }
    
    return $null
}

# Find Java installation
$javaHome = Find-JavaHome
if ($javaHome) {
    Write-Host "Found Java installation at: $javaHome"
    
    # Set JAVA_HOME for current session
    $env:JAVA_HOME = $javaHome
    
    # Set JAVA_HOME permanently
    [System.Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "User")
    
    # Add Java bin to PATH if not already there
    $javaBin = "$javaHome\bin"
    $currentPath = [System.Environment]::GetEnvironmentVariable("Path", "User")
    if (-not $currentPath.Contains($javaBin)) {
        [System.Environment]::SetEnvironmentVariable("Path", "$currentPath;$javaBin", "User")
        $env:Path = "$env:Path;$javaBin"
    }
    
    Write-Host "JAVA_HOME has been set to: $javaHome"
    Write-Host "Java version information:"
    java -version
    
    Write-Host "`nPlease restart your terminal for the changes to take effect."
} else {
    Write-Host "No Java installation found. Please install Java 17 or later."
} 