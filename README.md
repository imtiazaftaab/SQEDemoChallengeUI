## Demo Challenge

#### Instructions
1. Complete the project setup as listed below
2. Complete the Excerise
3. Email a synopsis of your work and the link to your git repo containing the completed exercise to: Ruslan_Kashapau@epam.com


#### Expectations
We will be evaluating
1. Naming conventions
2. Code readability
3. Code encapsulation
4. Code structure and organization
5. Quality of test cases
6. Variety  of testing types (examples: boundary, happy path, negative, etc) 


#### Technologies
1. Java
2. Selenium
3. TestNG
4. Any other technologies you see fit.
5. Please do not use a BDD framework.

#### Project Setup
1. Clone this project to your git account in a public repo
2. Setup the project in your IDE
3. Open the index.html file from src/test/resource/files in a browser
4. Copy the url from the browser and update the url value in src/test/resource/config.properties to be the copied url.
5. In src/test/resources update the config.properties file platform for your OS.
6. From command line run mvn clean install -U -DskipTests
7. Make sure you can run the DemoTest and chrome launches.  You may need to update the chromedriver in /src/test/resources/chromedriver/ to the version that works with your browser
   https://chromedriver.chromium.org/


#### Exercise
1. Use the site at the index.html
2. There are helper locators provided for you in the src/test/resource/files/locators.txt file.
3. In the Test Cases section below:
  - List all of the test cases you think are necessary to test the sample page
  - Note any defects or issues observed
4. Code up a few examples of:
  - At least one happy path case placing an order
  - At least one error case
5. When complete please check your code into your public git repo
3. 
#### Test Cases

1. Place a Pizza order with two toppings with all the required fields.
2. Place a Pizza Order with one toppings with all the required fields.
3. Verify title = Pizza Order Form
4. Verify page header (Pizza Order Form)
5. verify pizza 1 text and pizza names selections
6. verify toppings1 text and all toppings availability
7. verify toppings 2 and all toppings availability
8. verify maximum pizza quantity to order
9. verify total price
10. verify name, email and phone number fields
11. verify payment information  credit card and Cash on pickup radiobuttons and can select only one
12. verify place order button and message
13. verify reset button sets all values to default.
 
### Defects Identified

1. When we place order with 1 toppins than final response show 2 toppings.
2. Reset button does not reset values to default for following fields: Toppins1, Toppins2
3. In Payment Information section, I am able to select both options credit and cash.
4. Email field is not validating the email format.
5. User is able to select toppings after selecting no toppings type pizza in pizza1 field.
6. Phone number formation is not getting validated, it is accepting the alphabetic character as well.
7. User is able to place order without selecting any payment type.

### Some More observation in question and Code

1. There was 2 toppings1 name was there in locator file, looks like typing mistake.
2. Dialogbox xpath was wrong in locator file, it has one extra function bracket.
