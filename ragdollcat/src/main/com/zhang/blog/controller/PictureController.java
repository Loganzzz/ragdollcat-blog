package main.com.zhang.blog.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.com.zhang.blog.entity.Picture;
import main.com.zhang.blog.service.PictureService;

/** * @author zhang_chl 
    * @date 2017年7月24日下午7:29:17
    */

@Controller
@RequestMapping("picture")
public class PictureController {
	@Autowired
	private PictureService<Picture> pictureService;
	
	@RequestMapping(value = "/getpicture/{name}", method = RequestMethod.GET)
	public void getPictureByName(@PathVariable("name") String name,
	        HttpServletRequest request, HttpServletResponse response){
		Picture picture = pictureService.findByName(name);
		response.setContentType("image/x-png");
		response.setCharacterEncoding("UTF-8");//设置response的格式
		if(picture!=null){
			File file = new File(picture.getPath());
			try {
				BufferedImage image = ImageIO.read(file);
				ImageIO.write(image, picture.getIntro(), response.getOutputStream());//输出
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
