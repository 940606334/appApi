### yrcApp接口


0.需要把本项目使用maven 打成一个jar包。

    mvn clean package -Dmaven.test.skip=true

1.接口清单

    1、获取验证码
    2、注册
    3、登陆
    4、重置密码
    5、修改密码
    6、设置日工资
    7、获取当前用户业绩
    8、获取开卡指标
    9、获取用户当前业绩
    10、获取 业务员工作统计
    11、获取 业务员工作列表
    12、更新会员处理状态
    13、获取短信 模板
    14、发送短信 同时更新状态为已处理 
    15、通话记录登记 同时更新状态为已处理 
    16、查看会员详情 
    17、查看会员最近3个月消费 
    18、高级搜索会员列表 
    19、货号模糊匹配 
    
2.使用 Swagger2 构建 API文档,
    
    文档地址: http://localhost:8085/swagger-ui.html
    
3.配置多个数据源分别为mysql,oracle

    ##mysql库
    spring.db1.datasource.jdbcUrl=jdbc:mysql://xxx.xxx.xx.xxx:3306/XXXX?characterEncoding=UTF-8
    spring.db1.datasource.username=xxxx
    spring.db1.datasource.password=xxxx
    spring.db1.datasource.driver-class-name=com.mysql.jdbc.Driver
    
    ##oracle库
    spring.db2.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
    spring.db2.datasource.jdbcUrl=jdbc:oracle:thin:@//xxx.xxx.xx.xxx:1521/xxx
    spring.db2.datasource.username=xxxx
    spring.db2.datasource.password=xxxx
