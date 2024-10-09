from Administrator import Administrator
from FullTimeStudent import FullTimeStudent
from PartTimeStudent import PartTimeStudent
from Unit import Unit

import sys

"""
Student Enrollment System - Manages students and units within the Student Enrollment System and provides a menu-driven interface.

This system allows administrators to manage students and units, and enables students to enroll or remove themselves
from units. It includes features for adding/removing students and units, listing all students and units, and handling
enrollment with confirmation prompts.

Author: Antoni Erdeg - antoni.erdeg@monash.edu
Version: 1.0
"""

class StudentEnrollmentSystem:
    def __init__(self, admin):
        """
        Initializes the Student Enrollment System with an administrator.

        :param admin: The administrator managing the system.
        """
        self.students = {}  # Map of student IDs to Student objects
        self.units = {}  # Map of unit names to Unit objects
        self.admin = admin

    def display_header(self, page_name):
        """
        Displays a standard header before each menu.

        :param page_name: The name of the current page to display in the header.
        """
        header = (
            "\n---------------------------------\n"
            "|         FIT5136 Demo           |\n"
            "|    Student Enrollment System   |\n"
            "---------------------------------\n"
            f" -- Current Page: {page_name} --"
        )
        print(header)

    def get_students(self):
        """
        Retrieves the map of students in the system.

        :return: A dictionary of student IDs to Student objects.
        """
        return self.students

    def get_student(self, studentId):
        """
        Retrieves a Student object from the system based on the provided student ID.

        :param studentId: The ID of the student to retrieve.
        :return: The Student object corresponding to the provided student ID, or None if not found.
        """
        return self.students.get(studentId)

    def add_student(self, student):
        """
        Adds a student to the system if the student ID does not already exist.

        :param student: The student to be added.
        """
        student_id = student.get_id()
        if student_id in self.students:
            print(f"Error: Student ID '{student_id}' already exists. Cannot add new student with this ID.")
            return

        self.students[student_id] = student
        print(f"Student '{student_id}' added successfully.")


    def remove_student(self, student_id):
        """
        Removes a student from the system by their ID if they exist.

        :param student_id: The ID of the student to be removed.
        """
        if self.student_exists(student_id):
            self.students.pop(student_id, None)
            print(f"Student with ID '{student_id}' has been removed.")
        else:
            print(f"Unable to remove student with ID '{student_id}' as they do not exist.")

    def student_exists(self, student_id):
        """
        Checks if a student exists in the system by their ID.

        :param student_id: The ID of the student.
        :return: True if the student exists, False otherwise.
        """
        return student_id in self.students

    def add_unit(self, unit):
        """
        Adds a unit to the system if the unit name does not already exist.

        :param unit: The unit to be added.
        """
        unit_name = unit.get_code()
        if unit_name in self.units:
            print(f"Error: Unit name '{unit_name}' already exists. Cannot add new unit with this name.")
        else:
            self.units[unit_name] = unit
            print(f"Unit '{unit_name}' added successfully.")


    def is_unit_available(self, unit):
        """
        Checks if a unit is available in the system by its name.

        :param unit: The unit to check.
        :return: True if the unit is available, False otherwise.
        """
        return unit.get_code() in self.units

    def add_unit_to_student(self, student_id, unit):
        """
        Adds a unit to a student's list of units with confirmation.

        :param student_id: The ID of the student.
        :param unit: The unit to be added.
        """
        if self.student_exists(student_id):  # Check if student exists
            student = self.students[student_id]

            # Check if student is already enrolled in the unit
            if unit in student.get_units():
                print(f"Student is already enrolled in the unit '{unit.get_code()}'.")
                return

            # Check if student is already enrolled in 4 units
            if len(student.get_units()) >= 4:
                print("Student is already enrolled in the maximum number of units (4 units).")
                return

            confirmation = input(f"Confirm enrollment in unit '{unit.get_code()}' (Y/N): ").strip().upper()
            if confirmation == "Y":  # Confirm action
                student.add_unit(unit, self)
                print(f"\nEnrolled in unit '{unit.get_code()}' successfully.")
            else:
                print(f"\nEnrollment in unit '{unit.get_code()}' cancelled.")
        else:
            print("Student not found.")

    def remove_unit_from_student(self, student_id, unit_name):
        """
        Removes a unit from a student's list of units with confirmation.

        :param student_id: The ID of the student.
        :param unit_name: The name of the unit to be removed.
        """
        if self.student_exists(student_id):  # Check if student exists
            student = self.students[student_id]
            unit_to_remove = None

            # Ensure the set contains the unit by its name
            for unit in student.get_units():
                if unit.get_code() == unit_name:
                    unit_to_remove = unit
                    break

            if unit_to_remove is not None:  # Unit found
                confirmation = input(f"Confirm removal of unit '{unit_name}' (Y/N): ").strip().upper()
                if confirmation == "Y":  # Confirm action
                    student.remove_unit(unit_name)
                else:
                    print(f"Removal of unit '{unit_name}' cancelled.")
            else:
                print(f"Student isn't currently enrolled in the unit '{unit_name}'.")
        else:
            print("Student not found.")

    def list_student_units(self, student_id):
        """
        Lists all units currently enrolled by a student.

        :param student_id: The ID of the student.
        """
        if self.student_exists(student_id):  # Check if student exists
            student = self.students[student_id]
            student.list_units()
        else:
            print("Student not found.")

    def get_units(self):
        """
        Retrieves all units in the system.

        :return: A collection of units.
        """
        return self.units.values()

    def display_main_menu(self):
        """
        Displays the main menu and handles user interactions.
        """
        while True:
            self.display_header("Main Menu")
            print("\nSelect User Type:")
            print("1. Student")
            print("2. Administrator")
            print("0. Exit")
            choice = input("Enter choice: ").strip()

            if choice == '1':
                self.student_menu()  # Show student menu
            elif choice == '2':
                self.admin_menu()  # Show administrator menu
            elif choice == '0':
                print("Exiting system.")
                sys.exit()
            else:
                print("Invalid choice.")  # Handle invalid choice

    def student_menu(self):
        """
        Provides the menu for student interactions.
        """
        student_id = input("Enter Student ID: ").strip()

        if not self.student_exists(student_id):  # Check if student exists
            print("Student does not exist in the enrollment system.")
            return

        while True:
            self.display_header("Student Menu")
            print("\nStudent Menu:")
            print("1. Add Unit")
            print("2. Remove Unit")
            print("3. List Current Units")
            print("4. Back to Main Menu")
            print("0. Exit")
            student_choice = input("Enter choice: ").strip()

            if student_choice == '1':
                self.display_header("Student Menu: Add Unit")
                print("\nAvailable Units:" + "\n-----------------")

                # Display the list of available units
                for unit in self.get_units():
                    print(unit)

                # Prompt for the unit name to enroll
                unit_name = input("\nEnter Unit Code to Enroll: ").strip()
                unit_to_add = self.units.get(unit_name)  # Retrieve the unit object

                # Check if the unit exists
                if unit_to_add:
                    self.add_unit_to_student(student_id, unit_to_add)  # Enroll the student in the unit
                else:
                    print(f"Unit '{unit_name}' not found.")  # Print error message if unit is not found

            elif student_choice == '2':
                self.display_header("Student Menu: Remove Unit")
                print("\nUnits Enrolled:" + "\n-----------------")

                # Display the list of enrolled units for the student
                self.list_student_units(student_id)

                student = self.get_student(student_id)  # Retrieve the student object

                # Check if the student exists and has enrolled units
                if student is None or not student.units:
                    print("Not enrolled in any Units.")
                    return  # Return to the student menu if no units are enrolled

                # Prompt for the unit name to remove
                unit_name_to_remove = input("\nEnter Unit Code to Remove: ").strip()

                # Call the method to remove the unit from the student's enrollment
                self.remove_unit_from_student(student_id, unit_name_to_remove)
            elif student_choice == '3':
                print("\nCurrent Units:"+"\n-----------------")
                self.list_student_units(student_id)
                input("\nPress any key or 'B' to return to the Menu.")
            elif student_choice == '4':
                return  # Return to main menu
            elif student_choice == '0':
                print("Exiting system.")
                sys.exit()
            else:
                print("Invalid choice.")  # Handle invalid choice

    def admin_menu(self):
        """
        Provides the menu for administrator interactions.
        """
        while True:
            self.display_header("Administrator Menu")
            print("\nAdministrator Menu:")
            print("1. Add Student")
            print("2. Remove Student")
            print("3. Add Unit")
            print("4. Remove Unit")
            print("5. List All Students")
            print("6. List All Units")
            print("7. Back to Main Menu")
            print("8. Add Unit to Student")  # New option
            print("9. Remove Unit from Student")  # New option
            print("0. Exit")
            admin_choice = input("Enter choice: ").strip()

            if admin_choice == '1':
                self.display_header("Admin Menu: Add New Student")
                new_student_name = input("Enter New Student Name: ").strip()
                new_student_id = input("Enter New Student ID: ").strip()
                new_student_address = input("Enter New Student Address: ").strip()
                new_student_phone = input("Enter New Student Phone: ").strip()
                new_student_email = input("Enter New Student Email: ").strip()
                new_student_type = input("Enter Type of Student - [F] Full-time or [P] Part-time: ").strip().upper()

                if new_student_type == 'F':
                    new_student = FullTimeStudent(new_student_id, new_student_name, new_student_address, new_student_phone, new_student_email)
                elif new_student_type == 'P':
                    new_student = PartTimeStudent(new_student_id, new_student_name, new_student_address, new_student_phone, new_student_email)
                else:
                    print("Invalid Option. Returning to Main Menu.")
                    continue

                self.add_student(new_student)
            elif admin_choice == '2':
                self.display_header("Admin Menu: Remove Student")
                print("\nCurrent Students:"+"\n-----------------")
                students = list(self.get_students())
                if not students:
                    print("No students currently enrolled.")
                else:
                    for student in self.get_students().values():
                        print(student)
                    student_id_to_remove = input("\nEnter Student ID to Remove: ").strip()
                    confirmation = input(f"Confirm removal of student '{student_id_to_remove}' (Y/N): ").strip().upper()
                    if confirmation == 'Y':
                        self.remove_student(student_id_to_remove)
                    else:
                        print("Operation cancelled.")

            elif admin_choice == '3':
                self.display_header("Admin Menu: Add New Unit")
                new_unit_name = input("Enter New Unit Code: ").strip()
                new_unit_title = input("Enter New Unit title: ").strip()
                new_unit_credit_points = input("Enter Credit Points: ").strip()
                new_unit = Unit(new_unit_name, new_unit_title, new_unit_credit_points)

                confirmation = input(f"Confirm creation of unit '{new_unit_name}' (Y/N): ").strip().upper()
                if confirmation == 'Y':
                    self.add_unit(new_unit)
                else:
                    print("Operation cancelled.")

            elif admin_choice == '4':
                self.display_header("Admin Menu: Remove Unit")
                print("\nCurrent Units:"+"\n-----------------")
                units = list(self.get_units())
                if not units:
                    print("No units currently available.")
                else:
                    for unit in self.get_units():
                        print(unit)
                unit_name_to_remove = input("\nEnter Unit Code to Remove: ").strip()
                if unit_name_to_remove in self.units:
                    confirmation = input(f"Confirm removal of unit '{unit_name_to_remove}' (Y/N): ").strip().upper()
                    if confirmation == 'Y':
                        self.units.pop(unit_name_to_remove, None)
                        print(f"Unit '{unit_name_to_remove}' removed successfully.")
                    else:
                        print("Operation cancelled.")
                else:
                    print(f"Error: Unit '{unit_name_to_remove}' does not exist. Cannot remove a non-existent unit.")
            elif admin_choice == '5':
                self.display_header("Admin Menu: List All Students")
                print("\nCurrent Students:"+"\n-----------------")
                students = list(self.get_students())
                if not students:
                    print("No students currently enrolled.")
                else:
                    for student in self.get_students().values():
                        print(student)

                input("\nPress any key or 'B' to return to the Menu.")
            elif admin_choice == '6':
                self.display_header("Admin Menu: List All Units")
                print("\nCurrent Units:"+"\n-----------------")
                units = list(self.get_units())
                if not units:
                    print("No units currently available.")
                else:
                    for unit in self.get_units():
                        print(unit)
                input("\nPress any key or 'B' to return to the Menu.")
            elif admin_choice == '7':
                return  # Return to main menu
            elif admin_choice == '8':  # Add Unit to Student
                self.display_header("Admin Menu: Add Unit to Student")
                print("\nCurrent Students:"+"\n-----------------")
                students = list(self.get_students())
                if not students:
                    print("No students currently enrolled.")
                    return
                else:
                    for student in self.get_students().values():
                        print(student)
                student_id = input("\nEnter Student ID: ").strip()
                if self.student_exists(student_id):
                    print("\nAvailable Units:"+"\n-----------------")
                    for unit in self.get_units():
                        print(unit)
                    unit_name = input("\nEnter Unit Code to Enroll: ").strip()
                    unit_to_add = self.units.get(unit_name)
                    if unit_to_add:
                        self.add_unit_to_student(student_id, unit_to_add)
                    else:
                        print(f"Unit '{unit_name}' not found.")
                else:
                    print("Student not found.")
            elif admin_choice == '9':  # Remove Unit from Student
                self.display_header("Admin Menu: Remove Unit from Student")
                print("\nCurrent Students:"+"\n-----------------")
                students = list(self.get_students())
                if not students:
                    print("No students currently enrolled.")
                    return
                else:
                    for student in self.get_students().values():
                        print(student)
                student_id = input("\nEnter Student ID: ").strip()

                if self.student_exists(student_id):
                    student = self.get_students().get(student_id)  # Retrieve the student object
                    print("\nStudent's Current Units:"+"\n-----------------")
                    self.list_student_units(student_id)
                    if student.get_units():  # Check if the student has any units
                        unit_name_to_remove = input("\nEnter Unit Code to Remove: ").strip()
                        self.remove_unit_from_student(student_id, unit_name_to_remove)
                    else:
                        print("\nThis student is not enrolled in any units.")
                        return
                else:
                    print("Student not found.")
            elif admin_choice == '0':
                print("Exiting system.")
                sys.exit()
            else:
                print("Invalid choice.")  # Handle invalid choice



def main():
    """
    Main function to initialize the system, create default entries, and start the main menu.
    """
    ses = StudentEnrollmentSystem(Administrator("Admin"))

    # Create default unit and student for the system
    ses.add_unit(Unit("FIT5136","Software Engineering", 6))
    ses.add_student(FullTimeStudent("123123", "John Lee", "123 Fake Street", "1111111111", "JohnLee@fakestreet.com"))

    ses.display_main_menu()

if __name__ == "__main__":
    main()
