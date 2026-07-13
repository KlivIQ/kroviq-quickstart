<p align="center">
  <img src="https://raw.githubusercontent.com/kroviq/kroviq-core/main/src/main/resources/assets/kroviq_logo.png" alt="Kroviq" width="280">
</p>

<h1 align="center">Kroviq Quick Start</h1>

<p align="center">
  <strong>Clone → Build → Run in under 10 minutes.</strong><br>
  Minimal sample project demonstrating the Kroviq Core automation framework.
</p>

---

## What This Is

A self-contained demo project using the **WingIt Airlines** fictional booking platform.

- 10 scenarios (Authentication + Flight Search + Booking)
- Uses local HTML fixtures — **no external servers or APIs needed**
- Browser opens, tests run, report generates — all locally

---

## Kroviq Ecosystem

Kroviq is a modular enterprise test automation platform.

| Repository | Purpose |
|------------|---------|
| **kroviq-core** | Open source automation framework powering the Kroviq platform |
| **kroviq-quickstart** | Ready-to-run WingIt Airlines sample project for getting started in minutes |
| **kroviq-testsuite-template** | Production-ready enterprise test suite template |
| **kroviq-studio** | Low-code visual automation platform *(Commercial)* |
| **kroviq-enterprise** | Enterprise collaboration, governance and integrations *(Commercial)* |
| **kroviq-ai-workspace** | AI-powered automation and engineering workspace *(Commercial)* |

---

> **Looking for the framework?**
>
> This sample project is built on **Kroviq Core**.
>
> 👉 https://github.com/KlivIQ/kroviq-core

---

## License

Licensed under the [Apache License 2.0](LICENSE).

Copyright © 2026 KlivIQ Technologies.

---

## Prerequisites

| Requirement | How to Check |
|---|---|
| Java 17+ | `java -version` |
| Maven 3.8+ | `mvn -version` |
| Chrome browser | Installed and up to date |
| Git | `git --version` |

> **Note:** Browser drivers are managed automatically by WebDriverManager. You do NOT need to download ChromeDriver manually.

---

## Setup (One Time)

```bash
# 1. Clone the framework
git clone https://github.com/kroviq/kroviq-core.git

# 2. Install framework to local Maven repository
cd kroviq-core
mvn clean install -DskipTests

# 3. Clone this Quick Start project
cd ..
git clone https://github.com/kroviq/kroviq-quickstart.git
cd kroviq-quickstart
```

**Total setup time: ~5 minutes** (mostly Maven downloading dependencies)

---

## Run the Demo

```bash
mvn exec:java
```

That's it. Chrome will open, tests will execute against local HTML fixtures, and a report will be generated.

---

## What You'll See

### Console Output (Expected)

```
[START] Starting Kroviq TestSuite Execution...

[DEBUG] STEP 1: Initializing TestRunReportManager (creates Run folder)
[DEBUG] STEP 2: TestRunReportManager initialized
[OK] Applied tag filter: @QuickStart

10 Scenarios (10 passed)
30 Steps (30 passed)

[OK] Test execution completed
```

### Report Location

```
Reports/Run_YYYYMMDD_HHMMSS/TestRun_WingIt_Airlines_Custom.html
```

Open the HTML file in any browser to see the full test report with step details.

---

## Project Structure

```
kroviq-quickstart/
├── src/main/java/kroviq/constants/
│   └── WingItConstants.java          # Element locators
├── src/main/resources/testdata/json/
│   └── WingIt.json                   # Test data (TD_ resolution)
├── src/test/java/
│   ├── runners/TestRunner.java       # Execution entry point
│   └── stepdefinitions/WingItSteps.java  # Step definitions
├── src/test/resources/
│   ├── features/WingIt/              # Gherkin scenarios
│   │   ├── Authentication.feature
│   │   ├── FlightSearch.feature
│   │   └── Booking.feature
│   └── fixtures/
│       └── wingit-airlines.html      # Self-contained demo app
├── RunManager.json                   # Execution configuration
└── pom.xml                           # Maven build
```

---

## Available Suites

Edit `selectedSuite` in `RunManager.json` to run different subsets:

| Suite | Tag | Scenarios |
|---|---|---|
| `QuickStart` | `@QuickStart` | 10 (all) |
| `Authentication` | `@Authentication` | 3 |
| `FlightSearch` | `@FlightSearch` | 4 |
| `Booking` | `@Booking` | 3 |

---

## How It Works

1. **Feature files** describe tests in plain English (Gherkin)
2. **Test data** is externalized in JSON — `TD_Email` resolves to `"pilot@wingit.demo"` at runtime
3. **Step definitions** map Gherkin to Selenium actions
4. **Kroviq Core** provides the infrastructure: WebDriver management, reporting, data resolution, AI RCA
5. **Local fixtures** ensure tests always pass — no flaky external dependencies

---

## Next Steps

| Goal | Resource |
|---|---|
| Understand the framework | [Kroviq Core README](https://github.com/kroviq/kroviq-core) |
| See full capabilities (54 scenarios) | [Kroviq TestSuite Template](https://github.com/kroviq/kroviq-testsuite-template) |
| Enterprise demo (grids, REST, uploads) | *Coming soon: wingit-airlines-demo* |
| Read the User Guide | [docs/Kroviq_User_Guide.html](https://github.com/kroviq/kroviq-core/blob/main/docs/Kroviq_User_Guide.html) |

---

## Troubleshooting

| Problem | Solution |
|---|---|
| `Could not find artifact com.kroviq:kroviq-core:2.0.0` | Run `mvn clean install -DskipTests` in the `kroviq-core` directory first |
| Chrome doesn't open | Ensure Chrome is installed and up to date |
| `java: invalid source release: 17` | Install Java 17+ and set `JAVA_HOME` |
| Tests pass but no report | Check `Reports/` folder — report is generated after all tests complete |
| Maven SSL errors | Add `-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true` |

---

## License

Licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

**Kroviq by KlivIQ Technologies** — [www.kliviq.com](https://www.kliviq.com)
