package cn.yearcon.yrcocrmapi.dao;

import cn.yearcon.yrcocrmapi.modules.dsa.entity.AppCallog;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.Salary;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.SalaryItem;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.CallogDao;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.SalaryDao;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.SalaryitemDao;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.CardTarget;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Performance;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Product;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Store;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.PerformanceDao;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.ProductDao;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.StoreDao;
import io.swagger.annotations.ApiOperation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

/**
 * @author ayong
 * @create 2018-03-26 15:41
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {
    @Autowired
    private SalaryDao salaryDao;

    @Test
    public void test1(){
        Salary byUsername = salaryDao.findByUsername("53159201");
        System.out.println(byUsername);
    }
    @Autowired
    private SalaryitemDao salaryitemDao;
    @Test
    public void test2(){
        List<SalaryItem> list=salaryitemDao.findByWebid("2332");
        System.out.println(list.size());
    }
    @Autowired
    private PerformanceDao performanceDao;
    @Test
    public void test3(){
        /*List<Performance> performance = performanceDao.getPerformance("20180101", "20180327", "888888");
        System.out.println(performance);*/
        List<CardTarget> cardTarget = performanceDao.getCardTarget("888888", 0);
        System.out.println(cardTarget);
    }
    @Autowired
    StoreDao storeDao;
    @Test
    public  void test4(){
        Store byUsername = storeDao.findByUsername("888888");
        System.out.println(byUsername);
    }
    @Autowired
    ProductDao productDao;
    @Test
    public void test5(){
        Product entity=new Product();
        //entity.setProductNo("G");
        List<Product> list = productDao.findList("");
        for (Product product:list){
            System.out.println(product);
        }
    }
    @Test
     public void test6(){
        List<Product> list =productDao.findListByIdAndWebid(750,9582);
        for (Product product:list){
            System.out.println(product);
        }
     }
     @Autowired
     private CallogDao callogDao;
     @Test
    public void test7(){
         AppCallog appCallog=new AppCallog();
         appCallog.setUsername("888888");
         appCallog.setMobile("13058720637");
         appCallog.setCreateDate(new Date());
         appCallog.setCallTimes(120);
         appCallog.setCallType("测试");
         appCallog.setUpdateDate(new Date());
         appCallog.setWebid("456");
        int row=callogDao.insert(appCallog);
         Assert.assertEquals(row,1);
    }
}
