package cn.yearcon.yrcocrmapi.common.util;



/**
 * 随机生成数字
 *
 * @author ayong
 * @create 2018-03-22 9:12
 **/
public class GetRandomUtil {

    public static String getRandomStr(int num){
        String str="";
        int random;
        for(int i=0;i<num;i++){
            random=(int)(Math.random()*10);
            str+=random;
        }
        return str;
    }

    public static void main(String[] args){
        System.out.println(getRandomStr(6));
    }
}
