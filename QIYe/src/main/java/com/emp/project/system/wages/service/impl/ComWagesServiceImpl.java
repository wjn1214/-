package com.emp.project.system.wages.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emp.common.utils.DateUtils;
import com.emp.common.utils.StringUtils;
import com.emp.framework.web.domain.AjaxResult;
import com.emp.project.system.daily.domain.ComAttendDaily;
import com.emp.project.system.daily.mapper.ComAttendDailyMapper;
import com.emp.project.system.daily.service.impl.ComAttendDailyServiceImpl;
import com.emp.project.system.employees.domain.ComEmployees;
import com.emp.project.system.employees.service.impl.ComEmployeesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emp.project.system.wages.mapper.ComWagesMapper;
import com.emp.project.system.wages.domain.ComWages;
import com.emp.project.system.wages.service.IComWagesService;
import com.emp.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工薪资管理Service业务层处理
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Service
public class ComWagesServiceImpl extends ServiceImpl<ComWagesMapper, ComWages> implements IComWagesService
{
    @Autowired
    private ComWagesMapper comWagesMapper;

    @Autowired
    private ComEmployeesServiceImpl comEmployeesService;

    @Autowired
    private ComAttendDailyMapper dailyMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addWages() {
        List<ComEmployees> comEmployees = comEmployeesService.selectComEmployeesList(new ComEmployees());
        if (StringUtils.isNull(comEmployees)  || comEmployees.size() == 0)
            return AjaxResult.error("当前无可生成的员工信息");
        for (ComEmployees employees : comEmployees){
            Long empId = employees.getId();
            String wage = employees.getWage();
            ComAttendDaily daily=dailyMapper.selectDailyByTime(empId);
            if (StringUtils.isNull(daily)){
                continue;
            }
            //迟到一天扣除50
            int daysLate = daily.getDaysLate() * 50;
            //早退一天扣除50
            int daysLeave = daily.getDaysLeave() * 50;
            //获取当前月份天数信息
            int day = DateUtils.getCurrentMonthDay();

            //缺勤一天扣除当前工资除月份天数*3
            int daysAbsent=daily.getDaysAbsent() * (Integer.parseInt(wage)/day) *3;
            //请假一天扣除当前工资除月份天数*1
            int daysOff=daily.getDaysOff()* (day/Integer.parseInt(wage)) *1;
            //实发金额
            String realPay = String.valueOf(Integer.parseInt(wage)-(daysLate + daysLeave + daysAbsent + daysOff));
            //整合数据
            ComWages wages=new ComWages();
            wages.setEmpId(empId);
            wages.setShouldPay(wage);
            wages.setRealWages(realPay);
            int count=comWagesMapper.selectDailyByTime(empId);
            if (count==0){
                this.insertComWages(wages);
            }else if (count==1){
                ComWages comWages=comWagesMapper.selecWagesById(empId);
                wages.setId(comWages.getId());
                this.updateComWages(wages);
            }
        }
        return AjaxResult.success("成功");
    }
}
