package com.lj.excel.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/20 16:31
 * @version: V1.0
 */
@Data
public class AllPermission  implements Serializable {

    private String module;
    private String moduleCN;
    private String[] permission;
    private String[] permissionCN;

}
