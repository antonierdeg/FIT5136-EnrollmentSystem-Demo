from Student import Student

"""
PartTimeStudent - Represents a part-time student with additional functionality for a course advisor.

This class extends the {Student} class to include additional details specific to part-time students,
such as a course advisor. It provides methods to get and set the course advisor.

Author: Antoni Erdeg - antoni.erdeg@monash.edu
Version: 1.0
"""

class PartTimeStudent(Student):
    def __init__(self, student_id, name, address, phone, email):
        """
        Initializes a PartTimeStudent with additional attributes specific to part-time students.

        Args:
            student_id (str): The ID of the student.
            name (str): The name of the student.
            address (str): The address of the student.
            phone (str): The phone number of the student.
            email (str): The email address of the student.
        """
        super().__init__(student_id, name, address, phone, email)
        self.course_advisor = None  # Initializes the course advisor as None

    def set_course_advisor(self, advisor):
        """
        Sets the course advisor for the part-time student.

        Args:
            advisor (str): The name or ID of the course advisor.
        """
        self.course_advisor = advisor

    def get_course_advisor(self):
        """
        Retrieves the current course advisor for the part-time student.

        Returns:
            str: The name or ID of the course advisor, or None if not set.
        """
        return self.course_advisor