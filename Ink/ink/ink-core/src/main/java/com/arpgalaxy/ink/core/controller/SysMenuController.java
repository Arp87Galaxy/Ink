package com.arpgalaxy.ink.core.controller;

import java.util.*;

import com.arpgalaxy.ink.common.utils.response.InkStatus;
import com.arpgalaxy.ink.common.utils.response.ResponseEntity;
import com.arpgalaxy.ink.common.utils.string.CamelCase;
import com.arpgalaxy.ink.core.controller.commons.BaseController;
import com.arpgalaxy.ink.core.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.arpgalaxy.ink.core.entity.SysMenuEntity;
import com.arpgalaxy.ink.core.service.SysMenuService;
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.response.R;



/**
 * 菜单管理
 *
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@RestController
@RequestMapping("core/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    /**
     * 列表
     */
    @PostMapping("/init")
//    @RequiresPermissions("core:sysmenu:list")
    public ResponseEntity list(){
        List<SysMenuEntity> sysMenuEntities = sysMenuService.queryMenuTreeByUserId(getUser());
        Set<String> perms = sysUserService.queryPermissions(getUser());
        Map resultMap = new HashMap();
        resultMap.put("menuList",sysMenuEntities);
        resultMap.put("perms",perms);
        return new ResponseEntity<Map>(InkStatus.CORE_OK,"ok",resultMap);
    }
    @RequestMapping("/list")
    @RequiresPermissions("core:sysmenu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysMenuService.queryPage(params);
        List<String> columnNames = sysMenuService.queryMenuColumnNames();
//        //数据库列名list转驼峰,toArray(new String[columnNames.size()])中<T>声明是泛型方法，在方法上才能使用泛型。所以返回的是String数组,否则强转也会出现object转string错误
//        List<String> strings = Arrays.asList(CamelCase.toCamelCase(columnNames.toArray(new String[columnNames.size()])));
//        page.setColumnNames(strings);

        return R.ok().put("page", page)
//                .put("columnNames",strings)
                ;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("core:sysmenu:info")
    public R info(@PathVariable("menuId") Long menuId){
		SysMenuEntity sysMenu = sysMenuService.getById(menuId);

        return R.ok().put("sysMenu", sysMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("core:sysmenu:save")
    public R save(@RequestBody SysMenuEntity sysMenu){
        sysMenuService.save(sysMenu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("core:sysmenu:update")
    public R update(@RequestBody SysMenuEntity sysMenu){
		sysMenuService.updateById(sysMenu);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("core:sysmenu:delete")
    public R delete(@RequestBody Long[] menuIds){
		sysMenuService.removeByIds(Arrays.asList(menuIds));
        return R.ok();
    }

}
