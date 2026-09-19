# MAFQOOD (مفقود)

> **AI-Powered Lost & Found Campus Recovery Platform**  
> *Built for University of Bisha (جامعة بيشة)*

---

## 📌 Overview

**MAFQOOD** is a modern, native Android application engineered to solve lost and found logistics across the University of Bisha campus. Powered by intelligent visual attribute matching, spatial campus zone mapping, and privacy-preserving ownership verification, MAFQOOD streamlines item reporting and recovery for students, faculty, and campus security.

---

## ✨ Key Features

### 1. 🔍 AI-Powered Multi-Factor Match Engine
- **Multi-Vector Scoring**: Calculates match confidence using visual similarity, color profiles, brand recognition, campus spatial proximity, and timestamp deltas.
- **Match Tiers**:
  - 🟢 **Likely Match (90%+)**: High attribute and visual alignment.
  - 🟡 **Possible Match (70%–89%)**: Significant overlap with minor spatial or temporal variations.
  - ⚪ **Low Confidence (<70%)**: Surface-level similarities flagged for manual review.
- **Explainable AI**: Provides transparent match criteria chips (e.g., *"Same color"*, *"Same brand"*, *"Found within 20m"*).

### 2. 📍 7-Node Item Recovery Lifecycle
Every lost or found item progresses through a structured, auditable journey:
1. **REPORT** – User submits lost or found item details and photo.
2. **ANALYZE** – AI analyzes photo features, attributes, and location metadata.
3. **MATCH** – System queries candidates across campus logs.
4. **SCORE** – Match candidate scored and confidence tier assigned.
5. **CLAIM** – Finder/Owner initiates claim process.
6. **VERIFY** – Private security question confirmation (prevents false claims).
7. **RECOVER** – Campus security handover & case closure.

### 3. 🗺️ Campus Spatial Mapping (University of Bisha)
- Dedicated campus zones:
  - **Zone A**: Science College & Engineering College
  - **Zone B**: Central Library & University Mosque
  - **Zone C**: Main Cafeteria & Student Union
  - **Zone D**: Administration Building
  - **Zone E**: Sports Complex
  - **Zone F & G**: Parking P1 & Student Housing
- Micro-location selection (floors, lobbies, study cubicles, outdoor benches).

### 4. 🔒 Privacy-Preserving Ownership Verification
- Dynamic verification challenge questions (e.g., hidden marks, custom stickers, lock screen details, engravings).
- Eliminates public exposure of unique item identifiers to prevent fraudulent claims.
- Campus Security Desk pickup coordination with official reference numbers (`MFQ-XXXX`).

### 5. 🌐 Fully Bilingual (English & العربية)
- Dynamic in-app language switching between **English** and **Arabic (العربية)**.
- Localized typography, terminology, campus building names, and status badges.

---

## 🛠️ Architecture & Tech Stack

- **Platform**: Android (Kotlin)
- **UI Framework**: Jetpack Compose (100% Declarative UI)
- **Design System**: Material Design 3 (M3) with custom University palette:
  - Primary: Deep Navy (`#0F172A`)
  - Accent / Brand: Vivid Teal (`#0D9488` / `#14B8A6`)
  - Warning / Alert: Signal Amber (`#F59E0B`)
  - Neutral Canvas: Off-White Slate (`#F8FAFC`)
- **State Management**: Kotlin Coroutines & `StateFlow`
- **Architecture**: MVVM / Repository Pattern (`MafqoodRepository`)
- **Testing**: Robolectric (JVM-level Android tests) & Roborazzi (visual screenshot testing)

---

## 📱 User Journeys

### Reporting a Lost Item
1. Open the app and tap **"I Lost Something"**.
2. **Step 1 - Photo**: Upload or capture an image of the item.
3. **Step 2 - Details**: Specify category, brand, primary color, and unique private clues.
4. **Step 3 - Location**: Select the campus building and zone.
5. **Step 4 - Time**: Choose estimated time lost using quick presets.
6. **Step 5 - Review**: Confirm details and submit for AI analysis.

### Finding & Returning an Item
1. Tap **"I Found Something"**.
2. Complete the 5-step wizard and declare the item's current location (e.g., *"Handed to Campus Security"* or *"With me"*).
3. The AI engine instantly cross-references active lost reports and notifies potential owners.

### Claiming & Collecting
1. Review candidate matches with score breakdowns.
2. Answer the private verification challenge.
3. Receive pickup authorization instructions for the **Campus Security Desk** (Main Administration Building, Ground Floor).

---

## 🧪 Testing & Verification

Run local unit and Robolectric tests:
```bash
gradle :app:testDebugUnitTest
```

Verify Roborazzi screenshot tests:
```bash
gradle :app:verifyRoborazziDebug
```

---

## 📄 License & Attribution

Built for the **University of Bisha** campus community. Powered by Google AI Studio.
