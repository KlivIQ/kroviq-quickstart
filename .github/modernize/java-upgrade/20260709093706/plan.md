# Upgrade Plan: Kroviq Core (20260709093706)

- **Generated**: 2026-07-09 09:37:06
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 1.8.0_302: C:\Program Files\Eclipse Foundation\jdk-8.0.302.8-hotspot\bin
- JDK 17.0.12: C:\Program Files\Java\jdk-17\bin (current project JDK, used by step 2)
- JDK 21: **<TO_BE_INSTALLED>** (required by step 1)

**Build Tools**
- Maven 3.9.11: C:\Program Files\Maven\apache-maven-3.9.11\bin
- Maven Wrapper: not present

## Guidelines

- Upgrade the project runtime to the latest LTS Java version, which is Java 21.
- Preserve existing test infrastructure and verify full test suite pass after upgrade.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260709093706 (git unavailable; changes not version-controlled)
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 21 runtime

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------------------------------------- |
| Java | 17 | 21 | User requested latest LTS runtime |
| Maven | 3.9.11 | 3.9.0 | Compatible with Java 21; current installation is sufficient |
| maven-compiler-plugin | 3.11.0 | 3.11.0 | Recommended for Java 21; already compatible |
| JUnit Jupiter | 5.11.0 | 5.11.0 | Compatible with Java 21 |
| Selenium Java | 4.25.0 | 4.0.0 | Compatible with Java 21 |
| Cucumber Java | 7.24.0 | 7.0.0 | Compatible with Java 21 |
| Log4j API/Core | 2.24.0 | 2.17.0 | Compatible with Java 21 |
| Jackson Databind | 2.18.0 | 2.14.0 | Compatible with Java 21 |
| Apache POI | 5.3.0 | 5.0.0 | Compatible with Java 21 |

## Derived Upgrades

- Java 17 → Java 21: required by user request and latest LTS target.
- Maven 3.9.11: available and sufficient for Java 21, no wrapper upgrade needed.
- maven-compiler-plugin 3.11.0: already aligned with Java 21 support.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| pom.xml | maven.compiler.source | 17 | upgrade | 21 | User requested Java 21 |
| pom.xml | maven.compiler.target | 17 | upgrade | 21 | User requested Java 21 |
| pom.xml | maven-compiler-plugin `<source>` | 17 | upgrade | 21 | Align plugin compile target with Java 21 |
| pom.xml | maven-compiler-plugin `<target>` | 17 | upgrade | 21 | Align plugin compile target with Java 21 |

### Source Code Changes

| File | Location | Current | Required Change | Reason |
|------|----------|---------|----------------|--------|
| pom.xml | compiler settings | `<source>17</source>` / `<target>17</target>` | Update to `21` | Java 21 upgrade |
| pom.xml | compiler properties | `<maven.compiler.source>17</maven.compiler.source>` / `<maven.compiler.target>17</maven.compiler.target>` | Update to `21` | Java 21 upgrade |

### Configuration Changes

None identified beyond Maven compiler settings.

### CI/CD Changes

None identified in repository files; no pipeline files detected in workspace root.

### Risks & Warnings

- `WinAppDriverEngine.java` and several utility classes use `Field.setAccessible(true)` for reflection-based field mutation. This is not expected to be blocked for application-level access on the classpath, but it is a runtime risk if the project later moves to a module-path execution environment or stricter access controls.
- No Git repository is available in this workspace, so changes cannot be committed using version control. The plan must note that modifications are file-based only.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Install the required Java 21 runtime since it is not present on the machine and verify Maven is available.
  - **Changes to Make**: None to source code. Prepare runtime environment for upgrade and validation.
  - **Verification**: `java -version && mvn -version`, use JDK 21 path and Maven 3.9.11.

- Step 2: Setup Baseline
  - **Rationale**: Establish a working build baseline on the current Java 17 environment to compare pre-upgrade behavior and ensure current tests pass.
  - **Changes to Make**: None.
  - **Verification**: `mvn clean compile test-compile -q && mvn clean test -q`, use JDK 17.

- Step 3: Upgrade Java compiler settings to Java 21
  - **Rationale**: Apply the core upgrade to project build configuration, keeping the project compilable after the change.
  - **Changes to Make**: Update `pom.xml` `maven.compiler.source`, `maven.compiler.target`, and compiler plugin `source`/`target` values from `17` to `21`.
  - **Verification**: `mvn clean test-compile -q`, use JDK 21.

- Step 4: CVE Validation & Fix
  - **Rationale**: Scan direct dependencies for known CVEs and fix any issues by upgrading patch versions if needed.
  - **Changes to Make**: Adjust dependency versions only if CVEs are detected and a newer patch is available.
  - **Verification**: `mvn clean test-compile -q` and CVE re-scan.

- Step 5: Final Validation
  - **Rationale**: Confirm the Java 21 upgrade succeeds fully with compilation and all tests passing.
  - **Changes to Make**: Address any compiler or test failures discovered after the Java upgrade.
  - **Verification**: `mvn clean test -q`, use JDK 21.
