package com.emp.project.system.daily.service.impl;

import java.util.List;
import com.emp.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.project.system.daily.mapper.ComAttendDailyMapper;
import com.emp.project.system.daily.domain.ComAttendDaily;
import com.emp.project.system.daily.service.IComAttendDailyService;
import com.emp.common.utils.text.Convert;

/**
 * 员工考勤管理Service业务层处理
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Service
public class ComAttendDailyServiceImpl implements IComAttendDailyService 
{
    @Autowired
    private ComAttendDailyMapper comAttendDailyMapper;

    /**
     * 查询员工考勤管理
     * 
     * @param id 员工考勤管理ID
     * @return 员工考勤管理
     */
    @Override
    public ComAttendDaily selectComAttendDailyById(Long id)
    {
        return comAttendDailyMapper.selectComAttendDailyById(id);
    }

    /**
     * 查询员工考勤管理列表
     * 
     * @param comAttendDaily 员工考勤管理
     * @return 员工考勤管理
     */
    @Override
    public List<ComAttendDaily> selectComAttendDailyList(ComAttendDaily comAttendDaily)
    {
        return comAttendDailyMapper.selectComAttendDailyList(comAttendDaily);
    }

    /**
     * 新增员工考勤管理
     * 
     * @param comAttendDaily 员工考勤管理
     * @return 结果
     */
    @Override
    public int insertComAttendDaily(ComAttendDaily comAttendDaily)
    {
        comAttendDaily.setCreateTime(DateUtils.getNowDate());
        return comAttendDailyMapper.insertComAttendDaily(comAttendDaily);
    }

    /**
     * 修改员工考勤管理
     * 
     * @param comAttendDaily 员工考勤管理
     * @return 结果
     */
    @Override
    public int updateComAttendDaily(ComAttendDaily comAttendDaily)
    {
        return comAttendDailyMapper.updateComAttendDaily(comAttendDaily);
    }

    /**
     * 删除员工考勤管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteComAttendDailyByIds(String ids)
    {
        return comAttendDailyMapper.deleteComAttendDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除员工考勤管理信息
     * 
     * @param id 员工考勤管理ID
     * @return 结果
     */
    @Override
    public int deleteComAttendDailyById(Long id)
    {
        return comAttendDailyMapper.deleteComAttendDailyById(id);
    }
}
