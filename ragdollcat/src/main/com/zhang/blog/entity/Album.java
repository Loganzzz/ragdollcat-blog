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

/** * @author zhang_chl 
    * @date 2017年7月12日下午10:36:47
    */


/*
 * 暂时不实现
 * */
@Entity
public class Album {
	private int id;       		//id
	private String name;
	private Person person;                //所属人
	private Set<Picture> pictures;
	private String intro;
	private Timestamp datecreated;
	private boolean deleted;
	private int status;

	public Album(int id, String name, Person person, Set<Picture> pictures, String intro, Timestamp datecreated,
			boolean deleted, int status) {
		super();
		this.id = id;
		this.name = name;
		this.person = person;
		this.pictures = pictures;
		this.intro = intro;
		this.datecreated = datecreated;
		this.deleted = deleted;
		this.status = status;
	}

	public Album() {
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

	@ManyToOne
	@JoinColumn(name="person")
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToMany(mappedBy="album", fetch=FetchType.EAGER)//eager
	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	@Column
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column
	public Timestamp getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}

	@Column
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Column
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
