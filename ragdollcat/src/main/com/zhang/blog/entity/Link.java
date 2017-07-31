package main.com.zhang.blog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** * @author zhang_chl 
    * @date 2017年7月12日下午10:37:13
    */

@Entity
@Table(name = "link")
public class Link {
	private int lid;              //id
	private String title;         //名称
	private String intro;         //简介
	private String datecreated;   //创建日期
	private String url;           //url地址
	private String rss;           //rss地址
	private Person person;              //创建人
	private boolean deleted;
	private int status;           //状态
	
	public Link() {
	}

	public Link(int lid, String title, String intro, String datecreated, String url, String rss, Person person,
			boolean deleted, int status) {
		this.lid = lid;
		this.title = title;
		this.intro = intro;
		this.datecreated = datecreated;
		this.url = url;
		this.rss = rss;
		this.person = person;
		this.deleted = deleted;
		this.status = status;
	}

	@Id
	@Column(name="lid", nullable=false, unique=true)
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	@Column(name = "title", length=128)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "intro", length=256)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "datecreated", length=64)
	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	@Column(name = "url", length=128)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "rss", length=128)
	public String getRss() {
		return rss;
	}

	public void setRss(String rss) {
		this.rss = rss;
	}

	@ManyToOne/*(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})*/
	@JoinColumn(name="person")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
