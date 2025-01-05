---

# GST Calculator 🧮

A simple, user-friendly **GST Calculator** built with **Jetpack Compose** in **Kotlin**. This app allows users to calculate GST (Goods and Services Tax) by entering the amount and selecting the GST rate. It supports dynamic rate selection, bidirectional calculations, and GST breakdowns for IGST, CGST, and SGST.

---

## 🚀 Features

- **Dynamic GST Rate Selection**: Choose from predefined GST rates (0.25%, 3%, 5%, 12%, 18%, 28%).
- **Bi-Directional Calculations**: 
  - Enter the amount without GST to calculate the total amount and GST.
  - Enter the total amount with GST to calculate the base amount and GST.
  - Directly enter the GST amount for reverse calculations.
- **GST Breakdown**: Displays IGST, CGST, and SGST values.
- **Clear Functionality**: Resets all inputs and highlights the "Clear" button in red.
- **Responsive UI**: Designed to work on devices of all screen sizes, with buttons arranged in two rows.

---

## 🛠️ Technologies Used

- **Kotlin**: Programming language.
- **Jetpack Compose**: Declarative UI framework for building native Android apps.
- **Material3**: For modern UI design components.

---

## 🧑‍💻 How to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Sidd-Tiwari/gst-calculator.git
   cd gst-calculator
   ```

2. **Open the project** in Android Studio.

3. **Build and Run**:
   - Connect an Android device or start an emulator.
   - Click on the "Run" button to install and run the app.

---

## 📂 Project Structure

```
├── MainActivity.kt          # Main activity of the app
├── ScaffoldView.kt          # Main UI scaffold with LazyColumn and buttons
├── GSTCalculatorTheme.kt    # UI theme definitions
└── utils                    # Utility functions like validateInput
```

---

## ✨ Highlights

- **Real-Time Updates**: Values auto-update when inputs or GST rates change.
- **Validation**: Ensures inputs are valid numerical values.
- **Customizable**: Easily extend with additional GST rates or functionality.

---

## 🤝 Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve this project.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 🌟 Acknowledgments

Thanks to the open-source community for their inspiration and guidance in creating this project.

---

Feel free to copy, modify, and adjust the content as needed for your project.
