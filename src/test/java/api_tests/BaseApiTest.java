package api_tests;

import controllers.CasesController;
import controllers.MilestoneController;
import controllers.ProjectController;
import controllers.SectionController;
import io.restassured.response.Response;
import models.Project;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class BaseApiTest {

    protected int projectId;

    ProjectController projectController = new ProjectController();
    SectionController sectionController = new SectionController();
    CasesController casesController = new CasesController();
    MilestoneController milestoneController = new MilestoneController();


    @BeforeClass(alwaysRun = true)
    public void createTestProject() {
        Project project = Project.builder()
                .setName("TestProject")
                .setAnnouncement("Project for testing")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response response = projectController.addProject(project);
        projectId = response.getBody().jsonPath().getInt("id");

    }


    @AfterClass(alwaysRun = true)
    public void deleteTestProject() {
        projectController.deleteProject(projectId);
    }

}


