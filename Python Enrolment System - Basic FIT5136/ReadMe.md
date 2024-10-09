# Student Enrollment System
## Author Information

**Name:** Antoni Erdeg  
**Email:** [antoni.erdeg@monash.edu](mailto:antoni.erdeg@monash.edu)


## Disclaimer

We **WILL NOT** be providing any additional documentation or class diagrams for this example code. Please note that the existing class diagrams may differ from the given implementation. **DO NOT** ask for class diagrams or additional documentation on ED. This is optional content and not required for the completion of the assignment.

**WE DO NOT HAVE AND WE HAVEN'T CREATED ANY DIAGRAMS FOR THIS PROJECT, SO PLEASE DO NOT ASK FOR IT**

**Note:** This design has been simplified. To improve scalability and follow good design principles, consider refactoring the `StudentEnrollmentSystem` into three distinct classes: `Application`, `Menu`, and `StudentEnrollmentSystem`. This separation will enhance maintainability and support future expansion.

## Overview

The `StudentEnrollmentSystem` is a command-line Python application designed to manage student enrollments for units. It supports adding and removing students and units, enrolling students in units, and listing student enrollments. The system can be accessed by both students and administrators, with distinct functionalities for each role.

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
- `students`: A dictionary storing student IDs and their corresponding `Student` objects.
- `units`: A dictionary storing unit codes and their corresponding `Unit` objects.
- `admin`: An `Administrator` object representing the admin user.

**Constructor:**
- `__init__(self, admin)`: Initializes the system with an administrator.

**Methods:**
- `display_header(self, page_name)`: Displays the header for the given page.
- `get_students(self)`: Returns the dictionary of students.
- `get_student(self, student_id)`: Returns a specific student by ID.
- `add_student(self, student)`: Adds a student to the system.
- `remove_student(self, student_id)`: Removes a student by ID.
- `student_exists(self, student_id)`: Checks if a student exists by ID.
- `add_unit(self, unit)`: Adds a unit to the system.
- `is_unit_available(self, unit)`: Checks if a unit is available.
- `add_unit_to_student(self, student_id, unit)`: Adds a unit to a student's enrollment.
- `remove_unit_from_student(self, student_id, unit_name)`: Removes a unit from a student's enrollment.
- `list_student_units(self, student_id)`: Lists all units a student is enrolled in.
- `get_units(self)`: Returns the collection of units.
- `display_main_menu(self)`: Displays the main menu and handles user input.

### Student

An abstract class representing a student with basic details and enrollment functionalities. Subclasses should implement specific student types like `FullTimeStudent` and `PartTimeStudent`.

**Methods:**
- `add_unit(self, unit, system)`: Enrolls the student in a unit.
- `remove_unit(self, unit_code)`: Removes a unit from the student's enrollment.
- `list_units(self)`: Lists all units the student is enrolled in.

### FullTimeStudent & PartTimeStudent

Concrete implementations of the `Student` class representing full-time and part-time students, respectively.

### Unit

Represents an academic unit with a code, title, and credit points.

**Fields:**
- `code`: The unit's code.
- `title`: The unit's title.
- `credit_points`: The number of credit points for the unit.

**Methods:**
- `to_string(self)`: Returns a string representation of the unit.

### Administrator

Represents the administrator with permissions to manage students and units. Details of this class are assumed based on its usage in the `StudentEnrollmentSystem`.

## Usage

To start the system, instantiate the `StudentEnrollmentSystem` with an `Administrator` object and call `display_main_menu()`.

## Usage
To start the system, instantiate the `StudentEnrollmentSystem` with an `Administrator` object and call `displayMainMenu()`.

**Example:**
```Python
    ses = StudentEnrollmentSystem(Administrator("Admin"))
    ses.add_unit(Unit("FIT5136","Software Engineering", 6))
    ses.add_student(FullTimeStudent("123123", "John Lee", "123 Fake Street", "1111111111", "JohnLee@fakestreet.com"))
    ses.display_main_menu()
```
## User Interface
The application uses a command-line interface with menus for students and administrators:

- `Main Menu`: Allows the user to choose between student or administrator functions.
- `Student Menu`: Options for students to add/remove units and view their current enrollments.
- `Administrator Menu`: Options for administrators to manage students, units, and student enrollments.
