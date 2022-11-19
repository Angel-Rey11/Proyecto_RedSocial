package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.PostDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MenuPrincipalController implements Initializable {
	@FXML
	private GridPane postGrid;
	private List<PostDAO> posts;
	private List<UserDAO> users;
	@FXML
	private DialogPane vis;
	@FXML
	private TextArea post;
	private UserDAO u = new UserDAO();
	@FXML
	private Label sug;
	@FXML
	private ImageView img;
	@FXML
	private Button b;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		posts = new ArrayList<>(posts());
		users = new ArrayList<>(users());
		
		int columns = 0;
		int row = 1;
		
		if(DataService.userLogeado.getAllFollowing().size()<=0) {
			sug.setVisible(false);
			img.setVisible(false);
			b.setDisable(true);
			
			try {
				for (int i = 0; i < users.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("user.fxml"));
					AnchorPane an = fxmlLoader.load();
					UserController user = fxmlLoader.getController();
					user.setData(users.get(i));
					if(columns == 1) {
						columns = 0;
						++row;
					}
					
					postGrid.add(an, columns++, row);
					GridPane.setMargin(an, new Insets(10,10,10,90));
				}
			} catch (IOException e) {
					e.printStackTrace();
			}
			
		} else {
			sug.setVisible(true);
			img.setVisible(true);
			b.setDisable(false);
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
		
		
	}
	
	private List<PostDAO> posts() {
		List<PostDAO> ls = PostDAO.findAllByFollower();
		
		return ls;
	}
	
	private List<UserDAO> users() {
		List<UserDAO> ud = u.getRandomUsers();
		
		return ud;
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
		Timestamp date = new Timestamp(System.currentTimeMillis());;
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
	
	@FXML
	private void switchToSuggest() throws IOException {
		App.setRoot("SuggestUsers");
	}
 }
