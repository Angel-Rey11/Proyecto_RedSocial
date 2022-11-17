package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CommentController {
	@FXML
	private Label name;
	@FXML
	private Label comment2;
	
	public void setData(CommentDAO c) {
		name.setText(c.getUser().getNickname()); 
		comment2.setText(c.getText());
	}
}
