#!/bin/sh

export eureka_jar_log=logs/jar/eureka.log
export auth_jar_log=logs/jar/auth.log
export confjour_jar_log=logs/jar/confjour.log
export intermediator_jar_log=logs/jar/intermediator.log

export GREEN="\e[32m"
export BLUE="\e[34m"
export ENDCOLOR="\e[0m"

startjar(){
    echo "Stopping other applications if exists"
    echo "-----------------------------------"
    stopjar

    echo "Start running"
    echo "-----------------------------------"
    sleep 1
    mkdir -p logs/jar
    echo "Logs of running each module can be found in:"
    printf -- "- eureka:                  ${GREEN}$eureka_jar_log${ENDCOLOR}\n"
    printf -- "- auth:                   ${GREEN}$auth_jar_log${ENDCOLOR}\n"
    printf -- "- confjour:                  ${GREEN}$confjour_jar_log${ENDCOLOR}\n"
    printf -- "- intermediator:           ${GREEN}$intermediator_jar_log${ENDCOLOR}\n"
    echo " "
    echo "-----------------------------------"
    (java -jar eureka.jar >> $eureka_jar_log) &
    (java -jar auth.jar >> $auth_jar_log) &
    (java -jar confjour.jar  >> $confjour_jar_log) &
    (java -jar intermediator.jar >> $intermediator_jar_log) &
    (
        echo "Starting......"
        timer=0
        while(( $timer<=100 ))
        do
            printf "$timer %%\r"
            sleep 1
            timer=`expr $timer + 2`
        done
        echo "Running completed"
        echo "-----------------------------------"
        echo "Running address of each module:"
        printf -- "- eureka:                  ${BLUE}http://localhost:8085${ENDCOLOR}\n"
        printf -- "- auth:                   ${BLUE}http://localhost:8086${ENDCOLOR}\n"
        printf -- "- confjour:                  ${BLUE}http://localhost:8087${ENDCOLOR}\n"
        printf -- "- intermediator:           ${BLUE}http://localhost:8088${ENDCOLOR}\n"
        echo " "
        echo "Please visit eureka page to check whether all 5 modules are running as expected. "
    )
}

stopjar() {
    echo "Start stopping"
    echo "Profile: jar"
    echo "-----------------------------------"
    sleep 1
    eureka_pid=`lsof -i:8085|grep "LISTEN"|awk '{print $2}'`;
    auth_pid=`lsof -i:8086|grep "LISTEN"|awk '{print $2}'`;
    confjour_pid=`lsof -i:8087|grep "LISTEN"|awk '{print $2}'`;
    intermediator_pid=`lsof -i:8088|grep "LISTEN"|awk '{print $2}'`;
    
    if [ "$eureka_pid" != "" ]
    then
        kill -9 $eureka_pid
        echo "Successfully stopped eureka"
    else
        echo "eureka is not running"
    fi
    
    if [ "$auth_pid" != "" ]
    then
        kill -9 $auth_pid
        echo "Successfully stopped auth"
    else
        echo "auth is not running"
    fi
    
    if [ "$confjour_pid" != "" ]
    then
        kill -9 $confjour_pid
        echo "Successfully stopped confjour"
    else
        echo "confjour is not running"
    fi
    
    if [ "$intermediator_pid" != "" ]
    then
        kill -9 $intermediator_pid
        echo "Successfully stopped intermediator"
    else
        echo "intermediator is not running"
    fi
    echo "Stopping completed"
    echo "-----------------------------------"
}

case "$1" in
    start)
        startjar
    ;;
    stop)
        stopjar
    ;;
esac
exit 0
