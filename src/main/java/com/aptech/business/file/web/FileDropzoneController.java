package com.aptech.business.file.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aptech.common.system.config.domain.SysConfigEntity;
import com.aptech.common.system.config.service.SysConfigService;
import com.aptech.framework.util.PathUtil;

/** @ClassName:     FileDropzoneController.java 
 * @author         changl
 * @version        V1.0  
 * @Date           2017年6月22日 下午4:16:59 
 */
@Controller
@RequestMapping("/fileDropzone")
public class FileDropzoneController {
	@Autowired
	private SysConfigService sysConfigService;
	@RequestMapping("/upload")
	public @ResponseBody Map<String, String> upload(HttpServletRequest request,  MultipartFile file) throws IOException {
		Map<String, String> resultMap = new HashMap<String, String>();
		String realPath = PathUtil.getRealPath();
		String type = request.getParameter("type");
		List<SysConfigEntity>  configList = sysConfigService.findAll();
		Long attachmentSize = new Long(500);
		String[] writeList  = {"php", "php2", "php3", "php5", "phtml", "asp", "aspx", "ascx", "jsp", "cfm", "cfc", "pl","pl","bat", "exe", "dll", "reg", "cgi"};

		if(configList!=null && configList.size()>0){
			attachmentSize=Long.valueOf(configList.get(0).getFileMax());
			writeList = configList.get(0).getBlackList().split(",");
		}
		
		long fileSize = 0;
		
		fileSize = attachmentSize*1024*1024;
		if (file == null) {
			try {
				throw new Exception("上传失败：文件为空");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", "error");
				resultMap.put("errorMsg", "上传失败：文件为空");
				return resultMap;
			}
		}
		
		if (file.getSize() > fileSize) {
			try {
				throw new Exception("文件超出最大值"+attachmentSize.toString()+"M...");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", "error");
				resultMap.put("errorMsg", "文件超出最大值"+attachmentSize.toString()+"M");
				return resultMap;	
			}
		}
		
		
		String fileName = file.getOriginalFilename();
		String[] tempNames = fileName.split("\\.");
		
		//增加过滤
		if(tempNames.length>2){
			try {
				throw new Exception("上传失败：上传文件类型不正确");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", "error");
				resultMap.put("errorMsg", "上传文件类型不正确");
				return resultMap;	
			}
		}
		if(fileName.contains("\0")){
			try {
				throw new Exception("上传失败：上传文件类型不正确");
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", "error");
				resultMap.put("errorMsg", "上传文件类型不正确");
				return resultMap;	
			}
		}
		for(String key : writeList){
			if(fileName.contains("."+key)){
				
				try {
					throw new Exception("上传失败：上传文件类型不正确");
				} catch (Exception e) {
					e.printStackTrace();
					resultMap.put("result", "error");
					resultMap.put("errorMsg", "上传文件类型不正确");
					return resultMap;	
				}
			}   
		}
				
				
		String filePath = this.getFileName(request, type, fileName);
		File newFile = new File(realPath, filePath);
		file.transferTo(newFile);

		resultMap.put("result", "success");
		resultMap.put("url", filePath);
		resultMap.put("name", fileName);
		resultMap.put("file_size", String.valueOf(file.getSize()) + " bytes");
		resultMap.put("format", file.getContentType());
		return resultMap;
	}

	// 返回文件的相对路径
	private String getFileName(HttpServletRequest request, String type, String title) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat fileFormat = new SimpleDateFormat("HHmmss");

		StringBuilder fileName = new StringBuilder();
		StringBuilder fileDir = new StringBuilder();

		Date now = new Date();

		fileName.append(title);
		//fileName.append(fileFormat.format(now));

		fileDir.append("/upload").append("/").append(type).append("/").append(dateFormat.format(now)).append("/").append(fileFormat.format(now));

		String filePath = fileDir.toString();

		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		int index = basePath.indexOf("WEB-INF");
		String realPath = basePath.substring(0, index);

		realPath = realPath + filePath;
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return filePath + "/" + fileName;
	}
	
}
