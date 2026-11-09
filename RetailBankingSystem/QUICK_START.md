# Quick Start Guide - Java Swing UI

## 🚀 Running the Swing Application

### Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- Spring Boot backend running on `http://localhost:8080`

### Step 1: Build the Project
```bash
cd /Users/gaurav0850/sbox/projects/RetailBankingSystem/RetailBankingSystem
mvn clean install
```

### Step 2: Run the Main Application
Choose one of the following methods:

#### Method A: Using Main Class (Recommended)
```bash
java -cp target/classes com.project.ui.SwingApplication
```

#### Method B: Using Module Class Path
```bash
java --class-path target/classes com.project.ui.SwingApplication
```

#### Method C: Use Maven Exec Plugin
```bash
mvn exec:java -Dexec.mainClass="com.project.ui.SwingApplication"
```

### Step 3: Use the Application
1. **Application Start**: Select Login Type
   - Click "Employee Login" → Employee Login Form
   - Click "Customer Login" → Customer Login Form
   - Click "Exit" → Exit Application

2. **Employee Login**:
   - Username: Enter employee username (pattern: starts with letter, 1-20 characters)
   - Password: Enter password
   - Click "Login"
   - Use eye icon to toggle password visibility

3. **Customer Login**:
   - Customer ID: Enter numeric customer ID
   - Password: Enter password
   - Click "Login"
   - Use eye icon to toggle password visibility

## 📋 Sample Test Credentials

Before using, add test data to your database:

### Employee Test Account
```sql
INSERT INTO employee (username, password) VALUES ('john_doe', 'password123');
INSERT INTO employee (username, password) VALUES ('emp001', 'emp@123');
```

### Customer Test Account
```sql
INSERT INTO customer (customer_id, password) VALUES (1001, 'cust123');
INSERT INTO customer (customer_id, password) VALUES (2001, 'pass123');
```

## 📂 File Structure

```
src/main/java/com/project/ui/
├── SwingApplication.java          # Main entry point
├── LoginSelectionFrame.java       # Initial screen
├── EmployeeLoginFrame.java        # Employee login form
├── CustomerLoginFrame.java        # Customer login form
└── util/
    └── HttpClientUtil.java        # HTTP utility class
```

## 🔧 Configuration

### Backend URL
Edit `src/main/java/com/project/ui/util/HttpClientUtil.java`:
```java
private static final String BASE_URL = "http://localhost:8080";
```

### Connection Timeout
Edit the same file:
```java
private static final int TIMEOUT = 5000; // milliseconds
```

## 🎨 UI Features

- **Modern Design**: Clean, professional interface
- **Real-time Validation**: Input validation with helpful error messages
- **Password Toggle**: Show/hide password with eye icon
- **Responsive**: Smooth, non-blocking UI
- **Error Handling**: Graceful error messages
- **Navigation**: Easy switching between login forms

## ✅ Verification

To verify installation:

```bash
# Check if compiled classes exist
ls -la target/classes/com/project/ui/

# Run with verbose output
java -verbose -cp target/classes com.project.ui.SwingApplication
```

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| "Cannot find LoginSelectionFrame" | Run `mvn clean compile` first |
| "Connection refused" | Ensure Spring Boot backend is running on port 8080 |
| "Invalid Customer ID" | Customer ID must be numeric |
| "Invalid username" | Username must match pattern [a-zA-Z][a-zA-Z0-9-_.]{1,20} |
| UI not opening | Check Java version is 11+ |

## 📝 Notes

- The Swing UI runs independently of the web UI
- Both can be used simultaneously
- No need to modify Spring Boot configuration
- Backend remains the same for both web and Swing clients

## 🔐 Security Considerations

- Passwords are shown as bullets by default (encrypted visual)
- Can be toggled to show/hide
- Communication with backend uses standard HTTP
- Consider using HTTPS for production

---

For detailed information, see `SWING_UI_README.md`

