/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.login.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import system.login.App;
import system.login.model.AuthResponse;
import system.login.model.User;
import system.login.service.AuthService;
import system.login.utils.Flash;
import system.login.utils.TokenManager;

public class RegisterController {

    @FXML private TextField nama;
    @FXML private TextField nik;
    @FXML private TextArea alamat;
    @FXML private TextField noWa;
    @FXML private TextField email;
    @FXML private RadioButton radioBtn1; // Laki-laki
    @FXML private RadioButton radioBtn2; // Perempuan

    @FXML
    private void getDataPerson(ActionEvent event) {
        try {

            if (!inputValidasi()) {
                return;
            }

            String namaValue = nama.getText().trim();
            String nikString = nik.getText().trim();
            long nikValue = 0;
            String alamatValue = alamat.getText().trim();
            String emailValue = email.getText().trim();
            String passwordValue = "1234";
            String noWaValue = noWa.getText().trim();
            String jenisKelaminValue = radioBtn1.isSelected() ? "Laki-laki" : "Perempuan";

            nikValue = Long.parseLong(nikString);

            User request = new User(namaValue, nikValue, alamatValue, noWaValue, emailValue, passwordValue, jenisKelaminValue);
            try {
                AuthResponse response = AuthService.register(request);
            
                if (response != null && response.getToken() != null) {
                    System.out.println("Pendaftaran berhasil, Token: " + response.getToken());
                    TokenManager.setToken(response.getToken());
                    Flash.showAlert("Berhasil", "Pendaftaran Berhasil.", Alert.AlertType.INFORMATION);
                    clearForm();
                    App.setRoot("login");
                } else {
                    Flash.showAlert("Duplikat Data", "NIK atau email sudah terdaftar.", Alert.AlertType.ERROR);
                    return;
                }
            } catch (Exception e) {
                System.err.println("API Error saat registrasi: " + e.getMessage());
                e.printStackTrace();

                String errorMessage = e.getMessage().contains("Kesalahan API") || e.getMessage().contains("response code")
                        ? e.getMessage() 
                        : "Gagal terhubung ke server atau terjadi kesalahan tak terduga. Silakan coba lagi.";

                Flash.showAlert("Kesalahan Pendaftaran", errorMessage, Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            Flash.showAlert("Format NIK", "NIK harus angka.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void switchToLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    private boolean inputValidasi() {
        if (isEmpty(nama, "Nama")) {
            return false;
        }
        
        if (nama.getText().trim().length() < 3) {
            Flash.showAlert("Nama", "Nama minimal 3 karakter.", Alert.AlertType.ERROR);
            return false;
        }
        
        if (nama.getText().trim().matches(".*\\d.*")) {
            Flash.showAlert("Nama", "Nama tidak boleh mengandung angka.", Alert.AlertType.ERROR);
            return false;
        }

        if (isEmpty(nik, "Nik")) {
            return false;
        }
        
        String nikString = nik.getText().trim();
        if (!nikString.matches("\\d+")) {
            Flash.showAlert("NIK", "NIK harus angka", Alert.AlertType.ERROR);
            return false;
        }

        if (nikString.length() < 15) {
            Flash.showAlert("NIK", "NIK Minimal 16 Digit", Alert.AlertType.ERROR);
            return false;
        }
        
        if (nikString.length() > 18) {
            Flash.showAlert("NIK", "NIK Maksimal 18 Digit", Alert.AlertType.ERROR);
            return false;
        }

        if (isEmpty(alamat, "Alamat")) {
            return false;
        }
        
        if (!radioBtn1.isSelected() && !radioBtn2.isSelected()) {
            Flash.showAlert("Jenis Kelamin", "Pilih salah jenis kelamin anda", Alert.AlertType.ERROR);
            return false;
        }

        if (isEmpty(noWa, "No WhatsApp")) {
            return false;
        }
        
        if (!noWa.getText().trim().matches("\\d+")) {
            Flash.showAlert("No WhatsApp", "No WA harus angka", Alert.AlertType.ERROR);
            return false;
        }

        if (noWa.getText().trim().length() < 12) {
            Flash.showAlert("No WhatsApp", "Nomor WA Minimal 12", Alert.AlertType.ERROR);
            return false;
        }

        if (noWa.getText().trim().length() > 13) {
            Flash.showAlert("No WhatsApp", "Nomor WA Tidak lebih dari 13 digit", Alert.AlertType.ERROR);
            return false;
        }

        if (isEmpty(email, "Email")) {
            return false;
        }

        if (!email.getText().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            Flash.showAlert("Email", "alamat email tidak valid.", Alert.AlertType.ERROR);
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

    private boolean isEmpty(TextArea area, String title) {
        if (area.getText().trim().isEmpty()) {
            Flash.showAlert(title, title+" tidak boleh kosong", Alert.AlertType.ERROR);
            return true;
        }
        return false;
    }

    private void clearForm() {
        nama.clear();
        nik.clear();
        alamat.clear();
        noWa.clear();
        email.clear();
        radioBtn1.setSelected(false);
        radioBtn2.setSelected(false);
    }

}
