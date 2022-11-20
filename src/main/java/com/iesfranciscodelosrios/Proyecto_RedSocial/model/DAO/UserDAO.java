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
    private final static String UPDATE = "UPDATE User SET nickname = ?,  name = ?, password = ?, biografia = ? WHERE id = ?";
    private final static String GETALLFOLLOWER = "SELECT * FROM User WHERE id IN (SELECT id_user_follower FROM Follow WHERE id_user_following = ?)";
    private final static String GETALLFOLLOWING = "SELECT * FROM User WHERE id IN (SELECT id_user_following FROM Follow WHERE id_user_follower = ?)";
    private final static String FIND = "SELECT id, nickname, name, password, biografia FROM user WHERE id = ?";
    private final static String FINDBYNICKNAME = "SELECT id, nickname, name, password, biografia FROM user WHERE nickname = ?";
    private final static String LOGIN = "SELECT * FROM user WHERE nickname = ? AND password = ?";
    private final static String MODIFYBIO = "UPDATE `user` SET `biografia` = ? WHERE `user`.`id` = ?";
    private final static String RANDOMUSER = "SELECT * FROM `user` WHERE id NOT IN (?) ORDER BY RAND()*(25-10)+10 LIMIT 6";
    
    public UserDAO(){}
    public UserDAO(int id, String name, String nickname, String password, String biografia) {
        super(id, name, nickname, password, biografia);
    }
    public UserDAO(User u){
        this(u.getId(), u.getNickname(), u.getName(), u.getPassword(), u.getBiografia());
    }
    public UserDAO(int id) {
        this.find(id);
    }


    /**
     * Inserta un usuario en la base de datos
     * @return true si se ha insertado correctamente
     */
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

    /**
     * Elimina un usuario de la base de datos
     * @return true si se ha eliminado correctamente
     */
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

    /**
     * Actualiza un usuario de la base de datos
     * @return true si se ha actualizado correctamente
     */
    public boolean update() {
        Connection con = Connect.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(UPDATE);
            ps.setString(2, this.getName());
            ps.setString(1, this.getNickname());
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

    /**
     * Si los parametros coinciden con algun usuario de la base de datos permite entrar al programa
     * @param nickname nombre de usuario
     * @param password contraseña
     * @return true si se ha logueado correctamente
     */
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

    /**
     * Se trae todos los seguidores de un usuario
     * @return La lista con todos los seguidores
     */
    public List<UserDAO> getAllFollower() {
        Connection con = Connect.getConnection();
        List<UserDAO> followers = new ArrayList<>();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(GETALLFOLLOWER);
                ps.setInt(1, this.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    UserDAO u = new UserDAO();
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

    /**
     * Se trae todos los usuarios que sigue un usuario
     * @return La lista con todos los usuarios que sigue
     */
    public List<UserDAO> getAllFollowing() {
        Connection con = Connect.getConnection();
        List<UserDAO> following = new ArrayList<>();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(GETALLFOLLOWING);
                ps.setInt(1, this.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    UserDAO u = new UserDAO();
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
    /**
     * Busca a un usuario en la base de datos por su id
     * @param id id del usuario
     * @return El usuario encontrado
     */
    public UserDAO find(int id) {
        Connection con = Connect.getConnection();
        UserDAO u = null;
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(FIND);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    u = new UserDAO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                }
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    /**
     * Busca a un usuario en la base de datos por su nickname
     * @param nickname nickname del usuario
     * @return El usuario encontrado
     */
    public UserDAO find(String nickname) {
        Connection con = Connect.getConnection();
        UserDAO u = null;
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(FINDBYNICKNAME);
                ps.setString(1, nickname);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    u = new UserDAO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                }
                rs.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }
    
    /*public boolean changeBio() {
    	boolean modified = false;
    	Connection con = Connect.getConnection();
    	
    	if (con != null) {
    		try {
    			PreparedStatement ps = con.prepareStatement(MODIFYBIO);
    			ps.setString(1, this.getBiografia());
    			ps.setInt(2, this.getId());
    			ps.executeUpdate();
    			modified = true;
    			ps.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return modified;
    }*/

    /**
     * Funcion que trae una lista con usuarios random para mostrar en la pagina de sugerencias
     * @return La lista con los usuarios random
     */
    public List<UserDAO> getRandomUsers(){
    	Connection con = Connect.getConnection();
        List<UserDAO> random = new ArrayList<>();
        if(con != null){
            try{
                PreparedStatement ps = con.prepareStatement(RANDOMUSER);
                ps.setInt(1, DataService.userLogeado.getId());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    UserDAO u = new UserDAO();
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("name"));
                    u.setNickname(rs.getString("nickname"));
                    u.setPassword(rs.getString("password"));
                    u.setBiografia(rs.getString("biografia"));
                    random.add(u);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return random;
    }
}
