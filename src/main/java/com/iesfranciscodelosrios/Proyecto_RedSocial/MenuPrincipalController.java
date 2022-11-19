package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
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
				post.initializePrivado();
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
		List<PostDAO> ls = PostDAO.findAllByFollower();
		
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
		Date date = Date.valueOf(LocalDate.now());
		PostDAO pd = new PostDAO(-1,date,post.getText(),DataService.userLogeado);
		pd.create();
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
