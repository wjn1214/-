package com.emp.project.system.wages.controller;

import java.util.List;
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
import com.emp.project.system.wages.domain.ComWages;
import com.emp.project.system.wages.service.IComWagesService;
import com.emp.framework.web.controller.BaseController;
import com.emp.framework.web.domain.AjaxResult;
import com.emp.common.utils.poi.ExcelUtil;
import com.emp.framework.web.page.TableDataInfo;

/**
 * 员工薪资管理Controller
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Controller
@RequestMapping("/system/wages")
public class ComWagesController extends BaseController
{
    private String prefix = "system/wages";

    @Autowired
    private IComWagesService comWagesService;

    @RequiresPermissions("system:wages:view")
    @GetMapping()
    public String wages()
    {
        return prefix + "/wages";
    }

    /**
     * 查询员工薪资管理列表
     */
    @RequiresPermissions("system:wages:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ComWages comWages)
    {
        startPage();
        List<ComWages> list = comWagesService.selectComWagesList(comWages);
        return getDataTable(list);
    }

    /**
     * 导出员工薪资管理列表
     */
    @RequiresPermissions("system:wages:export")
    @Log(title = "员工薪资管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ComWages comWages)
    {
        List<ComWages> list = comWagesService.selectComWagesList(comWages);
        ExcelUtil<ComWages> util = new ExcelUtil<ComWages>(ComWages.class);
        return util.exportExcel(list, "wages");
    }

    /**
     * 新增员工薪资管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存员工薪资管理
     */
    @RequiresPermissions("system:wages:add")
    @Log(title = "员工薪资管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ComWages comWages)
    {
        return toAjax(comWagesService.insertComWages(comWages));
    }

    /**
     * 修改员工薪资管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ComWages comWages = comWagesService.selectComWagesById(id);
        mmap.put("comWages", comWages);
        return prefix + "/edit";
    }

    /**
     * 修改保存员工薪资管理
     */
    @RequiresPermissions("system:wages:edit")
    @Log(title = "员工薪资管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ComWages comWages)
    {
        return toAjax(comWagesService.updateComWages(comWages));
    }

    /**
     * 删除员工薪资管理
     */
    @RequiresPermissions("system:wages:remove")
    @Log(title = "员工薪资管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(comWagesService.deleteComWagesByIds(ids));
    }
}
