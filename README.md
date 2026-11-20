# BRAVIA NEXUS v1.0

**Premium Futuristic Android TV Dashboard for Sony BRAVIA KD-55A8H**

## Design Philosophy
Futuristic luxury technology × cinematic minimalism × dark glassmorphism. Designed specifically for 55-inch 4K OLED Sony Android TV (Android TV 10, UR3 Platform).

## Features
- **Cinematic Dark Glassmorphism UI**: Polished custom dark theme (`#080D15` background with `#56D9FF` cyan highlights).
- **Live Digital Clock & Date**: Real-time updating display.
- **Featured Cinematic Hero Banner**: Immersive showcase panel.
- **Application Dock**: Direct launching of streaming services (Netflix, YouTube, Prime Video, Disney+, Spotify) using Android package intents with graceful fallback.
- **Quick Settings Shortcuts**: Fast access to Wi-Fi, Bluetooth, Display, Sound, and System Settings.
- **D-Pad Remote Optimization**: Full directional focus handling and smooth focus animations for Sony TV remotes.

---

## Build Instructions (GitHub Actions)
1. Push this repository to GitHub.
2. Go to the **Actions** tab in your GitHub repository.
3. The workflow **Build Android TV APK** will automatically trigger and compile the project.
4. Once completed, download the `bravia-nexus-debug` artifact (containing `app-debug.apk`).

---

## Installation Guide on Sony BRAVIA Android TV (Android TV 10)

1. **Transfer APK to USB Drive**:
   - Copy the downloaded `app-debug.apk` onto a FAT32/exFAT formatted USB flash drive.
2. **Connect to Sony BRAVIA**:
   - Insert the USB drive into one of the USB ports on your Sony BRAVIA KD-55A8H.
3. **Enable Unknown Sources**:
   - On your TV, go to **Settings** > **Device Preferences** > **Security & restrictions** > **Unknown sources** and enable it for your file manager (e.g., *File Commander* or *X-plore*).
4. **Install Application**:
   - Open your file manager app on the TV, navigate to the USB drive, select `app-debug.apk`, and click **Install**.
5. **Launch**:
   - BRAVIA NEXUS will appear in your app drawer and leanback launcher. Enjoy your futuristic TV dashboard!
