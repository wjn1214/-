package com.emp.project.system.daily.controller;

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
import com.emp.project.system.daily.domain.ComAttendDaily;
import com.emp.project.system.daily.service.IComAttendDailyService;
import com.emp.framework.web.controller.BaseController;
import com.emp.framework.web.domain.AjaxResult;
import com.emp.common.utils.poi.ExcelUtil;
import com.emp.framework.web.page.TableDataInfo;

/**
 * 员工考勤管理Controller
 * 
 * @author wjn
 * @date 2020-10-24
 */
@Controller
@RequestMapping("/system/daily")
public class ComAttendDailyController extends BaseController
{
    private String prefix = "system/daily";

    @Autowired
    private IComAttendDailyService comAttendDailyService;

    @RequiresPermissions("system:daily:view")
    @GetMapping()
    public String daily()
    {
        return prefix + "/daily";
    }

    /**
     * 查询员工考勤管理列表
     */
    @RequiresPermissions("system:daily:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ComAttendDaily comAttendDaily)
    {
        startPage();
        List<ComAttendDaily> list = comAttendDailyService.selectComAttendDailyList(comAttendDaily);
        return getDataTable(list);
    }

    /**
     * 导出员工考勤管理列表
     */
    @RequiresPermissions("system:daily:export")
    @Log(title = "员工考勤管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ComAttendDaily comAttendDaily)
    {
        List<ComAttendDaily> list = comAttendDailyService.selectComAttendDailyList(comAttendDaily);
        ExcelUtil<ComAttendDaily> util = new ExcelUtil<ComAttendDaily>(ComAttendDaily.class);
        return util.exportExcel(list, "daily");
    }

    /**
     * 新增员工考勤管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存员工考勤管理
     */
    @RequiresPermissions("system:daily:add")
    @Log(title = "员工考勤管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ComAttendDaily comAttendDaily)
    {
        return toAjax(comAttendDailyService.insertComAttendDaily(comAttendDaily));
    }

    /**
     * 修改员工考勤管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ComAttendDaily comAttendDaily = comAttendDailyService.selectComAttendDailyById(id);
        mmap.put("comAttendDaily", comAttendDaily);
        return prefix + "/edit";
    }

    /**
     * 修改保存员工考勤管理
     */
    @RequiresPermissions("system:daily:edit")
    @Log(title = "员工考勤管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ComAttendDaily comAttendDaily)
    {
        return toAjax(comAttendDailyService.updateComAttendDaily(comAttendDaily));
    }

    /**
     * 删除员工考勤管理
     */
    @RequiresPermissions("system:daily:remove")
    @Log(title = "员工考勤管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(comAttendDailyService.deleteComAttendDailyByIds(ids));
    }
}
