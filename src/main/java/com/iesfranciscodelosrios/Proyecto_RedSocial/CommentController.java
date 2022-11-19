package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CommentController {
	@FXML
	private Label name;
	@FXML
	private Label comment2;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private Button mg;
	@FXML
	private Button dmg;
	
	public void setData(CommentDAO c) {
		name.setText(c.getUser().getNickname()); 
		comment2.setText(c.getText());
	}
	
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
}
