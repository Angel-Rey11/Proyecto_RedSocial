package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;

import javafx.fxml.FXML;

public class ConfController {
	@FXML
	private void switchToEditProfile() throws IOException {
		App.setRoot("ConfigUser");
	}
	
	@FXML
	private void switchToHome() throws IOException {
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
}
