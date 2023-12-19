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
