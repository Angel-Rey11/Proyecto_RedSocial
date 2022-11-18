package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;

import java.sql.Connection;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String nickname;
    private String password;
    private String biografia;
    private List<Post> posts;
    private List<User> followers;
    private List<User> following;

    public User(int id, String name, String nickname, String password, String biografia) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.biografia = biografia;
    }
    public User(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public String getBiografia() {
        return biografia;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", password=" + password
				+ ", biografia=" + biografia + ", posts=" + posts + ", followers=" + followers + ", following="
				+ following + "]";
	}
}
