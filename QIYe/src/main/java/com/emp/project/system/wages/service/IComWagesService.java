package com.emp.project.system.wages.service;

import java.util.List;
import com.emp.project.system.wages.domain.ComWages;

/**
 * 员工薪资管理Service接口
 * 
 * @author wjn
 * @date 2020-10-24
 */
public interface IComWagesService 
{
    /**
     * 查询员工薪资管理
     * 
     * @param id 员工薪资管理ID
     * @return 员工薪资管理
     */
    public ComWages selectComWagesById(Long id);

    /**
     * 查询员工薪资管理列表
     * 
     * @param comWages 员工薪资管理
     * @return 员工薪资管理集合
     */
    public List<ComWages> selectComWagesList(ComWages comWages);

    /**
     * 新增员工薪资管理
     * 
     * @param comWages 员工薪资管理
     * @return 结果
     */
    public int insertComWages(ComWages comWages);

    /**
     * 修改员工薪资管理
     * 
     * @param comWages 员工薪资管理
     * @return 结果
     */
    public int updateComWages(ComWages comWages);

    /**
     * 批量删除员工薪资管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteComWagesByIds(String ids);

    /**
     * 删除员工薪资管理信息
     * 
     * @param id 员工薪资管理ID
     * @return 结果
     */
    public int deleteComWagesById(Long id);
}
