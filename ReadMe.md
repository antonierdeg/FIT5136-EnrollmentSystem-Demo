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