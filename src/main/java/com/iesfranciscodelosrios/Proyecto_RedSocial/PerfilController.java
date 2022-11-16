package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class PerfilController implements Initializable{
	@FXML
	private Button follow;
	@FXML
	private Button unfollow;
	@FXML
	private TextArea bio;
	@FXML
	private GridPane postGrid;
	private List<Post> posts;
	
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
	
	private List<Post> posts() {
		List<Post> ls = new ArrayList<>();
		
		for(int i = 0; i<5; i++) {
			Post post = new Post();
			User u = new User(2,"Pepe","Pepito","1234","hola");
			post.setText("Hola");
			post.setUser(u);
			ls.add(post);
		}
		
		return ls;
	}
}
