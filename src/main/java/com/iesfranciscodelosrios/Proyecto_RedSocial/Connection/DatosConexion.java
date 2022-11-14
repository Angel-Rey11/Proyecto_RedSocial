package com.iesfranciscodelosrios.Proyecto_RedSocial.Connection;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="conexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosConexion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String server;
    private String database;
    private String user;
    private String password;

    public String getServer() {
        return server;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public DatosConexion(){}
    public DatosConexion(String server, String database, String user, String password) {
        super();
        this.server = server;
        this.database = database;
        this.user = user;
        this.password = password;
    }
}
