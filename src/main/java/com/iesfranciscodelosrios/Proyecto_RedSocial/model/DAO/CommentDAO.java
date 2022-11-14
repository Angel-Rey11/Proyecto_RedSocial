package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ICommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;

public class CommentDAO extends Comment implements ICommentDAO {
	
	private final static String INSERT = "INSERT INTO Comments (text, date, id_user, id_post) VALUES (?, ?, ?, ?)";
	private final static String DELETE = "DELETE FROM Comments WHERE id = ?";
	private final static String UPDATE = "UPDATE Comments SET text = ?, date = ?, id_user = ?, id_post = ? WHERE id = ?";
	private final static String FIND = "SELECT id, text, date, id_user, id_post FROM Comments WHERE id = ?";
	
	public CommentDAO() {
		
	}
	
	public CommentDAO(int id, String text, Date date) {
		super(id, text, date);
	}
	
	public CommentDAO(Comment c) {
		this(c.getId(), c.getText(), (Date) c.getDate());
		this.user = c.getUser();
		this.post = c.getPost();
	}
	
	public CommentDAO(int id) {
		this.find(id);
	}

	@Override
	public boolean create() {
		boolean added = false;
		
		Connection con = (Connection) Connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setString(1, this.text);
				ps.setDate(2, (Date) this.date);
				ps.setInt(3, this.user.getId());
				ps.setInt(4, this.post.getId());
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

	@Override
	public boolean update() {
		boolean modified = false;
		
		Connection con = Connect.getConnection();
		
		if (con != null) {
			PreparedStatement ps = con.prepareStatement(UPDATE);
			ps.setString(1, this.text);
			ps.setDate(2, (Date) this.date);
			ps.setInt(3, this.user.getId());
			ps.setInt(4, this.post.getId());
			ps.executeUpdate();
			modified = true;
			ps.close();
		}
		return modified;
	}

	@Override
	public CommentDAO find(int id) {
		CommentDAO c = null;
		Connection con = Connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(FIND);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					c = new CommentDAO();
					c.setId(rs.getInt(1));
					c.setText(rs.getString(2));
					c.setDate(rs.getDate(3));
					User u = UserDAO.find(rs.getInt(4));
					c.setUser(u);
					Post p = PostDAO.find(rs.getInt(5));
					c.setPost(p);
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
}
