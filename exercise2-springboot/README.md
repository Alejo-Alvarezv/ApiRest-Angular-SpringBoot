# Example 2 - SpringBoot

Dependencies:

- Spring Boot
- Web
- JPA
- H2

## IEmployeeRepository.java

Spring makes accessing data easy. By simply declaring the following EmployeeRepository interface we automatically will be able to
- Create new Employees
- Update existing ones
- Delete Employees
- Find Employees (one, all, or search by simple or complex properties)

## MainApplication.java

is a meta-annotation that pulls in component scanning, autoconfiguration, and property support. We won’t dive into the details of Spring Boot in this tutorial, but in essence, it will fire up a servlet container and serve up our service.

## LoadDataBase.java

Init bean to insert 2 Employees

Spring Boot will run ALL **CommandLineRunner** beans once the application context is loaded.

This runner will request a copy of the **IEmployeeRepository** you just created.

Using it, it will create two entities and store them.

## EmployeeController.java

@RestController => indicates that the data returned by each method will be written straight into the response body instead of rendering a template.

An ``EmployeeRepository``` is injected by constructor into the controller.

```EmployeeNotFoundException``` is an exception used to indicate when an employee is looked up but not found.

## EmployeeNotFoundException.java

When an EmployeeNotFoundException is thrown, is used to render an HTTP 404:

## EmployeeNotFoundAdvice.java

**@ResponseBody** signals that this advice is rendered straight into the response body.

**@ExceptionHandler** configures the advice to only respond if an **EmployeeNotFoundException** is thrown.

**@ResponseStatus** says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.

The body of the advice generates the content. In this case, it gives the message of the exception.
