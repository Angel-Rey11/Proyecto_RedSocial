package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Utils.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Utils.Dialog;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Utils.Loggers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConfigUserController implements Initializable {
	
	@FXML
	private AnchorPane editPerfil;
	@FXML
	private TextField nameUser;
	@FXML
	private TextArea biography;
	@FXML
	private TextField nickname;
	@FXML
	private Button update;
	@FXML
	private Button modifyPassword;
	
	@FXML
	private AnchorPane changePwd;
	@FXML
	private PasswordField passwordUser;
	@FXML
	private Button accept;
	
	@FXML
	private AnchorPane passwordNewModify;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField repeatNewPassword;
	@FXML
	private Button changeNewPassword;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameUser.setText(DataService.u.getName());
		biography.setText(DataService.u.getBiografia());
		nickname.setText(DataService.u.getNickname());
	}
	
	@FXML
	private void modifyUser() throws IOException {
		
		String name = nameUser.getText();
		String bio = biography.getText();
		String nick = nickname.getText();
		
		if (DataService.u != null) {
			if (!nameUser.getText().isEmpty() && !biography.getText().isEmpty() && !nickname.getText().isEmpty()) {
				User u = new User(DataService.u.getId(), name, nick, DataService.u.getPassword(), bio);
				UserDAO udao = new UserDAO(u.getId());
				udao.update();
				Dialog.showConfirm("Message", "CAMBIOS REALIZADOS CON ÉXITO", "EL USUARIO HA SIDO MODIFICADO CORRECTAMENTE");
				Loggers.LogsInfo("USUARIO MODIFICADO");
				
			}else {
				Dialog.showError("ERROR", "FALLO AL INTRODUCIR DATOS", "TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
				Loggers.LogsSevere("TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
			}
		}else {
			Dialog.showError("Message", "ERROR. NO EXISTE EL USUARIO ", "DEBES MOSTRAR EL USUARIO LOGUEADO");
			Loggers.LogsSevere("EL USUARIO NO EXISTE");
		}
		
	}
	
	@FXML
	private void changePassword() throws IOException {
		editPerfil.setVisible(false);
		changePwd.setVisible(true);
	}
	
	@FXML
	private void insertPasswordByChange() {
		String pass = passwordUser.getText();
		
		if (!pass.isEmpty()) {
			this.AnchorPanePasswordNewModify();
		}else {
			Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "EL CAMPO CONTRASEÑA DEBE SER COMPLETADO");
			Loggers.LogsSevere("EL ÚNICO CAMPO QUE EXISTE DEBE SER COMPLETADO");
		}
	}
	
	private void AnchorPanePasswordNewModify() {
		changePwd.setVisible(false);
		passwordNewModify.setVisible(true);
	}
	
	@FXML
	private void modifyPasswordUser() throws IOException {
		String pass = newPassword.getText();
		String passN = repeatNewPassword.getText();
		
		if (!pass.isEmpty() && !passN.isEmpty()) {
			if (pass.contentEquals(passN)) {
				//udao.update();
				Dialog.showConfirm("OPERACIÓN EXITOSA", "CAMBIOS REALIZADOS CON ÉXITO", "LA CONTRASEÑA HA SIDO MODIFICADA CORRECTAMENTE");
				App.setRoot("MenuPrincipal");
				Loggers.LogsInfo("CONTRASEÑA MODIFICADA");
			}
		} else {
			Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
			Loggers.LogsSevere("TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
		}
	}
}
