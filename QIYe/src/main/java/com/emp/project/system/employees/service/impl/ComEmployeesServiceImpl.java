package com.emp.project.system.employees.service.impl;

import java.util.List;
import com.emp.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.project.system.employees.mapper.ComEmployeesMapper;
import com.emp.project.system.employees.domain.ComEmployees;
import com.emp.project.system.employees.service.IComEmployeesService;
import com.emp.common.utils.text.Convert;

/**
 * 员工信息管理Service业务层处理
 * 
 * @author wjn
 * @date 2020-10-21
 */
@Service
public class ComEmployeesServiceImpl implements IComEmployeesService 
{
    @Autowired
    private ComEmployeesMapper comEmployeesMapper;

    /**
     * 查询员工信息管理
     * 
     * @param id 员工信息管理ID
     * @return 员工信息管理
     */
    @Override
    public ComEmployees selectComEmployeesById(Long id)
    {
        return comEmployeesMapper.selectComEmployeesById(id);
    }

    /**
     * 查询员工信息管理列表
     * 
     * @param comEmployees 员工信息管理
     * @return 员工信息管理
     */
    @Override
    public List<ComEmployees> selectComEmployeesList(ComEmployees comEmployees)
    {
        return comEmployeesMapper.selectComEmployeesList(comEmployees);
    }

    /**
     * 新增员工信息管理
     * 
     * @param comEmployees 员工信息管理
     * @return 结果
     */
    @Override
    public int insertComEmployees(ComEmployees comEmployees)
    {
        comEmployees.setCreateTime(DateUtils.getNowDate());
        return comEmployeesMapper.insertComEmployees(comEmployees);
    }

    /**
     * 修改员工信息管理
     * 
     * @param comEmployees 员工信息管理
     * @return 结果
     */
    @Override
    public int updateComEmployees(ComEmployees comEmployees)
    {
        comEmployees.setUpdateTime(DateUtils.getNowDate());
        return comEmployeesMapper.updateComEmployees(comEmployees);
    }

    /**
     * 删除员工信息管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteComEmployeesByIds(String ids)
    {
        return comEmployeesMapper.deleteComEmployeesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除员工信息管理信息
     * 
     * @param id 员工信息管理ID
     * @return 结果
     */
    @Override
    public int deleteComEmployeesById(Long id)
    {
        return comEmployeesMapper.deleteComEmployeesById(id);
    }
}
