package main.com.zhang.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** * @author zhang_chl 
    * @date 2017年7月12日下午9:26:53
    */
@Entity
@Table(name = "personInfo")
public class Person {
	
	private int pid;               // id号
	private String name;           // 登录名
	private String passward;       // 密码
	private String nickname;       // 昵称
	private String blogtitle;      // 头像url
	private String intro;          // 简介
	private String email;          // 邮箱
	private String datecreated;    // 注册日期
	private boolean enableComment; // 是否允许评论
	private boolean deleted;
	

	private Set<Category> categories;
	private Set<Article>  articles;
	private Set<Link>     links;
	private Set<Album>    albums;
	private Set<Picture>   pictures;
	private Set<Review>  reviews;
	
	private int status;            // 状态（此字段保留）
	
	public Person(){}



	public Person(int pid, String name, String passward, String nickname, String blogtitle, String intro, String email,
			String datecreated, boolean enableComment, boolean deleted, Set<Category> categories, Set<Article> articles,
			Set<Link> links, Set<Album> albums, Set<Picture> pictures, Set<Review> reviews, int status) {
		this.pid = pid;
		this.name = name;
		this.passward = passward;
		this.nickname = nickname;
		this.blogtitle = blogtitle;
		this.intro = intro;
		this.email = email;
		this.datecreated = datecreated;
		this.enableComment = enableComment;
		this.deleted = deleted;
		this.categories = categories;
		this.articles = articles;
		this.links = links;
		this.albums = albums;
		this.pictures = pictures;
		this.reviews = reviews;
		this.status = status;
	}



	@OneToMany(mappedBy="person")
	public Set<Category> getCategories() {
		return categories;
	}

	@OneToMany(mappedBy="person")
	public Set<Article> getArticles() {
		return articles;
	}

	@OneToMany(mappedBy="person")
	public Set<Link> getLinks() {
		return links;
	}

	@OneToMany(mappedBy="person")
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}



	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Id
	@Column(name="pid", nullable=false, unique=true)
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@Column(name = "name", length=64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "password", length=64)
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	@Column(name = "nickname", length=64)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Column(name = "blogtitle", length=128)
	public String getBlogtitle() {
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}
	@Column(name = "intro", length=256)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Column(name = "email", length=128)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "datecreated", length=64)
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
	@Column(name = "enableComment")
	public boolean isEnableComment() {
		return enableComment;
	}
	public void setEnableComment(boolean enableComment) {
		this.enableComment = enableComment;
	}

	@Column
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@OneToMany(mappedBy="person")
	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@OneToMany(mappedBy="person")
	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	
	
}
