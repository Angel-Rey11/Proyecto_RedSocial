package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ILikeDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Like;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class LikeDAO extends Like implements ILikeDAO{
	private final static String INSERT = "INSERT INTO Likes (id_user, id_post) VALUES (?, ?)";
	private final static String DELETE = "DELETE FROM Likes WHERE id = ?";
	private final static String GETALLLIKES = "SELECT * FROM Likes WHERE id_post = ?";

	public LikeDAO() {
	}
	
	public LikeDAO(int id, User user, Post post) {
		super(id);
	}
	
	public LikeDAO(Like l) {
		this(l.getId(), l.getUser(), l.getPost());
	}

	@Override
	public boolean create() {
		boolean added = false;
		
		Connection con = Connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setInt(1, this.getUser().getId());
				ps.setInt(2, this.getPost().getId());
				ps.executeUpdate();
				added = true;
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return added;
	}

	@Override
	public boolean delete() {
		boolean removed = false;
		
		Connection con = Connect.getConnection();

		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, this.getId());
				ps.executeUpdate();
				removed = true;
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return removed;
	}
	public List<LikeDAO> getAllLikesbyPost( int id_post) {
		List<LikeDAO> likes = new ArrayList<LikeDAO>();
		LikeDAO l = new LikeDAO();
		UserDAO u = new UserDAO();
		PostDAO p = new PostDAO();
		Connection con = Connect.getConnection();
		if(con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(GETALLLIKES);
				ps.setInt(1,id_post);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					u = u.find(rs.getInt("id_user"));
					p = p.find(rs.getInt("id_post"));
					l.setId(rs.getInt("id"));
					u.setId(rs.getInt("id_user"));
					p.setId(rs.getInt("id_post"));
					l.setUser(u);
					l.setPost(p);
					likes.add(l);
				}
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return likes;
	}
}
