package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.net.URL;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PostController implements Initializable {
	@FXML
	private Label name;
	@FXML
	private Label post2;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private Button mg;
	@FXML
	private Button dmg;
	
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
	
	public void setData(Post post) {
		name.setText(post.getUser().getNickname()); 
		post2.setText(post.getText());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dmg.setDisable(true);
		
	}
}
