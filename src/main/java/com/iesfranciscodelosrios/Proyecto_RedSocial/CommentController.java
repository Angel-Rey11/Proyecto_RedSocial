package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CommentController {
	@FXML
	private Label name;
	@FXML
	private Label comment2;
	
	public void setData(Comment co) {
		name.setText(co.getUser().getNickname()); 
		comment2.setText(co.getText());
	}
}
