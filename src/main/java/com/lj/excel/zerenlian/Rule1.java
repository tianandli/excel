package com.lj.excel.zerenlian;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/7 17:29
 * @version: V1.0
 */
public class Rule1 implements Filter {
    @Override
    public void execute(Alarm alarm, FilterChain filterChain) {
        //规则内容：如果是政府发生告警。告警等级设为最高
        if (alarm.getAlarmAddress().contains("政府")) {
            alarm.setAlarmLevel(4);
            System.out.println("执行规则1");
        }

        //注意回调FilterChain的doFilter方法，让FilterChain继续执行下一个Filter
        filterChain.doFilter(alarm, filterChain);
    }
}
