@echo off

SET eureka_jar_log=logs\jar\eureka.log
SET auth_jar_log=logs\jar\auth.log
SET confjour_jar_log=logs\jar\confjour.log
SET intermediator_jar_log=logs\jar\intermediator.log

for /F %%a in ('echo prompt $E ^| cmd') do set "ESC=%%a"

SET GREEN=[32m
SET BLUE=[34m
SET ENDCOLOR=[0m

IF "%~1"=="start" (
    CALL :startJar
) ELSE IF "%~1"=="stop" (
    CALL :stopJar
)

SET eureka_jar_log=
SET auth_jar_log=
SET confjour_jar_log=
SET intermediator_jar_log=

SET ESC=

SET GREEN=
SET BLUE=
SET ENDCOLOR=

EXIT /B 0


:startJar
echo Stopping other applications if exists
echo -----------------------------------
CALL :stopJar

echo Start running
echo -----------------------------------
timeout 1 /nobreak >nul
if not exist logs\jar mkdir logs\jar;
echo Logs of running each module can be found in:
echo - eureka:                  %ESC%%GREEN%%eureka_jar_log%%ESC%%ENDCOLOR%
echo - auth:                   %ESC%%GREEN%%auth_jar_log%%ESC%%ENDCOLOR%
echo - confjour:                  %ESC%%GREEN%%confjour_jar_log%%ESC%%ENDCOLOR%
echo - intermediator:           %ESC%%GREEN%%intermediator_jar_log%%ESC%%ENDCOLOR%
echo.
echo -----------------------------------
start java -jar eureka.jar ^>^> %eureka_jar_log%
start java -jar auth.jar ^>^> %auth_jar_log%
start java -jar confjour.jar ^>^> %confjour_jar_log%
start java -jar intermediator.jar ^>^> %intermediator_jar_log%
echo Starting......

setlocal EnableDelayedExpansion
for /F %%a in ('copy /Z "%~F0" NUL') do set "CR=%%a"

FOR /L %%i IN (0,2,100) DO ( < NUL set /P "=%%i %%!CR!" & timeout 1 /nobreak >nul)

echo Running completed
echo -----------------------------------
echo Running address of each module:
echo - eureka:                  %ESC%%BLUE%http://localhost:8085%ESC%%ENDCOLOR%
echo - auth:                   %ESC%%BLUE%http://localhost:8086%ESC%%ENDCOLOR%
echo - confjour:                  %ESC%%BLUE%http://localhost:8087%ESC%%ENDCOLOR%
echo - intermediator:           %ESC%%BLUE%http://localhost:8088%ESC%%ENDCOLOR%
echo.
echo Please visit eureka page to check whether all 5 modules are running as expected. 

EXIT /B 0

:stopJar
echo Start stopping
echo Profile: jar
echo -----------------------------------
timeout 1 /nobreak >nul
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:8085.*LISTENING
') DO SET eureka_pid=%%P
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:8086.*LISTENING
') DO SET auth_pid=%%P
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:8087.*LISTENING
') DO SET confjour_pid=%%P
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:8088.*LISTENING
') DO SET intermediator_pid=%%P

IF NOT "%eureka_pid%" == "" (
    TaskKill.exe /F /PID "%eureka_pid%" >nul
    SET eureka_pid=
    echo Successfully stopped eureka
) ELSE (
    echo eureka is not running
)
IF NOT "%auth_pid%" == "" (
    TaskKill.exe /F /PID "%auth_pid%" >nul
    SET auth_pid=
    echo Successfully stopped auth
) ELSE (
    echo auth is not running
)
IF NOT "%confjour_pid%" == "" (
    TaskKill.exe /F /PID "%confjour_pid%" >nul
    SET confjour_pid=
    echo Successfully stopped confjour
) ELSE (
    echo confjour is not running
)
IF NOT "%intermediator_pid%" == "" (
    TaskKill.exe /F /PID "%intermediator_pid%" >nul
    SET intermediator_pid=
    echo Successfully stopped intermediator
) ELSE (
    echo intermediator is not running
)
echo Stopping completed
echo -----------------------------------
EXIT /B 0
