@echo off
@if exist %~dp0merge-pdf-1.0-SNAPSHOT-jar-with-dependencies.jar (
    java -cp %~dp0merge-pdf-1.0-SNAPSHOT-jar-with-dependencies.jar com.demo.App %*
) else (
    java -cp %~dp0target\merge-pdf-1.0-SNAPSHOT-jar-with-dependencies.jar com.demo.App %*
)
