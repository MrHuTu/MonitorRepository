package com.zhongda.monitor.report.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.report.mapper.ReportPicMapper;
import com.zhongda.monitor.report.model.ReportPic;
import com.zhongda.monitor.report.service.ReportPicService;
import com.zhongda.monitor.report.utils.GitYmlParaUtils;
/**
 * 批量上传文件
 * @author huchao
 *
 */
@Service
public class ReportPicServiceImpl implements ReportPicService {
	@Autowired
	private GitYmlParaUtils gitYmlParaUtils;
	@Autowired
	private ReportPicMapper reportPicMapper;
	

	@Override
	public Result<String> insertPic(MultipartFile[] file,String projectId) {
		
		boolean success = false;
		
		List<String> error = new ArrayList<String>();
		
		List<String> error_1  = new ArrayList<String>();
		
		
		try {
			for(MultipartFile v: file){
				
			
			
			String fileName = v.getOriginalFilename();		
			
			error = verifyPicType(fileName,error);
			
			//如果不符合上面if的判断则跳过下面的逻辑代码,将格式文件不对的文件名全部加入error集合
			if(error.size()>0) continue;
			
			String path  = gitYmlParaUtils.accordingOsGetParm("upload")+fileName;
			
			error_1	= verifyPic(projectId,path,error_1);
			//如果不符合上面if的判断则跳过下面的逻辑代码,域服与务器上同名文件的文件名全部加入error1集合
			if(error_1.size()>0) continue;
			
			byte[] by = v.getBytes();
			
			FileOutputStream out  = new FileOutputStream(path);
			
			out.write(by);
			
			out.close();
			
			ReportPic reportPic = new ReportPic();
			
			reportPic.setPath(path);
			
			reportPic.setProjectId(projectId);
			
			success  =  reportPicMapper.insertPic(reportPic);
			
			if(!success) break;
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(error.size()>0) return analysisError(error,"上传失败,上传类型只支持 :"+gitYmlParaUtils.getPictype()+"格式式图片");
		
		if(error_1.size()>0) return analysisError(error_1,"上传失败,请不要重复上传,或者对该图片重命名");
		
		if(success){
			
			return  new Result<String>().setCode(Result.SUCCESS).setMsg("上传成功");
		}else{
			return  new Result<String>().setCode(Result.FAILURE).setMsg("上传失败");
		}
		
		
		
		
		
		
		
		
	}
	/**
	 * 查询对应项目下的图片名
	 */
	@Override
	public Result<List<String>> selectPicById(String id) {
		
		Result<List<String>> result  = new Result<List<String>>();
		
		List<String> list = new ArrayList<String>();
		
		List<ReportPic>  datas = reportPicMapper.selectPicById(id);
		
		Iterator<ReportPic> ter = datas.iterator();
		
		while(ter.hasNext()){
			
			ReportPic reportPic = ter.next();
			
			String path  = reportPic.getPath();
			
			list.add(path.substring(path.lastIndexOf("/")+1, path.length()));
						
		}
		
		result.setData(list);
		
		return result.setCode(Result.SUCCESS).setMsg("查询成功");
	}
	/**
	 * 图片格式校验
	 * @param type
	 * @return
	 */
	private List<String> verifyPicType(String type,List<String> error){
		
		
		String picType = gitYmlParaUtils.getPictype();
				
		String ctype = type.substring(type.lastIndexOf("."), type.length());
		
		if(picType.indexOf(ctype)<0){
			
			error.add(type);
			
		}
		
	
		return error;
		
		
	}
	/**
	 * 组装错误信息
	 * @param error
	 * @param errorMsg
	 * @return
	 */
	private Result<String> analysisError(List<String> error,String errorMsg){
		
		String msg = "";
		
		if(error.size()>0){
			
			
			
			for(int i=0;i<error.size();i++){
				
				if(i<error.size()-1){
					
					msg+=error.get(i)+",";
					
				}else{
					
					msg+=error.get(i);
				}
			
			}
			
			return new Result<String>().setCode(Result.FAILURE).setMsg(errorMsg+"请检查:《"+msg+"》");
		}
		
		return null;
		
	}
	/**
	 * 校验统一项目下,图片信息是否重复
	 */
	private List<String> verifyPic(String projectId,String path,List<String> error_1){
				
		List<ReportPic> datas = reportPicMapper.selectPicById(projectId);

		Iterator<ReportPic> ite = datas.iterator();
		
		while(ite.hasNext()){
			
			ReportPic reportPic = ite.next();
			
			if(reportPic.getPath().equalsIgnoreCase(path)){
								
				error_1.add(path.substring(path.lastIndexOf("/")+1, path.length()));							
				
				continue;
				
			}
		}
		
		return error_1;
		
	}
	

}
