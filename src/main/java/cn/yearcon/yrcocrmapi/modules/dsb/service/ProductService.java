package cn.yearcon.yrcocrmapi.modules.dsb.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.EmpDao;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Product;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.ProductDao;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ayong
 * @create 2018-03-29 14:27
 **/
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmpDao empDao;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public JsonResult fuzzyByProudctNo(String productNo){
        List<Product> list=productDao.findList(productNo);
        return new JsonResult(1,list);
    }

    public JsonResult findByIdAndWebid(Integer productid,String username){
        int webid=empDao.findWebidByUsername(username);
        logger.info("productid="+productid+",username="+username+",webid="+webid);
        List<Product> list2=(List)redisTemplate.opsForValue().get("productList"+productid+webid);
        if(list2!=null){
            return new JsonResult(1,list2);
        }
        List<Product> list = productDao.findListByIdAndWebid(productid, webid);
        if(list.size()==0){
            return new JsonResult(0,"没有库存");
        }
        //缓存10分钟
        redisTemplate.opsForValue().set("productList"+productid+webid,list,60*10, TimeUnit.SECONDS);
        return new JsonResult(1,list);

    }
}
