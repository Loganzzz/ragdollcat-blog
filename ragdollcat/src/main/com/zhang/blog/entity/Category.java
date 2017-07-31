package main.com.zhang.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/** * @author zhang_chl 
    * @date 2017年7月12日下午10:25:06
    */

@Entity
@Table(name = "category")
public class Category {
	private int cid;
	private Category parent;     //上级栏目
	private String title;    //栏目名称
	private String info;    //栏目简介
	private Person person;        //栏目创建者
	private int type;       //栏目类型（公共或专用）
	private boolean deleted;
	private Set<Article> articles; //文章
	private Set<Category> son;  //子栏目
	
	public Category(int cid, Category parent, String title, String info, Person person, int type, boolean deleted,
			Set<Article> articles, Set<Category> son) {
		this.cid = cid;
		this.parent = parent;
		this.title = title;
		this.info = info;
		this.person = person;
		this.type = type;
		this.deleted = deleted;
		this.articles = articles;
		this.son = son;
	}

	public Category() {
	}
	
	@Id
	@Column(nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	@ManyToOne()
	@JoinColumn(name="parent")
	public Category getParent() {
		return parent;
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	public Set<Category> getSon() {
		return son;
	}

	public void setSon(Set<Category> son) {
		this.son = son;
	}

	@OneToMany(mappedBy="category")
	public Set<Article> getArticles() {
		return articles;
	}


	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}


	@Column(name = "title", length=128)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "info", length=128)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@ManyToOne/*(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})*/
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person author) {
		this.person = author;
	}
	
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	@Column
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
