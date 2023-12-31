package tests;

import api_tests.BaseApiTest;
import lombok.extern.log4j.Log4j2;
import modals.DeleteCheckBoxModal;
import modals.DeleteConfirmationModal;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.InvokedListener;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
@Listeners({InvokedListener.class})
public abstract class BaseTest extends BaseApiTest {
    protected static final String BASE_URL = PropertyReader.getProperty("base_url");
    protected static final String USERNAME = PropertyReader.getProperty("username");
    protected static final String PASSWORD = PropertyReader.getProperty("password");
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected AddTestCasePage addTestCasePage;
    protected ProjectPage projectPage;
    protected TestCaseInfoPage testCaseInfoPage;
    protected DashboardPage dashboardPage;
    protected CreateProjectPage createProjectPage;
    protected AdministrationPage administrationPage;
    protected TestCasesTab testCasesTab;
    protected MilestonesTab milestonesTab;
    protected DeleteCheckBoxModal deleteCheckBoxModal;
    protected DeleteConfirmationModal deleteConfirmationModal;

    @Parameters({"browserName"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserName, ITestContext context) throws Exception {
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        } else {
            throw new Exception("Unsupported browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        addTestCasePage = new AddTestCasePage(driver);
        testCaseInfoPage = new TestCaseInfoPage(driver);
        dashboardPage = new DashboardPage(driver);
        createProjectPage = new CreateProjectPage(driver);
        administrationPage = new AdministrationPage(driver);
        testCasesTab = new TestCasesTab(driver);
        milestonesTab = new MilestonesTab(driver);
        deleteCheckBoxModal = new DeleteCheckBoxModal(driver);
        deleteConfirmationModal = new DeleteConfirmationModal(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get(BASE_URL);
        loginPage.acceptAlertIfPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }
}
