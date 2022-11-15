package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IPostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class PostDAO extends Post implements IPostDAO {
	
	private Connection con;
	
	//CONSULTAS DE MariaDB
	private final static String INSERT = "INSERT INTO Post (creation_date, modification_date, text, id_user) VALUES (?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE Post SET creation_date = ?, modification_date = ?, text = ?, id_user = ? WHERE id = ?";
	private final static String DELETE = "DELETE FROM Post WHERE id = ?";
	private final static String FIND = "SELECT id, creation_date, modification_date, text, id_user FROM Post WHERE id = ?";
	//FIN DE LAS CONSULTAS
	
	public PostDAO() {
		con = (Connection) Connect.getConnection();
	}
	public PostDAO(int id, Date creationDate, Date modificationDate, String text, User user) { super(id, creationDate, modificationDate, text, user); }
	public PostDAO(Post p) {
		super(p.getId(), p.getCreationDate(), p.getModificationDate(), p.getText(), p.getUser());
	}
	public PostDAO(int id) {
		this.find(id);
	}
	
	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		boolean addPost = false;
		
		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setDate(1, this.creationDate);
				ps.setDate(2, this.modificationDate);
				ps.setString(3, this.text);
				ps.setInt(4, this.user.getId());
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

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
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

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		boolean refresh = false;
		
		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(UPDATE);
				ps.setDate(1, this.creationDate);
				ps.setDate(2, this.modificationDate);
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

	@Override
	public PostDAO find(int id) {
		// TODO Auto-generated method stub
		PostDAO p = null;
		
		if(con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(FIND);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					p = new PostDAO();
					p.setId(rs.getInt("id"));
					p.setCreationDate(rs.getDate("creation_date"));
					p.setModificationDate(rs.getDate("modification_date"));
					p.setText(rs.getString("text"));
					//User u = UserDAO.find(rs.getInt("id_user"));
					//p.setUser(u);
					int id_user = rs.getInt("id_user");
					this.user = new UserDAO(id_user);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;
	}

}
