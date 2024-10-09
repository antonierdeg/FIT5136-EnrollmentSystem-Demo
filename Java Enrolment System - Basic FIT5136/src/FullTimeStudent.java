/**
 * FullTimeStudent - Represents a full-time student with additional constraints for unit enrollment.
 * <p>
 * This class extends the {@link Student} class to include specific constraints and behavior for full-time students.
 * Full-time students may have different constraints compared to part-time students, especially regarding unit enrollment.
 * </p>
 *
 * <p>
 * @author Antoni Erdeg - antoni.erdeg@monash.edu
 * @version: 1.0
 * </p>
 */
public class FullTimeStudent extends Student {

    /**
     * Constructs a FullTimeStudent with the specified details.
     *
     * @param id The unique identifier of the student.
     * @param name The name of the student.
     * @param address The address of the student.
     * @param phone The phone number of the student.
     * @param email The email address of the student.
     */
    public FullTimeStudent(String id, String name, String address, String phone, String email) {
        super(id, name, address, phone, email);
    }
}
