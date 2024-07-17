<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WCAG 2.2 Guidelines for iOS Developers</title>
    <style>
        body { font-family: Arial, sans-serif; line-height: 1.6; padding: 20px; }
        h1 { color: #333; }
        h2 { color: #0066cc; }
        pre { background-color: #f4f4f4; padding: 10px; border-radius: 5px; overflow-x: auto; }
        code { font-family: 'Courier New', Courier, monospace; }
    </style>
</head>
<body>
    <h1>WCAG 2.2 Guidelines for iOS Developers</h1>

    <h2>1. Focus Not Obscured (2.4.11 AA, 2.4.12 AAA)</h2>
    <p>Ensure that the keyboard focus indicator is not hidden by other content.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
// Adjust scroll view content inset when keyboard appears
NotificationCenter.default.addObserver(forName: UIResponder.keyboardWillShowNotification, object: nil, queue: .main) { [weak self] notification in
    guard let keyboardFrame = notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? CGRect else { return }
    self?.scrollView.contentInset.bottom = keyboardFrame.height
}
    </code></pre>

    <h2>2. Focus Appearance (2.4.13 AAA)</h2>
    <p>Make sure the focus indicator is clearly visible and meets minimum area and contrast requirements.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
extension UIButton {
    func setupAccessibilityFocus() {
        let focusedBackground = UIColor.systemBlue.withAlphaComponent(0.3)
        let focusedBorder = UIColor.systemBlue
        
        UIView.appearance(whenContainedInInstancesOf: [UIButton.self]).backgroundColor = .clear
        UIView.appearance(whenContainedInInstancesOf: [UIButton.self]).layer.borderWidth = 2
        UIView.appearance(whenContainedInInstancesOf: [UIButton.self]).layer.borderColor = focusedBorder.cgColor
        UIView.appearance(whenContainedInInstancesOf: [UIButton.self]).layer.cornerRadius = 5
        
        self.accessibilityTraits.insert(.button)
    }
}
    </code></pre>

    <h2>3. Dragging Movements (2.5.7 AA)</h2>
    <p>Provide alternatives for drag and drop operations.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
class ReorderableCell: UICollectionViewCell {
    var moveUpButton: UIButton!
    var moveDownButton: UIButton!
    
    func setupAlternativeMoveButtons() {
        moveUpButton = UIButton(type: .system)
        moveUpButton.setTitle("Move Up", for: .normal)
        moveUpButton.addTarget(self, action: #selector(moveItemUp), for: .touchUpInside)
        
        moveDownButton = UIButton(type: .system)
        moveDownButton.setTitle("Move Down", for: .normal)
        moveDownButton.addTarget(self, action: #selector(moveItemDown), for: .touchUpInside)
        
        // Add buttons to cell layout
    }
    
    @objc func moveItemUp() {
        // Implement move up logic
    }
    
    @objc func moveItemDown() {
        // Implement move down logic
    }
}
    </code></pre>

    <h2>4. Target Size (2.5.8 AA)</h2>
    <p>Ensure interactive elements are at least 24x24 CSS pixels.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
extension UIButton {
    func ensureMinimumSize() {
        let minSize: CGFloat = 44 // 44 points is approximately 24 CSS pixels
        
        NSLayoutConstraint.activate([
            self.widthAnchor.constraint(greaterThanOrEqualToConstant: minSize),
            self.heightAnchor.constraint(greaterThanOrEqualToConstant: minSize)
        ])
    }
}
    </code></pre>

    <h2>5. Consistent Help (3.2.6 A)</h2>
    <p>Implement a consistent help mechanism across the app.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
class BaseViewController: UIViewController {
    lazy var helpButton: UIBarButtonItem = {
        let button = UIBarButtonItem(image: UIImage(systemName: "questionmark.circle"), style: .plain, target: self, action: #selector(showHelp))
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.rightBarButtonItem = helpButton
    }
    
    @objc func showHelp() {
        let helpVC = HelpViewController()
        present(helpVC, animated: true, completion: nil)
    }
}
    </code></pre>

    <h2>6. Redundant Entry (3.3.7 A)</h2>
    <p>Avoid asking users to re-enter information they've already provided.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
class RegistrationManager {
    static let shared = RegistrationManager()
    
    var userInfo: [String: String] = [:]
    
    func saveUserInfo(key: String, value: String) {
        userInfo[key] = value
    }
    
    func getUserInfo(key: String) -> String? {
        return userInfo[key]
    }
}

// In your view controller
class AddressViewController: UIViewController {
    @IBOutlet weak var nameTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let savedName = RegistrationManager.shared.getUserInfo("name") {
            nameTextField.text = savedName
        }
    }
}
    </code></pre>

    <h2>7. Accessible Authentication (3.3.8 AA, 3.3.9 AAA)</h2>
    <p>Provide alternatives to cognitive function tests for authentication.</p>
    <p><strong>Example:</strong></p>
    <pre><code>
import LocalAuthentication

class AuthenticationManager {
    func authenticateUser(completion: @escaping (Bool) -> Void) {
        let context = LAContext()
        var error: NSError?
        
        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: "Authenticate to access the app") { success, authenticationError in
                DispatchQueue.main.async {
                    completion(success)
                }
            }
        } else {
            // Fallback to password/PIN entry
            showPasswordEntryUI(completion: completion)
        }
    }
    
    func showPasswordEntryUI(completion: @escaping (Bool) -> Void) {
        // Implement password/PIN entry UI
    }
}
    </code></pre>

    <p>These examples demonstrate how iOS developers can implement WCAG 2.2 guidelines in their applications. Remember to test these implementations thoroughly with various assistive technologies and user scenarios to ensure they meet accessibility requirements.</p>
</body>
</html>
