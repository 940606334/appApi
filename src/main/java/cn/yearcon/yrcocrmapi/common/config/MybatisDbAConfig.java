package cn.yearcon.yrcocrmapi.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @author ayong
 * @create 2018-03-18 16:00
 **/
@Configuration
@MapperScan(basePackages = {"cn.yearcon.yrcocrmapi.modules.dsa.mapper"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class MybatisDbAConfig {
   /* @Autowired
    @Qualifier("ds1")
    private DataSource ds1;*/

    @Bean(name = "ds1")
    @ConfigurationProperties(prefix = "spring.db1.datasource")
    @Primary
    public DataSource DataSource1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name="sqlSessionFactory1")
    @Primary
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("ds1") DataSource ds1) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds1); // 使用mysql数据源, 连接mysql库
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/dsa/*.xml"));
        factoryBean.setConfigLocation(
                new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
        return factoryBean.getObject();

    }
    @Bean(name = "ds1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("ds1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1")SqlSessionFactory sqlSessionFactory) throws Exception {
        //SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
