<!-- language=ALL disable -->
# Lombok

## pom.xml

<!-- language=ALL disable -->
```xml

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<plugins>
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
    </configuration>
</plugin>
</plugins>
```

## annotations:

1. @Data: annotation at the class level is provided by Lombok and tells Lombok to generate all
   of those missing methods as well as a constructor that accepts all final properties as
   arguments.

2. @Slf4j: at compilation time, will automatically generate an SLF4J [Simple Logging Facade for
   Java](https://www.slf4j.org/) Logger static property in the class. It has the same effect of
   below code.


<!-- language=ALL disable -->
```java
private static final org.slf4j.Logger log=
        org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
```

# Spring
## DB
1.  If there’s a file named schema.sql in the root of the application’s
    classpath, then the SQL in that file will be executed against the
    database when the application starts. Therefore, you should place
    the schema.sql in the src/main/resources folder.
2.  Spring Boot will also execute a file named data.sql from the root of
    the classpath when the application starts. Therefore, you can load
    the database placed in src/main/resources/data.sql.

## h2-console
1. set db name to tacocloud
```
spring:
  datasource:
    generate-unique-name: false
    name: tacocloud
```
2. open http://localhost:8080/h2-console and access it using
   jdbc:h2:mem:tacocloud

## request-mapping annotations
| Annotation | Description |
| -------- | -------- |
| @RequestMapping    | General-purpose request handling |
| @GetMapping    | Handles HTTP GET requests |
| @PostMapping    | Handles HTTP POST requests |
| @PutMapping    | Handles HTTP PUT requests |
| @DeleteMapping    | Handles HTTP DELETE requests |
| @PatchMapping    | Handles HTTP PATCH requests |

##  To apply validation in Spring MVC, you need to
1. Add the Spring Validation starter to the build.
2. Declare validation rules on the class that is to be validated:
   specifically, the Taco class.
3. Specify that validation should be
   performed in the controller methods that require validation:
   specifically, the DesignTacoController’s processTaco() method and the
   OrderController’s processOrder() method.
4. Modify the form views to
   display validation errors.

## Autowire
1.  when there’s only one constructor, Spring implicitly applies autowiring of dependencies through that constructor’s parameters. If there is more than one constructor, or if you just want autowiring to be explicitly stated, then you can annotate the constructor with @Autowired
# Java
## java.time
1. LocalDate: date without time or timezone info.
```java
import java.time.LocalDate;

public class DateExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now(); // Gets the current date
        LocalDate specificDate = LocalDate.of(2023, 1, 1); // Represents January 1, 2023

        System.out.println("Today: " + today);
        System.out.println("Specific Date: " + specificDate);
    }
}

```
2. LocalTime: time without date or timezone info
```java

import java.time.LocalTime;

public class TimeExample {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now(); // Gets the current time
        LocalTime specificTime = LocalTime.of(13, 30); // Represents 1:30 PM

        System.out.println("Current Time: " + now);
        System.out.println("Specific Time: " + specificTime);
    }
}
```
3.LocalDateTime: date and time, but without timezone info.
```java
import java.time.LocalDateTime;

public class DateTimeExample {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(); // Gets the current date and time
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 1, 1, 13, 30); // January 1, 2023, at 1:30 PM

        System.out.println("Current Date and Time: " + now);
        System.out.println("Specific Date and Time: " + specificDateTime);
    }
}

```

4. ZonedDateTime:date, time and timezone
```java
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class ZonedDateTimeExample {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(); // Current date and time in the system default timezone
        ZonedDateTime specificZone = ZonedDateTime.now(ZoneId.of("Asia/Tokyo")); // Current date and time in Tokyo

        System.out.println("Current Zoned Date and Time: " + now);
        System.out.println("Zoned Date and Time in Tokyo: " + specificZone);
    }
}

```
5. Instant: represent a specific moment on the timeline
```java
import java.time.Instant;

public class InstantExample {
    public static void main(String[] args) {
        Instant now = Instant.now(); // Current timestamp

        System.out.println("Current Timestamp: " + now);
    }
}
```



# Recommended books
1. Domain-Driven Design: Tackling Complexity in the Heart of Software --
   downloaded
2. 
