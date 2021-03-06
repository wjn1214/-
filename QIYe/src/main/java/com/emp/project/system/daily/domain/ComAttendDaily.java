package com.emp.project.system.daily.domain;

import com.emp.framework.aspectj.lang.annotation.Excel;
import com.emp.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 员工考勤信息对象 com_attend_daily
 * 
 * @author wjn
 * @date 2021-03-06
 */
@Data
public class ComAttendDaily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 员工工号 */
    @Excel(name = "员工工号")
    private Long empId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String empName;

    /** 迟到天数 */
    @Excel(name = "迟到天数")
    private Integer daysLate;

    /** 早退天数 */
    @Excel(name = "早退天数")
    private Integer daysLeave;

    /** 缺勤天数 */
    @Excel(name = "缺勤天数")
    private Integer daysAbsent;

    /** 请假天数 */
    @Excel(name = "请假天数")
    private Integer daysOff;


}
