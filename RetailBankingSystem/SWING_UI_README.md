# Java Swing UI for Retail Banking System

This document describes the Swing UI conversion of the web-based login forms.

## Overview

The HTML login forms have been converted to Java Swing GUI applications. The implementation includes:

- **EmployeeLoginFrame.java** - Employee login form (converted from login.html)
- **CustomerLoginFrame.java** - Customer login form (converted from customerLogin.html)
- **LoginSelectionFrame.java** - Initial screen to choose between employee/customer login
- **SwingApplication.java** - Main entry point for the Swing application
- **HttpClientUtil.java** - Utility class for HTTP communication with the backend

## Features

### Common Features (Both Employee & Customer Login)

1. **User Input Fields**
   - Text field for username/customer ID with user icon
   - Password field with lock icon
   - Password visibility toggle button (shows/hides password)

2. **Form Validation**
   - Checks for empty fields
   - Pattern validation for username (employee)
   - Numeric validation for customer ID

3. **Error Handling**
   - Real-time error message display
   - Disabled login button during authentication
   - Connection error handling

4. **Navigation**
   - Link to switch between employee and customer login
   - Exit button on selection screen

5. **Backend Integration**
   - HTTP POST requests to backend endpoints
   - Asynchronous login processing (doesn't freeze UI)

## Running the Application

### To run the Swing UI:

```bash
# From the project root directory
mvn clean install

# Then run the Swing application
java -cp "target/RetailBankingSystem-0.0.1-SNAPSHOT.jar:target/classes" com.project.ui.SwingApplication
```

### Or run individual login forms:

```bash
# Employee Login
java -cp "target/classes" com.project.ui.EmployeeLoginFrame

# Customer Login
java -cp "target/classes" com.project.ui.CustomerLoginFrame
```

## Components Description

### EmployeeLoginFrame
- Username field with regex pattern validation (matches: [a-zA-Z][a-zA-Z0-9-_.]{1,20})
- Password field with visibility toggle
- Authenticates against `/employeeHome` endpoint
- Displays success/error messages

### CustomerLoginFrame
- Customer ID field (numeric validation)
- Password field with visibility toggle
- Authenticates against `/customerHome` endpoint
- Displays success/error messages

### LoginSelectionFrame
- Initial screen presenting both login options
- Three buttons: Employee Login, Customer Login, Exit
- Material-like color scheme

## Backend Requirements

Ensure your Spring Boot application is running on `http://localhost:8080`

The following endpoints are expected:
- `POST /employeeHome` - Employee authentication
- `POST /customerHome` - Customer authentication

## Technical Details

### HTTP Communication
- Uses Java's built-in HttpURLConnection (no external dependencies required)
- Supports connection timeouts (5 seconds)
- Uses UTF-8 encoding
- Follows x-www-form-urlencoded format

### Threading
- Login operations run on background threads
- Prevents UI freezing during network requests
- Uses SwingUtilities.invokeLater for thread-safe UI updates

### UI Design
- GridBagLayout for flexible component arrangement
- Consistent color scheme (blue: #0066CC, green: #009900, red: #CC0000)
- Icon emojis for visual enhancement
- Professional appearance similar to the web version

## Customization

### Change Backend URL
Edit `HttpClientUtil.java` and modify `BASE_URL`:
```java
private static final String BASE_URL = "http://your-server:port";
```

### Customize Colors
Edit the RGB values in each Frame class:
- Employee Login Button: `new Color(0, 102, 204)`
- Customer Login Button: `new Color(0, 153, 76)`

### Modify Timeout
Edit `HttpClientUtil.java`:
```java
private static final int TIMEOUT = 5000; // in milliseconds
```

## Error Scenarios Handled

1. **Network Connection Failure** - Displays error message to user
2. **Invalid Credentials** - Shows "Invalid username or password"
3. **Empty Fields** - Validates before submission
4. **Pattern Mismatch** - Username/ID validation with instant feedback
5. **Backend Unavailability** - Graceful error handling

## Future Enhancements

1. Add "Remember Me" checkbox
2. Implement password reset functionality
3. Add loading spinner animation
4. Support for other authentication methods (OAuth, SSO)
5. Session management
6. Account lockout after failed attempts
7. Two-factor authentication

## Dependencies

The Swing implementation uses only Java standard library:
- java.swing.*
- java.awt.*
- java.net.*
- java.io.*
- java.nio.charset
- java.util.*

No external Maven dependencies are required for the Swing UI.

## Notes

- The Swing UI can run independently of the web UI
- Both web and Swing UIs can coexist in the same application
- The backend Spring Boot application need not be modified for Swing support
- Swing UI provides the same functionality as the web version
- Consider running the Swing app and web app on different machines if needed

