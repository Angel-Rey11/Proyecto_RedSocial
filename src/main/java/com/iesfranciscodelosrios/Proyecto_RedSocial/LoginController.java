package com.iesfranciscodelosrios.Proyecto_RedSocial;

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

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Utils u;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private AnchorPane signupPane;
    @FXML
    private AnchorPane passwordPane;
    @FXML
    private Button loginButton, signupButton,signUpconfirmButton,completeSignupButton;
    @FXML
    private TextField nicknameField, nicknameFieldsignup, nameField;
    @FXML
    private PasswordField passwordField, passwordFieldsignup,confirnmPasswordField;

    private LoginController() {
        u = new Utils();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginPane.setVisible(true);
        signupPane.setVisible(false);
        passwordPane.setVisible(false);
    }

    @FXML
    private void eventLogin(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(loginButton)) {
            if (!nicknameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                String user = nicknameField.getText();
                String pass = DigestUtils.sha256Hex(passwordField.getText());
            } else {
            }

        }
    }

    @FXML
    private void eventSignup(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(signupButton)) {
            loginPane.setVisible(false);
            signupPane.setVisible(true);
            if (!nicknameFieldsignup.getText().isEmpty() && !passwordFieldsignup.getText().isEmpty() && !nameField.getText().isEmpty()) {
                String user = nicknameFieldsignup.getText();
                String pass = DigestUtils.sha256Hex(passwordFieldsignup.getText());
                String name = nameField.getText();
                signupPane.setVisible(false);
                passwordPane.setVisible(true);
            }else{
                u.mostrarAlerta("Error", "Campos vacios", "Rellene todos los campos");
            }
        }
    }
}
