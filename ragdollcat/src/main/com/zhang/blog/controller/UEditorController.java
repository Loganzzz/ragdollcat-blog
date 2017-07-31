package main.com.zhang.blog.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;

import main.com.zhang.blog.entity.Album;
import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.entity.Picture;
import main.com.zhang.blog.service.AlbumService;
import main.com.zhang.blog.service.PictureService;


/** * @author zhang_chl 
    * @date 2017年7月19日上午9:38:51
    */

@Controller
@RequestMapping("uploadup")
public class UEditorController {
	@Autowired
	PictureService<Picture> pictureService;
	@Autowired
	AlbumService<Album> albumService;
	
	//配置ueditor编辑器的服务器
	 @RequestMapping(value="ueditor/config")
	 public void config(HttpServletRequest request, HttpServletResponse response) {	 
        response.setContentType("application/json");
        String rootPath = request.getSession()
                .getServletContext().getRealPath("/");
 
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	 }
	 
	  @RequestMapping(value = "ue/uploadimg")
	    public @ResponseBody Map<String, Object> ueUpload(HttpServletRequest request, HttpServletResponse response,
	            HttpSession session) throws IllegalStateException, IOException,
	            FileUploadException {
		  	System.out.println("------------------执行文件上传------------");
	        Map<String, Object> m = new HashMap<String, Object>();
	        // 对上传文件夹和临时文件夹进行初始化
	        String rootDir = /*configInfo.getUploadDir()*/"C:/Users/zhang/workspace/ragdollcat/WebContent/uploadimg";
	        File rootFile = new File(rootDir);
	        if(!rootFile.isDirectory())
	        	rootFile.mkdir();
	        String tmpDir = rootDir + "/tmp";
	        File tmpDirPath = new File(tmpDir);

	        if (ServletFileUpload.isMultipartContent(request)) {
	            request.setCharacterEncoding("utf-8");

	            DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
	            dff.setRepository(tmpDirPath);// 指定上传文件的临时目录
	            dff.setSizeThreshold(2 * 1024 * 1024);// 指定在内存中缓存数据大小,单位为byte
	            ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
	            sfu.setFileSizeMax(1000000000);// 指定单个上传文件的最大尺寸
	            sfu.setSizeMax(1000000000);// 指定一次上传多个文件的总尺寸
	            FileItemIterator fii = sfu.getItemIterator(request);// 解析request
	            // 请求,并返回FileItemIterator集合
	            if(fii.hasNext()) {
	                FileItemStream fis = fii.next();// 从集合中获得一个文件流
	                if (!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件域

	                    String filename = fis.getName();

	                    String[] FileName = filename.split("\\.");

	                    String preFile = FileName[0];
	                    String endFile = FileName[1];

	                    Date date = new Date();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	                    String nowdate = sdf.format(date);
	                    String newFileName = preFile + "_" + nowdate + "." + endFile;
	                    
	                    File appDir = new File(rootDir + "/img");
	                    if (!appDir.isDirectory()) {

	                        appDir.mkdir();
	                    }
	                    // 创建按月分类的子文件夹
	                    SimpleDateFormat sdfmonth = new SimpleDateFormat("yyyyMM");
	                    String currentMonth = sdfmonth.format(date);
	                    File appSubDir = new File(appDir + "/" + currentMonth);
	                    if (!appSubDir.isDirectory()) {
	                        appSubDir.mkdir();
	                    }

	                    String newFilepath = rootDir + "/img"+ "/" + currentMonth + "/" + newFileName;
	                    File resFile=new File(newFilepath);
	                    if(!resFile.exists())
	                    	resFile.createNewFile();
	                    BufferedInputStream in = new BufferedInputStream(fis.openStream());// 获得文件输入流
	                    BufferedOutputStream out =
	                            new BufferedOutputStream(new FileOutputStream(resFile));// 获得文件输出流
	                    Streams.copy(in, out, true);// 开始把文件写到你指定的上传文件夹
	                    //创建图片实体
	                    Picture picture = new Picture();
	                    if(session.getAttribute("person")!=null)
	                    	picture.setPerson((Person)session.getAttribute("person"));
	                    picture.setDatecreated(new Timestamp(System.currentTimeMillis()));
	                    picture.setDeleted(false);
	                    picture.setStatus(1);
	                    picture.setUrl("/ragdollcat/picture/getpicture/"+newFileName);
	                    picture.setPath(newFilepath);
	                    picture.setName(preFile + "_" + nowdate);//因为获取{name}时后面的点.不能算，所以不能加后缀
	                    picture.setIntro(endFile);                 
	                    picture.setAlbum(albumService.find(Album.class, 1));	
	                    pictureService.create(picture);
	                    //配置返回数据，否则返回406错误，浏览器无法接受                    
	                    m.put("original", filename);
	                    m.put("title", newFileName);
	                    m.put("url", "/picture/getpicture/"+newFileName);
	                    m.put("state", "SUCCESS");
	                }
	            }
	        }

	        return m;
	    }
}
