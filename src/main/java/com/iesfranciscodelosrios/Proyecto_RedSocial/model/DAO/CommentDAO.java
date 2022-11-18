package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ICommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class CommentDAO extends Comment implements ICommentDAO {
	private Connection con;
	private UserDAO uDAO;
	private PostDAO pDAO;
	
	private final static String INSERT = "INSERT INTO Comments (text, date, id_user, id_post) VALUES (?, ?, ?, ?)";
	private final static String DELETE = "DELETE FROM Comments WHERE id = ?";
	private final static String UPDATE = "UPDATE Comments SET text = ?, date = ?, id_user = ?, id_post = ? WHERE id = ?";
	private final static String FIND = "SELECT id, text, date, id_user, id_post FROM Comments WHERE id = ?";
	private final static String GETALLBYPOST = "SELECT id, text, date, id_user, id_post FROM Comments WHERE id_post = ?";
	
	public CommentDAO() {
		con = Connect.getConnection();
		uDAO = new UserDAO();
		pDAO = new PostDAO();
	}
	
	public CommentDAO(int id, String text, Date date, User u, Post p) {
		super(id, text, date);
	}
	
	public CommentDAO(Comment c) {
		this(c.getId(), c.getText(), (Date) c.getDate(), c.getUser(), c.getPost());
	}
	
	public CommentDAO(int id) {
		this.find(id);
	}

	@Override
	public boolean create() {
		boolean added = false;
		
		if (this.con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setString(1, this.text);
				ps.setDate(2, (java.sql.Date) this.date);
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

	@Override
	public boolean update() {
		boolean modified = false;

		
		if (this.con != null) {
			try{
				PreparedStatement ps = con.prepareStatement(UPDATE);
				ps.setString(1, this.text);
				ps.setDate(2, (java.sql.Date) this.date);
				ps.setInt(3, this.user.getId());
				ps.setInt(4, this.post.getId());
				ps.setInt(5, this.id);
				ps.executeUpdate();
				modified = true;
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return modified;
	}

	@Override
	public CommentDAO find(int id) {
		CommentDAO c = null;

		if (this.con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(FIND);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					c = new CommentDAO();
					c.setId(rs.getInt(1));
					c.setText(rs.getString(2));
					c.setDate(rs.getDate(3));
					User u = uDAO.find(rs.getInt(4));
					c.setUser(u);
					Post p = pDAO.find(rs.getInt(5));
					c.setPost(p);
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public List<CommentDAO> getAllCommentsByIdPost(int id) {
		List<CommentDAO> list = null;
		
		if (this.con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(GETALLBYPOST);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				CommentDAO cDAO = null;
				if (rs.next()) {
					list = new ArrayList<CommentDAO>();
					cDAO = new CommentDAO();
					cDAO.setId(rs.getInt(1));
					cDAO.setText(rs.getString(2));
					cDAO.setDate(rs.getDate(3));
					User u = uDAO.find(rs.getInt(4));
					cDAO.setUser(u);
					Post p = pDAO.find(rs.getInt(5));
					cDAO.setPost(p);
					list.add(cDAO);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
