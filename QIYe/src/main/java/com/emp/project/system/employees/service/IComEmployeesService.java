package com.emp.project.system.employees.service;

import java.util.List;
import com.emp.project.system.employees.domain.ComEmployees;

/**
 * 员工信息管理Service接口
 * 
 * @author wjn
 * @date 2020-10-21
 */
public interface IComEmployeesService 
{
    /**
     * 查询员工信息管理
     * 
     * @param id 员工信息管理ID
     * @return 员工信息管理
     */
    public ComEmployees selectComEmployeesById(Long id);

    /**
     * 查询员工信息管理列表
     * 
     * @param comEmployees 员工信息管理
     * @return 员工信息管理集合
     */
    public List<ComEmployees> selectComEmployeesList(ComEmployees comEmployees);

    /**
     * 新增员工信息管理
     * 
     * @param comEmployees 员工信息管理
     * @return 结果
     */
    public int insertComEmployees(ComEmployees comEmployees);

    /**
     * 修改员工信息管理
     * 
     * @param comEmployees 员工信息管理
     * @return 结果
     */
    public int updateComEmployees(ComEmployees comEmployees);

    /**
     * 批量删除员工信息管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComEmployeesByIds(String ids);

    /**
     * 删除员工信息管理信息
     * 
     * @param id 员工信息管理ID
     * @return 结果
     */
    public int deleteComEmployeesById(Long id);
}
