package tests;

import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class TestCaseTests extends BaseTest {
    private static final String EXPECTED_ERROR_MESSAGE = "Field Title is a required field.";
    private static final String FILE_NAME = "TestRail";
    private String filePath = System.getProperty("user.dir") + "/src/test/resources/TestRail.jpg";


    @BeforeMethod(alwaysRun = true)
    public void addTestCase() {
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProjectByName(project.getName());
        projectPage.clickAddTestCasesLink();

    }

    @Test(description = "Check if the test case can be created", groups = "smoke")
    public void positiveAddTestCaseTest() {
        TestCase actualTestCase = TestDataGenerator.positiveTestCaseGeneration();
        addTestCasePage.fillingOutTestCase(actualTestCase);
        addTestCasePage.clickAddTestCaseButton();
        TestCase expectedTestCase = testCaseInfoPage.getTestCaseInfo();
        Assert.assertEquals(expectedTestCase, actualTestCase);
    }

    @Test(description = "Check if the test case can not be created without title", groups = "smoke")
    public void negativeAddTestCaseTest() {
        TestCase testCase = TestDataGenerator.negativeTestCaseGeneration();
        addTestCasePage.fillingOutTestCase(testCase);
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertEquals(addTestCasePage.gerErrorMessageText(), EXPECTED_ERROR_MESSAGE);

    }

    @Test(description = "Check if file can be uploaded in test case", groups = "regression")
    public void fileUploadTest() {
        TestCase testCase = TestDataGenerator.positiveTestCaseGeneration();
        addTestCasePage.fillingOutTestCase(testCase);
        addTestCasePage.clickAddImageButton();
        addTestCasePage.uploadFile(filePath);
        addTestCasePage.clickSubmitAttachment();
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertTrue(testCaseInfoPage.isFileUploaded(FILE_NAME), "File was not uploaded");


    }

}
