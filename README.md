# Fund+ (The 0 to 200k Blueprint)
*The Apex Financial Symbiote*

## Overview
Fund+ (sebelumnya ber-codename LumiVault) adalah aplikasi pencatat keuangan revolusioner yang dirancang dengan **Arsitektur Level Dewa (SDLC 0 to 200.000)**. Repositori ini menyimpan fondasi "Ground Zero" dari aplikasi ini.

## Architecture & Tech Stack (Multi-module)
- **Pattern**: MVI (Model-View-Intent) + Clean Architecture
- **Language**: Kotlin Modern
- **UI Framework**: Jetpack Compose / M3
- **Dependency Management**: Gradle Version Catalog
- **CI/CD**: GitHub Actions

## Folders
- `:app` - Core module penghubung UI dan navigasi utama.
- `:core:*` - Kumpulan modul raksasa untuk Network, Database tersandi, dan Komponen UI dasar.
- `:feature:*` - Kumpulan modul fungsional terpisah (Dashboard, Pencatatan, dll).

## How to Run
1. Buka melalui Android Studio.
2. Sinkronisasikan Gradle.
3. Arsitektur modular akan secara otomatis diregistrasi via `settings.gradle.kts`.
