package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.FollowDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class PerfilAuxController implements Initializable{
	private FollowDAO fDAO;
	@FXML
	private Label nickname;
	@FXML
	private GridPane postGrid;
	@FXML
	private Button follow;
	@FXML
	private Button unfollow;
	@FXML
	private TextArea bio;
	@FXML 
	private Label nFollower;
	@FXML
	private Label nFollowing;
	@FXML 
	private Label nPost;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nickname.setText(DataService.pAux.getUser().getNickname());
		bio.setText(DataService.pAux.getUser().getBiografia());
		User pDAO = DataService.pAux.getUser();
		DataService.uAux = (UserDAO) pDAO;
		nFollower.setText(String.valueOf(DataService.uAux.getAllFollower().size()));
		nFollowing.setText(String.valueOf(DataService.uAux.getAllFollowing().size()));
		List<PostDAO> listPost = PostDAO.getPostsByUser(DataService.uAux.getId());
		nPost.setText(String.valueOf(listPost.size()));
	}
	
	@FXML
	private void follow() {
		fDAO = new FollowDAO(-1, DataService.userLogeado, DataService.pAux.getUser());
		if(fDAO.create()) {
			unfollow.setVisible(true);
			follow.setVisible(false);
			unfollow.setDisable(false);
			follow.setDisable(true);
		}

	}
	
	@FXML
	private void unfollow() {
		fDAO = new FollowDAO(-1, DataService.userLogeado, DataService.pAux.getUser());
		if(fDAO.delete()) {
			unfollow.setVisible(false);
			follow.setVisible(true);
			unfollow.setDisable(true);
			follow.setDisable(false);
		}
	}
	
	@FXML
	private void switchToMain() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	@FXML
	private void switchToLogin() throws IOException {
		App.setRoot("Login");
	}
	
	@FXML
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
}
