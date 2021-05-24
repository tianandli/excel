package com.lj.excel.model;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/19 11:12
 * @version: V1.0
 */
@Data
public class ShiJueRequest {
    private long time_used;
    private ShiJueResult result;
    private String request_id;



}
