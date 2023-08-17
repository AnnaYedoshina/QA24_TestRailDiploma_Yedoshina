package tests;


import models.Section;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;


public class SectionTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void addSection() {
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProject(PROJECT_NAME);
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSectionButton();
    }

    @Test(description = "Check if the test section can be created", groups = "regression")
    public void createSectionTest() {
        Section section = TestDataGenerator.sectionGeneration();
        testCasesTab.createSection(section);
        Assert.assertTrue(testCasesTab.isSectionExist(section.getName()), "Section was not created");
    }

    @Test(description = "Check if the test section can be updated", groups = "regression")
    public void updatedSectionTest() {
        Section section = TestDataGenerator.sectionGeneration();
        Section updatedSection = TestDataGenerator.sectionGeneration();
        testCasesTab.createSection(section);
        testCasesTab.clickEditSection(section.getName());
        testCasesTab.updateSection(updatedSection);
        Assert.assertTrue(testCasesTab.isSectionExist(updatedSection.getName()), "Section was not updated");
    }

    @Test(description = "Check if the test section can be deleted", groups = "regression")
    public void deletedSectionTest() {
        Section section = TestDataGenerator.sectionGeneration();
        testCasesTab.createSection(section);
        testCasesTab.clickDeleteSection(section.getName());
        deleteCheckBoxModal.waitForConfirmationWindowDisplayed();
        deleteCheckBoxModal.checkCheckbox();
        deleteCheckBoxModal.confirmDelete();
        testCasesTab.openCaseTab();
        Assert.assertTrue(testCasesTab.isAddSectionButtonDisplayed(), "Section has not been deleted");
    }

}
