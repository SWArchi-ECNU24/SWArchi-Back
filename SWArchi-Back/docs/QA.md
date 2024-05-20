# 后端项目常见问题

## 目录

- [后端项目常见问题](#后端项目常见问题)
  - [目录](#目录)
  - [常见报错](#常见报错)
    - [`package/repackage fail`](#packagerepackage-fail)
    - [无法识别`mvn`命令](#无法识别mvn命令)
    - [`java.lang.IllegalStateException`](#javalangillegalstateexception)
    - [项目正常运行且接口映射正确，但接口404](#项目正常运行且接口映射正确但接口404)

## 常见报错

大部分报错可以先尝试通过以下方式解决：

```bash
# Windows
# .\mvnw.cmd clean install -D skipTests -N
# .\mvnw.cmd clean install -D skipTests

# MacOS & Linux
./mvnw clean install -D skipTests -N
./mvnw clean install -D skipTests
```

如果未能解决，请继续在后文中查找。

### `package/repackage fail`

``` text
package/repackage fail ··· 
has been compiled by a more recent version of the Java Runtime
Java Runtime (class file version 61.0), this version of the Java Runtime only recognizes class file version ...
```

电脑默认Java版本太低，改成Java 17

### 无法识别`mvn`命令

- MacOs/Linux: `mvn`改成`./mvnw`
- Windows: `mvn`改成`mvnw.cmd`

### `java.lang.IllegalStateException`

```test
java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
```

保证项目路径没有空格或中文即可

### 项目正常运行且接口映射正确，但接口404

保证项目路径没有空格或中文即可
