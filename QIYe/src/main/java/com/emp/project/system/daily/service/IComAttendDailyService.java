package com.emp.project.system.daily.service;

import java.util.List;
import com.emp.project.system.daily.domain.ComAttendDaily;

/**
 * 员工考勤管理Service接口
 * 
 * @author wjn
 * @date 2020-10-24
 */
public interface IComAttendDailyService 
{
    /**
     * 查询员工考勤管理
     * 
     * @param id 员工考勤管理ID
     * @return 员工考勤管理
     */
    public ComAttendDaily selectComAttendDailyById(Long id);

    /**
     * 查询员工考勤管理列表
     * 
     * @param comAttendDaily 员工考勤管理
     * @return 员工考勤管理集合
     */
    public List<ComAttendDaily> selectComAttendDailyList(ComAttendDaily comAttendDaily);

    /**
     * 新增员工考勤管理
     * 
     * @param comAttendDaily 员工考勤管理
     * @return 结果
     */
    public int insertComAttendDaily(ComAttendDaily comAttendDaily);

    /**
     * 修改员工考勤管理
     * 
     * @param comAttendDaily 员工考勤管理
     * @return 结果
     */
    public int updateComAttendDaily(ComAttendDaily comAttendDaily);

    /**
     * 批量删除员工考勤管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComAttendDailyByIds(String ids);

    /**
     * 删除员工考勤管理信息
     * 
     * @param id 员工考勤管理ID
     * @return 结果
     */
    public int deleteComAttendDailyById(Long id);
}
