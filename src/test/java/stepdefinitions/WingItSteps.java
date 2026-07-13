package stepdefinitions;

import io.cucumber.java.en.*;
import kroviq.reporting.StepDescription;
import kroviq.reporting.StepReportingWrapper;
import kroviq.utils.TestContext;
import kroviq.wrapper.GenericWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class WingItSteps {

    private static final Logger logger = LogManager.getLogger(WingItSteps.class);

    @When("I navigate to local fixture {string}")
    @StepDescription("Navigate to local HTML fixture file")
    public void navigateToFixture(String fixtureName) {
        StepReportingWrapper.executeStep("Navigate to fixture: " + fixtureName, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            String fixturePath = new File("src/test/resources/fixtures/" + fixtureName).getAbsolutePath();
            String fileUrl = "file:///" + fixturePath.replace("\\", "/");
            driver.get(fileUrl);
            logger.info("Navigated to fixture: {}", fileUrl);
        });
    }

    @When("I enter {string} in the {string} field")
    @StepDescription("Enter value into input field by ID")
    public void enterInField(String value, String fieldId) {
        String resolved = resolveTestData(value);
        StepReportingWrapper.executeStep("Enter '" + resolved + "' in " + fieldId, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            WebElement field = driver.findElement(By.id(fieldId));
            field.clear();
            field.sendKeys(resolved);
        });
    }

    @When("I click the {string} button")
    @StepDescription("Click button by ID")
    public void clickButton(String buttonId) {
        StepReportingWrapper.executeStep("Click " + buttonId, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            driver.findElement(By.id(buttonId)).click();
        });
    }

    @When("I login as {string} with password {string}")
    @StepDescription("Login with credentials")
    public void loginAs(String email, String password) {
        StepReportingWrapper.executeStep("Login as " + email, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("btn-login")).click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome-message")));
        });
    }

    @When("I search flights from {string} to {string}")
    @StepDescription("Search flights between cities")
    public void searchFlights(String from, String to) {
        String resolvedFrom = resolveTestData(from);
        String resolvedTo = resolveTestData(to);
        StepReportingWrapper.executeStep("Search " + resolvedFrom + " → " + resolvedTo, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            driver.findElement(By.id("from-city")).sendKeys(resolvedFrom);
            driver.findElement(By.id("to-city")).sendKeys(resolvedTo);
            driver.findElement(By.id("btn-search")).click();
        });
    }

    @When("I select flight {string}")
    @StepDescription("Select flight from results")
    public void selectFlight(String flightNumber) {
        String resolved = resolveTestData(flightNumber);
        StepReportingWrapper.executeStep("Select flight " + resolved, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            String xpath = "//tr[td[text()='" + resolved + "']]//button";
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        });
    }

    @Then("I should see welcome message {string}")
    @StepDescription("Verify welcome message")
    public void verifyWelcome(String expected) {
        String resolved = resolveTestData(expected);
        StepReportingWrapper.executeStep("Verify welcome: " + resolved, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome-message")));
            if (!el.getText().contains(resolved)) {
                throw new AssertionError("Expected '" + resolved + "' but got '" + el.getText() + "'");
            }
        });
    }

    @Then("I should see error message {string}")
    @StepDescription("Verify error message is displayed")
    public void verifyError(String expected) {
        String resolved = resolveTestData(expected);
        StepReportingWrapper.executeStep("Verify error: " + resolved, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(d -> {
                List<WebElement> errors = d.findElements(By.cssSelector(".error"));
                return errors.stream().anyMatch(e ->
                    e.isDisplayed() && e.getText().contains(resolved));
            });
        });
    }

    @Then("I should see {string} flight results")
    @StepDescription("Verify flight result count")
    public void verifyFlightCount(String expectedCount) {
        String resolved = resolveTestData(expectedCount);
        int expected = Integer.parseInt(resolved);
        StepReportingWrapper.executeStep("Verify " + expected + " flights", () -> {
            WebDriver driver = GenericWrapper.getDriver();
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.presenceOfElementLocated(By.id("flightsTable")));
            List<WebElement> rows = driver.findElements(By.cssSelector("#flightsTable tbody tr"));
            if (rows.size() != expected) {
                throw new AssertionError("Expected " + expected + " flights, got " + rows.size());
            }
        });
    }

    @Then("I should see confirmation {string}")
    @StepDescription("Verify booking confirmation")
    public void verifyConfirmation(String expected) {
        String resolved = resolveTestData(expected);
        StepReportingWrapper.executeStep("Verify confirmation: " + resolved, () -> {
            WebDriver driver = GenericWrapper.getDriver();
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation-message")));
            if (!el.getText().contains(resolved)) {
                throw new AssertionError("Expected '" + resolved + "' but got '" + el.getText() + "'");
            }
        });
    }

    @When("I log {string} with status {string}")
    @StepDescription("Log message to report")
    public void logToReport(String message, String status) {
        StepReportingWrapper.recordManualStep(message, status);
    }

    private static JsonNode testDataRoot;

    private String resolveTestData(String value) {
        if (value == null || !value.startsWith("TD_")) return value;
        try {
            if (testDataRoot == null) {
                ObjectMapper mapper = new ObjectMapper();
                testDataRoot = mapper.readTree(new File("TestDatastore/json/WingIt.json"));
            }
            String tcId = extractTestCaseId();
            JsonNode tcNode = testDataRoot.get(tcId);
            if (tcNode != null && tcNode.has(value.substring(3))) {
                return tcNode.get(value.substring(3)).asText();
            }
            return "";
        } catch (Exception e) {
            logger.warn("Could not resolve {}: {}", value, e.getMessage());
            return value;
        }
    }

    private String extractTestCaseId() {
        var scenario = TestContext.getCurrentScenario();
        if (scenario != null) {
            for (String tag : scenario.getSourceTagNames()) {
                if (tag.startsWith("@TC_")) return tag.substring(1);
            }
        }
        String tcId = TestContext.getCurrentTestCaseId();
        return tcId != null ? tcId : "TC_WINGIT_AUTH_001";
    }
}
