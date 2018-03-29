package cn.yearcon.yrcocrmapi.modules.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义查询
 *
 * @author ayong
 * @create 2018-03-28 15:02
 **/
@Data
@ApiModel(value = "DefinedSearch")
public class DefinedSearch {
    @ApiModelProperty(value = "生日")
    private String  birthday;//生日
    @ApiModelProperty(value = "消费日期")
    private String  costDate;//消费日期
    @ApiModelProperty(value = "开始处理时间")
    private String beginDate;//开始处理时间
    @ApiModelProperty(value = "结束处理日期")
    private String endDate;//结束处理日期
    @ApiModelProperty(value = "关键字 会员手机,会员姓名")
    private String keyword;//关键字 会员手机,会员姓名
    @ApiModelProperty(value = "排序字段 0.开卡时间 1.消费时间 2.出生时间")
    private int sortWord;//排序字段 0.开卡时间 1.消费时间 2.出生时间
    @ApiModelProperty(value = "排序方式 0.升序 1.降序")
    private int sortModel;//排序方式 0.升序 1.降序
}
