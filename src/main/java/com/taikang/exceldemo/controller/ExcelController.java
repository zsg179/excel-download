package com.taikang.exceldemo.controller;

import com.taikang.exceldemo.entity.SysAccountEntity;
import com.taikang.exceldemo.service.SysAccountService;
import com.taikang.exceldemo.utils.ExcelExport2;
import com.taikang.exceldemo.utils.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther :朱树广
 * @Date :2020/7/25
 * @Description :com.taikang.exceldemo.controller
 * @Version :1.0
 */
@Controller
public class ExcelController {
    @Autowired
    private SysAccountService sysAccountService;

    @PostMapping("/download")
    @CrossOrigin
    public void download(@RequestBody Map<String,Object> map,HttpServletResponse response) {
        String key = (String) map.get("key");
        List<SysAccountEntity> list = sysAccountService.download(key);

        //创建一个数组用于设置表头
        String[] arr = new String[]{"id","投保人姓名","投保人电话","投保人年龄","投保人性别","所属团购码"};

        //调用Excel导出工具类
        ExcelExport2.export(response,list,arr);
    }

    /*@PostMapping("/download")
    @CrossOrigin
    public void download(@RequestBody Map<String,Object> map,
         HttpServletResponse response,HttpServletRequest request) {
        String key = (String) map.get("key");
        System.out.println(key);
        List<SysAccountEntity> list = sysAccountService.download(key);
        System.out.println(list);
        //定义导出的excel名字
        String excelName = "深圳个账表";

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id", "编号");
        fieldMap.put("applicantName", "投保人姓名");
        fieldMap.put("applicantTel", "投保人电话");
        fieldMap.put("applicantAge", "投保人年龄");
        fieldMap.put("applicantSex", "投保人性别");
        fieldMap.put("ownerCode", "所属团购码");

        new ExportExcelUtils().export(excelName, list, fieldMap, response);
    }*/
}
