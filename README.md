# 线程池配置不同参数压力测试


## 1. 生成一个springboot 项目，配置一个线程池，写一个接口模拟线程占用五秒,用ab test测试这个接口，让监控链接/actuator/health访问超时


好的,我会用中文回答您的问题,并为您生成一个 Spring Boot 项目,配置线程池,创建一个模拟线程占用的接口,并提供 AB 测试的指导。
首先,让我们创建一个基本的 Spring Boot 项目结构:
现在,让我们配置线程池:
接下来,创建一个控制器来处理模拟线程占用的请求:
现在,让我们配置 Actuator 并设置健康检查的超时时间:
要使用 AB 测试这个接口,您可以使用以下命令:
ab -n 1000 -c 100 http://localhost:8080/simulate

这将发送 1000 个请求,并发数为 100。
要测试健康检查端点是否超时,您可以使用:
ab -n 100 -c 10 http://localhost:8080/actuator/health
由于我们将健康检查超时设置为 3 秒,而模拟的任务需要 5 秒,因此在高负载下,健康检查可能会超时。
请注意,这个设置可能会导致您的应用程序在高负载下被认为是不健康的。在实际生产环境中,您可能需要调整线程池大小、队列容量和健康检查超时时间以达到最佳平衡。


/d/phpEnv/server/apache/bin/ab

## 2. 生成这个项目的pom.xml

好的,我将为您生成这个Spring Boot项目的pom.xml文件。这个文件将包含所有必要的依赖项,包括Spring Boot Starter、Spring Boot Actuator和其他相关依赖。
以下是pom.xml文件的内容:
这个pom.xml文件包含以下主要部分:
项目元数据: 包括groupId, artifactId, 版本等。
父项目: 使用Spring Boot的父POM来管理依赖版本。
Java版本: 设置为Java 11。
1. 依赖项:
   spring-boot-starter-web: 用于创建web应用程序。
   spring-boot-starter-actuator: 提供生产就绪的特性,如健康检查。
   spring-boot-starter-test: 用于测试。
2. 构建配置: 包含Spring Boot Maven插件,用于构建可执行的jar文件。
   这个pom.xml文件应该放在项目的根目录下。有了这个文件,您就可以使用Maven来构建和运行您的Spring Boot应用程序了。
   如果您需要添加或修改任何依赖项,请告诉我,我会相应地更新pom.xml文件。




## 3. 如何监控不通状态的线程数



要监控不同状态的线程数，我们可以使用 Spring Boot Actuator 和 Micrometer 来创建自定义的度量指标。以下是实现这个功能的步骤：
1. 首先，我们需要在 pom.xml 中添加 Micrometer 依赖：
   ```xml
   <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-prometheus</artifactId>
   </dependency>
   ```
2. 创建一个新的配置类来注册自定义的度量指标：
   
3. 修改 ThreadPoolConfig 类，将 ThreadPoolTaskExecutor 暴露为 Bean：
   在 application.properties 中添加以下配置，以启用 Prometheus 端点：
   这些更改将允许您通过 Actuator 的 Prometheus 端点监控线程池的各种状态。您可以通过访问 http://localhost:8080/actuator/prometheus 来查看这些指标。
   以下是一些您可以监控的指标：
   thread.pool.size: 当前线程池大小
   thread.pool.active.threads: 当前活跃线程数
   thread.pool.queued.tasks: 当前队列中的任务数
   thread.pool.core.threads: 核心线程数
   thread.pool.max.threads: 最大线程数
   您可以使用 Prometheus 或其他支持 Micrometer 的监控工具来收集和可视化这些指标。
   如果您需要更详细的线程状态信息（如 NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED），您可能需要编写更复杂的自定义指标收集器。这通常涉及到使用 Java 的 ThreadMXBean，但请注意，这可能会对性能产生影响，特别是在有大量线程的情况下。


## 4. 安装监控
- [windows安装prometheus+grafana](https://blog.csdn.net/qq_38362419/article/details/108527506)
- [9.prometheus监控--监控springboot2.x(Java)](https://www.cnblogs.com/yangmeichong/p/18156411)   