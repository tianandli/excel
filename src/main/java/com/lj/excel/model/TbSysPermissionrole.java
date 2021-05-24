package com.lj.excel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * tb_sys_permissionrole
 *
 * @author
 */
@Data
public class TbSysPermissionrole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String modulecode;

    private String permissioncode;

    private Boolean isdelete;

    private Integer roleid;

    @TableField(exist = false)
    private String[] permissionsList;
    private static final long serialVersionUID = 1L;
}