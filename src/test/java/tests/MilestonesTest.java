package tests;

import models.Milestone;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class MilestonesTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginAndOpenMilestonesTab() {
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProject(PROJECT_NAME);
        projectPage.openMilestoneTab();
        milestonesTab.isPageOpened();
        milestonesTab.clickAddMilestoneButton();
    }

    @Test(description = "Check if the milestone can be created", groups = "regression")
    public void createMilestoneTest() {
        Milestone milestone = TestDataGenerator.milestoneGeneration();
        milestonesTab.createMilestone(milestone);
        Assert.assertTrue(milestonesTab.isMilestoneExist(milestone.getName()), "Milestone was not created");
    }

    @Test(description = "Check if the milestone can be updated", groups = "regression")
    public void updatedMilestoneTest() {
        Milestone milestone = TestDataGenerator.milestoneGeneration();
        Milestone updatedMilestone = TestDataGenerator.milestoneGeneration();
        milestonesTab.createMilestone(milestone);
        milestonesTab.clickEditMilestone(milestone.getName());
        milestonesTab.updateMilestone(updatedMilestone);
        milestonesTab.openMilestoneTab();
        Assert.assertTrue(milestonesTab.isMilestoneExist(updatedMilestone.getName()), "Milestone was not updated");
    }

    @Test(description = "Check if the test milestone can be deleted", groups = "regression")
    public void deletedMilestoneTest() {
        Milestone milestone = TestDataGenerator.milestoneGeneration();
        milestonesTab.createMilestone(milestone);
        milestonesTab.clickDeleteMilestone(milestone.getName());
        deleteCheckBoxModal.waitForConfirmationWindowDisplayed();
        deleteCheckBoxModal.confirmDelete();
        milestonesTab.openMilestoneTab();
        Assert.assertFalse(milestonesTab.isMilestoneExist(milestone.getName()), "Milestone has not been deleted");
    }
}

