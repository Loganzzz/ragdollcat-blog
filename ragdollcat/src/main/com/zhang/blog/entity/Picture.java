package main.com.zhang.blog.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/** * @author zhang_chl 
    * @date 2017年7月24日上午9:18:55
    */
@Entity
public class Picture {
	private int id;
	private String url;        //获取的url
	private String path;       //真实路径
	private String name;
	private String intro;
	private boolean deleted;
	private Person person;
	private Article article;
	private Album album;
	private Timestamp datecreated;
	private int status;

	public Picture(int id, String url, String path, String name, String intro, boolean deleted, Person person,
			Article article, Album album, Timestamp datecreated, int status) {
		this.id = id;
		this.url = url;
		this.path = path;
		this.name = name;
		this.intro = intro;
		this.deleted = deleted;
		this.person = person;
		this.article = article;
		this.album = album;
		this.datecreated = datecreated;
		this.status = status;
	}

	public Picture() {
	}

	@Id
	@Column(nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@ManyToOne
	@JoinColumn(name="person")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne
	@JoinColumn(name="article")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@ManyToOne
	@JoinColumn(name="album")
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Column
	public Timestamp getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}

	@Column
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
