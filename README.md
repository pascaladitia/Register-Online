# 📱 Register Online App

Android app for **offline registration (draft)** and **online synchronization** using Kotlin + Jetpack Compose.

---

## 🚀 Demo
<p align="center">
  <iframe width="320" height="180"
    src="https://your-video-link-here.com"
    frameborder="0"
    allowfullscreen>
  </iframe>
</p>

---

## ✨ Features
- Input user data (Name, NIK, Phone, Birth, Status, Occupation)
- KTP & Domicile address
- Capture KTP images (Primary & Secondary)
- Save as **Draft (Room DB)**
- Upload:
  - Single draft
  - All drafts
- Auto delete draft after upload
- Image auto compress (≤ 2MB)

---

## 🏗️ Architecture
Clean Architecture + MVVM

```
data/      → local (Room), remote (API)
domain/    → usecase
ui/        → screen, state, component
utils/     → helper (image, etc)
```

---

## 🔄 Flow
```
Register → Login → Input → Save Draft → List Draft → Upload → Delete → Member List
```

---

## 🛠️ How to Run
```bash
git clone https://github.com/pascaladitia/Register-Online.git
```

- Open in Android Studio
- Sync Gradle
- Run app ▶️

---

## 🧱 Tech Stack
- Kotlin
- Jetpack Compose
- StateFlow
- Koin
- Room
- Ktor
- CameraX
- Coil

---

## 📡 API (Multipart)
```
name, nik, phone, birth_place, birth_date,
status, occupation,
address, provinsi, kota_kabupaten, kecamatan, kelurahan, kode_pos,
alamat_domisili, provinsi_domisili, kota_kabupaten_domisili,
kecamatan_domisili, kelurahan_domisili, kode_pos_domisili,
ktp_file, ktp_file_secondary
```

---

## 👨‍💻 Author
Pascal Aditia Muclis

---
