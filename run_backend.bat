@echo off

SET eureka_docker_log=logs\docker\eureka.log
SET auth_docker_log=logs\docker\auth.log
SET confjour_docker_log=logs\docker\confjour.log
SET intermediator_docker_log=logs\docker\intermediator.log

SET eureka_dev_log=logs\dev\eureka.log
SET auth_dev_log=logs\dev\auth.log
SET confjour_dev_log=logs\dev\confjour.log
SET intermediator_dev_log=logs\dev\intermediator.log

for /F %%a in ('echo prompt $E ^| cmd') do set "ESC=%%a"

SET GREEN=[32m
SET BLUE=[34m
SET ENDCOLOR=[0m

IF "%~1:%~2"=="start:dev" (
    CALL :startDev
) ELSE IF "%~1:%~2"=="start:docker" (
    CALL :startdocker
) ELSE IF "%~1:%~2"=="stop:dev" (
    CALL :stopDev
) ELSE IF "%~1:%~2"=="stop:docker" (
    CALL :stopdocker
)

SET eureka_docker_log=
SET auth_docker_log=
SET confjour_docker_log=
SET intermediator_docker_log=

SET eureka_dev_log=
SET auth_dev_log=
SET confjour_dev_log=
SET intermediator_dev_log=

SET ESC=

SET GREEN=
SET BLUE=
SET ENDCOLOR=

EXIT /B 0

:startdocker
echo Stopping other applications if exists
echo -----------------------------------
CALL :stopDev
CALL :stopdocker

echo Start running
echo Profile: docker
echo -----------------------------------
timeout 1 /nobreak >nul
if not exist logs\docker mkdir logs\docker;
echo Logs of running each module can be found in:
echo - eureka:                  %ESC%%GREEN%%eureka_docker_log%%ESC%%ENDCOLOR%
echo - auth:                   %ESC%%GREEN%%auth_docker_log%%ESC%%ENDCOLOR%
echo - confjour:                  %ESC%%GREEN%%confjour_docker_log%%ESC%%ENDCOLOR%
echo - intermediator:           %ESC%%GREEN%%intermediator_docker_log%%ESC%%ENDCOLOR%
echo. 
echo -----------------------------------
start /b cmd /c mvnw.cmd -pl eureka spring-boot:run -P docker ^>^> %eureka_docker_log%
start /b cmd /c mvnw.cmd -pl auth spring-boot:run -P docker ^>^> %auth_docker_log%
start /b cmd /c mvnw.cmd -pl confjour spring-boot:run -P docker ^>^> %confjour_docker_log%
start /b cmd /c mvnw.cmd -pl intermediator spring-boot:run -P docker ^>^> %intermediator_docker_log%
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
echo - intermediator:           %ESC%%BLUE%http://localhost:8090%ESC%%ENDCOLOR%
echo. 
echo Please visit eureka page to check whether all 5 modules are running as expected. 

EXIT /B 0


:startDev
echo Stopping other applications if exists
echo -----------------------------------
CALL :stopDev
CALL :stopdocker

echo Start running
echo Profile: dev
echo -----------------------------------
timeout 1 /nobreak >nul
if not exist logs\dev mkdir logs\dev;
echo Logs of running each module can be found in:
echo - eureka:                  %ESC%%GREEN%%eureka_dev_log%%ESC%%ENDCOLOR%
echo - auth:                   %ESC%%GREEN%%auth_dev_log%%ESC%%ENDCOLOR%
echo - confjour:                  %ESC%%GREEN%%confjour_dev_log%%ESC%%ENDCOLOR%
echo - intermediator:           %ESC%%GREEN%%intermediator_dev_log%%ESC%%ENDCOLOR%
echo.
echo -----------------------------------
start /b cmd /c mvnw.cmd -pl eureka spring-boot:run -P dev ^>^> %eureka_dev_log%
start /b cmd /c mvnw.cmd -pl auth spring-boot:run -P dev ^>^> %auth_dev_log%
start /b cmd /c mvnw.cmd -pl confjour spring-boot:run -P dev ^>^> %confjour_dev_log%
start /b cmd /c mvnw.cmd -pl intermediator spring-boot:run -P dev ^>^> %intermediator_dev_log%
echo Starting......

setlocal EnableDelayedExpansion
for /F %%a in ('copy /Z "%~F0" NUL') do set "CR=%%a"

FOR /L %%i IN (0,2,100) DO ( < NUL set /P "=%%i %%!CR!" & timeout 1 /nobreak >nul)

echo Running completed
echo -----------------------------------
echo Running address of each module:
echo - eureka:                  %ESC%%BLUE%http://localhost:5272%ESC%%ENDCOLOR%
echo - auth:                   %ESC%%BLUE%http://localhost:8762%ESC%%ENDCOLOR%
echo - confjour:                  %ESC%%BLUE%http://localhost:5678%ESC%%ENDCOLOR%
echo - intermediator:           %ESC%%BLUE%http://localhost:8090%ESC%%ENDCOLOR%
echo.
echo Please visit eureka page to check whether all 5 modules are running as expected. 

EXIT /B 0

:stopdocker
echo Start stopping
echo Profile: docker
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
    netstat -a -n -o ^| findstr 0.0.0.0:8090.*LISTENING
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

:stopDev
echo Start stopping
echo Profile: dev
echo -----------------------------------
timeout 1 /nobreak >nul
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:5272.*LISTENING
') DO SET eureka_pid=%%P
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:8762.*LISTENING
') DO SET auth_pid=%%P
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:5678.*LISTENING
') DO SET confjour_pid=%%P
FOR /F "tokens=5 delims= " %%P IN ('
    netstat -a -n -o ^| findstr 0.0.0.0:8090.*LISTENING
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
