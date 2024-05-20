# Software Architecture Project Backend Part

## 目录

- [Software Architecture Project Backend Part](#software-architecture-project-backend-part)
  - [目录](#目录)
  - [Contributors](#contributors)
  - [配置数据库](#配置数据库)
  - [运行项目](#运行项目)
    - [便捷的启动方式](#便捷的启动方式)
  - [文档](#文档)
  - [说明](#说明)

## Contributors

- 李亦杨 51265902027

## 配置数据库

使用MySQL数据库，数据库需要一个满足以下条件的用户：

- 权限：All
- 端口：`3306`
- 账号：``
- 密码：``

## 运行项目

1. 启动MySQL数据库后，启动eureka模块，随后启动其他模块

### 便捷的启动方式

根目录下运行：

```bash
# Windows
# .\run_backend.bat start prod

# MacOS & Linux
chmod 755 run_backend.sh
./run_backend.sh start prod
```

其中两个参数分别代表：

| 参数1 \ 参数2 | dev                 |
| ------------- | ------------------- |
| start         | 启动项目（dev环境） |
| stop          | 停止项目（dev环境） |

## 文档

- [各类问题](./docs/QA.md)
- [规范与开发教程](./docs/StandardInstruction.md)

## 说明

1. 以`master`分支为主分支。如果要进行开发等改动，请创建新分支进行修改，完成后提交pr，**不要自己merge**。
