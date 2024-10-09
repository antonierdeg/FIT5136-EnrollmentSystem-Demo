/**
 * Unit - Represents a unit with a name and credit points.
 * <p>
 * A unit in the context of the Student Enrollment System typically represents a course or module
 * that students can enroll in. Each unit has a name and a number of credit points associated with it.
 * </p>
 *
 * <p>
 * @author Antoni Erdeg - antoni.erdeg@monash.edu<br>
 * @version: 1.0
 * </p>
 */
public class Unit {
    // All final variables are constants and should be upper case.
    // Refer to PartTimeStudent for non-constant variable example i.e. courseAdvisor
    private final String CODE;
    private final String TITLE;
    private final int CREDIT_POINTS;

    /**
     * Constructs a Unit with the specified name and credit points.
     *
     * @param code The name of the unit.
     * @param creditPoints The credit points of the unit.
     */
    public Unit(String code, String title, int creditPoints) {
        this.CODE = code;
        this.CREDIT_POINTS = creditPoints;
        this.TITLE = title;
    }

    /**
     * Returns the title of the unit.
     *
     * @return The title of the unit.
     */
    public String getTitle() {
        return TITLE;
    }

    /**
     * Returns the name of the unit.
     *
     * @return The name of the unit.
     */
    public String getCode() {
        return CODE;
    }

    /**
     * Returns the credit points of the unit.
     *
     * @return The credit points of the unit.
     */
    public int getCreditPoints() {
        return CREDIT_POINTS;
    }

    @Override
    public String toString() {
        return CODE + " - "+ TITLE + " (" + CREDIT_POINTS + " credit points)";
    }
}
