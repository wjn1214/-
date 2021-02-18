package com.emp.project.system.daily.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.emp.framework.aspectj.lang.annotation.Excel;
import com.emp.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 员工考勤管理对象 com_attend_daily
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Data
public class ComAttendDaily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 员工id */
    @Excel(name = "员工id")
    private Long empId;

    /** 是否迟到 1正常 2迟到 */
    @Excel(name = "是否迟到 1正常 2迟到")
    private String isLate;

    /** 迟到原因 */
    @Excel(name = "迟到原因")
    private String lateReason;

    /** 签到时间 */
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

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
