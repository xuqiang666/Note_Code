# Java Learning Notes

可运行的 Java 学习笔记。每个包聚焦一个主题，示例尽量保持独立、便于阅读和调试。

## 环境

- JDK 8
- Maven 3.8+

```bash
mvn clean test
```

## 学习地图

| 目录 | 内容 |
| --- | --- |
| `algorithm` | 数组、字符串、动态规划、回溯、树、栈等算法题 |
| `core` | 集合、排序、I/O、NIO、反射、Stream、SPI、日期时间 |
| `concurrency` | 线程池、Fork/Join、同步器、Future、定时与计时器 |
| `designpattern` | 建造者、单例、代理、状态模式 |
| `persistence` | JDBC 示例 |
| `framework` | MyBatis 手写实现与 Spring 相关示例 |
| `integration` | HTTP 客户端示例 |
| `library` | Guava 等第三方库的使用笔记 |
| `xml` | XML 与树结构处理示例 |
| `scratch` | 临时实验；验证完成后应归入对应主题 |

所有代码位于 `com.xq.notes` 根包下，目录与包名保持一一对应并使用小写英文。

## 新增笔记约定

1. 先选择最贴近主题的目录；没有合适分类时再新建一级目录。
2. 类名使用 PascalCase，包名使用全小写英文。
3. 可以运行的验证代码优先放入 `src/test/java`；`src/main/java` 保留示例实现和演示入口。
4. 外部服务（MySQL、Redis、网络接口）的示例必须写明前置条件，避免默认测试依赖本机环境。
