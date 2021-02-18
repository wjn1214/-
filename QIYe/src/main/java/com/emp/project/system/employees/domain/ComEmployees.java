package com.emp.project.system.employees.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.emp.framework.aspectj.lang.annotation.Excel;
import com.emp.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 员工信息管理对象 com_employees
 * 
 * @author wjn
 * @date 2020-10-21
 */
@Data
public class ComEmployees extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工id */
    private Long id;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String name;

    /** 出生年月 */
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    /** 性别 1男 2女 */
    @Excel(name = "性别 1男 2女")
    private String gender;

    /** 电话 */
    @Excel(name = "电话")
    private String mobile;

    /** 住址 */
    @Excel(name = "住址")
    private String address;

    /** 入职日期 */
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 是否删除 1存在 0删除 */
    @Excel(name = "是否删除 1存在 0删除")
    private String isDel;

    @TableField(exist = false)
    private String deptName;


}
