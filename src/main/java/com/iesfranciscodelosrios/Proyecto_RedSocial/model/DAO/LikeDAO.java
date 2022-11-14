package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ILikeDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Like;

public class LikeDAO extends Like implements ILikeDAO{
	private final static String INSERT = "INSERT INTO Likes (id_user, id_post) VALUES (?, ?)";
	private final static String DELETE = "DELETE FROM Likes WHERE id = ?";

	private Connection con;
	public LikeDAO() {
		con= (Connection) Connect.getConnection();
	}
	
	public LikeDAO(int id) {
		super(id);
	}
	
	public LikeDAO(Like l) {
		this(l.getId());
		this.user = l.getUser();
		this.post = l.getPost();
	}

	@Override
	public boolean create() {
		boolean added = false;
		
		if (this.con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setInt(1, this.user.getId());
				ps.setInt(2, this.post.getId());
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

		if (this.con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(DELETE);
				ps.setInt(1, this.id);
				ps.executeUpdate();
				removed = true;
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return removed;
	}
}
