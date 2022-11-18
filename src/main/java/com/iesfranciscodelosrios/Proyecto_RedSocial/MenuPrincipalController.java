package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.Post;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MenuPrincipalController implements Initializable {
	@FXML
	private GridPane postGrid;
	private List<PostDAO> posts;
	private List<PostDAO> ls;
	@FXML
	private DialogPane vis;
	@FXML
	private TextArea post;

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
	
	private List<PostDAO> posts() {
		ls = new ArrayList<>();
		
			PostDAO post = new PostDAO();
			User u = new User(1,"Pepe","Canela","1234","hola");
			post.setText("Hola");
			post.setUser(u);
			post.setId(1);
			ls.add(post);
			
			PostDAO post2 = new PostDAO();
			User u2 = new User(2,"Antonio","Pepito","1234","hola");
			post2.setText("QUE");
			post2.setUser(u2);
			post2.setId(2);
			ls.add(post2);
		
		
		return ls;
	}
	
	@FXML
	private void switchToProfile() throws IOException {
		App.setRoot("Perfil");
	}
	
	@FXML
	private void addPost() {
		vis.setVisible(true);
	}
	
	@FXML
	private void addPostConfirm() {
		PostDAO post3 = new PostDAO();
		post3.setText(post.getText());
		post3.setUser(DataService.userLogeado);
		post3.setId(6);
		ls.add(post3);
		vis.setVisible(false);
		
	}
	
	@FXML
	private void switchToConf() throws IOException {
		App.setRoot("Conf");
	}
	
	@FXML
	private void switchToLogin() throws IOException {
		App.setRoot("Login");
	}
 }
