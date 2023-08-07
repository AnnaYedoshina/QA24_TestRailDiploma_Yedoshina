package tests;

import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;


public class ProjectTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void addProject() {
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
    }

    @Test(description = "Check if the project can be created", groups = "smoke", priority = 1)
    public void createProjectTest() {
        Project project = TestDataGenerator.projectGeneration();
        createProjectPage.createProject(project);
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isProjectExist(project.getName()), "Project was not created");
    }

    @Test(description = "Check if the project can be updated", groups = "regression", priority = 2)
    public void updateProjectTest() {
        Project project = TestDataGenerator.projectGeneration();
        Project updatedProject = TestDataGenerator.updatedProjectGeneration();
        createProjectPage.createProject(project);
        administrationPage.isPageOpened();
        administrationPage.editProject(project.getName());
        createProjectPage.updateProject(updatedProject);
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isProjectExist(updatedProject.getName()), "Project was not updated");

    }

    @Test(description = "Check if the project can be deleted", groups = "regression", priority = 3)
    public void deleteProjectTest() {
        Project project = TestDataGenerator.projectGeneration();
        createProjectPage.createProject(project);
        administrationPage.isPageOpened();
        administrationPage.deleteProject(project.getName());
        administrationPage.confirmDeleteProject();
        dashboardPage.open();
        Assert.assertFalse(dashboardPage.isProjectExist(project.getName()), "Project has not been deleted");
    }
}
