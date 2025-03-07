@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist taskInfo.txt del taskInfo.txt

REM compile the code into the bin folder (recursive compilation for /minion package)
dir /s /b ..\src\main\java\minion\*.java > sources.txt
javac -cp ..\src\main\java -Xlint:none -d ..\bin @sources.txt
del sources.txt

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin minion.Minion < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
