package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

public class Like {
	protected int id;
	protected User user;
	protected Post post;
	
	public Like() {
		
	}
	
	public Like(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public void setPost(Post p) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user + ", post=" + post + "]";
	}
}
