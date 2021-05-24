package com.lj.excel.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class GeZhongFormatUtil {
    public static void main(String[] args) {
//        formatNumber();
//        formatDecimal();
        formatKeXueJiShuFa();
    }
    public static void formatNumber(){
        double myNum = 1220.0455;
        System.out.println(NumberFormat.getInstance().getClass() + "-->" + NumberFormat.getInstance().format(myNum));
        System.out.println(NumberFormat.getCurrencyInstance().getClass() + "-->" + NumberFormat.getCurrencyInstance().format(myNum));//货币格式
        System.out.println(NumberFormat.getIntegerInstance().getClass() + "-->" + NumberFormat.getIntegerInstance().format(myNum));
        System.out.println(NumberFormat.getNumberInstance().getClass() + "-->" + NumberFormat.getNumberInstance().format(myNum));
        System.out.println(NumberFormat.getPercentInstance().getClass() + "-->" + NumberFormat.getPercentInstance().format(myNum));//对数字 进行 百分比 格式化
    }
    public static void formatDecimal(){
        double myNum = 1220.0455;
        System.out.println("===============0的使用===============");
        System.out.println("只保留整数部分：" + new DecimalFormat("0").format(myNum));
        System.out.println("保留3位小数：" + new DecimalFormat("0.000").format(myNum));
        System.out.println("整数部分、小数部分都5位。不够的都用0补位(整数高位部，小数低位补0)：" + new DecimalFormat("00000.00000").format(myNum));

        System.out.println("===============#的使用===============");
        System.out.println("只保留整数部分：" + new DecimalFormat("#").format(myNum));
        System.out.println("保留2为小数并以百分比输出：" + new DecimalFormat("#.##%").format(myNum));

        // 非标准数字（不建议这么用）
        System.out.println("===============非标准数字的使用===============");
        System.out.println(new DecimalFormat("666").format(myNum));
        System.out.println(new DecimalFormat(".6666").format(myNum));
    }
    public static void formatKeXueJiShuFa(){
        double myNum = 1220.0455;
        System.out.println(new DecimalFormat("0E0").format(myNum));
        System.out.println(new DecimalFormat("0E00").format(myNum));
        System.out.println(new DecimalFormat("00000E00000").format(myNum));
        System.out.println(new DecimalFormat("#E0").format(myNum));
        System.out.println(new DecimalFormat("#E00").format(myNum));
        System.out.println(new DecimalFormat("#####E00000").format(myNum));
    }
}
