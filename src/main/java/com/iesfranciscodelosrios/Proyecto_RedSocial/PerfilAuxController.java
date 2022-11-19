package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class PerfilAuxController implements Initializable{
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nickname.setText(DataService.pAux.getUser().getNickname());
		bio.setText(DataService.pAux.getUser().getBiografia());
	}
	
	@FXML
	private void follow() {
		unfollow.setVisible(false);
		follow.setVisible(true);
		unfollow.setDisable(true);
		follow.setDisable(false);
	}
	
	@FXML
	private void unfollow() {
		unfollow.setVisible(true);
		follow.setVisible(false);
		follow.setDisable(true);
		unfollow.setDisable(false);
		
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