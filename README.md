Test Automation Framework for Testrail.io
<a href="https://www.gurock.com/testrail/">
<img src="https://res.cloudinary.com/duauoz75o/image/upload/v1624549552/Logotip-TestRail_husnvu.png" align="right" height="120" />
</a>

# TestRail Diploma Project
<h2>Description of Product</h2>
<h4>TestRail is a web-based test management tool used by testers, developers and other stake holders to manage, track and organize software testing efforts. 
It follows a centralized test management concept that helps in easy communication and enables rapid development of task across QA team and other stakeholders.<h4>
<h2>Checklist</h2>
<h3>Login</h3>
<h4>1. Login with correct values. [Expected: Successful authorization, Home page is opened]</h4>
<h4>2. Email field is empty [Expected: Error Unsuccessful authorization]</h4> 
<h4>3. Password field is empty [Expected: Error Unsuccessful authorization]</h4>
<h4>5. Invalid Email [Expected: Error Unsuccessful authorization]</h4> 
<h4>4. Ivalid password [Expected: Error Unsuccessful authorization]</h4>
<h3>MileStone</h3>
<h4>1.Create new Milestone [Expected: Milestone is created and exists in Milestone Tab]</h4>
<h4>2.Update Milestone [Expected: Milestone is updated and exists in Milestone Tab]</h4>
<h4>3.Delete Milestone [Expected: Created Milestone is deleted]</h4>
<h3>Project</h3>
<h4>1. Create new Project [Expected: Project is created and exists on the Projects page]</h4>
<h4>3. Uptade created Project [Expected:Project is updated]</h4>
<h4>2. Delete Project [Expected: Created project is deleted]</h4>
<h3>Section</h3>
<h4>1. Create new Section [Expected: Section is created and exists in the Test Cases Tab]</h4>
<h4>3. Uptade created Section [Expected:Section is updated]</h4>
<h4>2. Delete Section [Expected: Created section is deleted]</h4>
<h3>TestCase</h3>
<h4>1. Create new Test Case [Expected: Test Case is created and exists in Test Cases Tab]</h4>
<h4>3. Update Test Case [Expected: Test Case is updated and exists in Test Cases Tab]</h4>
<h4>2. Delete Test Case [Expected: Created Test Case is deleted]</h4>
<h3>Stack</h3>
<h4>[Selenium  4.9.1]</h4>
<h4>[Maven 2.12.4]</h4>
<h4>[TestNG 7.8.0]</h4>
<h4>[Lombok 1.18.8]</h4>
<h4>[Rest Assured 5.3.1]</h4>
<h4>[Gson 2.10.1]</h4>
<h4>[Java Faker 1.0.2]</h4>
<h4>[Jackson-DataFormat-yaml 2.12.3]</h4>
<h4>[Log4j2 2.20.0]</h4>
<h4>[Allure TestNG 2.19.0]</h4>
<h4>[OS: macOs Ventura 13.4]</h4>
<h3>Api Requests</h3>
<h4>Add Project<h4>
<h4>Update Project<h4>
<h4>Get Project<h4>
<h4>Delete Project<h4>
<h4>Add Test Case<h4>
<h4>Get Test Case<h4>
<h4>Update Test Case<h4>
<h4>Delete Test Case<h4>
<h4>Add Milestone<h4>
<h4>Get Milestone<h4>
<h4>Update Milestone<h4>
<h4>Delete Milestone<h4>
<h3>Patterns Used:<h3>
<h4>Page Object Model<h4>
<h4>Builder<h4>
<h4>Element Decorators<h4>
<h4>Data Provider<h4>
<h4>Value Object<h4>
<h3>Instruction to run tests<h3>
<h4>1. Open resources tab<h4>
<h4>2. Open config.properties tab<h4>
<h4>3. Change base_url<h4>
<h4>4. Change username<h4>
<h4>5. Change password<h4>
<h4>TestNG command for running all tests: - mvn clean test<h4>
