package com.lj.excel.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/4/20 16:33
 * @version: V1.0
 */
@Data
public class SysPermission implements Serializable {
    private Integer roleId;
    private Integer adminId;
    private String moduleCode;
    private String[] permissions;

    private List<String> permissionsList;

    private String roleIds;

}
