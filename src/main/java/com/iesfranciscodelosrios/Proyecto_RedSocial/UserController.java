package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.FollowDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController {
	@FXML
	private Label name;
	private UserDAO u;
	private FollowDAO fDAO;
	@FXML
	private Button follow;
	@FXML
	private Button unfollow;
	
	public void setData(UserDAO user) {
		name.setText(user.getNickname());
		this.u = user;
	}
	
	@FXML
	private void follow() {
		fDAO = new FollowDAO(-1, DataService.userLogeado, this.u);
		if(fDAO.create()) {
			unfollow.setVisible(true);
			follow.setVisible(false);
			unfollow.setDisable(false);
			follow.setDisable(true);
		}

	}
	
	@FXML
	private void unfollow() {
		fDAO = new FollowDAO(-1, DataService.userLogeado, this.u);
		if(fDAO.delete()) {
			unfollow.setVisible(false);
			follow.setVisible(true);
			unfollow.setDisable(true);
			follow.setDisable(false);
		}
	}
	public void initializePrivado(){
		DataService.userLogeado.getAllFollowing().forEach((u)->{
			if(u.getId() == this.u.getId()) {
				unfollow.setVisible(true);
				follow.setVisible(false);
				unfollow.setDisable(false);
				follow.setDisable(true);
			}else{
				unfollow.setVisible(false);
				follow.setVisible(true);
				unfollow.setDisable(true);
				follow.setDisable(false);
			}
		});
	}
}
