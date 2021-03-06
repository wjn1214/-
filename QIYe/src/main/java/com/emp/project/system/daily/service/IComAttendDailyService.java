package com.emp.project.system.daily.service;

import java.util.List;
import com.emp.project.system.daily.domain.ComAttendDaily;

/**
 * 员工考勤信息Service接口
 * 
 * @author wjn
 * @date 2021-03-06
 */
public interface IComAttendDailyService 
{
    /**
     * 查询员工考勤信息
     * 
     * @param id 员工考勤信息ID
     * @return 员工考勤信息
     */
    public ComAttendDaily selectComAttendDailyById(Long id);

    /**
     * 查询员工考勤信息列表
     * 
     * @param comAttendDaily 员工考勤信息
     * @return 员工考勤信息集合
     */
    public List<ComAttendDaily> selectComAttendDailyList(ComAttendDaily comAttendDaily);

    /**
     * 新增员工考勤信息
     * 
     * @param comAttendDaily 员工考勤信息
     * @return 结果
     */
    public int insertComAttendDaily(ComAttendDaily comAttendDaily);

    /**
     * 修改员工考勤信息
     * 
     * @param comAttendDaily 员工考勤信息
     * @return 结果
     */
    public int updateComAttendDaily(ComAttendDaily comAttendDaily);

    /**
     * 批量删除员工考勤信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComAttendDailyByIds(String ids);

    /**
     * 删除员工考勤信息信息
     * 
     * @param id 员工考勤信息ID
     * @return 结果
     */
    public int deleteComAttendDailyById(Long id);

    String importUser(List<ComAttendDaily> userList, boolean updateSupport);
}
