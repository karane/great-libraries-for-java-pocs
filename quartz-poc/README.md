# Quartz POC

This project demonstrates how to use the Quartz Scheduler in Java to schedule jobs with different intervals. It includes multiple jobs, including one that is delayed by 30 seconds before starting.

## Requirements

- Java 17 or higher
- Maven

## Setup



1. **Clone the Repository**:
```sh
git clone https://github.com/karane/great-libraries-for-java-pocs.git
cd quartz-poc
```

2. **Running the Project**

```sh
mvn clean install
mvn exec:java -Dexec.mainClass="org.karane.Main"
```

3. **Expected Behavior**:

When you run the application, you will see the following:

Job 1 will print a message every 5 seconds.
Job 2 will print a message every 10 seconds.
GoodbyeJob will print a "Goodbye" message 30 seconds after the application starts, and then repeat every 15 seconds.

**Example output**:
```
Hello from Job 1!
Hello from Job 2!
Goodbye from GoodbyeJob!
Hello from Job 1!
Hello from Job 2!
Goodbye from GoodbyeJob!
```