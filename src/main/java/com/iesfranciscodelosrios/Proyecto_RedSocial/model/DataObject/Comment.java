package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.util.Date;

public class Comment {
	protected int id;
	protected String text;
	protected Date date;
	protected User user;
	protected Post post;
	
	public Comment() {
		
	}
	
	public Comment(int id, String text, Date date) {
		this.id = id;
		this.text = text;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", date=" + date + ", user=" + user + ", post=" + post + "]";
	}
}
