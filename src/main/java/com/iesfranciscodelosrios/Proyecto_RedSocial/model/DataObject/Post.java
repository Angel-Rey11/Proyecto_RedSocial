package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.sql.Date;

public class Post {

	protected int id;
	protected Date creationDate;
	protected String text;
	protected User user;
	
	public Post() {
		super();
	}

	public Post(int id, Date creationDate, String text, User user) {
		this.id = id;
		this.creationDate = creationDate;
		this.text = text;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", creationDate=" + creationDate + ", modificationDate=" 
				+ ", text=" + text + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
