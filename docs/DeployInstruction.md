# 部署文档

## 目录

- [部署文档](#部署文档)
  - [目录](#目录)
  - [配置数据库](#配置数据库)
  - [Docker 打包](#docker-打包)
  - [部署时需要示例数据](#部署时需要示例数据)

## 配置数据库

1. 使用MySQL数据库（版本：8），数据库需要一个满足以下条件的用户：

   - 权限：All
   - 端口：`3306`
   - 账号：`root`
   - 密码：`rootroot`

    SQL:

    ```sql
    #DROP USER if EXISTS `root`;

    #CREATE USER `root`@`localhost` 
    #    IDENTIFIED WITH mysql_native_password 
    #    BY 'rootroot' PASSWORD EXPIRE NEVER;

    #GRANT Alter, Alter Routine, Create, Create Routine, Create Temporary Tables, Create User, Create View, Delete, Drop, Event, Execute, File, Grant Option, Index, Insert, Lock Tables, Process, References, Reload, Replication Client, Replication Slave, Select, Show Databases, Show View, Shutdown, Super, Trigger, Update ON *.* TO `root`@`localhost`;
    ```

2. 初始化数据库：运行`database/init.sql`

## Docker 打包

按顺序执行以下命令：

1. 项目下载依赖：

    ```bash
    # Windows
    # .\mvnw.cmd clean install -D skipTests -N
    # .\mvnw.cmd clean install -D skipTests

    # MacOS & Linux
    ./mvnw clean install -D skipTests -N
    ./mvnw clean install -D skipTests
    ```

2. 项目打成jar包：

    ```bash
    # Windows
    # .\mvnw.cmd clean package -D skipTests -P docker

    # MacOS & Linux
    ./mvnw clean package -D skipTests -P docker
    ```

3. docker build：

    ```bash
    docker build -t sw-archi-backend .
    ```

4. docker run：

    ```bash
    docker run -p 8085:8085 -p 8086:8086 -p 8087:8087 -p 8088:8088 --name sw-archi-backend virtual-pet-hospital
    ```

## 部署时需要示例数据

如果需要向数据库里导入示例数据，需要进行以下操作：

1. 在MySQL数据库中，运行`database/data.sql`，以向数据库中导入示例数据。
