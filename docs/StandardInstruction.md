# 项目规范

## 目录

- [项目规范](#项目规范)
  - [目录](#目录)
  - [规范](#规范)
    - [Commit Message](#commit-message)
    - [代码规范](#代码规范)
  - [常用`mvn`命令](#常用mvn命令)
  - [学习资料](#学习资料)
    - [Java小技巧](#java小技巧)
    - [Spring-boot子模块架构相关](#spring-boot子模块架构相关)
    - [Spring-boot数据库相关](#spring-boot数据库相关)
    - [Spring-boot文件传输](#spring-boot文件传输)
    - [Spring-boot测试相关](#spring-boot测试相关)
    - [Spring-cloud微服务框架相关](#spring-cloud微服务框架相关)
    - [接口相关](#接口相关)

## 规范

### Commit Message

- feat: 新功能、特性
- fix: 修bug
- docs: 文档修改
- test: 测试
- style: 代码格式等修改
- refactor: 重构
- config: 项目配置
- perf: 性能优化
- version: 版本更新

### 代码规范

1. **命名要能清楚表达变量/函数等作用**，驼峰法，变量类型不用在变量名内提醒
2. 复杂函数勤写注释
3. 其他函数可以考虑简单写点参数与返回值之类的注释
4. 尽量不要过长或过短的函数。能复用的部分尽可能复用

## 常用`mvn`命令

- `mvn test`: 跑测试
- `mvn clean install`: 重新加载项目、下载依赖等
- `mvn spotless:check`：检查Java格式
- `mvn spotless:apply`：Java格式化
- `mvn versions:set -D newVersion=版本号`: 更新项目版本
- `mvn versions:revert`: 项目版本回滚
- `mvn versions:commit`: 项目版本提交
- `mvn package`: 项目打包
- `mvn 指令 -D skipTests`: 跳过测试
- `mvn 指令 -P 环境定义ID`: 指定环境运行

## 学习资料

### Java小技巧

- [Java8提供的stream流操作](https://blog.csdn.net/MinggeQingchun/article/details/123184273)
- [Java正则表达式](https://blog.csdn.net/qq_41154902/article/details/124948491?spm=1001.2101.3001.6650.15&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-15-124948491-blog-114474391.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-15-124948491-blog-114474391.pc_relevant_default&utm_relevant_index=16)

### Spring-boot子模块架构相关

- [Spring-boot四层MVC框架](https://blog.csdn.net/weixin_44532671/article/details/117914161?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-117914161-blog-125536691.pc_relevant_multi_platform_whitelistv3&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-117914161-blog-125536691.pc_relevant_multi_platform_whitelistv3)
- [常用注解](https://zhuanlan.zhihu.com/p/489217499)

### Spring-boot数据库相关

- [Spring-boot连数据库](https://www.w3cschool.cn/article/69469419.html)
- [外键写法概要](https://www.cnblogs.com/luo630/p/15428367.html)
- [一对一、一对多、多对多等外键写法](https://blog.csdn.net/sinianliushui/article/details/101452018?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-101452018-blog-127540339.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-101452018-blog-127540339.pc_relevant_default&utm_relevant_index=6)
- [JPA级连操作](https://www.jianshu.com/p/ae07c9f147bc)
- [JPA deleteById](https://blog.csdn.net/qq_34465338/article/details/121336199)
- [JPA saveAndFlush](https://blog.csdn.net/chusen/article/details/112913759)
- [用JPA来减少撰写sql](https://blog.csdn.net/weixin_45815335/article/details/125203399)

### Spring-boot文件传输

- [Spring接收、发送文件](https://blog.csdn.net/qq_57390446/article/details/127797971)
- [MultipartFile多服务间中转文件](https://blog.csdn.net/weixin_43202160/article/details/129025774?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-2-129025774-blog-123480628.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-2-129025774-blog-123480628.pc_relevant_default&utm_relevant_index=5)
- [文件中转](https://blog.csdn.net/qq_38066290/article/details/111253699)
- [大文件转发](https://stackoverflow.com/questions/15781885/how-to-forward-large-files-with-resttemplate/36226006#36226006)
- [中转小/大型文件](https://blog.csdn.net/eleanoryss/article/details/123480628)
- [文件分块上传](https://blog.csdn.net/u011974797/article/details/127614183)

### Spring-boot测试相关

- [Spring-boot测试回滚](https://blog.csdn.net/weixin_60719453/article/details/127423660)
- [Spring-boot测试](https://blog.csdn.net/m0_52601969/article/details/125954919)
- [Spring-boot Mock测试](https://blog.csdn.net/oschina_41790905/article/details/111501402)

### Spring-cloud微服务框架相关

- [Spring-cloud模块间调用](https://www.cnblogs.com/moonandstar08/p/7565524.html)

### 接口相关

- [REST风格API](https://zhuanlan.zhihu.com/p/536437382)
- [Swagger接口文档导出](https://stackoverflow.com/questions/54259816/how-to-generate-a-pdf-or-markup-from-openapi-3-0)
