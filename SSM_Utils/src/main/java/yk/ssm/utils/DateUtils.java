package yk.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换字符串
    public static String dateToStirng(Date date,String patt){
        SimpleDateFormat sdf =new SimpleDateFormat(patt);
        return sdf.format(date);
    }

    //字符串转日期
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf =new SimpleDateFormat(patt);
        return sdf.parse(str);
    }
}
