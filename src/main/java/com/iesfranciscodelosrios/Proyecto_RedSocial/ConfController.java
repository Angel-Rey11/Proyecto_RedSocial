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
}
