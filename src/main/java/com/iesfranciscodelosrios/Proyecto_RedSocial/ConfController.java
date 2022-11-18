package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;

import javafx.fxml.FXML;

public class ConfController {
	@FXML
	private void switchToEditProfile() throws IOException {
		App.setRoot("ConfigUser");
	}
}
