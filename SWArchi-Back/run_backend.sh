#!/bin/sh

export eureka_docker_log=logs/docker/eureka.log
export auth_docker_log=logs/docker/auth.log
export confjour_docker_log=logs/docker/confjour.log
export intermediator_docker_log=logs/docker/intermediator.log

export eureka_dev_log=logs/dev/eureka.log
export auth_dev_log=logs/dev/auth.log
export confjour_dev_log=logs/dev/confjour.log
export intermediator_dev_log=logs/dev/intermediator.log

export GREEN="\e[32m"
export BLUE="\e[34m"
export ENDCOLOR="\e[0m"

startdocker(){
    echo "Stopping other applications if exists"
    echo "-----------------------------------"
    stopDev
    stopdocker

    echo "Start running"
    echo "Profile: docker"
    echo "-----------------------------------"
    sleep 1
    mkdir -p logs/docker
    echo "Logs of running each module can be found in:"
    printf -- "- eureka:                  ${GREEN}$eureka_docker_log${ENDCOLOR}\n"
    printf -- "- auth:                   ${GREEN}$auth_docker_log${ENDCOLOR}\n"
    printf -- "- confjour:                  ${GREEN}$confjour_docker_log${ENDCOLOR}\n"
    printf -- "- intermediator:           ${GREEN}$intermediator_docker_log${ENDCOLOR}\n"
    echo " "
    echo "-----------------------------------"
    (./mvnw -pl eureka spring-boot:run -P docker >> $eureka_docker_log) &
    (./mvnw -pl auth spring-boot:run -P docker >> $auth_docker_log) &
    (./mvnw -pl confjour spring-boot:run -P docker >> $confjour_docker_log) &
    (./mvnw -pl intermediator spring-boot:run -P docker >> $intermediator_docker_log) &
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
        printf -- "- intermediator:           ${BLUE}http://localhost:8090${ENDCOLOR}\n"
        echo " "
        echo "Please visit eureka page to check whether all 5 modules are running as expected. "
    )
}

startDev(){
    echo "Stopping other applications if exists"
    echo "-----------------------------------"
    stopDev
    stopdocker

    echo "Start running"
    echo "Profile: dev"
    echo "-----------------------------------"
    sleep 1
    mkdir -p logs/dev
    echo "Logs of running each module can be found in:"
    printf -- "- eureka:                  ${GREEN}$eureka_dev_log${ENDCOLOR}\n"
    printf -- "- auth:                   ${GREEN}$auth_dev_log${ENDCOLOR}\n"
    printf -- "- confjour:                  ${GREEN}$confjour_dev_log${ENDCOLOR}\n"
    printf -- "- intermediator:           ${GREEN}$intermediator_dev_log${ENDCOLOR}\n"
    echo " "
    echo "-----------------------------------"
    (./mvnw -pl eureka spring-boot:run -P dev >> $eureka_dev_log) &
    (./mvnw -pl auth spring-boot:run -P dev >> $auth_dev_log) &
    (./mvnw -pl confjour spring-boot:run -P dev >> $confjour_dev_log) &
    (./mvnw -pl intermediator spring-boot:run -P dev >> $intermediator_dev_log) &
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
        printf -- "- eureka:                  ${BLUE}http://localhost:5272${ENDCOLOR}\n"
        printf -- "- auth:                   ${BLUE}http://localhost:8762${ENDCOLOR}\n"
        printf -- "- confjour:                  ${BLUE}http://localhost:5678${ENDCOLOR}\n"
        printf -- "- intermediator:           ${BLUE}http://localhost:8090${ENDCOLOR}\n"
        echo " "
        echo "Please visit eureka page to check whether all 5 modules are running as expected. "
    )
}

stopdocker() {
    echo "Start stopping"
    echo "Profile: docker"
    echo "-----------------------------------"
    sleep 1
    eureka_pid=`lsof -i:8085|grep "LISTEN"|awk '{print $2}'`;
    auth_pid=`lsof -i:8086|grep "LISTEN"|awk '{print $2}'`;
    confjour_pid=`lsof -i:8087|grep "LISTEN"|awk '{print $2}'`;
    intermediator_pid=`lsof -i:8090|grep "LISTEN"|awk '{print $2}'`;
    
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

stopDev() {
    echo "Start stopping"
    echo "Profile: dev"
    echo "-----------------------------------"
    sleep 1
    eureka_pid=`lsof -i:5272|grep "LISTEN"|awk '{print $2}'`;
    auth_pid=`lsof -i:8762|grep "LISTEN"|awk '{print $2}'`;
    confjour_pid=`lsof -i:5678|grep "LISTEN"|awk '{print $2}'`;
    intermediator_pid=`lsof -i:8090|grep "LISTEN"|awk '{print $2}'`;
    
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

case "$1:$2" in
    start:dev)
        startDev
    ;;
    start:docker)
        startdocker
    ;;
    stop:dev)
        stopDev
    ;;
    stop:docker)
        stopdocker
    ;;
esac
exit 0
