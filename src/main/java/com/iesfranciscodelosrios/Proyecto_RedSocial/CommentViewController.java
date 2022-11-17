package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.CommentDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Comment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CommentViewController implements Initializable {
	@FXML
	private Label name;
	
	@FXML
	private Label post;
	
	@FXML
	private ImageView image1;
	
	@FXML
	private ImageView image2;
	
	@FXML
	private GridPane commentGrid;
	
	private List<CommentDAO> comment;
	
	CommentDAO cDAO = new CommentDAO();
	PostDAO pDAO = new PostDAO();
	
	@FXML
	private void switchToMain() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	@FXML
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	@FXML
	private void switchToLogin() throws IOException {
		App.setRoot("Login");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comment = new ArrayList<>(comments());
		
		int columns = 0;
		int row = 1;
		
		try {
			for (int i = 0; i < comment.size(); i++) {
				FXMLLoader f = new FXMLLoader();
				f.setLocation(getClass().getResource("comment.fxml"));
				AnchorPane a = f.load();
				CommentController c = f.getController();
				c.setData(comment.get(i));
				
				if (columns == 1) {
					columns = 0;
					++row;
				}
				
				commentGrid.add(a, columns++, row);
				GridPane.setMargin(a, new Insets(10));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		name.setText(String.valueOf(DataService.p.getUser().getNickname()));
		post.setText(DataService.p.getText());
	}
	
	private List<CommentDAO> comments() {
		List<CommentDAO> list = cDAO.getAllCommentsByIdPost(DataService.p.getId());
		
		for (int i = 0; i < list.size() ; i++) {
			CommentDAO comment = new CommentDAO();
			comment.setText(list.get(i).getText());
			comment.setUser(list.get(i).getUser());
			list.add(comment);
		}
		
		return list;
	}
}
