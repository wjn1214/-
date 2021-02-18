package com.emp.project.system.wages.service.impl;

import java.util.List;
import com.emp.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.project.system.wages.mapper.ComWagesMapper;
import com.emp.project.system.wages.domain.ComWages;
import com.emp.project.system.wages.service.IComWagesService;
import com.emp.common.utils.text.Convert;

/**
 * 员工薪资管理Service业务层处理
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Service
public class ComWagesServiceImpl implements IComWagesService 
{
    @Autowired
    private ComWagesMapper comWagesMapper;

    /**
     * 查询员工薪资管理
     * 
     * @param id 员工薪资管理ID
     * @return 员工薪资管理
     */
    @Override
    public ComWages selectComWagesById(Long id)
    {
        return comWagesMapper.selectComWagesById(id);
    }

    /**
     * 查询员工薪资管理列表
     * 
     * @param comWages 员工薪资管理
     * @return 员工薪资管理
     */
    @Override
    public List<ComWages> selectComWagesList(ComWages comWages)
    {
        return comWagesMapper.selectComWagesList(comWages);
    }

    /**
     * 新增员工薪资管理
     * 
     * @param comWages 员工薪资管理
     * @return 结果
     */
    @Override
    public int insertComWages(ComWages comWages)
    {
        comWages.setCreateTime(DateUtils.getNowDate());
        return comWagesMapper.insertComWages(comWages);
    }

    /**
     * 修改员工薪资管理
     * 
     * @param comWages 员工薪资管理
     * @return 结果
     */
    @Override
    public int updateComWages(ComWages comWages)
    {
        comWages.setUpdateTime(DateUtils.getNowDate());
        return comWagesMapper.updateComWages(comWages);
    }

    /**
     * 删除员工薪资管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteComWagesByIds(String ids)
    {
        return comWagesMapper.deleteComWagesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除员工薪资管理信息
     * 
     * @param id 员工薪资管理ID
     * @return 结果
     */
    @Override
    public int deleteComWagesById(Long id)
    {
        return comWagesMapper.deleteComWagesById(id);
    }
}
