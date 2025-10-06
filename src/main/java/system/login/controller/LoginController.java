/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.login.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.login.App;
import system.login.model.AuthResponse;
import system.login.service.AuthService;
import system.login.utils.Flash;
import system.login.utils.TokenManager;

public class LoginController {

    @FXML private   Button btnLogin;
    @FXML private   TextField email;
    @FXML private   PasswordField password;
    @FXML private   Hyperlink linkDaftar;
    @FXML private   Hyperlink lupaPassword;

    @FXML
    private void btnLogin(ActionEvent event) {
        try {
            if (!inputValidasi()) {
                return;
            }
            
            String emailValue = email.getText().trim();
            String passwordValue = password.getText().trim();
            
            AuthResponse response = AuthService.login(emailValue, passwordValue);
            
            if (response.getToken() != null) {
                Flash.showAlert("Login berhasil! Selamat datang, ", response.getUser().getNama(), Alert.AlertType.INFORMATION);
                TokenManager.setToken(response.getToken());
                App.setRoot("home");
            } else {
                Flash.showAlert("Login gagal", "Email atau password salah.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            Flash.showAlert("Terjadi kesalahan: ", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    @FXML
    void lupaPassword(ActionEvent event) throws IOException {

    }

    @FXML
    void toLink(ActionEvent event) throws IOException {
        App.setRoot("register");
    }
    
    private boolean inputValidasi() {
        if (isEmpty(email, "Email")) {
            return false;
        }
        
        if (!email.getText().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Flash.showAlert("Email", "alamat email tidak valid.", Alert.AlertType.ERROR);
            return false;
        }
        
        if (isEmpty(password, "Password")) {
            return false;
        }

        return true;
    }
    
    private boolean isEmpty(TextField field, String title) {
        if (field.getText().trim().isEmpty()) {
            Flash.showAlert(title, title+" tidak boleh kosong", Alert.AlertType.ERROR);
            return true;
        }
        return false;
    }
    
    private void clearForm() {
        email.clear();
        password.clear();
    }
}
