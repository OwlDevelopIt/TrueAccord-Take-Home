# How To Run
This is a Spring Boot project and the JSON Lines output will be printed to the console as soon as application starts.
### Terminal
If you would like to run the executable .jar file in the target directory 

`$ cd /location/to/true-accord/`  --cd into root directory of application

`$ mvn clean install` --builds package

`$ java -jar target/true-accord-0.0.1-SNAPSHOT.jar` --executes .jar

To stop application: CTRL + C

### IDE
Import as Maven project

Run the TrueAccordApplication class 

# Overview
### Time Spent
Most of my time was spent creating the ProcessorService class (which contains the bulk of my logic) and the test classes. I first created the different models for the objects being grabbed from the API. Once I decided on the way I wanted these objects returned from the API service classes, I began writing the logic to process the debts for the outputs and then wrote the corresponding tests. Finally I focused on refactoring my code to make it readable and extendable. 

### Approach
I used Spring Boot because it's great for quickly building web applications, API calls, and dependency injection. I also leveraged Gson to create each json object for the required JSON Lines format. 

I created the CommandLineRunner class to inject the `ProcesserService` and invoke the `processDebts()` method once the app starts. The `Util` class to call the static `convertToJson()` and `print()` methods. I created seperate services for each API (debts,payment_plans,payments) for scalability and clear separation of concerns. Lastly, I use the `ProcessorService` class to hold the logic of processing each debt. 

The test suite tests all API calls and each of the processor methods. 

Given more time, I would add a controller layer to expose an API to this program (`e.g. "/getCalculatedDebts"`). I would also build a simple front end that would hit this new API and display the results instead of using the `CommandLineRunner`. 
I would also add the settled amount if a debt was on a payment plan
<!-- have completed a full back end for this internal web application and a simple front end to display the results instead of using the CommandLineRunner. -->

# Key Notes
You will find most of the logic in /src/main/java/hewitt/jalisa/trueaccord/service/ProcessorService.java 

`ProcessorService` invokes API services, links plans to debts, and determines next_payment_due_date and remaining_amount. 

The other service classes in the same package hit each of the API endpoints for the debts, payments, and payment plans.

The TrueAccordCommandLineApp runs immediately once the TrueAccordApplication Springboot Application runs. This class calls the ProcessorService to process all debts and prints the output.

Tests can be found under /src/test/

# Assumptions
I made the assumption that all payments were sorted by date to calculate the last payment date in a certain payment plan. If this was not the case, I would loop through each payment to find the latest payment instead of taking the last payment in the list. The amount listed in the output is the total debt amount, not the settled amount on a payment plan.





