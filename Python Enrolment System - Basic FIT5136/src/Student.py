"""
Student - Represents a student with personal details and a list of enrolled units.

This  class encapsulates the essential details of a student, including personal information
and the units they are enrolled in. It provides methods to add and remove units, list all enrolled units,
and access personal details.

Author: Antoni Erdeg - antoni.erdeg@monash.edu
Version: 1.0
"""

class Student:
    def __init__(self, student_id, name, address, phone, email):
        """
        Constructs a Student with the specified details.

        :param student_id: The unique identifier of the student.
        :param name: The name of the student.
        :param address: The address of the student.
        :param phone: The phone number of the student.
        :param email: The email address of the student.
        """
        self.student_id = student_id
        self.name = name
        self.address = address
        self.phone = phone
        self.email = email
        self.units = set()

    def get_id(self):
        """
        Returns the unique identifier of the student.

        :return: The student ID.
        """
        return self.student_id

    def add_unit(self, unit, ses):
        """
        Adds a unit to the student's list of units if not already enrolled.

        :param unit: The unit to be added.
        :param ses: The student enrollment system instance.
        """
        if ses.is_unit_available(unit) and len(self.units) < 4:
            if unit in self.units:
                print(f"\nUnit '{unit}' - Student is already enrolled.")
            else:
                self.units.add(unit)
                print(f"\nStudent '"+ self.__str__() +"' is now enrolled in \n'"+ unit.__str__() +"'.")
        elif not ses.is_unit_available(unit):
            print("\nUnit is not available.")
        else:
            print("\nCannot enroll in more than 4 units.")

    def remove_unit(self, unit_name):
        """
        Removes a unit from the student's list of units by name.

        :param unit_name: The name of the unit to be removed.
        """
        unit_to_remove = next((unit for unit in self.units if unit.get_code() == unit_name), None)
        if unit_to_remove:
            self.units.remove(unit_to_remove)
            print(f"\nUnit '{unit_name}' removed successfully.")
        else:
            print(f"\nStudent isn't currently enrolled in the unit '{unit_name}'.")

    def list_units(self):
        """
        Lists all units the student is currently enrolled in.
        """
        if not self.units:
            print("No units enrolled.")
        else:
            for unit in self.units:
                print(unit)

    def get_units(self):
        """
        Returns the set of units the student is enrolled in.

        :return: The set of enrolled units.
        """
        return self.units

    def get_name(self):
        """
        Returns the name of the student.

        :return: The name of the student.
        """
        return self.name

    def __str__(self):
        """
        Returns a string representation of the student.

        :return: A string representation of the stiudent.
        """
        return f"{self.student_id} - {self.name}"
