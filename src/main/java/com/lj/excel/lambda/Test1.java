package com.lj.excel.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/20 10:22
 * @version: V1.0
 */
public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add(null);
        list.add("3");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("==========");
        /**
         * 满足条件的会放到新集合中（集合的过滤）
         */
        List<String> newList = list.stream().filter(string -> string != null).collect(Collectors.toList());
        for (int i = 0; i < newList.size(); i++) {
            System.out.println(newList.get(i));
        }

    }
}
