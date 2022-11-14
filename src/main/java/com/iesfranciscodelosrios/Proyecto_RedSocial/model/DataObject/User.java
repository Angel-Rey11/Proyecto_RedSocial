package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject;

public class User {
    private int id;
    private String name;
    private String nickname;
    private String password;
    private String biografia;

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

    public String getBiografia() {
        return biografia;
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
}
