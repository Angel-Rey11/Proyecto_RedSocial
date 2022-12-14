package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class PostDAO extends Post implements IPostDAO {
	
	//CONSULTAS DE MariaDB
	private final static String INSERT = "INSERT INTO Post (id,creation_date, text, id_user) VALUES (NULL, ?, ?, ?)";
	private final static String UPDATE = "UPDATE Post SET creation_date = ?, modification_date = ?, text = ?, id_user = ? WHERE id = ?";
	private final static String DELETE = "DELETE FROM Post WHERE id = ?";
	private final static String FIND = "SELECT id, creation_date, text, id_user FROM Post WHERE id = ?";
	private final static String FINDALLBYFOLLOWER = "SELECT p.* FROM Post as p, user as u, follow as f WHERE (p.id_user=f.id_user_following and f.id_user_follower=u.id and u.id=?) OR (p.id_user=u.id and u.id=?) GROUP BY p.id Order by p.creation_date desc;";
	private final static String FINDALLBYUSER="SELECT id,text,creation_date,id_user from Post where id_user=? GROUP BY creation_date DESC";
	//FIN DE LAS CONSULTAS
	
	//Constructores
	public PostDAO() {}
	public PostDAO(int id, Timestamp creationDate, String text, User user) { super(id, creationDate,text, user); }
	public PostDAO(Post p) {
		super(p.getId(), p.getCreationDate(), p.getText(), p.getUser());
	}
	public PostDAO(int id) {
		this.find(id);
	}
	
	/**
	 * Este método nos sirve para insertar todos los campos en la tabla Post.
	 * @return true si se añade los post, false si no se han insertado correctamente.
	 */
	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		boolean addPost = false;
		
		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setTimestamp(1, this.creationDate);
				ps.setString(2, this.text);
				ps.setInt(3, this.user.getId());
				ps.executeUpdate();  //devuelve 1 si todo ok
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					this.id = rs.getInt(1);
				}
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addPost;
	}

	/**
	 * Este método nos sirve para eliminar de la tabla Post, todos los post por la id
	 * principal.
	 * @return true si se elimina correctamente todos los post, false si no se ha eliminado.
	 */
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		boolean remove = false;
		
		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(DELETE);
				ps.setInt(1, this.id);
				if(ps.executeUpdate() == 1) {
					this.id = -1;
				}	
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return remove;
	}

	/**
	 * Este método nos sirve para actualizar todos los campos de la tabla Post.
	 * @return true si se han actualizado todos los campos de la tabla Post, false
	 * si no se han actualizado.
	 */
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		boolean refresh = false;
		
		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(UPDATE);
				ps.setTimestamp(1, this.creationDate);
				ps.setString(3, this.text);
				ps.setInt(4, this.user.getId());
				ps.setInt(5, this.id);
				ps.executeUpdate();  //devuelve 1 si todo ok
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return refresh;
	}

	/**
	 * Este método se encarga de obtener de la tabla Post, todos los campos
	 * de dicha tabla por la id principal de dicha tabla.
	 * 
	 * @param id el valor del campo por el que se obtiene. 
	 * @return el post obtenido o null si no existe.
	 */
	@Override
	public PostDAO find(int id) {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		PostDAO p = null;
		UserDAO u = null;
		if(con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(FIND);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					p = new PostDAO();
					u = new UserDAO();
					p.setId(rs.getInt("id"));
					p.setCreationDate(rs.getTimestamp("creation_date"));
					p.setText(rs.getString("text"));
					u.find(rs.getInt("id_user"));
					p.setUser(u);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	/**
	 * Este método se encarga de obtener una lista de todos los campos de
	 * la tabla Post.
	 * @return el post de la lista obtenida por sus campos.
	 */
	public static List<PostDAO> findAllByFollower() {
		UserDAO ud = new UserDAO();
		Connection con = Connect.getConnection();
		List<PostDAO> posts = new ArrayList<PostDAO>();

		if(con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(FINDALLBYFOLLOWER);
				ps.setInt(1, DataService.userLogeado.getId());
				ps.setInt(2, DataService.userLogeado.getId());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					PostDAO p = new PostDAO();
					p.setId(rs.getInt("id"));
					p.setText(rs.getString("text"));
					p.setCreationDate(rs.getTimestamp("creation_date"));
					p.setUser(ud.find(rs.getInt("id_user")));
					posts.add(p);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return posts;
	}
	
	/**
	 * Este método se encarga de obtener una lista de post por la id del usuario dado.
	 * @param id el valor del campo por el que se obtiene. 
	 * @return el post de la lista obtenida por el usuario.
	 */
	public static List<PostDAO> getPostsByUser(int id){
		UserDAO ud = new UserDAO();
		Connection con = Connect.getConnection();
		List<PostDAO> posts = new ArrayList<PostDAO>();

		if(con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(FINDALLBYUSER);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					PostDAO p = new PostDAO();
					p.setId(rs.getInt("id"));
					p.setText(rs.getString("text"));
					p.setCreationDate(rs.getTimestamp("creation_date"));
					p.setUser(ud.find(rs.getInt("id_user")));
					posts.add(p);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return posts;
	}
}
