/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.login.model;

/**
 *
 * @author dwi
 */
public class User {
    private String nama;
    private long nik;
    private String alamat;
    private String noWa;
    private String email;
    private String password;
    private String jenisKelamin;

    public User(String nama, long nik, String alamat, String noWa, String email, String password, String jenisKelamin) {
        this.nama = nama;
        this.nik = nik;
        this.alamat = alamat;
        this.noWa = noWa;
        this.email = email;
        this.password = password;
        this.jenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getNik() {
        return nik;
    }

    public void setNik(long nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoWa() {
        return noWa;
    }

    public void setNoWa(String noWa) {
        this.noWa = noWa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
    
}
