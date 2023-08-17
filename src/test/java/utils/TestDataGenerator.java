package utils;

import com.github.javafaker.Faker;
import models.*;

public class TestDataGenerator {
    static Faker faker = new Faker();
    private static final String TITLE = "Позитивное тестирование формы Login";

    public static TestCase positiveTestCaseGeneration() {
        return TestCase.builder()
                .setTitle(TITLE)
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();

    }

    public static TestCase negativeTestCaseGeneration() {
        return TestCase.builder()
                .setTitle("")
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();
    }

    public static Section sectionGeneration() {
        return Section.builder()
                .setName(faker.animal().name() + faker.number().randomDigit())
                .setDescription(faker.country().capital())
                .build();
    }


    public static Milestone milestoneGeneration() {
        return Milestone.builder()
                .setName(faker.country().name() + faker.number().randomDigit())
                .setDescription(faker.currency().name() + faker.number().randomDigit())
                .build();
    }

    public static Project projectGeneration() {
        return Project.builder()
                .setName(faker.app().name() + faker.number().randomDigit())
                .setAnnouncement(faker.app().version())
                .setSuiteMode(1)
                .build();
    }
}
