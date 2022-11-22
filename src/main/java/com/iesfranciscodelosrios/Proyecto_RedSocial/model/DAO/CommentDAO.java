package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.ICommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

/**
 * Clase CommentDAO que hereda de Comment e implementa ICommentDAO
 * @author Francisco Berral, Antonio Jesús Luque, Francisco Prados, Ángel Rey  
 *
 */
public class CommentDAO extends Comment implements ICommentDAO {
	private UserDAO uDAO;
	private PostDAO pDAO;
	
	private final static String INSERT = "INSERT INTO Comments (text, date, id_user, id_post) VALUES (?, ?, ?, ?)";
	private final static String DELETE = "DELETE FROM Comments WHERE id = ?";
	private final static String UPDATE = "UPDATE Comments SET text = ?, date = ?, id_user = ?, id_post = ? WHERE id = ?";
	private final static String FIND = "SELECT id, text, date, id_user, id_post FROM Comments WHERE id = ?";
	private final static String GETALLBYPOST = "SELECT id, text, date, id_user FROM Comments WHERE id_post = ? ORDER BY date DESC";
	private final static String GETCOUNT = "SELECT COUNT(*) FROM Comments WHERE id_post = ?";

	/**
	 * Constructor
	 */
	public CommentDAO() {
		uDAO = new UserDAO();
		pDAO = new PostDAO();
	}
	
	/**
	 * Constructor 
	 * @param id ID del comentario
	 * @param text Texto del comentario
	 * @param date Fecha del comentario
	 * @param u Usuario del comentario
	 * @param p Post del comentario
	 */
	public CommentDAO(int id, String text, Timestamp date, User u, Post p) {
		super(id, text, date,u,p);
	}
	
	/**
	 * Constructor
	 * @param c Comentario
	 */
	public CommentDAO(Comment c) {
		this(c.getId(), c.getText(), c.getDate(), c.getUser(), c.getPost());
	}
	
	/**
	 * Constructor
	 * @param id ID del comentario
	 */
	public CommentDAO(int id) {
		this.find(id);
	}

	/**
	 * Método para añadir un comentario
	 * @return Si el comentario ha sido añadido
	 */
	@Override
	public boolean create() {
		boolean added = false;
		
		Connection con = Connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setString(1, this.getText());
				ps.setTimestamp(2, this.getDate());
				ps.setInt(3, this.getUser().getId());
				ps.setInt(4, this.getPost().getId());
				ps.executeUpdate();
				added = true;
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return added;
	}

	/**
	 * Método para borrar un comentario
	 * @return Si el comentario ha sido borrado
	 */
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

	/**
	 * Método para modificar un comentario
	 * @return Si el comentario ha sido modificado
	 */
	@Override
	public boolean update() {
		boolean modified = false;

		Connection con = Connect.getConnection();
		
		if (con != null) {
			try{
				PreparedStatement ps = con.prepareStatement(UPDATE);
				ps.setString(1, this.getText());
				ps.setTimestamp(2,this.getDate());
				ps.setInt(3, this.getUser().getId());
				ps.setInt(4, this.getPost().getId());
				ps.setInt(5, this.getId());
				ps.executeUpdate();
				modified = true;
				ps.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return modified;
	}

	/**
	 * Método para buscar un comentario por su id
	 * @param id ID del comentario
	 * @return Comentario encontrado
	 */
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
					c.setDate(rs.getTimestamp(3));
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
	
	/**
	 * Método para obtener todos los comentarios por la ID del post
	 * @param id ID del post
	 * @return Lista de comentarios del post
	 */
	@Override
	public List<CommentDAO> getAllCommentsByIdPost(int id) {
		UserDAO ud = new UserDAO();
		Connection con = Connect.getConnection();
		List<CommentDAO> list = new ArrayList<CommentDAO>();
		
		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(GETALLBYPOST);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					CommentDAO cDAO = null;
					cDAO = new CommentDAO();
					cDAO.setId(rs.getInt(1));
					cDAO.setText(rs.getString(2));
					cDAO.setDate(rs.getTimestamp("date"));
					cDAO.setUser(ud.find(rs.getInt("id_user")));
					list.add(cDAO);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getCommentsCount(int id_post) {
		Connection con = Connect.getConnection();
		int count = 0;

		if (con != null) {
			try {
				PreparedStatement ps = con.prepareStatement(GETCOUNT);
				ps.setInt(1, id_post);
				ResultSet rs = ps.executeQuery();
				rs.next();
				count = rs.getInt(1);
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
