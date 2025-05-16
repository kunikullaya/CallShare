Here's a comprehensive technical guide for integrating the PingOne Verify SDK into your SwiftUI application using the MVVM architecture. This documentation includes setup instructions, MVVM integration, and UX customization examples.

---

# 📘 PingOne Verify SDK Integration in SwiftUI (MVVM)

## Overview

PingOne Verify enables secure identity verification by capturing and validating government-issued IDs and biometric data. This guide demonstrates how to integrate the PingOne Verify SDK into a SwiftUI application following the MVVM architecture. 

---

## 🔧 Prerequisites

* Xcode 14 or later
* Swift 5.7+
* iOS 14+
* PingOne account with Verify service enabled
* Configured PingOne Verify policy([Ping Identity Documentation][2])

---

## 📦 SDK Installation

### Using Swift Package Manager

1. In Xcode, navigate to **File > Add Packages...**
2. Enter the repository URL:

   ```swift
   https://github.com/pingidentity/pingone-verify-mobile-sdk-ios
   ```



3. Select the desired version and add the package to your project.

---

## 🧱 Project Structure (MVVM)

Organize your project as follows:

```

├── Models/
│   └── VerificationResult.swift
├── ViewModels/
│   └── VerificationViewModel.swift
├── Views/
│   └── VerificationView.swift
├── Services/
│   └── PingOneVerifyService.swift
├── Resources/
│   └── Branding/
│       ├── logo.png
│       └── colors.json
└── App.swift
```



---

## 🧩 Integration Steps

### 1. Configure PingOne Verify

Set up your PingOne environment and create a Verify policy. Refer to the [PingOne documentation](https://docs.pingidentity.com/pingone/identity_verification_using_pingone_verify/p1_getting_started_with_p1_verify.html) for detailed instructions.

### 2. Implement the Service Layer

Create a service to handle interactions with the PingOne Verify SDK.

```swift
import Foundation
import PingOneVerify

class PingOneVerifyService {
    private var verifyClient: PingOneVerifyClient?

    init() {
        let config = PingOneVerifyConfiguration(environmentId: "YOUR_ENVIRONMENT_ID")
        self.verifyClient = PingOneVerifyClient(configuration: config)
    }

    func startVerification(completion: @escaping (Result<VerificationResult, Error>) -> Void) {
        verifyClient?.startVerification { result in
            switch result {
            case .success(let verificationResult):
                completion(.success(verificationResult))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
}
```



### 3. Create the ViewModel

Implement the ViewModel to manage the verification process.

```swift
import Foundation
import Combine

class VerificationViewModel: ObservableObject {
    @Published var verificationResult: VerificationResult?
    @Published var errorMessage: String?

    private let verifyService = PingOneVerifyService()

    func initiateVerification() {
        verifyService.startVerification { [weak self] result in
            DispatchQueue.main.async {
                switch result {
                case .success(let result):
                    self?.verificationResult = result
                case .failure(let error):
                    self?.errorMessage = error.localizedDescription
                }
            }
        }
    }
}
```



### 4. Build the SwiftUI View

Design the user interface to initiate verification and display results.

```swift
import SwiftUI

struct VerificationView: View {
    @StateObject private var viewModel = VerificationViewModel()

    var body: some View {
        VStack(spacing: 20) {
            if let result = viewModel.verificationResult {
                Text("Verification Successful: \(result.status)")
            } else if let error = viewModel.errorMessage {
                Text("Error: \(error)")
                    .foregroundColor(.red)
            } else {
                Text("Ready to verify your identity.")
            }

            Button(action: {
                viewModel.initiateVerification()
            }) {
                Text("Start Verification")
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
        }
        .padding()
    }
}
```


 

## 📚 References

* [PingOne Verify SDK for iOS Documentation](https://apidocs.pingidentity.com/pingone/native-sdks/v1/api/#pingone-verify-sdk-for-ios)
* [PingOne Verify SDK GitHub Repository](https://github.com/pingidentity/pingone-verify-mobile-sdk-ios)
* [Getting Started with PingOne Verify](https://docs.pingidentity.com/pingone/identity_verification_using_pingone_verify/p1_getting_started_with_p1_verify.html)


---

# 🎨 UI Customization for PingOne Verify SDK (iOS v2.2.0)

## Overview

The PingOne Verify SDK allows developers to tailor the user interface to match their application's branding and user experience requirements. Customization can be achieved through:

* **Local Customization**: Programmatically configuring UI elements using the `UIAppearanceSettings` class.
* **Remote Customization**: Utilizing the PingOne Admin Console to define branding and theming options.

---

## 🛠️ Local Customization with `UIAppearanceSettings`

The `UIAppearanceSettings` class provides properties to customize various UI components during runtime.([Ping Identity API Documentation][1])

### Class Definition

```swift
@objc public class UIAppearanceSettings: NSObject {
    private var logoImage: UIImage?
    private var backgroundColor: UIColor?
    private var bodyTextColor: UIColor?
    private var headingTextColor: UIColor?
    private var navigationBarColor: UIColor?
    private var navigationBarTextColor: UIColor?
    private var iconTintColor: UIColor?
    private var solidButtonAppearance: ButtonAppearance?
    private var borderedButtonAppearance: ButtonAppearance?
}
```



### Customizable Properties

| Method                                                        | Description                                                        |                                        |
| ------------------------------------------------------------- | ------------------------------------------------------------------ | -------------------------------------- |
| `setLogoImage(_ image: UIImage)`                              | Sets the logo image displayed at the center of the navigation bar. |                                        |
| `setBackgroundColor(_ color: UIColor)`                        | Sets the application's background color.                           |                                        |
| `setBodyTextColor(_ color: UIColor)`                          | Sets the color of body text.                                       |                                        |
| `setHeadingTextColor(_ color: UIColor)`                       | Sets the color of heading text.                                    |                                        |
| `setNavigationBarColor(_ color: UIColor)`                     | Sets the background color of the navigation bar.                   |                                        |
| `setNavigationBarTextColor(_ color: UIColor)`                 | Sets the text color of the navigation bar.                         |                                        |
| `setIconTintColor(_ color: UIColor)`                          | Sets the tint color for icons.                                     |                                        |
| `setSolidButtonAppearance(_ appearance: ButtonAppearance)`    | Sets the appearance for solid buttons.                             |                                        |
| `setBorderedButtonAppearance(_ appearance: ButtonAppearance)` | Sets the appearance for bordered buttons.                          | ([Ping Identity API Documentation][1]) |

### Example Usage

```swift
let appearanceSettings = UIAppearanceSettings()
appearanceSettings.setLogoImage(UIImage(named: "customLogo")!)
appearanceSettings.setBackgroundColor(.white)
appearanceSettings.setBodyTextColor(.darkGray)
appearanceSettings.setHeadingTextColor(.black)
appearanceSettings.setNavigationBarColor(.blue)
appearanceSettings.setNavigationBarTextColor(.white)
appearanceSettings.setIconTintColor(.blue)

// Configure solid button appearance
let solidButton = ButtonAppearance()
solidButton.setBackgroundColor(.blue)
solidButton.setTextColor(.white)
appearanceSettings.setSolidButtonAppearance(solidButton)

// Configure bordered button appearance
let borderedButton = ButtonAppearance()
borderedButton.setBorderColor(.blue)
borderedButton.setTextColor(.blue)
appearanceSettings.setBorderedButtonAppearance(borderedButton)
```



To apply these settings, pass the `appearanceSettings` instance to the `PingOneVerifyClient.Builder` during SDK initialization.([Ping Identity API Documentation][1])

---

## 🌐 Remote Customization via PingOne Admin Console

The PingOne Admin Console allows for centralized UI customization, enabling consistent branding across different platforms.

### Customizable Elements

* Logo
* Navigation bar color
* Button colors (background, text, border)
* Application background color
* Heading and body text colors([Ping Identity API Documentation][1])

### Steps to Customize

1. Log in to the [PingOne Admin Console](https://console.pingidentity.com/).
2. Navigate to **Experiences** > **Branding & Themes**.
3. Modify the desired branding elements.
4. Save the changes to apply them across your applications.

These settings are fetched by the SDK at runtime, ensuring that the latest branding is always applied.

---

## 🌍 Localization

To support multiple languages, utilize the `Localizable.strings` file in your Xcode project. Define key-value pairs for each supported language to localize text displayed by the SDK.([Ping Identity API Documentation][1])

---
