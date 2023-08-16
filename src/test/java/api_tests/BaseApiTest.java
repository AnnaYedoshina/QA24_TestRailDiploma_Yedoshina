package api_tests;

import com.github.javafaker.Faker;
import controllers.CasesController;
import controllers.MilestoneController;
import controllers.ProjectController;
import controllers.SectionController;
import io.restassured.response.Response;
import models.Project;
import org.testng.annotations.BeforeClass;
import utils.TestDataGenerator;


public class BaseApiTest {

    protected int projectId;
    protected Project project;

    ProjectController projectController = new ProjectController();
    SectionController sectionController = new SectionController();
    CasesController casesController = new CasesController();
    MilestoneController milestoneController = new MilestoneController();
    static Faker faker = new Faker();


    @BeforeClass(alwaysRun = true)
    public void createTestProject() {
        project = TestDataGenerator.projectGeneration();
        Response response = projectController.addProject(project);
        projectId = response.getBody().jsonPath().getInt("id");


    }

}





