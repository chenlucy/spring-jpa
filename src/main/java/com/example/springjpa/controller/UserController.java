package com.example.springjpa.controller;

import com.example.springjpa.compoment.Result;
import com.example.springjpa.entity.User;
import com.example.springjpa.server.UserService;
import com.example.springjpa.utils.ExcelUtil;
import com.example.springjpa.utils.POIUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "findAll")
    public Result findAll(HttpServletResponse response) {
        List<User> list = userService.findAll();
        Map<String, String> headers = new HashMap<>();
        headers.put("id", "主键");
        headers.put("name", "姓名");
        headers.put("birthday", "生日");
        headers.put("mobile", "手机");
        headers.put("remark", "备注");
        headers.put("isEnable", "是否有效");
        Collection<User> dataset = userService.findAll();
        OutputStream out = null;
        response.setContentType("application/vnd.ms-excel");
        String excelFileName = String.valueOf(System.currentTimeMillis());
        response.setHeader("content-disposition", "attachment;filename=" + excelFileName + ".xls");
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelUtil.exportExcel(headers, dataset, out);
        return Result.genSuccessResult(list);
    }

    @RequestMapping(value = "getById")
    public User getById(Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/findById")
    public User findById() {
        return userService.findById();
    }

    @GetMapping("/findByName")
    public User findByName() {
        return userService.findById();
    }

    @GetMapping("/save")
    public Result save(User user) {
        Integer name=user.getId();
        if(name==null||(name!=100&&name!=200)){
            return Result.genSuccessResult("参数有误");
        }
        userService.save(user);
        return Result.genSuccessResult(user);
    }

    @RequestMapping(value = "findPage")
    public Result findPage(HttpServletResponse response,
                           @RequestParam(defaultValue = "0") Integer pageNum,
                           @RequestParam(defaultValue = "2") Integer pageSize) {
        OutputStream fOut = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            fOut = response.getOutputStream();
            // 设置excel名称:yyyyMMddHHmmssSSS
            String excelFileName = String.valueOf(System.currentTimeMillis());
            response.setHeader("content-disposition", "attachment;filename=" + excelFileName + ".xls");
            // 查询导出数据集合
            List<User> dataSet = userService.findPage(pageNum, pageSize);

            // 设置显示字段名称
            String[] columnHeads = new String[]{"主键", "姓名", "生日"};
            // 设置显示表头名称
            String[] domainNames = new String[]{"id", "name", "birthday"};
            // 生产excel表格文件
            HSSFWorkbook workbook = POIUtil.exportPOI(dataSet, domainNames, columnHeads);
            workbook.write(fOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Result.genSuccessResult("good");
    }
}
