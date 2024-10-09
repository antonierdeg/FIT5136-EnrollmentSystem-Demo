# Student Enrollment System
## Author Information

**Name:** Antoni Erdeg  
**Email:** [antoni.erdeg@monash.edu](mailto:antoni.erdeg@monash.edu)


## Disclaimer

We **WILL NOT** be providing any additional documentation or class diagrams for this example code. Please note that the existing class diagrams may differ from the given implementation. **DO NOT** ask for class diagrams or additional documentation on ED. This is optional content and not required for the completion of the assignment.

**WE DO NOT HAVE AND WE HAVEN'T CREATED ANY DIAGRAMS FOR THIS PROJECT, SO PLEASE DO NOT ASK FOR IT**

**Note:** This design has been simplified. To improve scalability and follow good design principles, consider refactoring the `StudentEnrollmentSystem` into three distinct classes: `Application`, `Menu`, and `StudentEnrollmentSystem`. This separation will enhance maintainability and support future expansion.

## Overview
The `StudentEnrollmentSystem` is a command-line Java application designed to manage student enrollments for units. It supports adding and removing students and units, enrolling students in units, and listing student enrollments. The system can be accessed by both students and administrators, with distinct functionalities for each role.

## Features

### Administrator Functions:
- Add and remove students and units.
- List all students and units.
- Enroll and remove units from students.
- View and manage current student enrollments.

### Student Functions:
- Enroll in or remove units.
- List currently enrolled units.

## Class Descriptions

### StudentEnrollmentSystem
This is the main class that handles student and unit management. It provides functionalities for adding, removing, and managing students and units.

**Fields:**
- `STUDENTS`: A map storing student IDs and their corresponding `Student` objects.
- `UNITS`: A map storing unit codes and their corresponding `Unit` objects.
- `SCANNER`: A `Scanner` object for reading user input.
- `ADMIN`: An `Administrator` object representing the admin user.

**Constructor:**
- `StudentEnrollmentSystem(Administrator admin)`: Initializes the system with an administrator.

**Methods:**
- `displayHeader(String pageName)`: Displays the header for the given page.
- `getStudents()`: Returns the map of students.
- `addStudent(Student student)`: Adds a student to the system.
- `removeStudent(String studentId)`: Removes a student by ID.
- `studentExists(String studentId)`: Checks if a student exists by ID.
- `addUnit(Unit unit)`: Adds a unit to the system.
- `isUnitAvailable(Unit unit)`: Checks if a unit is available.
- `addUnitToStudent(String studentId, Unit unit)`: Adds a unit to a student's enrollment.
- `removeUnitFromStudent(String studentId, String unitName)`: Removes a unit from a student's enrollment.
- `listStudentUnits(String studentId)`: Lists all units a student is enrolled in.
- `getUnits()`: Returns the collection of units.
- `displayMainMenu()`: Displays the main menu and handles user input.

### Student
An abstract class representing a student with basic details and enrollment functionalities. Subclasses should implement specific student types like `FullTimeStudent` and `PartTimeStudent`.

**Methods:**
- `addUnit(Unit unit, StudentEnrollmentSystem system)`: Enrolls the student in a unit.
- `removeUnit(String unitCode)`: Removes a unit from the student's enrollment.
- `listUnits()`: Lists all units the student is enrolled in.

### FullTimeStudent & PartTimeStudent
Concrete implementations of the `Student` class representing full-time and part-time students, respectively.

### Unit
Represents an academic unit with a code, title, and credit points.

**Fields:**
- `code`: The unit's code.
- `title`: The unit's title.
- `creditPoints`: The number of credit points for the unit.

**Methods:**
- `toString()`: Returns a string representation of the unit.

### Administrator
Represents the administrator with permissions to manage students and units. Details of this class are assumed based on its usage in the `StudentEnrollmentSystem`.

## Usage
To start the system, instantiate the `StudentEnrollmentSystem` with an `Administrator` object and call `displayMainMenu()`.

**Example:**
```java
Administrator admin = new Administrator("adminId", "adminName", "adminAddress", "adminPhone", "adminEmail");
StudentEnrollmentSystem system = new StudentEnrollmentSystem(admin);
system.displayMainMenu();
```
## User Interface
The application uses a command-line interface with menus for students and administrators:

- `Main Menu`: Allows the user to choose between student or administrator functions.
- `Student Menu`: Options for students to add/remove units and view their current enrollments.
- `Administrator Menu`: Options for administrators to manage students, units, and student enrollments.

