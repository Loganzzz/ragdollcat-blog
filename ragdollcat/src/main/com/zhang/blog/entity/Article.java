package main.com.zhang.blog.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/** * @author zhang_chl 
    * @date 2017年7月12日下午10:35:30
    */

@Entity
@Table(name = "article")
public class Article {
	private int aid;
	private Category category;          //所属目录
	private Person person;          //所属人
	private String title;     //标题
	private String content;   //内容
	private String intro;     //简介
	private String tagimg;    //封面图片
	private String template;  //文档显示模板
	private String file;      //文档文件URL
	private String image;     //文档图片URL
	private int readtimes;         //阅读次数
	private boolean top;      //置顶
	private int type;         //类型（草稿，发布，精华）
	private boolean enablereview; //允许评论
	private int status;       //状态
	private Timestamp datecreated; //创建日期
	private boolean deleted;    //删除标识
	private Set<Review> reviews;
	private Set<Picture> pictures;
	
	public Article() {

	}

	public Article(int aid, Category category, Person person, String title, String content, String intro, String tagimg,
			String template, String file, String image, int readtimes, boolean top, int type, boolean enablereview,
			int status, Timestamp datecreated, boolean deleted, Set<Review> reviews, Set<Picture> pictures) {
		super();
		this.aid = aid;
		this.category = category;
		this.person = person;
		this.title = title;
		this.content = content;
		this.intro = intro;
		this.tagimg = tagimg;
		this.template = template;
		this.file = file;
		this.image = image;
		this.readtimes = readtimes;
		this.top = top;
		this.type = type;
		this.enablereview = enablereview;
		this.status = status;
		this.datecreated = datecreated;
		this.deleted = deleted;
		this.reviews = reviews;
		this.pictures = pictures;
	}

	@Id
	@Column(name="aid", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getAid() {
		return aid;
	}

	@ManyToOne/*(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})*/
	@JoinColumn(name = "category")
	public Category getCategory() {
		return category;
	}

	@ManyToOne/*(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})*/
	@JoinColumn(name = "person")
	public Person getPerson() {
		return person;
	}

	
	@OneToMany(mappedBy="article")
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@Column(name = "title", length=128)
	public String getTitle() {
		return title;
	}

	@Column(name = "content",length=2048, nullable=false)
	public String getContent() {
		return content;
	}

	@Column(name = "intro", length=128)
	public String getIntro() {
		return intro;
	}

	@Column(name = "tagimg", length=64)
	public String getTagimg() {
		return tagimg;
	}

	@Column(name = "template", length=16, nullable=false)
	public String getTemplate() {
		return template;
	}

	@Column(name = "file", length=64)
	public String getFile() {
		return file;
	}

	@Column(name = "image", length=64)
	public String getImage() {
		return image;
	}

	@Column(name = "readtimes")
	public int getReadtimes() {
		return readtimes;
	}

	@Column(name = "top", nullable=false)
	public boolean isTop() {
		return top;
	}

	@Column(name = "type", nullable=false)
	public int getType() {
		return type;
	}

	@Column(name = "enablereview")
	public boolean isEnablereview() {
		return enablereview;
	}
	
	@Column(name = "status" )
	public int getStatus() {
		return status;
	}

	@Column(name = "datecreated", nullable=false)
	public Timestamp getDatecreated() {
		return datecreated;
	}
	
	
	@Column
	public boolean isDeleted() {
		return deleted;
	}
	
	@OneToMany(mappedBy="article")
	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPerson(Person author) {
		this.person = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setTagimg(String tagimg) {
		this.tagimg = tagimg;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setReadtimes(int read) {
		this.readtimes = read;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setEnablereview(boolean enablereview) {
		this.enablereview = enablereview;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}
	
	
	
}
