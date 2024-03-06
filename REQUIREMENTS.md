# Java Assignment for Engineering Managers in Consumer Tech

## Problem Statement
You are implementing a part of a shopping platform. Your task is to design and implement a
service that will provide a REST API for **calculating a given product's price based on the number
of products ordered**.

# Functional Requirements
 - Products in the system are identified by UUID;
 - There should be the possibility of applying discounts based on two policies:
   - **Amount-based** - the more pieces of the product are ordered, the bigger the discount is (e.g., 10 items – 2 USD off, 100 items – 5 USD off etc.);
   - **Percentage-based** - user gets a percentage discount – the more items ordered; the bigger the percentage discount is. (e.g., 10 items, 3% off, 50 items, 5% off etc.);
   - Policies should be configurable;

# Non-functional requirements
 - Use Java >= 17 and frameworks of your choice
 - The project should be containerized and easy to build and run via Gradle or Maven.
 - Please provide a README file with instructions on how to launch it
 - There's no need for full test coverage. Implement only the most essential (in your opinion) tests
 - Use the git repository for developing the project, and after you’re done, send us a link to it
 - Make sure we can run the project easily, without any unnecessary local dependencies (e.g., Don’t use OS-specific code)
 - Try not to spend more than one or two evenings on the assignment
 - You will eventually have a chance to explain your code in the next stage of the interview
 - If you must make some assumptions, document them as you see fit. DO the same with the technical assumptions you make.