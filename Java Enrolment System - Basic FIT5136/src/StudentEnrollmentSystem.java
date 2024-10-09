import java.util.*;

/**
 * Student Enrollment System - Manages students and units within the Student Enrollment System and provides a menu-driven interface.
 * <p>
 * This system allows administrators to manage students and units, and enables students to enroll or remove themselves
 * from units. It includes features for adding/removing students and units, listing all students and units, and handling
 * enrollment with confirmation prompts.
 * </p>
 *
 * <p>
 * @author Antoni Erdeg - antoni.erdeg@monash.edu<br>
 * @version: 1.0
 * </p>
 */
public class StudentEnrollmentSystem {
    // All final variables are constants and should be upper case.
    // Refer to PartTimeStudent for non-constant variable example i.e. courseAdvisor
    private final Map<String, Student> STUDENTS = new HashMap<>();
    private final Map<String, Unit> UNITS = new HashMap<>();
    private final Scanner SCANNER = new Scanner(System.in);
    private Administrator admin;

    /**
     * Initializes the Student Enrollment System with an administrator.
     *
     * @param admin The administrator managing the system.
     */
    public StudentEnrollmentSystem(Administrator admin) {
        this.admin = admin;
    }

    /**
     * Displays a standard header before each menu.
     *
     * @param pageName The name of the current page to display in the header.
     */
    public void displayHeader(String pageName) {
        String header = "\n---------------------------------\n" +
                "|         FIT5136 Demo           |\n" +
                "|    Student Enrolment System    |\n" +
                "---------------------------------\n" +
                " -- Current Page: " + pageName + " --";

        System.out.println(header);
    }

    /**
     * Retrieves the map of Students in the system.
     *
     * @return A map of student IDs to Student objects.
     */
    public Map<String, Student> getStudents() {
        return STUDENTS;
    }

    /**
     * Adds a student to the system if the student ID does not already exist.
     *
     * @param student The student to be added.
     */
    public void addStudent(Student student) {
        String studentId = student.getId();
        if (STUDENTS.containsKey(studentId)) {
            System.out.println("Error: Student ID '" + studentId + "' already exists. Cannot add new student with this ID.");
        } else {
            STUDENTS.put(studentId, student);
            System.out.println("Student '" + studentId + "' added successfully.");
        }
    }


    /**
     * Removes a student from the system by their ID if they exist.
     *
     * @param studentId The ID of the student to be removed.
     */
    public void removeStudent(String studentId) {
        if (studentExists(studentId)) {
            STUDENTS.remove(studentId);
            System.out.println("Student with ID '" + studentId + "' has been removed.");
        } else {
            System.out.println("Unable to remove student with ID '" + studentId + "' as they do not exist.");
        }
    }

    /**
     * Checks if a student exists in the system by their ID.
     *
     * @param studentId The ID of the student.
     * @return True if the student exists, false otherwise.
     */
    public boolean studentExists(String studentId) {
        return STUDENTS.containsKey(studentId);
    }

    /**
     * Adds a unit to the system if the unit name does not already exist.
     *
     * @param unit The unit to be added.
     */
    public void addUnit(Unit unit) {
        String unitName = unit.getCode();
        if (UNITS.containsKey(unitName)) {
            System.out.println("Error: Unit name '" + unitName + "' already exists. Cannot add new unit with this name.");
        } else {
            UNITS.put(unitName, unit);
            System.out.println("Unit '" + unitName + "' added successfully.");
        }
    }


    /**
     * Checks if a unit is available in the system by its name.
     *
     * @param unit The unit to check.
     * @return True if the unit is available, false otherwise.
     */
    public boolean isUnitAvailable(Unit unit) {
        return UNITS.containsKey(unit.getCode());
    }

    /**
     * Adds a unit to a student's list of units with confirmation.
     *
     * @param studentId The ID of the student.
     * @param unit The unit to be added.
     */
    public void addUnitToStudent(String studentId, Unit unit) {
        if (studentExists(studentId)) { // Check if student exists
            Student student = STUDENTS.get(studentId);

            // Check if student is already enrolled in the unit
            if (student.getUnits().contains(unit)) {
                System.out.println("Student is already enrolled in the unit '" + unit.getCode() + "'.");
                return;
            }

            // Check if student is already enrolled in 4 units
            if (student.getUnits().size() >= 4) {
                System.out.println("Student is already enrolled in the maximum number of units (4 units).");
                return;
            }

            System.out.print("Confirm enrollment in unit '" + unit.getCode() + "' (Y/N): ");
            String confirmation = SCANNER.nextLine().trim().toUpperCase();

            if (confirmation.equals("Y")) { // Confirm action
                student.addUnit(unit, this);
                System.out.println("\nEnrolled in unit '" + unit.getCode() + "' successfully.");
            } else {
                System.out.println("\nEnrollment in unit '" + unit.getCode() + "' cancelled.");
            }
        } else {
            System.out.println("\nStudent not found.");
        }
    }

    /**
     * Removes a unit from a student's list of units with confirmation.
     *
     * @param studentId The ID of the student.
     * @param unitName The name of the unit to be removed.
     */
    public void removeUnitFromStudent(String studentId, String unitName) {
        if (studentExists(studentId)) { // Check if student exists
            Student student = STUDENTS.get(studentId);
            Set<Unit> enrolledUnits = student.getUnits();

            // Ensure the set contains the unit by its name
            Unit unitToRemove = null;
            for (Unit unit : enrolledUnits) {
                if (unit.getCode().equals(unitName)) {
                    unitToRemove = unit;
                    break;
                }
            }

            if (unitToRemove != null) { // Unit found
                System.out.print("Confirm removal of unit '" + unitName + "' (Y/N): ");
                String confirmation = SCANNER.nextLine().trim().toUpperCase();

                if (confirmation.equals("Y")) { // Confirm action
                    student.removeUnit(unitName);
                } else {
                    System.out.println("Removal of unit '" + unitName + "' cancelled.");
                }
            } else {
                System.out.println("Student isn't currently enrolled in the unit '" + unitName + "'.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Lists all units currently enrolled by a student.
     *
     * @param studentId The ID of the student.
     */
    public void listStudentUnits(String studentId) {
        if (studentExists(studentId)) { // Check if student exists
            Student student = STUDENTS.get(studentId);
            student.listUnits();
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Retrieves all units in the system.
     *
     * @return A collection of units.
     */
    public Collection<Unit> getUnits() {
        return UNITS.values();
    }

    /**
     * Displays the main menu and handles user interactions.
     */
    public void displayMainMenu() {
        while (true) {
            displayHeader("Main Menu");
            System.out.println("\nSelect User Type:");
            System.out.println("1. Student");
            System.out.println("2. Administrator");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (choice) {
                case 1:
                    studentMenu(); // Show student menu
                    break;
                case 2:
                    adminMenu(); // Show administrator menu
                    break;
                case 0:
                    System.out.println("Exiting system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice."); // Handle invalid choice
            }
        }
    }

    /**
     * Provides the menu for student interactions.
     */
    private void studentMenu() {
        System.out.print("Enter Student ID: ");
        String studentId = SCANNER.nextLine();

        if (!studentExists(studentId)) { // Check if student exists
            System.out.println("Student does not exist in the enrollment system.");
            return;
        }

        while (true) {
            displayHeader("Student Menu");
            System.out.println("\nStudent Menu:");
            System.out.println("1. Add Unit");
            System.out.println("2. Remove Unit");
            System.out.println("3. List Current Units");
            System.out.println("4. Back to Main Menu");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int studentChoice = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (studentChoice) {
                case 1:
                    // Show available units and handle unit enrollment
                    this.displayHeader("Student Menu: Add Unit");
                    System.out.println("\nAvailable Units:"+"\n-----------------");
                    getUnits().forEach(unit -> System.out.println(unit.toString()));
                    System.out.print("\nEnter Unit Code to Enroll: ");
                    String unitName = SCANNER.nextLine();
                    Unit unitToAdd = UNITS.get(unitName);
                    if (unitToAdd != null) { // Check if unit exists
                        addUnitToStudent(studentId, unitToAdd);
                    } else {
                        System.out.println("Unit '" + unitName + "' not found.");
                    }
                    break;
                case 2:
                    // Show enrolled units and handle unit removal
                    this.displayHeader("Student Menu: Remove Unit");
                    System.out.println("\nUnits Enrolled:"+"\n-----------------");
                    listStudentUnits(studentId);

                    Student student = STUDENTS.get(studentId);
                    if (student == null || student.getUnits().isEmpty()) { // Check if student has units
                        System.out.println("Not enrolled in any Units.");
                        break; // Return to student menu
                    }

                    System.out.print("\nEnter Unit Code to Remove: ");
                    String unitNameToRemove = SCANNER.nextLine();

                    removeUnitFromStudent(studentId, unitNameToRemove);

                    break;
                case 3:
                    // List current units for the student
                    System.out.println("\nCurrent Units:"+"\n-----------------");
                    listStudentUnits(studentId);

                    // Prompt to return to the menu
                    System.out.println("\nPress any key or 'B' to return to the Menu.");
                    SCANNER.nextLine(); // Wait for user input
                    break;
                case 4:
                    return; // Return to main menu
                case 0:
                    System.out.println("Exiting system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice."); // Handle invalid choice
            }
        }
    }

    /**
     * Provides the menu for administrator interactions.
     */
    private void adminMenu() {
        while (true) {
            displayHeader("Administrator Menu");
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Add Unit");
            System.out.println("4. Remove Unit");
            System.out.println("5. List All Students");
            System.out.println("6. List All Units");
            System.out.println("7. Back to Main Menu");
            System.out.println("8. Add Unit to Student");  // New option
            System.out.println("9. Remove Unit from Student");  // New option
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int adminChoice = SCANNER.nextInt();
            SCANNER.nextLine();  // Consume newline

            switch (adminChoice) {
                case 1:
                    // Add a new student
                    displayHeader("Admin Menu: Add New Student");
                    System.out.print("Enter New Student Name: ");
                    String newStudentName = SCANNER.nextLine();
                    System.out.print("Enter New Student ID: ");
                    String newStudentId = SCANNER.nextLine();
                    System.out.print("Enter New Student Address: ");
                    String newStudentAddress = SCANNER.nextLine();
                    System.out.print("Enter New Student Phone: ");
                    String newStudentPhone = SCANNER.nextLine();
                    System.out.print("Enter New Student Email: ");
                    String newStudentEmail = SCANNER.nextLine();
                    System.out.print("Enter Type of Student - [F] Full-time or [P] Part-time: ");
                    String newStudentType = SCANNER.nextLine().trim().toUpperCase();

                    Student newStudent;
                    if (newStudentType.equals("F")) {
                        newStudent = new FullTimeStudent(newStudentId, newStudentName, newStudentAddress, newStudentPhone, newStudentEmail);
                    } else if (newStudentType.equals("P")) {
                        newStudent = new PartTimeStudent(newStudentId, newStudentName, newStudentAddress, newStudentPhone, newStudentEmail);
                    } else {
                        System.out.println("Invalid Option. Returning to Main Menu.");
                        break;
                    }
                    addStudent(newStudent);
                    break;

                case 2:
                    // Remove a student
                    displayHeader("Admin Menu: Remove Student");
                    System.out.println("\nCurrent Students:" + "\n-----------------");
                    List<Student> studentList = new ArrayList<>(STUDENTS.values());
                    if (studentList.isEmpty()) {
                        System.out.println("No students currently enrolled.");
                    } else {
                        for (Student student : studentList) {
                            System.out.println(student);
                        }
                        System.out.print("\nEnter Student ID to Remove: ");
                        String studentIdToRemove = SCANNER.nextLine();
                        System.out.print("Confirm removal of student '" + studentIdToRemove + "' (Y/N): ");
                        String confirmation = SCANNER.nextLine().trim().toUpperCase();
                        if (confirmation.equals("Y")) {
                            removeStudent(studentIdToRemove);
                        } else {
                            System.out.println("Operation cancelled.");
                        }
                    }
                    break;

                case 3:
                    // Add a new unit
                    displayHeader("Admin Menu: Add New Unit");
                    System.out.print("Enter New Unit Code: ");
                    String newUnitName = SCANNER.nextLine();
                    System.out.print("Enter New Unit Title: ");
                    String newUnitTitle = SCANNER.nextLine();
                    System.out.print("Enter Credit Points: ");
                    int newUnitPoints;
                    try {
                        newUnitPoints = Integer.parseInt(SCANNER.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for credit points. Returning to Main Menu.");
                        break;
                    }

                    Unit newUnit = new Unit(newUnitName, newUnitTitle, newUnitPoints);
                    System.out.print("Confirm creation of unit '" + newUnitName + "' to the system (Y/N): ");
                    String addUnitConfirmation = SCANNER.nextLine().trim().toUpperCase();
                    if (addUnitConfirmation.equals("Y")) {
                        addUnit(newUnit);
                    } else {
                        System.out.println("Operation cancelled.");
                    }
                    break;

                case 4:
                    // Remove a unit
                    displayHeader("Admin Menu: Remove Unit");
                    System.out.println("\nCurrent Units:" + "\n-----------------");
                    List<Unit> unitList = new ArrayList<>(UNITS.values());
                    if (unitList.isEmpty()) {
                        System.out.println("No units currently available.");
                    } else {
                        for (Unit unit : unitList) {
                            System.out.println(unit);
                        }
                    }
                    System.out.print("\nEnter Unit Code to Remove: ");
                    String unitNameToRemove = SCANNER.nextLine().trim();
                    if (UNITS.containsKey(unitNameToRemove)) {
                        System.out.print("Confirm removal of unit '" + unitNameToRemove + "' (Y/N): ");
                        String removeUnitConfirmation = SCANNER.nextLine().trim().toUpperCase();
                        if (removeUnitConfirmation.equals("Y")) {
                            UNITS.remove(unitNameToRemove);
                            System.out.println("Unit '" + unitNameToRemove + "' removed successfully.");
                        } else {
                            System.out.println("Operation cancelled.");
                        }
                    } else {
                        System.out.println("Error: Unit '" + unitNameToRemove + "' does not exist. Cannot remove a non-existent unit.");
                    }
                    break;

                case 5:
                    // List all students
                    displayHeader("Admin Menu: List All Students");
                    System.out.println("\nCurrent Students:" + "\n-----------------");
                    studentList = new ArrayList<>(STUDENTS.values());
                    if (studentList.isEmpty()) {
                        System.out.println("No students currently enrolled.");
                    } else {
                        for (Student student : studentList) {
                            System.out.println(student);
                        }
                    }
                    System.out.println("\nPress any key or 'B' to return to the Menu.");
                    SCANNER.nextLine();  // Wait for user input
                    break;

                case 6:
                    // List all units
                    displayHeader("Admin Menu: List All Units");
                    System.out.println("\nCurrent Units:" + "\n-----------------");
                    unitList = new ArrayList<>(UNITS.values());
                    if (unitList.isEmpty()) {
                        System.out.println("No units currently available.");
                    } else {
                        for (Unit unit : unitList) {
                            System.out.println(unit);
                        }
                    }
                    System.out.println("\nPress any key or 'B' to return to the Menu.");
                    SCANNER.nextLine();  // Wait for user input
                    break;

                case 7:
                    return;  // Return to main menu

                case 8:
                    // Add Unit to Student
                    displayHeader("Admin Menu: Add Unit to Student");
                    System.out.println("\nCurrent Students:" + "\n-----------------");
                    studentList = new ArrayList<>(STUDENTS.values());
                    if (studentList.isEmpty()) {
                        System.out.println("No students currently enrolled.");
                        return;
                    } else {
                        for (Student student : studentList) {
                            System.out.println(student);
                        }
                    }
                    System.out.print("\nStudent ID: ");
                    String studentId = SCANNER.nextLine().trim();
                    if (studentExists(studentId)) {
                        System.out.println("\nAvailable Units:" + "\n-----------------");
                        for (Unit unit : UNITS.values()) {
                            System.out.println(unit);
                        }
                        System.out.print("\nEnter Unit Code to Enroll: ");
                        String unitName = SCANNER.nextLine().trim();
                        Unit unitToAdd = UNITS.get(unitName);
                        if (unitToAdd != null) {
                            addUnitToStudent(studentId, unitToAdd);
                        } else {
                            System.out.println("Unit '" + unitName + "' not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 9:
                    // Remove Unit from Student
                    displayHeader("Admin Menu: Remove Unit from Student");
                    System.out.println("\nCurrent Students:" + "\n-----------------");
                    studentList = new ArrayList<>(STUDENTS.values());
                    if (studentList.isEmpty()) {
                        System.out.println("No students currently enrolled.");
                        return;
                    } else {
                        for (Student student : studentList) {
                            System.out.println(student);
                        }
                    }
                    System.out.print("\nEnter Student ID: ");
                    studentId = SCANNER.nextLine().trim();
                    if (studentExists(studentId)) {
                        System.out.println("\nStudent's Current Units:"+"\n-----------------");
                        listStudentUnits(studentId);
                        if( !STUDENTS.get(studentId).getUnits().isEmpty() ){
                            System.out.print("\nEnter Unit Code to Remove: ");
                            String unitNameToRemoveFromStudent = SCANNER.nextLine().trim();
                            removeUnitFromStudent(studentId, unitNameToRemoveFromStudent);
                        }
                        else
                        {
                            System.out.println("\nThis student is not enrolled in any units.");
                            return;
                        }

                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");  // Handle invalid choice
            }
        }
    }


    /**
     * Entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Administrator admin = new Administrator("John");
        StudentEnrollmentSystem system = new StudentEnrollmentSystem(admin);
        system.addStudent(new FullTimeStudent("123123", "John Lee", "123 Fake Street", "1111111111", "JohnLee@fakestreet.com"));
        system.addUnit(new Unit("FIT5136", "Software Engineering", 6));
        system.displayMainMenu();
    }
}
