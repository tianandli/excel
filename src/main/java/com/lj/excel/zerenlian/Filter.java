package com.lj.excel.zerenlian;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/7 17:28
 * @version: V1.0
 */
public interface Filter {
    void execute(Alarm alarm, FilterChain chain);
}
