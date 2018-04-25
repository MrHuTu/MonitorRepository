package com.zhongda.monitor.report.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zhongda.monitor.core.model.Result;

public class DownloadUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadUtils.class);
	
	/**
	 * 
	 * @param filePath
	 *            已文件类型结尾 比如".docx   .txt"
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public static void downloadSolve(String filePath,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		// 得到该文件
		File file = new File(filePath);
		if (!file.exists()) {
					
			return;// 文件不存在就退出方法
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		
		// 设置Http响应头告诉浏览器下载这个附件
		response.setHeader("Content-Disposition","attachment;Filename=" + file.getName());
		
		OutputStream outputStream = response.getOutputStream();
		
		byte[] bytes = new byte[2048];
		
		int len = 0;
		
		while ((len = fileInputStream.read(bytes)) > 0) {
			
			outputStream.write(bytes, 0, len);
			
		}
		fileInputStream.close();
		
		outputStream.close();
	}

	/**
	 * 
	 * @param path
	 *            已文件类型结尾 比如".docx   .txt"
	 * @param delete
	 *            是否做服务文件缓存
	 * @return
	 */
	
	public static  Object downloadSolve(String path, boolean delete) {
		
		Result<String> result = 	new Result<String>();
		
		if(path.indexOf("ERROR")!=-1){//下载报告的错误码
			
			//以错误信息相应客户
			return result.setCode(1).success("下载失败",path); 
			
		}else{
			byte[] body = null;
			
			File file = new File(path);
			
			InputStream is;
			
			logger.info("服务器文件生成目录:"+path);
			
			try {
				is = new FileInputStream(file);
				
				body = new byte[is.available()];
				
				is.read(body);
				
				is.close();
				
				if (delete) {
					
					file.delete();
					logger.info("服务器文件删除成功:"+path);
				}
				
			} catch (FileNotFoundException e) {

				logger.error("文件下载失败"+path,e);
				
			} catch (IOException e) {

				e.printStackTrace();
			}

			HttpHeaders headers = new HttpHeaders();
			try {
				
				headers.add("Content-Disposition", "attchement;filename=\""+ new String(file.getName().getBytes("UTF-8"),"ISO8859-1") + "\";filename*=utf-8''" + URLEncoder.encode(file.getName(), "utf-8").replaceAll("\\+", "%20"));
			
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			HttpStatus statusCode = HttpStatus.OK;
			
			ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body,headers, statusCode);
			
			logger.info("用户文件下载成功:"+path);
			
			//以文件形式相应用户
			return entity ;
		}
		
		
			
		
		

	}

	/**
	 * 
	 * @param path
	 *            已文件类型结尾 比如".docx   .txt"
	 * @return
	 */
	public static ResponseEntity<byte[]> downloadSolve(String path) {
		
		byte[] body = null;
		
		File file = new File(path);
		
		InputStream is;
		
		try {
			is = new FileInputStream(file);
			
			body = new byte[is.available()];
			
			is.read(body);
			
			is.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		
		try {
			
			headers.add("Content-Disposition", "attchement;filename="+ URLEncoder.encode(file.getName(), "utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body,headers, statusCode);
		
		return entity;

	}
	/**
	 * 2018年4月23日16:55:57
	 */
	public static void download(String fileName, String filePath,
			 HttpServletResponse response) 
			throws Exception {
			    //声明本次下载状态的记录对象
			   // DownloadRecord downloadRecord = new DownloadRecord(fileName, filePath, request);
			    //设置响应头和客户端保存文件名
			    response.setCharacterEncoding("utf-8");
			    response.setContentType("multipart/form-data");
			    response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			    //用于记录以完成的下载的数据量，单位是byte
			   // long downloadedLength = 0l;
			    try {
			        //打开本地文件流
			        InputStream inputStream = new FileInputStream(filePath);
			        //激活下载操作
			        OutputStream os = response.getOutputStream();

			        //循环写入输出流
			        byte[] b = new byte[2048];
			        int length;
			        while ((length = inputStream.read(b)) > 0) {
			            os.write(b, 0, length);
			           // downloadedLength += b.length;
			        }

			        // 这里主要关闭。
			        os.close();
			        inputStream.close();
			    } catch (Exception e){
			       // downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
			        throw e;
			    }
			   /* downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
			    downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
			    downloadRecord.setLength(downloadedLength);*/
			    //存储记录
			}
}