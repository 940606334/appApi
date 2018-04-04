package cn.yearcon.yrcocrmapi.modules.service;

import cn.yearcon.yrcocrmapi.common.json.JsonResult;
import cn.yearcon.yrcocrmapi.common.util.DateUtil;
import cn.yearcon.yrcocrmapi.modules.dsa.entity.Usertaskstatus;
import cn.yearcon.yrcocrmapi.modules.dsa.mapper.TaskStatusDao;
import cn.yearcon.yrcocrmapi.modules.dsa.service.TaskStatusService;
import cn.yearcon.yrcocrmapi.modules.dsb.entity.VIPInfo;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.StoreDao;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoDao;
import cn.yearcon.yrcocrmapi.modules.dsb.mapper.VIPInfoMapper;
import cn.yearcon.yrcocrmapi.modules.dsb.service.VIPInfoService;
import cn.yearcon.yrcocrmapi.modules.entity.DefinedSearch;
import cn.yearcon.yrcocrmapi.modules.entity.VIPStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ayong
 * @create 2018-03-27 10:23
 **/
@Service
public class VIPService {
    @Autowired
    private VIPInfoDao vipInfoDao;
    @Autowired
    private VIPInfoMapper vipInfoMapper;
    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private StoreDao storeDao;

    /**
     * 获取会员状态列表
     * @param username
     * @return
     */
    public VIPStatus vipList(String username){
        //获取七天内会员消费记录
        //获取当前时间
        List<VIPInfo> sevenDayList =getListBySevenDayBack(username);
        //创建会员列表状态
        VIPStatus vipStatus=new VIPStatus();

        //获取会员信息列表中的唤醒状态数量
        int waken=getStatusCount(sevenDayList,username);//7天回访已唤醒数
        int[] sevenDayNum={sevenDayList.size()-waken,waken};//7天回访
        vipStatus.setSevenDayNum(sevenDayNum);

        //获取店铺id
        int storeid= storeDao.findByUsername(username).getId();

        //获取沉睡会员列表
        List<VIPInfo> vipStatusList=vipInfoMapper.getVIPByStore(username,storeid);

        List<VIPInfo> opencardNotExpenseList=findListByStatus("5",vipStatusList);//5开卡未消费
        waken=getStatusCount(opencardNotExpenseList,username);//开卡未消费已唤醒数
        int[] opencardNotExpense={opencardNotExpenseList.size()-waken,waken};
        vipStatus.setOpencardNotExpense(opencardNotExpense);

        List<VIPInfo> sleepList=findListByStatus("4",vipStatusList);//4睡眠
        waken=getStatusCount(sleepList,username);//睡眠已唤醒数
        int[] sleep={sleepList.size()-waken,waken};
        vipStatus.setSleep(sleep);

        List<VIPInfo> deepSleepList=findListByStatus("3",vipStatusList);//修眠
        waken=getStatusCount(deepSleepList,username);//修眠已唤醒数
        int[] deepSleep={deepSleepList.size()-waken,waken};
        vipStatus.setDeepSleep(deepSleep);

        List<VIPInfo> runAwayList=findListByStatus("2",vipStatusList);//流失
        waken=getStatusCount(runAwayList,username);//流失已唤醒数
        int[] runAway={runAwayList.size()-waken,waken};
        vipStatus.setRunAway(runAway);

        List<VIPInfo> activateList=findListByStatus("1",vipStatusList);//激活
        waken=getStatusCount(activateList,username);//激活已唤醒数
        int[] activate={activateList.size()-waken,waken};
        vipStatus.setActivate(activate);


        List<VIPInfo> vipBirthdayList=vipStatusList.stream() //生日关怀
                .filter(vipInfo -> DateUtil.isbirthday(vipInfo.getBirthday()))
                .collect(Collectors.toList());
        waken=getStatusCount(vipBirthdayList,username);
        int[] vipBirthday={vipBirthdayList.size()-waken,waken};
        vipStatus.setVipBirthday(vipBirthday);

        return vipStatus;

    }

    /**
     * 高级搜索
     * @return
     */
    public JsonResult findVipListBySearch(String username, Integer storeid, DefinedSearch definedSearch){
        //获取沉睡会员列表
        List<VIPInfo> vipStatusList=vipInfoMapper.getVIPByStore(username,storeid);
        getStatusCount(vipStatusList,username);//获取处理时间,次数
        if (definedSearch==null){
            return new JsonResult(1,vipStatusList);
        }
        String birthday=definedSearch.getBirthday();
        if (birthday!=null&&!"".equals(birthday)){
            vipStatusList=vipStatusList.stream()
                    .filter(vipInfo -> birthday.equals(vipInfo.getBirthday().substring(4)))
                    .collect(Collectors.toList());
        }
        String costDate=definedSearch.getCostDate();
        if(costDate!=null&&!"".equals(costDate)){
            vipStatusList=vipStatusList.stream()
                    .filter(vipInfo -> costDate.equals(vipInfo.getLastdate()))
                    .collect(Collectors.toList());
        }
        String beginDate=definedSearch.getBeginDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        if (beginDate!=null&&beginDate!=""){
            try {
                Date begin=sdf.parse(beginDate);
                vipStatusList=vipStatusList.stream()
                        .filter(vipInfo -> vipInfo.getDealDate().getTime()>=
                        begin.getTime())
                        .collect(Collectors.toList());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String endDate=definedSearch.getEndDate();
        if (endDate!=null&&endDate!=""){
            try {
                Date end=sdf.parse(endDate);
                vipStatusList=vipStatusList.stream()
                        .filter(vipInfo -> vipInfo.getDealDate().getTime()<=
                                end.getTime())
                        .collect(Collectors.toList());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int sortWord=definedSearch.getSortWord();
        switch (sortWord){
            case 0://开卡时间进行排序
                vipStatusList.sort(new Comparator<VIPInfo>() {
                    @Override
                    public int compare(VIPInfo o1, VIPInfo o2) {
                        return o1.getId()-o2.getId();
                    }
                });
                break;
            case 1://消费时间进行排序
                vipStatusList.sort(new Comparator<VIPInfo>() {
                    @Override
                    public int compare(VIPInfo o1, VIPInfo o2) {
                        if(o1.getLastdate()!=null &&o2.getLastdate()!=null){
                            return Integer.parseInt(o1.getLastdate())-
                                    Integer.parseInt(o2.getLastdate());
                        }
                        if(o1.getLastdate()==null&&o2.getLastdate()==null){
                            return o1.getId()-o2.getId();
                        }else if(o1.getLastdate()==null&&o2.getLastdate()!=null){
                            return -1;
                        }else{
                            return 1;
                        }
                    }
                });
                break;
            case 2: //生日进行排序
                vipStatusList.sort(new Comparator<VIPInfo>() {
                    @Override
                    public int compare(VIPInfo o1, VIPInfo o2) {
                        if(o1.getBirthday()!=null&&o2.getBirthday()!=null){
                            return Integer.parseInt(o1.getBirthday())-
                                    Integer.parseInt(o2.getBirthday());
                        }
                        if(o1.getBirthday()==null &&o2.getBirthday()==null){
                            return o1.getId()-o2.getId();
                        }else if(o1.getBirthday()==null &&o2.getBirthday()!=null){
                            return -1;
                        }else{
                            return 1;
                        }


                    }
                });
                break;
                default:break;
        }
        if(definedSearch.getSortModel()==1){ //0升序,1降序
            Collections.reverse(vipStatusList);   //反转整个数组
        }
        String keyword=definedSearch.getKeyword();//关键字,名称或者手机号
        if(keyword!=null&&keyword!=""){
            if(isInteger(keyword)){//如果是电话号码
                vipStatusList=vipStatusList.stream()
                        .filter(vipInfo ->
                                vipInfo.getMobile()==null?false:
                                vipInfo.getMobile().contains(keyword)
                                ||keyword.equals(vipInfo.getMobile()))
                        .collect(Collectors.toList());
            }else{
                vipStatusList=vipStatusList.stream()
                        .filter(vipInfo ->
                                vipInfo.getVipname()==null?false:
                                vipInfo.getVipname().contains(keyword)
                                ||keyword.equals(vipInfo.getVipname()))
                        .collect(Collectors.toList());
            }
        }
        return new JsonResult(1,vipStatusList);
    }

    /**
     * 判断是否为整数
     * @param str
     * @return
     */
    public boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    /**
     * vip会员是否唤醒天7天返回列表
     * @param username
     * @return
     */
    public JsonResult wakenSevenDaybackList(String username){
        List<VIPInfo> sevenDayList=getListBySevenDayBack(username);
        getStatusCount(sevenDayList,username);
        return new JsonResult(1,sevenDayList);
    }

    /**
     * 根据会员状态返回会员列表
     * @param username
     * @param vipStatus 1.激活 2.流失 3.修眠 4.睡眠 5.开卡未消费
     * @return
     */
    public JsonResult wakenVipStatusList(String username,String vipStatus){
        //获取店铺id
        int storeid= storeDao.findByUsername(username).getId();
        //获取沉睡会员列表
        List<VIPInfo> vipStatusList=vipInfoMapper.getVIPByStore(username,storeid);
        //判断是否为会员状态6 生日关怀
        if("6".equals(vipStatus)){
            List<VIPInfo> vipBirthdayList=vipStatusList.stream() //生日关怀
                    .filter(vipInfo -> DateUtil.isbirthday(vipInfo.getBirthday()))
                    .collect(Collectors.toList());
            getStatusCount(vipBirthdayList,username);
            return new JsonResult(1,vipBirthdayList);
        }
        if("7".equals(vipStatus)){//判断会员状态是否为7 7天返回
            List<VIPInfo> sevenDayList=getListBySevenDayBack(username);
            getStatusCount(sevenDayList,username);
            return new JsonResult(1,sevenDayList);
        }
        List<VIPInfo> wakenVipStatusList=findListByStatus(vipStatus,vipStatusList);//5开卡未消费
        getStatusCount(wakenVipStatusList,username);
        return new JsonResult(1,wakenVipStatusList);
    }

    /**
     * 获取生日关怀列表
     * @param username
     * @return
     */
    public JsonResult wakenBirthdayList(String username){
        //获取店铺id
        int storeid= storeDao.findByUsername(username).getId();
        //获取沉睡会员列表
        List<VIPInfo> vipStatusList=vipInfoMapper.getVIPByStore(username,storeid);
        List<VIPInfo> vipBirthdayList=vipStatusList.stream() //生日关怀
                .filter(vipInfo -> DateUtil.isbirthday(vipInfo.getBirthday()))
                .collect(Collectors.toList());
        getStatusCount(vipBirthdayList,username);
        return new JsonResult(1,vipBirthdayList);
    }

    /**
     * 获取七天回访列表
     * @return
     */
    public List<VIPInfo> getListBySevenDayBack(String username){
        //获取七天内会员消费记录
        //获取当前时间
        Calendar calendar=Calendar.getInstance();
        Date date=new Date();//获取系统当前时间
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,-7);
        Date last=calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMdd");
        String nowDate=sdf.format(date);
        String lastDate=sdf.format(last);
        List<VIPInfo> sevenDayList = vipInfoDao.findByUsernameAndDate(username, nowDate, lastDate);
        return sevenDayList;
    }


    /**
     * 根据不同的状态获取会员列表
     * @return
     */
    public List<VIPInfo> findListByStatus(String status,List<VIPInfo> list){
        return  list.stream()
                .filter(vipInfo -> status.equals(vipInfo.getVip_status()))
                .collect(Collectors.toList());
    }

    /**
     * 获取列表中的处理数
     * @return
     */
    public int getStatusCount(List<VIPInfo> list,String username){
        int waken=0;
        for(VIPInfo vipInfo:list){
            int row=taskStatusService.save(username,vipInfo);
            vipInfo.setWaken_status(row);
            waken+=row;
        }
        return waken;
    }
}
