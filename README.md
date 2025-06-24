![image](https://github.com/user-attachments/assets/03b58bc1-f1a0-4ce9-95b0-5cc35dfdaa8e)
![image](https://github.com/user-attachments/assets/06f87e2f-a1eb-4113-822c-012ac34216ee)
![image](https://github.com/user-attachments/assets/d8094430-ab8a-43a2-9700-0dc26f71c23f)
![image](https://github.com/user-attachments/assets/5b44c140-20b9-4d4f-9876-4dec22ebae7b)
![image](https://github.com/user-attachments/assets/2c6a7718-a950-4321-8697-a3ee239ffa32)




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


