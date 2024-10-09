/**
 * PartTimeStudent - Represents a part-time student with additional functionality for a course advisor.
 * <p>
 * This class extends the {@link Student} class to include additional details specific to part-time students,
 * such as a course advisor. It provides methods to get and set the course advisor.
 * </p>
 *
 * <p>
 * @author Antoni Erdeg - antoni.erdeg@monash.edu
 * @version: 1.0
 * </p>
 */
public class PartTimeStudent extends Student {
    //This is a regular variables
    private String courseAdvisor;

    /**
     * Constructs a PartTimeStudent with the specified details.
     *
     * @param id The unique identifier of the student.
     * @param name The name of the student.
     * @param address The address of the student.
     * @param phone The phone number of the student.
     * @param email The email address of the student.
     */
    public PartTimeStudent(String id, String name, String address, String phone, String email) {
        super(id, name, address, phone, email);
    }

    /**
     * Returns the course advisor of the student.
     *
     * @return The course advisor.
     */
    public String getCourseAdvisor() {
        return courseAdvisor;
    }

    /**
     * Sets the course advisor for the student.
     *
     * @param courseAdvisor The course advisor to be set.
     */
    public void setCourseAdvisor(String courseAdvisor) {
        this.courseAdvisor = courseAdvisor;
    }
}
