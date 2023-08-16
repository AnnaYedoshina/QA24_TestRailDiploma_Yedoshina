package api_tests;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestDataGenerator;


public class MilestoneApiTests extends BaseApiTest {
    protected Milestone milestone;
    private int milestoneId;

    @BeforeClass(alwaysRun = true)
    public void addNewMilestone() {
        milestone = TestDataGenerator.milestoneGeneration();
        Response response = milestoneController.addMilestone(milestone, projectId);
        milestoneId = response.getBody().jsonPath().getInt("id");

    }

    @Test(description = "Check if the milestone can be gotten by API", priority = 1, groups = "api")
    public void getMilestone() {
        Response response = milestoneController.getMilestone(milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Milestone.class, ObjectMapperType.GSON), milestone);
    }

    @Test(description = "Check if the milestone can be created by API", priority = 2, groups = "api")
    public void addMilestone() {
        Milestone milestone = TestDataGenerator.milestoneGeneration();
        Response response = milestoneController.addMilestone(milestone, projectId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Milestone.class, ObjectMapperType.GSON), milestone);
    }

    @Test(description = "Check if the milestone can be updated by API", priority = 3, groups = "api")
    public void updateMilestone() {
        Milestone milestone = TestDataGenerator.milestoneGeneration();
        Response response = milestoneController.updateMilestone(milestone, milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Milestone.class, ObjectMapperType.GSON), milestone);
    }


    @Test(description = "Check if the milestone can be deleted by API", priority = 4, groups = "api")
    public void deleteMilestone() {
        Response response = milestoneController.deleteMilestone(milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}