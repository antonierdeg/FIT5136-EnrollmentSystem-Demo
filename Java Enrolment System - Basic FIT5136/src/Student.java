import java.util.HashSet;
import java.util.Set;

/**
 * Student - Represents a student with personal details and a list of enrolled units.
 * <p>
 * This abstract class encapsulates the essential details of a student, including personal information
 * and the units they are enrolled in. It provides methods to add and remove units, list all enrolled units,
 * and access personal details.
 * </p>
 *
 * <p>
 * @author Antoni Erdeg - antoni.erdeg@monash.edu
 * @version: 1.0
 * </p>
 */
public abstract class Student {
    // All final variables are constants and should be upper case.
    // Refer to PartTimeStudent for non-constant variable example i.e. courseAdvisor
    private final String ID;
    private final String NAME;
    private final String ADDRESS;
    private final String PHONE;
    private final String EMAIL;
    private final Set<Unit> UNITS;

    /**
     * Constructs a Student with the specified details.
     *
     * @param id The unique identifier of the student.
     * @param name The name of the student.
     * @param address The address of the student.
     * @param phone The phone number of the student.
     * @param email The email address of the student.
     */
    public Student(String id, String name, String address, String phone, String email) {
        this.ID = id;
        this.NAME = name;
        this.ADDRESS = address;
        this.PHONE = phone;
        this.EMAIL = email;
        this.UNITS = new HashSet<>();
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return The student ID.
     */
    public String getId() {
        return ID;
    }

    /**
     * Adds a unit to the student's list of units if not already enrolled.
     * <p>
     * Checks if the unit is available and if the student is not already enrolled in the maximum number of units (4).
     * If the student is already enrolled in the unit, a message is displayed.
     * Otherwise, the unit is added to the student's list of units.
     * </p>
     *
     * @param unit The unit to be added.
     * @param ses The student enrollment system instance.
     */
    public void addUnit(Unit unit, StudentEnrollmentSystem ses) {
        if (ses.isUnitAvailable(unit) && getUnits().size() < 4) {
            if (this.getUnits().contains(unit)) {
                System.out.println("\nUnit '" + unit.toString() + "' - Student is already enrolled.");
            }
            else {
                getUnits().add(unit);
                System.out.println("\nStudent '"+ this.toString() + "' is now enrolled in \n'" + unit.toString() + "'.");
            }
        } else if (!ses.isUnitAvailable(unit)) {
            System.out.println("\nUnit is not available.");
        } else {
            System.out.println("\nCannot enroll in more than 4 units.");
        }
    }

    /**
     * Removes a unit from the student's list of units by name.
     * <p>
     * Checks if the unit is in the list of units. If so, it is removed; otherwise, a message indicating
     * that the student is not enrolled in the unit is displayed.
     * </p>
     *
     * @param unitName The name of the unit to be removed.
     */
    public void removeUnit(String unitName) {
        // Check if the unit is in the list and remove it
        boolean removed = UNITS.removeIf(unit -> unit.getCode().equals(unitName));
        if (removed) {
            System.out.println("\nUnit '"+ unitName +"' removed successfully.");
        } else {
            System.out.println("\nStudent isn't currently enrolled in the unit '"+ unitName +"'.");
        }
    }

    /**
     * Lists all units the student is currently enrolled in.
     * <p>
     * Displays a list of all units the student is enrolled in. If the student is not enrolled in any units,
     * a message indicating that no units are enrolled is displayed.
     * </p>
     */
    public void listUnits() {
        if (UNITS.isEmpty()) {
            System.out.println("No units enrolled.");
        } else {
            for (Unit unit : UNITS) {
                System.out.println(unit);
            }
        }
    }

    /**
     * Returns the set of units the student is enrolled in.
     *
     * @return The set of enrolled units.
     */
    public Set<Unit> getUnits() {
        return UNITS;
    }

    /**
     * Returns the name of the student.
     *
     * @return The name of the student.
     */
    public String getName() {
        return NAME;
    }

    @Override
    public String toString() {
        return ID + " - "+ NAME;
    }
}
