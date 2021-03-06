package com.emp.project.system.daily.service.impl;

import java.util.List;

import com.emp.common.exception.BusinessException;
import com.emp.common.utils.DateUtils;
import com.emp.common.utils.StringUtils;
import com.emp.common.utils.security.ShiroUtils;
import com.emp.project.system.user.domain.User;
import com.emp.project.system.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.project.system.daily.mapper.ComAttendDailyMapper;
import com.emp.project.system.daily.domain.ComAttendDaily;
import com.emp.project.system.daily.service.IComAttendDailyService;
import com.emp.common.utils.text.Convert;

/**
 * 员工考勤信息Service业务层处理
 * 
 * @author wjn
 * @date 2021-03-06
 */
@Service
public class ComAttendDailyServiceImpl implements IComAttendDailyService 
{
    @Autowired
    private ComAttendDailyMapper comAttendDailyMapper;

    private static final Logger log = LoggerFactory.getLogger(ComAttendDailyServiceImpl.class);

    /**
     * 查询员工考勤信息
     * 
     * @param id 员工考勤信息ID
     * @return 员工考勤信息
     */
    @Override
    public ComAttendDaily selectComAttendDailyById(Long id)
    {
        return comAttendDailyMapper.selectComAttendDailyById(id);
    }

    /**
     * 查询员工考勤信息列表
     * 
     * @param comAttendDaily 员工考勤信息
     * @return 员工考勤信息
     */
    @Override
    public List<ComAttendDaily> selectComAttendDailyList(ComAttendDaily comAttendDaily)
    {
        return comAttendDailyMapper.selectComAttendDailyList(comAttendDaily);
    }

    /**
     * 新增员工考勤信息
     * 
     * @param comAttendDaily 员工考勤信息
     * @return 结果
     */
    @Override
    public int insertComAttendDaily(ComAttendDaily comAttendDaily)
    {
        comAttendDaily.setCreateTime(DateUtils.getNowDate());
        return comAttendDailyMapper.insertComAttendDaily(comAttendDaily);
    }

    /**
     * 修改员工考勤信息
     * 
     * @param comAttendDaily 员工考勤信息
     * @return 结果
     */
    @Override
    public int updateComAttendDaily(ComAttendDaily comAttendDaily)
    {
        return comAttendDailyMapper.updateComAttendDaily(comAttendDaily);
    }

    /**
     * 删除员工考勤信息对象
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
     * 删除员工考勤信息信息
     * 
     * @param id 员工考勤信息ID
     * @return 结果
     */
    @Override
    public int deleteComAttendDailyById(Long id)
    {
        return comAttendDailyMapper.deleteComAttendDailyById(id);
    }

    @Override
    public String importUser(List<ComAttendDaily> userList, boolean updateSupport)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String operName = ShiroUtils.getLoginName();
        //String password = configService.selectConfigByKey("sys.user.initPassword");
        for (ComAttendDaily user : userList)
        {
            try
            {
                // 验证是否存在这个这条信息
                ComAttendDaily u = comAttendDailyMapper.selectDailyByTime(user.getEmpId());
                if (StringUtils.isNull(u))
                {
                    user.setCreateTime(DateUtils.getNowDate());
                    //user.setCreateBy(operName);
                    this.insertComAttendDaily(user);
                    successNum++;
                    successMsg.append("<br/>"  + " 导入成功");
                }
                else if (updateSupport)
                {
                    //user.setUpdateBy(operName);
                    user.setId(u.getId());
                    user.setCreateTime(DateUtils.getNowDate());
                    this.updateComAttendDaily(user);
                    successNum++;
                    successMsg.append("<br/>"+ " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + " 数据已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
