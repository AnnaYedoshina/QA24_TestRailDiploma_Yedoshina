package api_tests;

import com.google.gson.Gson;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectApiTests extends BaseApiTest {

    @Test(description = "Check if the project can be gotten by API", priority = 1, groups = "api")
    public void getProject() {
        Response response = projectController.getProject(projectId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Project.class, ObjectMapperType.GSON), project);

    }

    @Test(description = "Check if the project can be created by API from file", priority = 2, groups = "api")
    public void createProjectFromFile() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/requestProjectBody.json";
        File file = new File(filePath);
        Response response = projectController.addProject(file);
        Project expectedProject = null;
        try {
            expectedProject = new Gson().fromJson(Files.readString(Paths.get(filePath)), Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Project.class, ObjectMapperType.GSON), expectedProject);
    }

    @Test(description = "Check if the project can be createg by API", priority = 3, groups = "api")
    public void createProject() {
        Project project = TestDataGenerator.projectGeneration();
        Response response = projectController.addProject(project);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Project.class, ObjectMapperType.GSON), project);
    }

    @Test(description = "Check if the project can be updated by API", priority = 4, groups = "api")
    public void updateProject() {
        Project project = TestDataGenerator.projectGeneration();
        Response response = projectController.updateProject(project, projectId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Project.class, ObjectMapperType.GSON), project);
    }

    @Test(description = "Check if the project can be deleted by API", priority = 5, groups = "api")
    public void deleteProject() {
        Response response = projectController.deleteProject(projectId);
        Assert.assertEquals(response.getStatusCode(), 200);


    }

}
