/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.login.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.login.App;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink linkDaftar;

    @FXML
    private Hyperlink lupaPassword;

    @FXML
    private PasswordField password;

    @FXML
    void btnLogin(ActionEvent event) {

    }

    @FXML
    void lupaPassword(ActionEvent event) throws IOException {

    }

    @FXML
    void toLink(ActionEvent event) throws IOException {
        App.setRoot("register");
    }
}
