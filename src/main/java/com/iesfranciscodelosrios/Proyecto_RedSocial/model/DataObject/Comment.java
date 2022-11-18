package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import java.util.Date;

public class Comment {
	private int id;
	private String text;
	private Date date;
	private User user;
	private Post post;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
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
