/**
 * FileName : EditorController.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2015. 6. 6.
 */
package com.dhkim.sworks.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhkim.sworks.board.bean.PhotoVo;
import com.dhkim.sworks.common.util.FileControlUtil;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EditorController {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	// 이미지 업로드 경로를 설정값으로 정한다. ----------------------
	@Value("${config.file.editor.path}")
	private String imgFilePath;
	@Value("${config.file.base.path}")
	private String baseFilePath;
	//-------------------------------------------------------------------------------------------
	
	/**
	 * HTML5 미지원
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/board/photoUpload.do")
	public String photoUpload(HttpServletRequest request, @ModelAttribute("PhotoVo") PhotoVo vo) throws Exception {
		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();
		String file_result = "";
		try {
			if(vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null && !vo.getFiledata().getOriginalFilename().equals("")){
				//파일이 존재하면
				String original_name = vo.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".")+1);
				//파일 기본경로
				//String defaultPath = request.getSession().getServletContext().getRealPath("/");
				//파일 기본경로 _ 상세경로
//				String path = defaultPath + "resource_file" + File.separator + "photo_upload" + File.separator + menuPath;
				// 위 경로에서 게시판ID별로 이미지경로를 만든다.(세션의 게시판ID) ----------------------
				String menu = (String) request.getSession().getAttribute("menu");
				String menuPath = "";
				if (menu != null && menu != ""){
					menuPath = menu + "/";
				}
				String imgPath = baseFilePath + "/" + imgFilePath;
				//파일 기본경로 _ 상세경로
				String directPath = imgPath + "/"+ menuPath;
				Path strpath = Paths.get(directPath);
				directPath = strpath.toString();
				logger.debug("path:"+directPath);
				//-------------------------------------------------------------------------------------------
				File file = new File(directPath);
				//디렉토리 존재하지 않을경우 디렉토리 생성
				FileControlUtil.createDirectory(file);

				//서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
				String realname = UUID.randomUUID().toString() + "." + ext;
				///////////////// 서버에 파일쓰기 ///////////////// 
				vo.getFiledata().transferTo(new File(directPath + File.separator + realname));
//				file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL=/resource_file/photo_upload/"+realname;
				// 위 경로에서 게시판ID별로 이미지경로를 만든다.(세션의 게시판ID) ----------------------
				String sfileurl = imgFilePath + "/" + menuPath + "/" + realname;
				sfileurl = sfileurl.replace(File.separator, "/");
				sfileurl = sfileurl.replaceAll("/+", "/");
				file_result += "&bNewLine=true&sFileName="+original_name+"&sFileURL="+sfileurl;
				file_result = file_result.replaceAll("/+", "/");
				//-------------------------------------------------------------------------------------------
			} else {
				file_result += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + callback + "?callback_func="+callback_func+file_result;

	}

	/**
	 * HTML5 전용
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/board/multiplePhotoUpload.do")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//파일정보
			String sFileInfo = "";
			//파일명을 받는다 - 일반 원본파일명
			String filename = request.getHeader("file-name");
			//파일 확장자
			String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
			//확장자를소문자로 변경
			filename_ext = filename_ext.toLowerCase();
			//파일 기본경로
			//String dftFilePath = request.getSession().getServletContext().getRealPath("/");
//			//파일 기본경로 _ 상세경로
//			String filePath = dftFilePath + "resource_file" + File.separator + "photo_upload" + File.separator;
			// 위 경로에서 게시판ID별로 이미지경로를 만든다.(세션의 게시판ID) ----------------------
			String menu = (String) request.getSession().getAttribute("menu");
			String menuPath = "";
			if (menu != null && menu != ""){
				menuPath = menu + "/";
			}
			String imgPath = baseFilePath + "/" + imgFilePath;
			//파일 기본경로 _ 상세경로
			String filePath = imgPath + "/" + menuPath;
			Path strpath = Paths.get(filePath);
			filePath = strpath.toString();
			logger.debug("path:"+filePath);
			//-------------------------------------------------------------------------------------------
			File file = new File(filePath);
			FileControlUtil.createDirectory(file);

			String realFileNm = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today= formatter.format(new java.util.Date());
			realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			String rlFileNm = filePath + File.separator + realFileNm;
			///////////////// 서버에 파일쓰기 ///////////////// 
			InputStream is = request.getInputStream();
			OutputStream os=new FileOutputStream(rlFileNm);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while((numRead = is.read(b,0,b.length)) != -1){
				os.write(b,0,numRead);
			}
			if(is != null) {
				is.close();
			}
			os.flush();
			os.close();
			///////////////// 서버에 파일쓰기 /////////////////
			// 정보 출력
			sFileInfo += "&bNewLine=true";
			// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
			sFileInfo += "&sFileName="+ filename;;
//			sFileInfo += "&sFileURL="+"/resource_file/photo_upload/"+realFileNm;
			// 위 경로에서 게시판ID별로 이미지경로를 만든다.(세션의 게시판ID) ----------------------
			String sfileurl = imgFilePath + "/" + menuPath + "/" + realFileNm;
			sfileurl = sfileurl.replace(File.separator, "/");
			sfileurl = sfileurl.replaceAll("/+", "/");
			sFileInfo += "&sFileURL=" + sfileurl;
			sFileInfo = sFileInfo.replaceAll("/+", "/");
			//-------------------------------------------------------------------------------------------
			
			PrintWriter print = response.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
