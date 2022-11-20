package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SuggestUserController implements Initializable {
	private List<UserDAO> users;
	@FXML
	private GridPane postGrid;
	private UserDAO u = new UserDAO();
	
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
	@FXML
	private void switchToHome() throws IOException {
		App.setRoot("MenuPrincipal");
	}
	
	private List<UserDAO> users() {
		List<UserDAO> ud = u.getRandomUsers();
		
		return ud;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int columns = 0;
		int row = 1;
		users = new ArrayList<>(users());
		try {
			for (int i = 0; i < users.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("user.fxml"));
				AnchorPane an = fxmlLoader.load();
				UserController user = fxmlLoader.getController();
				user.setData(users.get(i));
				user.initializePrivado();
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
	}
}
