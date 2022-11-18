package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IUserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User{

    private final static String INSERT = "INSERT INTO `user` (`id`, `nickname`, `name`, `password`, `biografia`) VALUES (NULL,?,?,?,'')";
    private final static String DELETE = "DELETE FROM User WHERE id = ?";
    private final static String UPDATE = "UPDATE User SET name = ?,  nickname = ?, password = ?, biografia = ? WHERE id = ?";
    private final static String GETALLFOLLOWER = "SELECT * FROM User WHERE id IN (SELECT id_follower FROM Follow WHERE id_following = ?)";
    private final static String GETALLFOLLOWING = "SELECT * FROM User WHERE id IN (SELECT id_following FROM Follow WHERE id_follower = ?)";
    private final static String FOLLOW = "INSERT INTO Follow VALUES (?, ?)";
    private final static String UNFOLLOW = "DELETE FROM Follow WHERE id_follower = ? AND id_following = ?";
    private final static String FIND = "SELECT id, name, nickname, password, biografia FROM user WHERE id = ?";
    private final static String FINDBYNICKNAME = "SELECT id, name, nickname, password, biografia FROM user WHERE nickname = ?";
    private final static String LOGIN = "SELECT * FROM user WHERE nickname = ? AND password = ?";
    public UserDAO(){}
    public UserDAO(int id, String name, String nickname, String password, String biografia, List<Post> posts, List<User> followers, List<User> following) {
        super(id, name, nickname, password, biografia);
    }
    public UserDAO(User u){
        this(u.getId(), u.getNickname(), u.getName(), u.getPassword(), u.getBiografia(), u.getPosts(), u.getFollowers(), u.getFollowing());
    }
    public UserDAO(int id) {
        this.find(id);
    }




    public boolean insert() {
        Connection con = Connect.getConnection();
        boolean insertado = false;
        if(con!=null){
            try {
                PreparedStatement ps = con.prepareStatement(INSERT);
                ps.setString(1, this.getNickname());
                ps.setString(2, this.getName());
                ps.setString(3, this.getPassword());
                ps.executeUpdate();
                insertado = true;
                System.out.println("Usuario insertado correctamente");
            } catch (SQLException e) {
                insertado = false;
                e.printStackTrace();
            }
        }else{
            System.out.println("No se ha podido conectar a la base de datos");
            insertado = false;
        }
        return insertado;
    }


    public boolean delete() {
        Connection con = Connect.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(DELETE);
            ps.setInt(1, this.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update() {
        Connection con = Connect.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(UPDATE);
            ps.setString(1, this.getName());
            ps.setString(2, this.getNickname());
            ps.setString(3, this.getPassword());
            ps.setString(4, this.getBiografia());
            ps.setInt(5, this.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean login(String nickname, String password) {
        Connection con = Connect.getConnection();
        boolean logeado = false;
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(LOGIN);
                ps.setString(1, nickname);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    logeado=true;
                }else {
                    logeado=false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("No se ha podido conectar a la base de datos");
            logeado = false;
        }
        return logeado;
    }


    public List<User> getAllFollower() {
        Connection con = Connect.getConnection();
        List<User> followers = new ArrayList<>();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(GETALLFOLLOWER);
                ps.setInt(1, this.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setNickname(rs.getString("nickname"));
                    u.setPassword(rs.getString("password"));
                    u.setBiografia(rs.getString("biografia"));
                    followers.add(u);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return followers;
    }


    public List<User> getAllFollowing() {
        Connection con = Connect.getConnection();
        List<User> following = new ArrayList<>();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(GETALLFOLLOWING);
                ps.setInt(1, this.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setNickname(rs.getString("nickname"));
                    u.setPassword(rs.getString("password"));
                    u.setBiografia(rs.getString("biografia"));
                    following.add(u);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return following;
    }


    public boolean follow(User u) {
        Connection con = Connect.getConnection();
        boolean insertado = false;
        try {
            PreparedStatement ps = con.prepareStatement(FOLLOW);
            ps.setInt(1, this.getId());
            ps.setInt(2, u.getId());
            ps.executeUpdate();
            insertado = true;
        } catch (SQLException e) {
            insertado = false;
            e.printStackTrace();
        }
        return insertado;
    }


    public boolean unfollow(User u) {
        Connection con = Connect.getConnection();
        boolean borrado = false;
        try{
            PreparedStatement ps = con.prepareStatement(UNFOLLOW);
            ps.setInt(1, this.getId());
            ps.setInt(2, u.getId());
            ps.executeUpdate();
            borrado = true;
        }catch(SQLException e){
            borrado = false;
            e.printStackTrace();
        }
        return borrado;
    }


    public boolean like(User u, IPostDAO p) {

        return false;
    }


    public boolean unlike(User u, IPostDAO p) {

        return false;
    }


    public UserDAO find(int id) {
        Connection con = Connect.getConnection();
        UserDAO u = null;
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(FIND);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    u = new UserDAO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), null, null, null);
                }
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }
    
    public UserDAO find(String nickname) {
        Connection con = Connect.getConnection();
        UserDAO u = null;
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(FINDBYNICKNAME);
                ps.setString(1, nickname);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    u = new UserDAO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), null, null, null);
                }
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }
}
