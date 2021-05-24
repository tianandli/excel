package com.lj.excel.controller;

import com.lj.excel.model.User;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*最终在浏览器调用这个接口即可http://localhost:8080/api/getExcelData*/
@Controller
@RequestMapping("/api")
public class ExcelController {

    //创建表头
    private void createTitle(HSSFWorkbook workbook,HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("身份证号码");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("性别");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("生日");
        cell.setCellStyle(style);
    }

    //生成user表excel
    @RequestMapping("/getExcelData")
    public String getExcelData(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");//这个是excel中的表的名字

        createTitle(workbook,sheet);//创建表头

        List<User> rows = new ArrayList<User>();

        //模拟从数据库中读取到了数据
        rows.add(new User(1, "张三1", "610321199312021111", "男", new Date()));
        rows.add(new User(2, "张三2", "610322199412022222", "女", new Date()));
        rows.add(new User(3, "张三3", "610323199512023333", "男", new Date()));
        rows.add(new User(4, "张三4", "610324199612024444", "女", new Date()));
        rows.add(new User(5, "张三5", "610325199712025555", "男", new Date()));
        rows.add(new User(6, "张三6", "610326199812026666", "女", new Date()));

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(User user:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getIdCard());
            row.createCell(3).setCellValue(user.getSex());
            HSSFCell cell = row.createCell(4);//创建一个单元格
            cell.setCellValue(user.getBirthday());//单元格里面的值是生日
            cell.setCellStyle(style);//日期要格式化处理一下
            rowNum++;
        }

        String fileName = "导出excel的例子.xls";//这个是excel文件的名字

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
