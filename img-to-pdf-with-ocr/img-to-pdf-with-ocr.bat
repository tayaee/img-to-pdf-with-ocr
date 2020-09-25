@echo off
setlocal enabledelayedexpansion
if .%1. == .. (
	echo usage: %~nx0 *.jpg *.png
	echo        %~nx0 file1.jpg ... fileN.jpg
	echo        %~nx0 file1.png ... fileN.png
	goto :eof
)

set TESSDATA_DIR=%LOCALAPPDATA%\Tesseract-OCR\tessdata

:determine_jar_dir
set JAR_FILE=img-to-pdf-with-ocr-1.0-SNAPSHOT-jar-with-dependencies.jar
set JAR_DIR=
for %%f in (
	"target\"
	"%~dp0"
) do (
	if exist %%f!JAR_FILE! (
		set JAR_PATH=%%f!JAR_FILE!
		set JAR_PATH=!JAR_PATH:"=!
		goto run_jar
	)
)
echo Cannot find !JAR_FILE!.
goto :eof

:run_jar
echo java -cp "!JAR_PATH!" -Dtessdata-dir="!TESSDATA_DIR!" com.demo.App %*
java -cp "!JAR_PATH!" -Dtessdata-dir="!TESSDATA_DIR!" com.demo.App %*
