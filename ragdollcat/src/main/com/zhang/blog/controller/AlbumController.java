package main.com.zhang.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import main.com.zhang.blog.entity.Album;
import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.entity.Picture;
import main.com.zhang.blog.service.AlbumService;
import main.com.zhang.blog.service.PersonService;
import main.com.zhang.blog.service.PictureService;

/** * @author zhang_chl 
    * @date 2017年7月25日上午11:28:34
    */
@Controller
@RequestMapping("album")
public class AlbumController {
	@Autowired
	private AlbumService<Album> albumService;
	@Autowired
	private PersonService<Person> personService;
	@Autowired
	private PictureService<Picture> pictureService;
	
	@RequestMapping("/showall")
	public String showAllAlbum(int album, HttpServletResponse response,
			HttpSession session, Model model){
		Album al = albumService.find(Album.class, album);
		List<Picture> pictureList = pictureService.getPicturesByAlbum(al);
		if(pictureList==null)System.out.println("xxxxxxxxxxxxxxxxxxx");
		model.addAttribute("pictureList", pictureList);
		return "album";
	}
	
	
	@RequestMapping("/show")
	public String showAlbumByPerson(String id,Model model){
		if(id!=null&&!id.equals("2")){
			int pid = Integer.valueOf(id);
			Person person = personService.find(Person.class, pid);
			if(person!=null) System.out.println("----------xxx---------------");
			List<Album> listForPerson = albumService.getAlbumByPerson(person);
			model.addAttribute("listForPerson", listForPerson);
		}
		List<Album> listForVisitor = albumService.getAlbumByPerson(personService.find(Person.class, 2));
		model.addAttribute("listForVisitor", listForVisitor);
		return "albums";
	}
}
