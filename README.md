# Zerion-Testing
Automating the two testing activities for Zerion Testing

*Pre-requisites*:

1. JAVA 8 JDK installed
2. Editor - IntelliJ IDEA Or Eclipse
3. Web browser - Chrome browser

*How to checkout/import/open the project:*

1. clone/checkout this project by
* a) Either downloading the project and opening the pom.xml file from your editor
* b) From your terminal - git clone https://github.com/lavanyamantha/Zerion-Testing.git and open the pom.xml file from your editor
2. Make sure to do maven sync to import dependencies/.jars in your editor
3. Run command - mvn clean build from terminl Or Build Project in your editor
4. Right-click the test class file and Run/Debug in editor
* The 2 tests are at
   ``` 
   https://github.com/lavanyamantha/Zerion-Testing/blob/master/src/test/java/LoginCreateNewUser.java
   https://github.com/lavanyamantha/Zerion-Testing/blob/master/src/test/java/LoginCreateRecord.java
   ```
5. The test should open the web browser and run the tests and result displayed in edotor's console.


*Tools/Technology stack used for development*:

1. Editor - IntelliJ IDEA for test development
2. Test Framework - TestNG
3. Build dependency - Maven (selenium, testng, log4J)
4. Java - Programming language
5. Git/GitHub - T commit/puh code to Central repo & maintain
6. Test Design - Maintained proper structure for 
    * Config data, 
    * Object rpository, 
    * Base class for webdriver instantiation,
    * Utility class for helper methods,
    * IFormBuilderTestCase class for all common methods to be used in all tests,
    * Take screenshot when tests fails.
    * Support for Chromebrowser, Firefox & IE browsers. (Harcoded to chrome for now in Config file. Can be parameterized to do cross-browser testing.)
    
 *Things we can do Next*:
 
 1. Integrate with CI tools like Jenkins as 
      * independent test build job Or
      * include in dev build/test/deploy as a stage in current pipeline
      * reporting support - attach reports to test runs
 2. Communication of test result - Through Jenkins - send emails/ notify slack etc.
 3. Incorporate Behavior Driven Development (BDD) framework within this test design. Write .feature files for test cases following Gherkin syntax and implement the step definitions using Selenium+Java. (I am well versed with this test design too.)
    



