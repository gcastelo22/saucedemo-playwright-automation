# Saucedemo Playwright Automation 🚀

High-performance E2E test automation framework for [SauceLabs Inventory](https://www.saucedemo.com/), migrated from Selenium to **Playwright Java**. This project implements modern testing patterns to ensure stability, speed and clean code.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Automation Engine:** [Playwright](https://playwright.dev/java/)
* **Test Runner:** JUnit 4
* **Build Tool:** Maven
* **Configuration:** Typesafe Config (HOCON)
* **Logging:** Java Util Logging

## ✨ Key Features

* **Page Object Model (POM):** Optimized structure for maintainability.
* **Fluent Interface:** Readable and chainable test steps (Method Chaining).
* **Auto-waiting:** Leverages Playwright's native actionability checks to eliminate flaky tests.
* **Cross-browser Support:** Easy switching between Chromium, Firefox and Webkit.
* **Automated Evidence:** Screenshots are automatically captured on test completion and saved to "target/screenshots/".
* **Headless Execution:** Support for CI/CD integration.

## 📁 Project Structure

The project follows a standard Maven structure where all automation logic and test suites reside in the same root package for consistency:

```text
src
└── test/java/com/github/gcastelo22
    ├── core/           # Framework configuration and Playwright engine setup
    ├── pages/          # Page Objects (POM) with Fluent UI patterns
    └── tests/          # Functional and E2E Test Suites
src/main/resources
└── config/env.conf     # Global environment and user configurations
```

## 🚀 Getting Started

### Prerequisites
* **Java JDK 21**: Ensure your "JAVA_HOME" is correctly configured.
* **Maven 3.8+**: Used for dependency management and test execution.

### Installation
1. Clone the repository to your local machine:
   ```bash
   git clone [https://github.com/YOUR_USERNAME/saucedemo-playwright-automation.git](https://github.com/YOUR_USERNAME/saucedemo-playwright-automation.git)
   cd saucedemo-playwright-automation
   ```
2. Install dependencies and download the required Playwright browser binaries:
   ```bash
   mvn install
   ```
> **Note:** On the first execution, Playwright will automatically download the bundled browser executables (Chromium, Firefox and Webkit). This process happens only once and may take a few minutes depending on your internet connection.
### Configuration
The framework uses the **HOCON** format for flexible configuration management. You can customize the execution by editing the "src/main/resources/config/env.conf" file:

```hocon
conf {
  # Choose the browser engine: CHROMIUM, FIREFOX or WEBKIT
  browser = "CHROMIUM"
  
  # Set to "true" for CI/CD pipelines or "false" for local debugging
  headless = false
  
  # Global timeout for actions in milliseconds (e.g., 10000 = 10s)
  timeout = 10000
}
```

## 🧪 Running Tests

You can execute the test suites using Maven commands from the project root.

### Run all tests
To execute all test classes in the project:
```bash
mvn test
```

### Run a specific test suite
If you want to run only a specific test class (e.g., the E2E flow):
```bash
mvn test -Dtest=E2EPurchaseTest
```

### Run tests in a specific browser
To switch the execution environment, simply update the "browser" property in the "env.conf" file. The framework currently supports:
* "CHROMIUM" (Default - Chrome, Edge)
* "FIREFOX"
* "WEBKIT" (Safari engine)

Example for Firefox:
```hocon
conf.browser = "FIREFOX"
```

## 📊 Evidence & Reports

After each test execution, the framework generates evidence to help with debugging and reporting:

### 📸 Screenshots
The "BaseTest" class is configured to capture a screenshot automatically upon the completion of each test method. 
* **Location:** "target/screenshots/"
* **Format:** "testName_timestamp.png"

### 📝 Test Reports
Detailed execution logs and pass/fail status can be found in the standard Maven Surefire reports:
* **Terminal output:** Detailed logs are printed to the console during execution.
* **HTML/XML Reports:** Located in "target/surefire-reports/".

### 🔍 Playwright Inspector (Debug Mode)
To debug a failing test visually, you can run the tests with the "PWDEBUG" environment variable:
```bash
PWDEBUG=1 mvn test -Dtest=E2EPurchaseTest
```

---

**Developed by Guilherme Castelo**
*Senior Quality Engineer | SDET | Data Integrity Specialist*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilhermecastelo/)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:lguilherme.castelo@gmail.com)	