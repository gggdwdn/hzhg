package com.aptech.tool.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * jxls导出excel 
 */
public class excelUnit {
	
	/**
	 * 下载excel 
	 * @param request
	 * @param response
	 * @param templateFileName 模板名
	 * @param destFileName 导出文件名
	 * @param resultMap 包含数据
	 * @throws UnsupportedEncodingException
	 */
	public static void export(HttpServletRequest request,HttpServletResponse response,String templateFileName,String destFileName,Map<String,Object> resultMap){
	       // XLSTransformer transformer = new XLSTransformer();  
	        InputStream in=null;  
	        OutputStream out=null;  
	        //设置响应   
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        try {
				response.setHeader("Content-Disposition","attachment;filename=" + new String(destFileName.getBytes("gb2312"), "ISO8859-1"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
	        try {
	        	templateFileName=request.getSession().getServletContext().getRealPath("/") + "\\reportTemplet\\"+templateFileName;
	            in=new FileInputStream(templateFileName);  
	            XLSTransformer transformer = new XLSTransformer();
	            XSSFWorkbook  workbook=(XSSFWorkbook) transformer.transformXLS(in, resultMap);
	            //XSSFSheet sheet = workbook.getSheetAt(0);
	            //sheet.addMergedRegion(new CellRangeAddress(3, 2+Optional.fromNullable((int)resultMap.get("dataSize")).or(0),0,0));  
	            //sheet.addMergedRegion(new CellRangeAddress(6+Optional.fromNullable((int)resultMap.get("dataSize")).or(0), 6+Optional.fromNullable((int)resultMap.get("dataSize")).or(0),2,20));
	            out=response.getOutputStream();  
	            workbook.write(out);  
	            out.flush();  
	        }catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (ParsePropertyException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}finally {  
	            if (in!=null){try {in.close();} catch (IOException e) {}}  
	            if (out!=null){try {out.close();} catch (IOException e) {}}  
	        }  
	    }  
}
