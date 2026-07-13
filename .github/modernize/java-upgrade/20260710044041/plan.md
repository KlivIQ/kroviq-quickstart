# Upgrade Plan: Kroviq Core (20260710044041)

- **Generated**: 2026-07-10 04:40:41
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 17.0.12: C:\Program Files\Java\jdk-17\bin (current project JDK, used for baseline)
- JDK 25: **<TO_BE_INSTALLED>** (required for final validation)

**Build Tools**
- Maven 3.9.11: C:\Program Files\Maven\apache-maven-3.9.11\bin

## Guidelines

- Upgrade the Java runtime to the latest LTS version supported by the build toolchain.
- Keep the change minimal and preserve current application behavior.
- Verify compilation and tests after the upgrade.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260710044041
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| ---------------------- | ------- | -------------- | ---------------- |
| Java | 17 | 25 | User requested |
| Maven Compiler Plugin | 3.11.0 | 3.14.0 | Recommended for newer JDK compatibility |

## Derived Upgrades

- Java 25 requires the project compiler target to be raised from 17 to 25.
- Maven compiler plugin should be updated to a version with better support for newer JDK releases.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|-----------|---------|--------|--------|--------|
| pom.xml | maven.compiler.source | 17 | upgrade | 25 | User requested |
| pom.xml | maven.compiler.target | 17 | upgrade | 25 | User requested |
| pom.xml | maven-compiler-plugin | 3.11.0 | upgrade | 3.14.0 | Better compatibility with Java 25 |

### Source Code Changes

| File | Location | Current | Required Change | Reason |
|------|----------|---------|----------------|--------|
| None expected | Build configuration | Java target 17 | Update compiler settings to Java 25 | Runtime upgrade |

### Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
|------|------------------|---------|-----------------|--------|
| pom.xml | compiler source/target | 17 | 25 | Java runtime upgrade |

### CI/CD Changes

| File | Location | Current | Required Change |
|------|----------|---------|----------------|
| None detected | - | - | - |

### Risks & Warnings

- The build uses a direct Java compiler target, so a version mismatch could cause compile failures if the runtime is not aligned.
- Mitigation: update the compiler config and validate via a clean compile and test run with the target JDK.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: The target JDK must be available before the build can be validated against it.
  - **Changes to Make**: Install JDK 25 and confirm the toolchain path.
  - **Verification**: `java -version`, JDK 25, Expected Result: JDK 25 available.

- Step 2: Setup Baseline
  - **Rationale**: Capture the current build state before changing the compiler target.
  - **Changes to Make**: Run the existing compile and test suite with the current JDK 17.
  - **Verification**: `mvn clean test-compile -q && mvn clean test -q`, JDK 17, Expected Result: Baseline build runs successfully.

- Step 3: Upgrade Java Build Target
  - **Rationale**: The Maven compiler settings must be aligned with the new JDK runtime.
  - **Changes to Make**: Apply the compiler target and plugin version changes from the Impact Analysis.
  - **Verification**: `mvn clean test-compile -q`, JDK 25, Expected Result: Main and test sources compile successfully.

- Step 4: Final Validation
  - **Rationale**: Confirm the full project still builds and all tests pass with the new runtime.
  - **Changes to Make**: Run the full test suite and fix any issues caused by the JDK upgrade.
  - **Verification**: `mvn clean test -q`, JDK 25, Expected Result: 100% of tests pass.
