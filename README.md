# Janji

Saya Vivi Agustina Suryana dengan NIM 2400456 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek Untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.

# Flappy Bird Java Swing

Game Flappy Bird sederhana dibuat menggunakan Java Swing. Program ini menampilkan menu awal dan gameplay yang melibatkan pergerakan burung, pipa rintangan, serta sistem skor.

## Desain Program

Struktur utama terdiri dari lima kelas utama:

### 1. `App`
- Kelas utama untuk menjalankan program.
- Menampilkan menu utama (MainMenu) saat program dimulai.
- Setelah tombol **Play** ditekan, menu akan ditutup dan game dimulai.
- Mengatur JFrame utama untuk menampilkan area permainan.

### 2. `MainMenu`
- Menampilkan tampilan awal game dengan dua tombol utama: **Play** dan **Exit**.
- Background menggunakan gambar dari folder `assets`.
- Tombol **Play** memulai game, tombol **Exit** menutup program.
- Setelah **Play** diklik, variabel `startGame` berubah menjadi `true` agar kelas `App` memulai permainan.

### 3. `View`
- Panel utama tempat semua elemen game digambar.
- Menampilkan:
  - Background
  - Burung
  - Pipa
  - Skor
  - Teks “Tekan SPACE untuk mulai” dan “GAME OVER”
- Mengambil data dari `Logic` untuk menggambar posisi elemen.

### 4. `Logic`
- Mengatur seluruh mekanika permainan:
  - Gerakan burung (naik turun).
  - Penambahan dan pergerakan pipa.
  - Deteksi tabrakan (game over).
  - Perhitungan skor.
- Berkomunikasi dengan `View` agar tampilan diperbarui setiap frame.

### 5. `Player`
- Mewakili karakter burung pemain.
- Menyimpan posisi X, Y, ukuran, dan gambar burung.
- Memiliki getter dan setter untuk memudahkan pengaturan posisi.

### 6. `Pipe`
- Mewakili rintangan vertikal.
- Terdiri dari dua gambar: pipa atas dan bawah.
- Bergerak dari kanan ke kiri.
- Memiliki posisi dan ukuran yang bisa diatur melalui getter dan setter.

---

## Alur Program

1. Saat program dijalankan, muncul **Main Menu** dengan tombol *Play* dan *Exit*.
2. Jika pengguna menekan **Play**, menu ditutup dan game dimulai.
3. Burung muncul dan jatuh karena gravitasi.
4. Pemain menekan **SPACE** untuk membuat burung terbang.
5. Pipa muncul secara acak dari sisi kanan layar dan bergerak ke kiri.
6. Skor bertambah saat pemain berhasil melewati pipa.
7. Jika burung menabrak pipa atau jatuh, permainan berakhir.
8. Teks **“GAME OVER”** dan skor akhir ditampilkan.
9. Pemain bisa menekan **R** untuk memulai ulang permainan.

---

## Dokumentasi

### Tampilan Menu

<img width="347" height="632" alt="1  Tampilan awal" src="https://github.com/user-attachments/assets/7a39b2a3-8bd3-413e-b5d3-3e96946f106c" />


### Tampilan Game 

<img width="361" height="672" alt="2  Tampilan sebelum memulai game" src="https://github.com/user-attachments/assets/2b0df14b-12ed-4dd4-9be8-04468db57ae7" />



### Tampilan GameOver

<img width="347" height="632" alt="1  Tampilan awal" src="https://github.com/user-attachments/assets/b7836010-81be-48e1-a39b-6d1ea27107df" />


### Video GamePlay

https://github.com/user-attachments/assets/ebbf9b5b-bae2-4017-a1a8-e65fe2a3af44

