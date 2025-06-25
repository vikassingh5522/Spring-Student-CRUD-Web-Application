Project Description: Student CRUD Web Application

![image](https://github.com/user-attachments/assets/03b58bc1-f1a0-4ce9-95b0-5cc35dfdaa8e)

![image](https://github.com/user-attachments/assets/06f87e2f-a1eb-4113-822c-012ac34216ee)

![image](https://github.com/user-attachments/assets/3661e39a-9348-4515-b6b4-512f4a1a8ddd)

![image](https://github.com/user-attachments/assets/d8094430-ab8a-43a2-9700-0dc26f71c23f)

![image](https://github.com/user-attachments/assets/5b44c140-20b9-4d4f-9876-4dec22ebae7b)

![image](https://github.com/user-attachments/assets/2c6a7718-a950-4321-8697-a3ee239ffa32)


https://chatgpt.com/c/685aa69d-62b0-8009-a4b7-4995173e9f64

### Project Description: Student CRUD Web Application


The **Student CRUD Web Application** is a Java-based web application designed to manage student records using a Create, Read, Update, and Delete (CRUD) interface. Built with Spring MVC, it provides a user-friendly JSP-based UI for performing operations on student data stored in a MySQL database. The application incorporates HikariCP for efficient database connection pooling, Logback for logging, and AOP for cross-cutting concerns like logging method execution. It leverages Jakarta EE 10 (Servlet 6.0) and is deployed on Apache Tomcat 10.1, ensuring modern web standards and compatibility.

#### Key Features
- **Student Management**:
  - Create, view, edit, and delete student records (ID, name, email).
  - Validation ensures non-empty names, valid email formats, and unique emails.
- **Web Interface**:
  - JSP pages (`list.jsp`, `create.jsp`, `edit.jsp`, `view.jsp`, `logs.jsp`, `index.jsp`) with JSTL for dynamic rendering.
  - CSS styling for a clean, responsive UI.
- **Database Integration**:
  - MySQL database (`studentdb`) with a `student` table.
  - HikariCP for optimized connection management.
  - Spring JDBC for database operations via `StudentDAO`.
- **Logging**:
  - Logback writes logs to `student-crud-web/logs/student-crud.log` in the project directory.
  - `LogViewer` displays logs via the `/students/logs` endpoint.
  - AOP-based logging tracks method entry/exit and exceptions.
- **Error Handling**:
  - Graceful handling of database and file access errors.
  - User-friendly error messages in the UI.

#### Technology Stack
- **Backend**: Spring MVC 6.1.14, Spring JDBC, Spring AOP
- **Frontend**: JSP, JSTL (Jakarta EE 10), CSS
- **Database**: MySQL 8.0, HikariCP 5.1.0
- **Logging**: Logback 1.5.13, SLF4J
- **Server**: Apache Tomcat 10.1 (Servlet 6.0, Jakarta EE 10)
- **Build Tool**: Maven
- **Java**: 17
- **Validation**: Jakarta Validation 3.0.2, Hibernate Validator 8.0.1

#### Project Structure
- **Source Code**: `src/main/java/com/example/` (controller, dao, model, util, config, aspect, annotation)
- **Resources**: `src/main/resources/` (logback.xml)
- **Web Assets**: `src/main/webapp/` (WEB-INF/views/*.jsp, css/, web.xml)
- **Logs**: `student-crud-web/logs/student-crud.log`
- **WAR**: `target/student-crud-web.war`

#### Challenges Addressed
- **HTTP 404 Error**: Fixed by aligning `web.xml` and `pom.xml` with Jakarta EE 10, using Tomcat 10.1, and ensuring proper Spring MVC configuration.
- **HTTP 500 Error**: Resolved database connectivity issues, JSP syntax errors (e.g., unterminated `<c:choose>` tag), and added exception handling.
- **Logging Issues**: Configured Logback and `LogViewer` to write/read logs from `student-crud-web/logs/`, fixing path and syntax errors.

#### Usage
- **Setup**:
  - Configure MySQL (`studentdb`, `student` table).
  - Create `student-crud-web/logs/` directory.
  - Build with `mvn clean install`.
  - Deploy `target/student-crud-web.war` to Tomcat 10.1.
- **Endpoints**:
  - `/`: Home page (`index.jsp`).
  - `/students`: List students.
  - `/students/create`: Create student.
  - `/students/{id}`: View student.
  - `/students/edit/{id}`: Edit student.
  - `/students/delete/{id}`: Delete student.
  - `/students/logs`: View application logs.

#### Future Enhancements
- Add user authentication and authorization.
- Implement pagination for large student lists.
- Enable log rotation and size limits in Logback.
- Add REST API endpoints for external integration.

This application serves as a robust, maintainable solution for student record management, demonstrating best practices in Spring MVC, database integration, and logging.




- **pom.xml**: Defines Maven dependencies and build configuration for Spring 6.1.14, Jakarta EE 10, and MySQL.
- **web.xml**: Configures Spring DispatcherServlet and welcome file (`index.jsp`) for Servlet 6.0 (Jakarta EE 10).
- **SpringConfig.java**: Spring configuration with MVC, component scanning, HikariCP, and JSP view resolver.
- **Student.java**: Model class with student attributes (id, name, email) and Jakarta validation annotations.
- **StudentDAO.java**: Interface for CRUD operations on student records using Spring JDBC.
- **StudentDAOImpl.java**: Implements StudentDAO with JdbcTemplate for database interactions.
- **StudentController.java**: Handles HTTP requests for student CRUD and log viewing with Spring MVC.
- **LogViewer.java**: Reads logs from `student-crud-web/logs/student-crud.log` for display.
- **LoggingAspect.java**: AOP aspect for logging method entry, exit, and exceptions.
- **Loggable.java**: Custom annotation to mark methods for AOP logging.
- **DatabaseInitializer.java**: Initializes MySQL `student` table on application startup.
- **index.jsp**: Welcome page with links to student management and logs.
- **list.jsp**: Displays a table of students with view, edit, and delete options.
- **create.jsp**: Form for creating a new student with validation.
- **edit.jsp**: Form for updating an existing student’s details.
- **view.jsp**: Displays details of a single student.
- **logs.jsp**: Shows application logs from `LogViewer` with error handling.
- **style.css**: CSS styles for consistent UI across JSP pages.
- **logback.xml**: Configures Logback to write logs to `student-crud-web/logs/student-crud.log`.
- 




Great question, Vikas! You're asking how the `@Loggable` annotation, `LoggingAspect`, and `LogViewer.getLastLogLines()` work **together in the Spring AOP logging flow**. Let's break this down step by step, so you clearly understand the entire data flow and how everything connects.

---

## 🔁 OVERALL FLOW SUMMARY

```text
Your Annotated Method (with @Loggable)
        ↓ (Intercepted by Spring AOP)
    LoggingAspect Logs Details
        ↓
   Execution Continues Normally
        ↓
 LogViewer Reads Log File Content
        ↓
 Thymeleaf Displays Logs in the UI
```

---

## ✅ 1. `@Loggable` Annotation

### What it does:

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loggable {
}
```

* This is a **custom annotation**.
* It can be placed on any method you want to **automatically log when called**.
* Example:

  ```java
  @Loggable
  public String getStudentList() {
      ...
  }
  ```

---

## ✅ 2. `LoggingAspect` Class

### Purpose:

This uses **Spring AOP** to intercept any method that has `@Loggable` and logs before and after the method runs.

```java
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.example.annotation.Loggable)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        logger.trace("Entering {}.{} with arguments: {}", className, methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed(); // Execute original method
            logger.info("Exiting {}.{} with result: {}", className, methodName, result);
            return result;
        } catch (Throwable e) {
            logger.error("Exception in {}.{}: {}", className, methodName, e.getMessage(), e);
            throw e;
        }
    }
}
```

### 📌 Flow:

1. When a method annotated with `@Loggable` is called,
2. Spring's AOP picks it up via `@Around("@annotation(...)")`.
3. It logs:

   * Entry (method name, class name, args),
   * Exit (result),
   * Any exception thrown.

### ✅ It uses `joinPoint.proceed()` to call the actual method after logging entry.

---

## ✅ 3. Log Output Written to File

Spring Boot with Logback (via `logback.xml`) automatically writes all logs:

* To `Console`
* To `logs/student-crud.log`

This is defined in your `logback.xml`:

```xml
<file>${LOG_DIR}/student-crud.log</file>
```

So when `LoggingAspect` logs:

```java
logger.info("Exiting ...");
```

It goes into `student-crud.log`.

---

## ✅ 4. `LogViewer.getLastLogLines()` Method

This is a helper to **read log contents from the file**:

```java
public String getLastLogLines(int numberOfLines) {
    Path logFile = Paths.get(LOG_FILE_PATH);
    List<String> allLines = Files.readAllLines(logFile);
    List<String> lastLines = allLines.subList(Math.max(0, allLines.size() - numberOfLines), allLines.size());
    return String.join("\n", lastLines);
}
```

### 💡 Purpose:

* Reads the last N lines from the log file.
* Returns them as a single formatted string.
* This is useful for displaying logs dynamically in a webpage.

---

## ✅ 5. Thymeleaf View

Finally, in your controller:

```java
@GetMapping("/logs")
public String showLogs(Model model) {
    String logs = logViewer.getLastLogLines(100); // or readLogFile()
    model.addAttribute("logContent", logs);
    return "logs";
}
```

Then in your HTML (Thymeleaf):

```html
<pre th:text="${logContent}"></pre>
```

So the logs from the aspect, stored in the file, are **read via `LogViewer`**, passed to the **controller**, then rendered to the **browser via Thymeleaf**.

---

## 🔄 FULL SEQUENCE EXAMPLE:

1. You call a method like:

   ```java
   @Loggable
   public String getAllStudents() { return "Success"; }
   ```

2. The `LoggingAspect` logs:

   ```
   Entering StudentService.getAllStudents with arguments: []
   Exiting StudentService.getAllStudents with result: Success
   ```

3. These logs go to `student-crud.log`.

4. The `LogViewer.getLastLogLines(50)` reads them.

5. Thymeleaf renders them inside `<pre>...</pre>` in browser.

---

## ✅ Final Thoughts:

| Component       | Role                                         |
| --------------- | -------------------------------------------- |
| `@Loggable`     | Marker to trigger logging                    |
| `LoggingAspect` | Intercepts method, logs entry/exit/exception |
| `logback.xml`   | Saves logs to file                           |
| `LogViewer`     | Reads logs from file                         |
| Thymeleaf page  | Displays logs in the browser                 |


Great, Vikas! Here's a **visual diagram** that clearly shows how everything works together in your Spring Boot log viewer setup using AOP and Thymeleaf.

---

## 🧠 **Spring Boot Logging Flow with AOP + Thymeleaf**

```plaintext
     🧑 You call a method
     ↓
   ┌──────────────────────────────┐
   │  @Loggable (custom annotation) │
   └──────────────────────────────┘
                ↓
   ┌──────────────────────────────┐
   │  LoggingAspect (Spring AOP)   │
   │  - Logs entry                 │
   │  - Calls actual method        │
   │  - Logs result or exception   │
   └──────────────────────────────┘
                ↓
   ┌──────────────────────────────┐
   │      Logback (logback.xml)   │
   │  - Writes to student-crud.log│
   └──────────────────────────────┘
                ↓
   ┌──────────────────────────────┐
   │   LogViewer.getLastLogLines()│
   │   - Reads N lines from log   │
   └──────────────────────────────┘
                ↓
   ┌──────────────────────────────┐
   │   LogController (/logs)      │
   │   - Calls LogViewer method   │
   │   - Adds logs to model       │
   └──────────────────────────────┘
                ↓
   ┌──────────────────────────────┐
   │ Thymeleaf View (logs.html)   │
   │ - Displays logs inside <pre> │
   └──────────────────────────────┘
                ↓
         🌐 Logs on browser
```

---

## 🧩 Breakdown of Components

| Component       | Description                                                               |
| --------------- | ------------------------------------------------------------------------- |
| `@Loggable`     | Marks methods to be auto-logged                                           |
| `LoggingAspect` | AOP aspect that intercepts methods with `@Loggable` and logs input/output |
| `logback.xml`   | Log configuration that stores output into `student-crud.log`              |
| `LogViewer`     | Reads the log file from disk                                              |
| `LogController` | Provides data to the Thymeleaf page                                       |
| `logs.html`     | Renders the log content in a nice format                                  |

---

## ✅ Sample Workflow Example

```java
@Loggable
public String fetchStudents() {
    return "Fetched 12 students";
}
```

* `LoggingAspect` logs entry:
  `🔍 Entering: fetchStudents()`

* Executes the method.

* `LoggingAspect` logs exit:
  `✅ Exiting: fetchStudents() | Result: Fetched 12 students`

* This gets written to:
  `logs/student-crud.log`

* `LogViewer.getLastLogLines(10)` reads it.

* `/logs` page renders it using Thymeleaf.

---

## 🛠 Want More?

Let me know if you want:

* ✅ Real-time log updates (AJAX or WebSocket)
* ✅ Log filtering (e.g., only `ERROR` logs)
* ✅ Download button for logs
* ✅ Switch between "Full log" and "Last N lines"
* ✅ Highlight different log levels with colors (e.g., ERROR = red)

Just say the word, and I’ll show you how!



