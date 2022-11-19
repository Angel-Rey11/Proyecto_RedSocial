package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class PerfilController implements Initializable{
	@FXML
	private TextArea bio;
	@FXML
	private GridPane postGrid;
	@FXML
	private Label nickname;
	@FXML 
	private Label nFollower;
	@FXML
	private Label nFollowing;
	@FXML 
	private Label nPost;
	private List<PostDAO> posts;
	
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
	private void addBio() {
		String text = bio.getText();
		bio.setText(text);
		bio.setEditable(false);
		DataService.userLogeado.setBiografia(text);
		DataService.userLogeado.changeBio();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nickname.setText(DataService.userLogeado.getNickname());
		bio.setText(DataService.userLogeado.getBiografia());
		if (!bio.getText().equals("")) {
			bio.setEditable(false);
		}
		
		nFollower.setText(String.valueOf(DataService.userLogeado.getAllFollower().size()));
		nFollowing.setText(String.valueOf(DataService.userLogeado.getAllFollowing().size()));
		List<PostDAO> listPost = PostDAO.getPostsByUser(DataService.userLogeado.getId());
		nPost.setText(String.valueOf(listPost.size()));
		
		posts = new ArrayList<>(posts());
		
		int columns = 0;
		int row = 1;
		try {
			for (int i = 0; i < posts.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("post.fxml"));
				AnchorPane an = fxmlLoader.load();
				PostController post = fxmlLoader.getController();
				post.setData(posts.get(i));
				
				if(columns == 1) {
					columns = 0;
					++row;
				}
				
				postGrid.add(an, columns++, row);
				GridPane.setMargin(an, new Insets(10));
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	private List<PostDAO> posts() {
		List<PostDAO> ls = PostDAO.getPostsByUser(DataService.userLogeado.getId());
		return ls;
	}
}
