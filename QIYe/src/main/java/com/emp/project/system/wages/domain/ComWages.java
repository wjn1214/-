package com.emp.project.system.wages.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.emp.framework.aspectj.lang.annotation.Excel;
import com.emp.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 员工薪资管理对象 com_wages
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Data
public class ComWages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 员工id */
    @Excel(name = "员工id")
    private Long empId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 应发金额 */
    @Excel(name = "应发金额")
    private String shouldPay;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

    /**
     * 员工姓名
     */
    @TableField(exist = false)
    private String name;
}
