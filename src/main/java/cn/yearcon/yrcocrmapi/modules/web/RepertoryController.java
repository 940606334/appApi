package cn.yearcon.yrcocrmapi.modules.web;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.modules.dsb.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import sun.awt.SunHints;

import java.util.Calendar;

/**
 * @author ayong
 * @create 2018-03-29 14:23
 **/
@Api(description = "库存查询(货号模糊查询,同城库存)")
@RestController
@RequestMapping(value="repertory")
public class RepertoryController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "fuzzy.no",method = {RequestMethod.GET})
    @ApiOperation(value = "货号模糊查询",notes = "货号模糊查询")
    @ApiImplicitParam(value = "产品货号",name ="productNo",dataType = "string",paramType = "query")
    public JsonResult fuzzyByNo(String productNo){
        return productService.fuzzyByProudctNo(productNo);
    }
    @RequestMapping(value = "product.webid",method = {RequestMethod.GET})
    @ApiOperation(value = "获取同城库存",notes = "获取同城库存")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "产品id",name ="productid",dataType = "int",paramType = "query",required = true),
            @ApiImplicitParam(value = "员工编号",name="username",dataType = "string",paramType = "query",required = true)
    })
    public JsonResult findByIdAndWebid(Integer productid,String username){
        return productService.findByIdAndWebid(productid,username);
    }
}
