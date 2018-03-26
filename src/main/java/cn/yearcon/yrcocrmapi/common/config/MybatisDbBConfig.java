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
@MapperScan(basePackages = {"cn.yearcon.yrcocrmapi.modules.dsb.mapper"}, sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MybatisDbBConfig {
   /* @Autowired
    @Qualifier("ds2")
    private DataSource ds2;*/



    @Bean(name = "ds2")
    @ConfigurationProperties(prefix = "spring.db2.datasource")
    @Primary
    public DataSource DataSource2() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name="sqlSessionFactory2")
    @Primary
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("ds2") DataSource ds2) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2); // 使用oracle数据源, 连接oracle库
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/dsb/*.xml"));
        factoryBean.setConfigLocation(
                new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
        return factoryBean.getObject();

    }
    @Bean(name = "ds2TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("ds2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "sqlSessionTemplate2")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2")SqlSessionFactory sqlSessionFactory) throws Exception {
        //SqlSessionTemplate template = new SqlSessionTemplate(); // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
