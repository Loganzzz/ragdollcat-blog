package main.com.zhang.blog.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/** * @author zhang_chl 
    * @date 2017年7月12日下午10:35:58
    */

@Entity
@Table(name = "review")
public class Review {
	private int rid;
	private Person person;			//评论者
	private Article article;       		//评论的文章
	private String content;    	//评论内容
	private Timestamp datecreated;	//评论时间
	private String ip;          //ip地址
	private int status;
	private boolean deleted;
	
	public Review() {
		
	}

	public Review(int rid, Person person, Article article, String content, Timestamp datecreated, String ip, int status,
			boolean deleted) {
		this.rid = rid;
		this.person = person;
		this.article = article;
		this.content = content;
		this.datecreated = datecreated;
		this.ip = ip;
		this.status = status;
		this.deleted = deleted;
	}

	@Id
	@Column(name="aid", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRid() {
		return rid;
	}

	@ManyToOne(fetch=FetchType.EAGER)/*(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})*/
	@JoinColumn(name = "person")
	public Person getPerson() {
		return person;
	}

	@ManyToOne
	@JoinColumn(name = "article")
	public Article getArticle() {
		return article;
	}

	@Column(name = "content",length=512, nullable=false)
	public String getContent() {
		return content;
	}
	
	@Column(name = "datecreated")
	public Timestamp getDatecreated() {
		return datecreated;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	@Column
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
