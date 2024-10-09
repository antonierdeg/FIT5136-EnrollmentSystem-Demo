"""
Administrator - Represents an administrator who can manage students and units within the Student Enrollment System.

This class provides functionalities for administrators to add or remove students, manage unit enrollments for students,
and add new units to the system. It assumes interaction with a {StudentEnrollmentSystem} instance.

Author: Antoni Erdeg - antoni.erdeg@monash.edu
Version: 1.0
"""

class Administrator:
    def __init__(self, name):
        """
        Initializes the Administrator with a name.

        Args:
            name (str): The name of the administrator.
        """
        self.name = name

    def add_student(self, ses, student):
        """
        Adds a student to the Student Enrollment System.

        Args:
            ses (StudentEnrollmentSystem): The student enrollment system instance.
            student (Student): The student object to be added.

        Prints:
            str: Success message after adding the student.
        """
        ses.add_student(student)
        print("Student added successfully.")

    def add_unit_to_student(self, ses, student_id, unit):
        """
        Adds a unit to a student's list of units.

        Args:
            ses (StudentEnrollmentSystem): The student enrollment system instance.
            student_id (str): The ID of the student to whom the unit will be added.
            unit (Unit): The unit to be added to the student.

        This method calls the `add_unit_to_student` method of the `StudentEnrollmentSystem` class.
        """
        ses.add_unit_to_student(student_id, unit)

    def remove_unit_from_student(self, ses, student_id, unit_name):
        """
        Removes a unit from a student's list of units.

        Args:
            ses (StudentEnrollmentSystem): The student enrollment system instance.
            student_id (str): The ID of the student from whom the unit will be removed.
            unit_name (str): The name of the unit to be removed.

        Prints:
            str: Success or error message based on the operation outcome.
        """
        if ses.student_exists(student_id):
            # If the student exists in the system
            student = ses.students[student_id]
            units = student.list_units()
            if units is not None and unit_name in units:
                # If the unit is in the student's list of units
                student.remove_unit(unit_name)
                print(f"Unit '{unit_name}' removed successfully.")
            else:
                # If the unit is not found in the student's list
                print(f"Student isn't currently enrolled within Unit '{unit_name}'.")
        else:
            # If the student does not exist
            print("Student not found.")

    def add_unit(self, ses, unit):
        """
        Adds a unit to the Student Enrollment System.

        Args:
            ses (StudentEnrollmentSystem): The student enrollment system instance.
            unit (Unit): The unit object to be added.

        Prints:
            str: Success message after adding the unit.
        """
        ses.add_unit(unit)
        print("Unit added to the system successfully.")
