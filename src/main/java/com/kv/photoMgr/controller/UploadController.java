package com.kv.photoMgr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kv.photoMgr.domain.PhotoVO;
import com.kv.photoMgr.service.PhotoVOService;
import com.kv.photoMgr.util.UploadFileUtils;



@Controller
@RequestMapping("/photo/*")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	PhotoVO photo;
	@Autowired
	public PhotoVOService service;
	
	
	private String innerUploadPath = "resources/upload";
	
	@Resource(name="uploadPath")
	private String outUploadPath;
	
		
	
	@RequestMapping(value="/innerUpload", method=RequestMethod.GET)
	public String innerUploadForm() {
		logger.info("--------------innerUpload GET");
		return "/photo/innerUploadForm";
	}
	
	@RequestMapping(value="/innerUploadPost", method=RequestMethod.POST)
	public String innerUploadPost(String test, MultipartFile file, 
							HttpServletRequest request, Model model) throws Exception {
		logger.info("--------------registerPost POST");
		logger.info(" test  =" + test);
		logger.info(" file  =" + file.getOriginalFilename());
		logger.info(" file size =" + file.getSize());
		
		
		
		String root_path = request.getSession().getServletContext().getRealPath("/");// /ex03서버
		// ex03/resources/upload
		File dirPath = new File(root_path+"/"+innerUploadPath);
		if(dirPath.exists() == false) {//폴더 없음
			dirPath.mkdir();//upload 폴더 만듬
		}
		
		//빈 껍데기 파일이 만들어짐
		UUID uid = UUID.randomUUID();//중복되지 않는 고유한 키값을 설정할 때 사용
		String savedName = uid+"_"+file.getOriginalFilename();
		File target = new File(root_path+"/"+innerUploadPath, savedName);
		FileCopyUtils.copy(file.getBytes(), target);//파일 업로드 완료
		
		model.addAttribute("memberid", test);
		model.addAttribute("fullname", savedName);
		photo.getMemberid();
	
		
		logger.info("--------------registerPost model=" + model);
	   service.register(photo);	
		
		return "/photo/innerUploadResult";
	}
	
	
	
	@RequestMapping(value="innerMultiUpload", method=RequestMethod.GET)
	public String innerMultiUploadGet() {
		logger.info("------------ innerMultiUpload GET");
		return "innerMultiUploadForm";
	}

	
	@RequestMapping(value="innerMultiUpload", method=RequestMethod.POST)
	public String innerMultiUploadPOST(String test, List<MultipartFile> files,
								HttpServletRequest request, Model model ) throws IOException {
		logger.info("------------ innerMultiUpload POST");
		logger.info("test ="+test);
		for(MultipartFile file : files) {
			logger.info(file.getOriginalFilename());
			logger.info(file.getSize()+"");
			logger.info(file.getContentType());
		}
		String root_path = request.getSession().getServletContext().getRealPath("/");
		File dir = new File(root_path+"/"+innerUploadPath);
		if(dir.exists() == false) {
			dir.mkdir();
		}
		
		ArrayList<String> list = new ArrayList<>();
		for(MultipartFile file : files) {
			UUID uid = UUID.randomUUID();
			String savedName = uid.toString() +"_" + file.getOriginalFilename();			
			File target = new File(root_path+"/"+innerUploadPath, savedName);
			FileCopyUtils.copy(file.getBytes(), target);
			list.add(savedName);
		}
		
		model.addAttribute("test", test);
		model.addAttribute("list", list);		
		
		return "photo/innerMultiUploadResult";
	}
	@RequestMapping(value="outUpload", method=RequestMethod.GET)
	public String outUploadGet() {
		logger.info("-------- outUpload GET");
		
		return "outUploadForm";				
	}
	
	@RequestMapping(value="outUpload", method=RequestMethod.POST)
	public String outUploadPOST(String test, MultipartFile file, Model model) throws IOException {
		logger.info("-------- outUpload POST");
		logger.info("test : "+test);
		logger.info(file.getOriginalFilename());
		logger.info(file.getSize()+"");
		
		UUID uid = UUID.randomUUID();
		String savedNamed = uid+"_"+file.getOriginalFilename();
		File target = new File(outUploadPath, savedNamed);//outUploadPath경로가 반드시 존재한다는 가정하게 처리됨
		FileCopyUtils.copy(file.getBytes(), target);
		
		model.addAttribute("test", test);
		model.addAttribute("file", savedNamed);
		
		return "outUploadResult";				
	}
	
	@RequestMapping(value="/displayFile", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> displayFile(String filename){
		logger.info("-------------- displayFile, filename="+filename);
		
		String formatName = filename.substring(filename.lastIndexOf(".")+1);//확장자만 뽑아냄
		MediaType mType = null;
		ResponseEntity<byte[]> entity = null;
		
		if(formatName.equalsIgnoreCase("jpg")) {
			mType = MediaType.IMAGE_JPEG;
		}else if(formatName.equalsIgnoreCase("gif")) {
			mType = MediaType.IMAGE_GIF;
		}else if(formatName.equalsIgnoreCase("png")) {
			mType = MediaType.IMAGE_PNG;
		}
		InputStream in = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(outUploadPath+"/"+filename);
			headers.setContentType(mType);
			
			entity = new ResponseEntity<byte[]>(
												IOUtils.toByteArray(in),
												headers,
												HttpStatus.CREATED
												);		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);			
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return entity;
	}
	
	@RequestMapping(value="dragUpload", method=RequestMethod.GET)
	public String dragUploadGet() {
		logger.info("-----dragUpload GET");
		return "dragDropForm"; 
	}
	
	@RequestMapping(value="dragUpload", method=RequestMethod.POST)
	public ResponseEntity<List<String>> dragUploadPOST(List<MultipartFile> files, String test ) {
		logger.info("-----dragUpload POST");
		logger.info("test:"+test);
		for(MultipartFile file : files) {
			logger.info(file.getOriginalFilename());
			logger.info(file.getSize()+"");
		}
		ResponseEntity<List<String>> entity = null;
		List<String> list = new ArrayList<>();
		
		try {
			for(MultipartFile file : files) {
				String savedName = UploadFileUtils.uploadFile(outUploadPath, 
																file.getOriginalFilename(),
																file.getBytes());
				list.add(savedName);
			}
			entity = new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity; 
	}
	
	@ResponseBody
	@RequestMapping(value="deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String filename){
		logger.info("---------------deleteFile, filename = "+filename);
		ResponseEntity<String> entity = null;
		
		try {
			//큰 이미지 삭제
			String originFile = filename.substring(0, 12)+filename.substring(14);			
			File file = new File(outUploadPath+"/"+originFile);
			if(file.exists()) {
				logger.info("file exist");
				file.delete();//파일 삭제
			}else {
				logger.info("file not exist");
			}
			
			//작은 이미지 삭제
			File file2 = new File(outUploadPath+"/"+filename);
			if(file2.exists()) {
				logger.info("file exist");
				file2.delete();//파일 삭제
			}else {
				logger.info("file not exist");
			}
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	@RequestMapping(value="outUpload2", method=RequestMethod.GET)
	public String outUpload2Get() {
		logger.info("------------- outUpload2 GET");
		return "outUploadForm2";
	}
}











