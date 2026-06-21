$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$sourceRoot = Join-Path $projectRoot "src\main\java"
$resourceRoot = Join-Path $projectRoot "src\main\resources"
$testRoot = Join-Path $projectRoot "src\test\java"
$buildRoot = Join-Path $projectRoot "build"
$classRoot = Join-Path $buildRoot "classes"
$testClassRoot = Join-Path $buildRoot "test-classes"
$distRoot = Join-Path $projectRoot "dist"
$version = "1.0.0"
$jarPath = Join-Path $distRoot "math-toolkit-$version.jar"
$sourcesJarPath = Join-Path $distRoot "math-toolkit-$version-sources.jar"
$manifestPath = Join-Path $buildRoot "MANIFEST.MF"

# Creates clean build and distribution directories.
Remove-Item -LiteralPath $buildRoot -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -LiteralPath $distRoot -Recurse -Force -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force -Path $classRoot, $testClassRoot, $distRoot | Out-Null

# Compiles the library for Java 17 compatibility.
$mainSources = Get-ChildItem -Path $sourceRoot -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac --release 17 -d $classRoot $mainSources

# Copies version metadata into the compiled class directory.
Copy-Item -Path (Join-Path $resourceRoot '*') -Destination $classRoot -Recurse -Force

# Writes the executable JAR manifest.
@(
    "Manifest-Version: 1.0"
    "Main-Class: ca.algonquin.cst8411.mathtoolkit.MathToolkitDemo"
    "Implementation-Title: CST8411 Math Toolkit"
    "Implementation-Version: $version"
    "Created-By: JDK $((javac -version 2>&1) -replace 'javac ', '')"
    ""
) | Set-Content -LiteralPath $manifestPath -Encoding ascii

# Packages compiled classes and resources into an executable JAR.
jar --create --file $jarPath --manifest $manifestPath -C $classRoot .

# Packages the source code for users who want API source access.
jar --create --file $sourcesJarPath -C $sourceRoot .

# Compiles and runs the dependency-free test suite.
$testSources = Get-ChildItem -Path $testRoot -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac --release 17 -cp $classRoot -d $testClassRoot $testSources
java -cp "$classRoot;$testClassRoot" ca.algonquin.cst8411.mathtoolkit.MathToolkitSelfTest

# Records checksums so downloaded artifacts can be verified.
$hashLines = Get-FileHash -Algorithm SHA256 $jarPath, $sourcesJarPath |
    ForEach-Object { "$($_.Hash.ToLower())  $([System.IO.Path]::GetFileName($_.Path))" }
$hashLines | Set-Content -LiteralPath (Join-Path $distRoot "SHA256SUMS.txt") -Encoding ascii

Write-Host "Build completed successfully."
Write-Host "JAR: $jarPath"

