package com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Connection.Connect;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Interfaces.IFollowDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Follow;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

public class FollowDAO extends Follow implements IFollowDAO {
	
	//CONSULTAS DE MariaDB
	private final static String INSERT = "INSERT INTO Follow (id_user_follower, id_user_following) VALUES (?, ?)";
	private final static String UPDATE = "UPDATE Follow SET id_user_follower = ?, id_user_following = ? WHERE id = ?";
	private final static String DELETE = "DELETE FROM Follow WHERE id_user_follower = ? AND id_user_following = ?";
	private final static String FIND = "SELECT id, id_user_follower, id_user_following FROM Follow WHERE id = ?";
	//FIN DE LAS CONSULTAS
	
	public FollowDAO() {}
	public FollowDAO(int id, User follower, User following) { super(id, follower, following); }
	public FollowDAO(Follow f) {
		super(f.getId(), f.getFollower(), f.getFollowing());
	}
	public FollowDAO(int id) {
		this.find(id);
	}

	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		boolean addFollow = false;

		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, this.getFollower().getId());
				ps.setInt(2, this.getFollowing().getId());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					this.id = rs.getInt(1);
					addFollow = true;
				}
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addFollow;
	}

	@Override
	public boolean delete() {
		Connection con = Connect.getConnection();
		boolean borrado = false;
		try{
			PreparedStatement ps = con.prepareStatement(DELETE);
			ps.setInt(1, this.follower.getId());
			ps.setInt(2, this.following.getId());
			ps.executeUpdate();
			borrado = true;
		}catch(SQLException e){
			borrado = false;
			e.printStackTrace();
		}
		return borrado;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		boolean refresh = false;
		
		if(con != null) {
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(UPDATE);
				
				ps.setInt(3, this.id);
				ps.executeUpdate();  //devuelve 1 si todo ok
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return refresh;
	}

	@Override
	public FollowDAO find(int id) {
		// TODO Auto-generated method stub
		Connection con = Connect.getConnection();
		FollowDAO fdao = null;
		
		if(con != null) {
			PreparedStatement ps;
				try {
					ps = con.prepareStatement(FIND);
					ps.setInt(1, id);
					if(ps.execute()) {
						ResultSet rs = ps.getResultSet();
						if(rs.next()) {
							this.id = rs.getInt("id");
							int id_user_follower = rs.getInt("id_user_follower");
							this.follower = new UserDAO(id_user_follower);
							int id_user_following = rs.getInt("id_user_following");
							this.following = new UserDAO(id_user_following);
						}
					}
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return fdao;
	}
}
