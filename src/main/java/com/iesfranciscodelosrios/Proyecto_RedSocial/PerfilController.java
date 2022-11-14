package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class PerfilController implements Initializable{
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private Button mg;
	@FXML
	private Button dmg;
	@FXML
	private Button follow;
	@FXML
	private Button unfollow;
	@FXML
	private TextArea bio;
	
	@FXML
	private void mg() {
		img1.setVisible(false);
		img2.setVisible(true);
		mg.setDisable(true);
		dmg.setDisable(false);
	}
	
	@FXML
	private void dmg() {
		img1.setVisible(true);
		img2.setVisible(false);
		dmg.setDisable(true);
		mg.setDisable(false);
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
	private void addBio() {
		String text = bio.getText();
		bio.setText(text);
		bio.setEditable(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dmg.setDisable(true);
		
	}
}
