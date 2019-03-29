package com.example.springjpa.utils;

/**
 * Created by Administrator on 2017/7/5.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.springjpa.entity.User;
import org.apache.commons.beanutils.BeanMap;
import org.apache.poi.hssf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class POIUtil {


    /**
     * @param dataSet     数据集
     * @param pojoName 导出项的字段字符串   "字段1,字段2,字段3".split(",")
     * @param heads sheet首列中文名：  "字段1,字段2,字段3".split(",")
     * @return
     * @throws Exception
     */
    public static<T> HSSFWorkbook exportPOI(List<T> dataSet, String[] pojoName, String[] heads) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        int sheetNum = dataSet.size()/3;
        if((dataSet.size()%3)>0){
            sheetNum++;
        }
        int start=0;
        int end=0;
        for(int k=0;k<sheetNum;k++) {
             start=3*k;
             end=3*k+3;
            if(end>dataSet.size()){
                end=dataSet.size();
            }
            List<T> dataTem =dataSet.subList(start,end);
            System.out.println();
            String sheetName = "Sheet" + k;
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet,默认名称 Sheet1
            HSSFSheet sheet = wb.createSheet(sheetName);
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow((int) 0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            /**设置单元格格式为文本格式*/
            HSSFDataFormat format = wb.createDataFormat();
            style.setDataFormat(format.getFormat("@"));
//        // 创建一个居中格式
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFCell cell = null;
            for (int i = 0; i < heads.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(heads[i]);
                cell.setCellStyle(style);
            }
            // 第五步，写入实体数据 实际应用中这些数据从数据库得到.
            Object columnValue = null;
            Iterator itData = dataTem.iterator();
            long i = 0;
            while (itData.hasNext()) {
                row = sheet.createRow((int) i + 1);
                User user = (User) itData.next();
                Map map = objToMap(user);
                // 第六步，创建单元格，并设置值
                for (int j = 0; j < pojoName.length; j++) {
                    HSSFCell hssfCell = row.createCell(j);
                    hssfCell.setCellStyle(style);//设置单元格格式为"文本"
                    hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    hssfCell.setCellStyle(style);
                    // 获取字段值
//                columnValue =getMapValue(beanMap,pojoName[j]);
                    columnValue = map.get(pojoName[j]);
                    // 创建单元格，并设置值
                    if (columnValue == null || "".equals(columnValue)) {
                        hssfCell.setCellValue("");
                    } else {
                        hssfCell.setCellValue(columnValue.toString());
                    }
                }
                i++;
            }
        }

        return wb;
    }




//    /**
//     * 如果实体中含有时间字段，格式化
//     * @param beanMap
//     * @param key
//     * @return
//     */
//    public static Object getMapValue(BeanMap beanMap,String key){
//        Object value = beanMap.get(key);
//        if(value instanceof Date){
//            return DateUtils.format((Date)value,DateUtils.STYLE3);
//        }
//        return value;
//    }

    /**
     * 实体对象转成Map
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> objToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                // 开放private 字段访问权
                field.setAccessible(true);
                Object value = field.get(obj);
                // 如果是时间字段，格式化
//                if(value instanceof Date){
//                    S
//                    String date = DateUtils.format((Date)value,DateUtils.STYLE3);
//                    map.put(field.getName(), date);
//                }else {
                    map.put(field.getName(), value);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * @param sheetName    表格名
     * @param dataSet      数据集
     * @param domainNames  导出项的字段字符串
     * @param columnIndexs 索引数 "0,1,2,3,4,5,6".split(",");
     * @param columnHeads  sheet首列中文名：  "字段1,字段2,字段3".split(",")
     * @return
     * @throws Exception tangzr
     *                   2016年4月13日
     */
    public InputStream exportPOIStream(String sheetName, Collection dataSet, String domainNames, String[] columnIndexs, String[] columnHeads) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = null;
        for (int i = 0; i < columnHeads.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(columnHeads[i]);
            cell.setCellStyle(style);
        }

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//		List list = getStudent();

        String[] pojoName = domainNames.split(",");

//		long lstart1 = System.currentTimeMillis();
        Object columnValue = null;
        Iterator itData = dataSet.iterator();
        long i = 0;
        while (itData.hasNext()) {
            row = sheet.createRow((int) i + 1);
            Map dataM = (Map) itData.next();
//			System.out.println(map.get("name"));
            // 第六步，创建单元格，并设置值
            for (int j = 0; j < columnIndexs.length; j++) {
//        		System.out.println( i +"行"+ j +"列的值是"+ columnIndexs[ j ]);
                columnValue = (Object) dataM.get(pojoName[Integer.valueOf(columnIndexs[j])]);
                //columnValue = (Object)m.get(pojoName[ Integer.valueOf(columnIndexs[ j ]) ]);
                if (columnValue == null || "".equals(columnValue)) {
                    row.createCell(j).setCellValue("");
                } else {
                    row.createCell(j).setCellValue(columnValue.toString());
                }
            }
            i++;
        }

        // 第七步，将文件存到指定位置
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            wb.write(baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        baos.close();
        return is;
    }


}
