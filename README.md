# Login system

---

## Tujuan Pengembangan Sistem
Tujuan utama pengembangan aplikasi ini adalah:
    - Menyediakan antarmuka desktop berbasis JavaFX untuk melakukan login dan registrasi pengguna.
    - Menghubungkan aplikasi client dengan server REST API guna melakukan operasi autentikasi secara online.
    - Menyediakan sistem autentikasi aman antara client dan server (menggunakan protokol HTTP dengan JSON).
    - Menjadi pondasi awal pengembangan sistem multi-platform berbasis arsitektur client–server.

---

## Ruang Lingkup Sistem
Ruang lingkup sistem pada tahap pengembangan ini meliputi:
- **Aplikasi Client (JavaFX)**:
    - Modul login dan registrasi pengguna.
    - Menampilkan pesan sukses atau gagal dari hasil respons server.
    - Mengelola sesi pengguna setelah login berhasil.
- **Aplikasi Server (REST API)**:
    - Endpoint `/register` untuk registrasi pengguna baru.
    - Endpoint `/login` untuk autentikasi pengguna.
    - Endpoint `/user` untuk mengambil profil pengguna (opsional).
Sistem dapat dikembangkan lebih lanjut untuk menambah fitur seperti dashboard pengguna, otorisasi token (JWT), dan pengelolaan data profil.

---

## Identifikasi Aktor Sistem
| No | Aktor                           | Deskripsi                                                                                                           |
| -- | ------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| 1  | **Pengguna (User)**             | Menggunakan aplikasi JavaFX untuk mendaftar dan login ke sistem.                                                    |
| 2  | **Client Application (JavaFX)** | Mengirim permintaan (request) ke server REST API dan menampilkan hasilnya kepada pengguna.                          |
| 3  | **Server REST API**             | Memproses request dari client, memverifikasi data, mengakses database, dan mengembalikan respons dalam format JSON. |

---

## Identifikasi Aktor Sistem
| No | Aktor                           | Deskripsi                                                                                                           |
| -- | ------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| 1  | **Pengguna (User)**             | Menggunakan aplikasi JavaFX untuk mendaftar dan login ke sistem.                                                    |
| 2  | **Client Application (JavaFX)** | Mengirim permintaan (request) ke server REST API dan menampilkan hasilnya kepada pengguna.                          |
| 3  | **Server REST API**             | Memproses request dari client, memverifikasi data, mengakses database, dan mengembalikan respons dalam format JSON. |

---

## Kebutuhan Fungsional (Functional Requirements)

| No | Kebutuhan Fungsional           | Deskripsi                                                                                                      |
| -- | ------------------------------ | -------------------------------------------------------------------------------------------------------------- |
| 1  | **Registrasi Pengguna**        | Client harus menyediakan form registrasi (nama, email, password) dan mengirimkan data ke endpoint `/register`. |
| 2  | **Validasi Input Client**      | Client harus memastikan data input tidak kosong sebelum dikirim ke server.                                     |
| 3  | **Validasi Server**            | Server harus memeriksa duplikasi email dan menyimpan data pengguna baru ke database.                           |
| 4  | **Login Pengguna**             | Client mengirimkan data login ke endpoint `/login` dan menunggu respons autentikasi.                           |
| 5  | **Menampilkan Respons Server** | Client menampilkan pesan sukses/gagal berdasarkan hasil respons JSON dari server.                              |
| 6  | **Akses Halaman Home**         | Jika login berhasil, client menampilkan halaman utama (home/dashboard).                                        |
| 7  | **Manajemen Sesi Client**      | Client menyimpan token (session key) sementara setelah login berhasil.                                         |
| 8  | **Logout**                     | Client dapat menghapus data sesi dan kembali ke halaman login.                                                 |

---

## Kebutuhan Non-Fungsional (Non-Functional Requirements)
| No | Kebutuhan Non-Fungsional           | Deskripsi                                                                            |
| -- | ---------------------------------- | ------------------------------------------------------------------------------------ |
| 1  | **Keamanan (Security)**            | Data login dikirim melalui HTTPS; password tidak ditampilkan di UI.                  |
| 2  | **Kompatibilitas (Compatibility)** | Client dapat berjalan di sistem operasi Windows, Linux, dan macOS.                   |
| 3  | **Performa (Performance)**         | Waktu respons API tidak melebihi 3 detik.                                            |
| 4  | **Reliabilitas (Reliability)**     | Koneksi antara client dan server tetap stabil meskipun jaringan berubah.             |
| 5  | **Maintainability**                | Kode client modular (MVC pattern) agar mudah dikembangkan.                           |
| 6  | **Scalability**                    | Sistem dapat dikembangkan menjadi aplikasi multi-user dengan tambahan modul backend. |

---

## Deskripsi Data
Struktur Data Pengguna (User Object)

Data pengguna yang digunakan oleh client dan server dalam format JSON:

```bash
{
  "id": 1,
  "nama": "Bahlil",
  "email": "bahlil@example.com",
  "password": "hashed_password",
  "createdAt": "2025-10-07T10:00:00Z"
}

```

---

## Data Request/Response
| Endpoint    | Method | Request Body                                  | Response                                                    |
| ----------- | ------ | --------------------------------------------- | ----------------------------------------------------------- |
| `/register` | POST   | `{ "nama": "", "email": "", "password": "" }` | `{ "status": "success", "message": "Registrasi berhasil" }` |
| `/login`    | POST   | `{ "email": "", "password": "" }`             | `{ "status": "success", "token": "jwt_token" }`             |
| `/user`     | GET    | Header: `Authorization: Bearer <token>`       | `{ "id": 1, "nama": "Bahlil", "email": "bahlil@example.com" }`    |

---

## Diagram Konteks Sistem
```bash
+-------------------+           +-----------------------+
|    Pengguna       |           |     Server REST API   |
|-------------------|           |-----------------------|
| - Input data login|           | - Terima request JSON |
| - Input registrasi|           | - Validasi data       |
|                   |           | - Akses database      |
+--------+----------+           | - Kirim response JSON |
         |                      +-----------+-----------+
         |                                   ^
         |  Request (JSON via HTTP)          |
         v                                   |
+----------------------------+                |
|   Aplikasi Client (JavaFX) |----------------+
| - Form login & register    |
| - Kirim HTTP Request       |
| - Tampilkan hasil respons  |
+----------------------------+

```
