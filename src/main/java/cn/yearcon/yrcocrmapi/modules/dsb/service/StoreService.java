package cn.yearcon.yrcocrmapi.modules.dsb.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.Store;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-28 14:07
 **/
@Service
public class StoreService {
    @Autowired
    private StoreDao storeDao;

    public JsonResult findStoreByUsername(String username){
        Store byUsername = storeDao.findByUsername(username);
        return new JsonResult(1,byUsername);
    }
}
