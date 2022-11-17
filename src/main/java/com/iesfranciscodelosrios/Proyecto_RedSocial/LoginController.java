package com.iesfranciscodelosrios.Proyecto_RedSocial;

import com.iesfranciscodelosrios.Proyecto_RedSocial.model.DAO.UserDAO;
import com.iesfranciscodelosrios.Proyecto_RedSocial.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private UserDAO uDAO;
    private Utils u;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private AnchorPane signupPane;
    @FXML
    private AnchorPane passwordPane;
    @FXML
    private TextField nicknameField, nicknameFieldsignup, nameField;
    @FXML
    private PasswordField passwordField, passwordFieldsignup,confirnmPasswordField;

    private LoginController() {
        u = new Utils();
        uDAO = new UserDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginPane.setVisible(true);
        signupPane.setVisible(false);
        passwordPane.setVisible(false);
    }

    @FXML
    private void eventLogin() throws IOException {

        if (!nicknameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            String user = nicknameField.getText();
            String pass = DigestUtils.sha256Hex(passwordField.getText());
            if (uDAO.login(user, pass)) {
                u.mostrarInfo("Login", "Login correcto", "Bienvenido " + user);
                App.setRoot("MenuPrincipal");
            } else {
                u.mostrarAlerta("Login", "Login incorrecto", "Usuario o contraseña incorrectos");
            }
        } else {
            u.mostrarAlerta("Error", "Error", "Rellena todos los campos");
        }
    }

    @FXML
    private void eventSignup() {
            loginPane.setVisible(false);
            signupPane.setVisible(true);
            if (!nicknameFieldsignup.getText().isEmpty() && !passwordFieldsignup.getText().isEmpty() && !nameField.getText().isEmpty()) {
                signupPane.setVisible(false);
                passwordPane.setVisible(true);
            }else {
                u.mostrarAlerta("Error", "Campos vacios", "Rellene todos los campos");
            }
    }
    @FXML
    private void eventSignUpConfirm() throws IOException {
        UserDAO userDAO;
        if (!confirnmPasswordField.getText().isEmpty()) {
            String pass = DigestUtils.sha256Hex(confirnmPasswordField.getText());
            String nickname = nicknameFieldsignup.getText();
            String name = nameField.getText();
            if(confirnmPasswordField.getText().equals(passwordFieldsignup.getText())){
                userDAO = new UserDAO(-1,nickname, pass, name,"",null,null,null);
                userDAO.insert();
                u.mostrarInfo("Registro", "Registro correcto", "Bienvenido " + nickname);
                loginPane.setVisible(true);
                signupPane.setVisible(false);
                passwordPane.setVisible(false);
            }else{
                u.mostrarAlerta("Error", "Contraseñas no coinciden", "Las contraseñas no coinciden");
                nicknameFieldsignup.setText("");
                nameField.setText("");
                passwordFieldsignup.setText("");
                confirnmPasswordField.setText("");
                signupPane.setVisible(true);
                passwordPane.setVisible(false);
            }
            App.setRoot("MenuPrincipal");
        } else {
            u.mostrarAlerta("Error", "Error", "Rellena todos los campos");
        }
    }
    @FXML
    private void returnBack(){
        nicknameFieldsignup.setText("");
        nameField.setText("");
        passwordFieldsignup.setText("");
        loginPane.setVisible(true);
        signupPane.setVisible(false);
        passwordPane.setVisible(false);
    }
}
