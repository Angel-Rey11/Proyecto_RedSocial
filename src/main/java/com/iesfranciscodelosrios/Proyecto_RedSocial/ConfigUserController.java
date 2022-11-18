package com.iesfranciscodelosrios.Proyecto_RedSocial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.DataService;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DataObject.User;

import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Dialog;
import com.iesfranciscodelosrios.Proyecto_RedSocial.Assets.Loggers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
	
	@FXML
	private Button home;
	@FXML
	private Button profile;
	@FXML
	private MenuButton plus;
	@FXML
	private MenuItem settings;
	@FXML
	private MenuItem sign_off;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameUser.setText(DataService.userLogeado.getName());
		biography.setText(DataService.userLogeado.getBiografia());
		nickname.setText(DataService.userLogeado.getNickname());
	}
	
	@FXML
	private void modifyUser() throws IOException {
		
		String name = nameUser.getText();
		String bio = biography.getText();
		String nick = nickname.getText();
		
		if (DataService.userLogeado != null) {
			if (!nameUser.getText().isEmpty() && !biography.getText().isEmpty() && !nickname.getText().isEmpty()) {
				User u = new User(DataService.userLogeado.getId(), name, nick, DataService.userLogeado.getPassword(), bio);
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
	private void insertPasswordByChange() throws IOException {
		String pass = passwordUser.getText();
		
		if (!pass.isEmpty()) {
			this.AnchorPanePasswordNewModify();
		}else {
			Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "EL CAMPO CONTRASEÑA DEBE SER COMPLETADO");
			Loggers.LogsSevere("EL ÚNICO CAMPO QUE EXISTE DEBE SER COMPLETADO");
		}
	}
	
	private void AnchorPanePasswordNewModify() throws IOException {
		changePwd.setVisible(false);
		passwordNewModify.setVisible(true);
	}
	
	@FXML
	private void modifyPasswordUser() throws IOException {

		String pass = newPassword.getText();
		String passN = repeatNewPassword.getText();
		
		if (!pass.isEmpty() && !passN.isEmpty()) {
			if (pass.contentEquals(passN)) {
				User u = new User(DataService.userLogeado.getId(), DataService.userLogeado.getName(), DataService.userLogeado.getNickname(), pass, DataService.userLogeado.getBiografia());
				UserDAO udao = new UserDAO(u.getId());
				udao.update();
				Dialog.showConfirm("OPERACIÓN EXITOSA", "CAMBIOS REALIZADOS CON ÉXITO", "LA CONTRASEÑA HA SIDO MODIFICADA CORRECTAMENTE");
				App.setRoot("MenuPrincipal");
				Loggers.LogsInfo("CONTRASEÑA MODIFICADA");
			}
		} else {
			Dialog.showError("ERROR", "FALLO AL INTRODUCIR LA CONTRASEÑA", "TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
			Loggers.LogsSevere("TODOS LOS CAMPOS DEBEN SER COMPLETADOS");
		}
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
