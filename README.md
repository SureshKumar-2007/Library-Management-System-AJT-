# Library Management System (Starter)

Tech: Java 11, Spring MVC 5, JSP/JSTL, JDBC (JdbcTemplate), MySQL (XAMPP), Tomcat 9, Maven.

## Quick Start

1. Start **XAMPP MySQL**.
2. Open `schema.sql` in **phpMyAdmin** (http://localhost/phpmyadmin) and run it.
3. In NetBeans: **Open Project** (choose this folder). Clean and Build.
4. Add **Apache Tomcat 9** as the server. Run the project.
5. Go to `http://localhost:8080/library-mvc/`
6. Login with **admin / admin**.
7. Manage Books & Members from the dashboard.

> DB config lives in `WEB-INF/spring-servlet.xml` (change username/password if needed).
> JSPs are under `WEB-INF/views`.

## Next steps
- Add issue/return (loans) controller & pages
- Add validation and search
- Switch to BCrypt & Spring Security for real auth
