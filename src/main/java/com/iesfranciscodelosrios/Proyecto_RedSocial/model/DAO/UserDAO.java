package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IUserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User implements IUserDAO {
    private final static String INSERT = "INSERT INTO User VALUES (null,?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM User WHERE id = ?";
    private final static String UPDATE = "UPDATE User SET name = ?,  nickname = ?, password = ?, biografia = ? WHERE id = ?";
    private final static String GETALLFOLLOWER = "SELECT * FROM User WHERE id IN (SELECT id_follower FROM Follow WHERE id_following = ?)";
    private final static String GETALLFOLLOWING = "SELECT * FROM User WHERE id IN (SELECT id_following FROM Follow WHERE id_follower = ?)";
    private final static String FOLLOW = "INSERT INTO Follow VALUES (?, ?)";
    private final static String UNFOLLOW = "DELETE FROM Follow WHERE id_follower = ? AND id_following = ?";
    private Connection con;
    public UserDAO(){
        con= (Connection) Connect.getConnection();
    }
    public UserDAO(int id, String name, String nickname, String password, String biografia) {
        super(id, name, nickname, password, biografia);
    }
    public UserDAO(User u){
        this(u.getId(), u.getName(), u.getNickname(), u.getPassword(), u.getBiografia());
    }



    @Override
    public boolean create(User u) {
        boolean insertado = false;
        try {
            PreparedStatement ps = this.con.prepareStatement(INSERT);
            ps.setString(1, u.getName());
            ps.setString(2, u.getNickname());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getBiografia());
            ps.executeUpdate();
            insertado = true;
        } catch (SQLException e) {
            insertado = false;
            e.printStackTrace();
        }
        return insertado;
    }

    @Override
    public boolean delete(User u) {
        try{
            PreparedStatement ps = this.con.prepareStatement(DELETE);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User u) {
        try{
            PreparedStatement ps = this.con.prepareStatement(UPDATE);
            ps.setString(1, u.getName());
            ps.setString(2, u.getNickname());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getBiografia());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllFollower() {
        List<User> followers = new ArrayList<>();
        if(this.con != null){
            try{
                PreparedStatement ps = this.con.prepareStatement(GETALLFOLLOWER);
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

    @Override
    public List<User> getAllFollowing() {
        List<User> following = new ArrayList<>();
        if(this.con != null){
            try{
                PreparedStatement ps = this.con.prepareStatement(GETALLFOLLOWING);
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

    @Override
    public boolean follow(User u, User u2) {
        boolean insertado = false;
        try {
            PreparedStatement ps = this.con.prepareStatement(FOLLOW);
            ps.setInt(1, u.getId());
            ps.setInt(2, u2.getId());
            ps.executeUpdate();
            insertado = true;
        } catch (SQLException e) {
            insertado = false;
            e.printStackTrace();
        }
        return insertado;
    }

    @Override
    public boolean unfollow(User u, User u2) {
        boolean borrado = false;
        try{
            PreparedStatement ps = this.con.prepareStatement(UNFOLLOW);
            ps.setInt(1, u.getId());
            ps.setInt(2, u2.getId());
            ps.executeUpdate();
            borrado = true;
        }catch(SQLException e){
            borrado = false;
            e.printStackTrace();
        }
        return borrado;
    }

    @Override
    public boolean like(User u, IPostDAO p) {
        return false;
    }

    @Override
    public boolean unlike(User u, IPostDAO p) {
        return false;
    }

    @Override
    public IUserDAO find(User u) {
        return null;
    }
}
