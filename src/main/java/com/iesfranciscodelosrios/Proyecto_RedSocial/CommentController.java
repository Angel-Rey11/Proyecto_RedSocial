package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CommentController {
	private CommentDAO cd;
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
	@FXML
	private Label fecha;
	@FXML
	private AnchorPane an;
	@FXML
	private TextArea ta;
	
	public void setData(CommentDAO c) {
		name.setText(c.getUser().getNickname()); 
		comment2.setText(c.getText());
		this.cd = c;
		String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getDate());
		fecha.setText(s);
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
	
	@FXML
	private void deleteComment() {
		cd.delete();
	}
	
	@FXML
	private void showModifyComment() {
		an.setVisible(true);
	}
	
	@FXML
	private void modifyComment() {
		Timestamp date = new Timestamp(System.currentTimeMillis());;
		CommentDAO newComment = new CommentDAO(this.cd.getId(),ta.getText(),date,DataService.userLogeado,DataService.p);
		newComment.update();
		an.setVisible(false);
	}
}
