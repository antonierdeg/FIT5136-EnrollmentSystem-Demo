/**
 * Administrator - Represents an administrator who can manage students and units within the Student Enrollment System.
 * <p>
 * This class provides functionalities for administrators to add or remove students, manage unit enrollments for students,
 * and add new units to the system. It assumes interaction with a {@link StudentEnrollmentSystem} instance.
 * </p>
 *
 * <p>
 * @author Antoni Erdeg - antoni.erdeg@monash.edu<br>
 * @version: 1.0
 * </p>
 */
public class Administrator {
    // All final variables are constants and should be upper case.
    // Refer to PartTimeStudent for non-constant variable example i.e. courseAdvisor
    private final String NAME;

    /**
     * Constructs an Administrator with the specified name.
     *
     * @param name The name of the administrator.
     */
    public Administrator(String name) {
        this.NAME = name;
    }

    /**
     * Adds a student to the Student Enrollment System.
     *
     * @param ses The Student Enrollment System instance.
     * @param student The student to be added.
     */
    public void addStudent(StudentEnrollmentSystem ses, Student student) {
        ses.addStudent(student);
        System.out.println("Student added successfully.");
    }

    /**
     * Adds a unit to a specific student's list of units.
     *
     * @param ses The Student Enrollment System instance.
     * @param studentId The ID of the student.
     * @param unit The unit to be added.
     */
    public void addUnitToStudent(StudentEnrollmentSystem ses, String studentId, Unit unit) {
        // Check if student exists and then add the unit to the student's list
        if (ses.studentExists(studentId)) {
            ses.addUnitToStudent(studentId, unit);
            System.out.println("Unit added to student successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Removes a unit from a specific student's list of units.
     *
     * @param ses The Student Enrollment System instance.
     * @param studentId The ID of the student.
     * @param unitName The name of the unit to be removed.
     */
    public void removeUnitFromStudent(StudentEnrollmentSystem ses, String studentId, String unitName) {
        // Check if student exists
        if (ses.studentExists(studentId)) {
            Student student = ses.getStudents().get(studentId);
            // List current units and check if the unit is enrolled
            student.listUnits();
            boolean unitFound = student.getUnits().stream()
                    .anyMatch(unit -> unit.getCode().equalsIgnoreCase(unitName));
            if (unitFound) {
                student.removeUnit(unitName);
                System.out.println("Unit removed successfully.");
            } else {
                System.out.println("Student isn't currently enrolled in that unit.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Adds a new unit to the Student Enrollment System.
     *
     * @param ses The Student Enrollment System instance.
     * @param unit The unit to be added.
     */
    public void addUnit(StudentEnrollmentSystem ses, Unit unit) {
        ses.addUnit(unit);
        System.out.println("Unit added to the system successfully.");
    }

    /**
     * Returns the name of the administrator.
     *
     * @return The name of the administrator.
     */
    public String getName() {
        return NAME;
    }
}
