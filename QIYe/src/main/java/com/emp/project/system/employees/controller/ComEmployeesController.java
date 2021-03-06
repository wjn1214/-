package com.emp.project.system.employees.controller;

import java.util.List;

import com.emp.project.system.post.service.IPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.emp.framework.aspectj.lang.annotation.Log;
import com.emp.framework.aspectj.lang.enums.BusinessType;
import com.emp.project.system.employees.domain.ComEmployees;
import com.emp.project.system.employees.service.IComEmployeesService;
import com.emp.framework.web.controller.BaseController;
import com.emp.framework.web.domain.AjaxResult;
import com.emp.common.utils.poi.ExcelUtil;
import com.emp.framework.web.page.TableDataInfo;

/**
 * 员工信息管理Controller
 * 
 * @author wjn
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/system/employees")
public class ComEmployeesController extends BaseController
{
    private String prefix = "system/employees";

    @Autowired
    private IComEmployeesService comEmployeesService;
    @Autowired
    private IPostService postService;


    @RequiresPermissions("system:employees:view")
    @GetMapping()
    public String employees()
    {
        return prefix + "/employees";
    }

    /**
     * 查询员工信息管理列表
     */
    @RequiresPermissions("system:employees:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ComEmployees comEmployees)
    {
        startPage();
        List<ComEmployees> list = comEmployeesService.selectComEmployeesList(comEmployees);
        return getDataTable(list);
    }

    /**
     * 导出员工信息管理列表
     */
    @RequiresPermissions("system:employees:export")
    @Log(title = "员工信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ComEmployees comEmployees)
    {
        List<ComEmployees> list = comEmployeesService.selectComEmployeesList(comEmployees);
        ExcelUtil<ComEmployees> util = new ExcelUtil<ComEmployees>(ComEmployees.class);
        return util.exportExcel(list, "employees");
    }

    /**
     * 新增员工信息管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存员工信息管理
     */
    @RequiresPermissions("system:employees:add")
    @Log(title = "员工信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ComEmployees comEmployees)
    {

        return toAjax(comEmployeesService.insertComEmployees(comEmployees));
    }

    /**
     * 修改员工信息管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ComEmployees comEmployees = comEmployeesService.selectComEmployeesById(id);
        mmap.put("comEmployees", comEmployees);
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/edit";
    }

    /**
     * 修改保存员工信息管理
     */
    @RequiresPermissions("system:employees:edit")
    @Log(title = "员工信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ComEmployees comEmployees)
    {
        return toAjax(comEmployeesService.updateComEmployees(comEmployees));
    }

    /**
     * 删除员工信息管理
     */
    @RequiresPermissions("system:employees:remove")
    @Log(title = "员工信息管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(comEmployeesService.deleteComEmployeesByIds(ids));
    }
}
