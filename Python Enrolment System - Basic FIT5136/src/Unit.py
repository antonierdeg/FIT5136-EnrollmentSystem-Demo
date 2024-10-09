"""
Unit - Represents a unit with a name and credit points.

A unit in the context of the Student Enrollment System typically represents a course or module
that students can enroll in. Each unit has a name and a number of credit points associated with it.

Author: Antoni Erdeg - antoni.erdeg@monash.edu
Version: 1.0
"""

class Unit:
    def __init__(self, code, title, credit_points):
        """
        Constructs a Unit with the specified name and credit points.

        :param name: The name of the unit.
        :param credit_points: The credit points of the unit.
        """
        self.code = code
        self.credit_points = credit_points
        self.title = title

    def get_title(self):
        """
        Returns the title of the unit.

        :return: The title of the unit.
        """
        return self.title

    def get_code(self):
        """
        Returns the name of the unit.

        :return: The name of the unit.
        """
        return self.code

    def get_credit_points(self):
        """
        Returns the credit points of the unit.

        :return: The credit points of the unit.
        """
        return self.credit_points

    def __str__(self):
        """
        Returns a string representation of the unit.

        :return: A string representation of the unit.
        """
        return f"{self.code} - {self.title} ({self.credit_points} credit points)"
