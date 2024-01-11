# Quartz
Quartz是一个开源的任务调度框架，用于在Java应用程序中执行定时任务。它提供了灵活的调度机制，允许开发人员按照各种时间间隔和模式安排任务执行。Quartz广泛用于构建定时任务、计划任务和后台作业的应用程序。

# Quartz API的关键接口
1. Scheduler - 与调度程序交互的主要API。
2. Job - 你想要调度器执行的任务组件需要实现的接口
3. JobDetail - 用于定义作业的实例。
4. Trigger（即触发器） - 定义执行给定作业的计划的组件。
5. JobBuilder - 用于定义/构建 JobDetail 实例，用于定义作业的实例。
6. TriggerBuilder - 用于定义/构建触发器实例。
7. Scheduler 的生命期，从 SchedulerFactory 创建它时开始，到 Scheduler 调用shutdown() 方法时结束；Scheduler 被创建后，可以增加、删除和列举 Job 和 Trigger，以及执行其它与调度相关的操作（如暂停 Trigger）。但是，Scheduler 只有在调用 start() 方法后，才会真正地触发 trigger（即执行 job）.

使用Quartz的基本流程通常包括创建Job类，创建Trigger来定义任务执行的时间规则，然后配置Scheduler并将Job和Trigger关联起来。

# 使用指南
1. 下载本系统源码示例;
2. 自定义Job,也可以使用本系统实例Job;
3. 运行QuartzControllerTest.startJob();

## Quartz常用配置
1. 数据源配置：如果你想要将Quartz的调度信息存储在数据库中，你需要配置数据源信息。这是一个基本的quartz.properties中的数据源配置示例：
```yml
org.quartz.dataSource.myDS.driver = com.mysql.jdbc.Driver
org.quartz.dataSource.myDS.URL = jdbc:mysql://localhost:3306/quartz
org.quartz.dataSource.myDS.user = yourUsername
org.quartz.dataSource.myDS.password = yourPassword
org.quartz.dataSource.myDS.maxConnections = 5
```
2. 调度器实例配置：配置调度器实例的相关属性，例如调度器的实例名称、线程池等：
```yml
org.quartz.scheduler.instanceName = MyScheduler
org.quartz.scheduler.instanceId = AUTO
org.quartz.threadPool.threadCount = 10
org.quartz.threadPool.threadPriority = 5
```
3. 作业存储配置：配置作业（Job）的存储方式，Quartz支持内存和数据库两种存储方式。下面是一个使用数据库存储的配置示例：
```yml
org.quartz.jobStore.class = org.quartz.impl.jdbcjobstore.JobStoreTX
org.quartz.jobStore.driverDelegateClass = org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.useProperties = false
org.quartz.jobStore.dataSource = myDS
```
4. 触发器配置：配置触发器的相关属性，如触发器的优先级、错过触发时的处理方式等：
```yml
org.quartz.jobStore.misfireThreshold = 60000
org.quartz.jobStore.isClustered = true
org.quartz.threadPool.makeThreadsDaemons = true
```
5. 全局配置：一些全局的配置项，例如配置是否使用JMX、调度器关闭时是否等待正在执行的作业完成等：
```yml
org.quartz.scheduler.jmx.export = true
org.quartz.scheduler.skipUpdateCheck = true
org.quartz.scheduler.shutdownHook = true
```

## cron 配置示例
```js
0 * * * * ? // 每1分钟整点触发一次
0 0 * * * ? // 每天每1小时整点触发一次
0 0 10 * * ? // 每天10点触发一次
0 * 14 * * ? // 在每天下午2点到下午2:59期间的每1分钟触发
0 30 9 1 * ? // 每月1号上午9点半
0 15 10 15 * ? // 每月15日上午10:15触发
*/5 * * * * ? // 每隔5秒执行一次
0 */1 * * * ? // 每隔1分钟执行一次
0 0 5-15 * * ? // 每天5-15点整点触发
0 0/3 * * * ? // 每三分钟触发一次
0 0-5 14 * * ? // 在每天下午2点到下午2:05期间的每1分钟触发
0 0/5 14 * * ? // 在每天下午2点到下午2:55期间的每5分钟触发
0 0/5 14,18 * * ? // 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
0 0/30 9-17 * * ? // 朝九晚五工作时间内每半小时
0 0 10,14,16 * * ? // 每天上午10点，下午2点，4点
0 0 12 ? * // WED 表示每个星期三中午12点
0 0 17 ? * // TUES,THUR,SAT 每周二、四、六下午五点
0 10,44 14 ? 3 // WED 每年三月的星期三的下午2:10和2:44触发
0 15 10 ? * // MON-FRI 周一至周五的上午10:15触发
0 0 23 L * ? // 每月最后一天23点执行一次
0 15 10 L * ? // 每月最后一日的上午10:15触发
0 15 10 ? * 6L // 每月的最后一个星期五上午10:15触发
0 15 10 * * ? // 2005 2005年的每天上午10:15触发
0 15 10 ? * 6L // 2002-2005 2002年至2005年的每月的最后一个星期五上午10:15触发
0 15 10 ? * 6#3 // 每月的第三个星期五上午10:15触发
```

