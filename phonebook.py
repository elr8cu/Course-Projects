## Emily Roberts (elr8cu)
## CS1113-300
## November 4, 2016
## Project 2- Edit a phonebook by reading a file and manipulating dictionaries

#Define the function load_phonebook(file)
def load_phonebook(file):

    #Open the file in read mode
    infile = open(file, "r")

    #Create a new empty dictionary named phonebook
    phonebook = {}

    #Create a for loop to loop through each line of the file
    for line in infile:

        #Strip the \n off of the line
        line = line.strip()

        #Split the line into a name and number
        name, number = line.split("#")

        #Add the contact entry to the dictionary phonebook by setting the key (name) equal to its value (number)
        phonebook[name] = number

    #Close the file
    infile.close()

    #Return the phonebook dictionary
    return phonebook

#Define the function display_phonebook(dictionary)
def display_phonebook(dictionary):

    #Create a for loop to loop through each contact entry in the dictionary
    for contact in dictionary:

        #Print the name and phone number
        print("%-20s%15s" %(contact, dictionary[contact]))

#Define the function print_menu()
def print_menu():

    #Print menu of available choices to user (8 lines)
    print()
    print("0. Display All Entries")
    print("1. Add an Entry")
    print("2. Edit an Entry")
    print("3. Remove an Entry")
    print("4. Lookup an Entry")
    print("5. Quit")
    print()


#Define the function main()
def main():

    #Ask user to enter the phonebook file name
    input_file = input("Enter the phonebook file name: ")

    #Call the load_phonebook function of the user's input
    phonebook = load_phonebook(input_file)

    #Print number of entries in the dictionary
    print("The loaded phonebook has", len(phonebook), "entries")

    #Call the print_menu function
    print_menu()

    #Ask user to type in a number 0-5
    choice = int(input("Type in a number (0-5): "))

    #Create a while loop while user's choice is not five
    while choice != 5:

        #If the user's input equals zero
        if choice == 0:

            #Display phonebook by calling display_phonebook function
            display_phonebook(phonebook)

            #Call the print_menu function
            print_menu()

            #Ask user to type in a number 0-5
            choice = int(input("Type in a number (0-5): "))

        #Elif the user's input equals one
        elif choice == 1:

            #Print "ADDING AN ENTRY"
            print("ADDING AN ENTRY")

            #Ask user for new name
            new_name = str(input("Enter the name: "))

            #Ask user for new number
            new_number = input("Enter the number for " + new_name + ": ")

            #If name is not in phonebook
            if new_name not in phonebook:

                #Add the new contact entry to the dictionary phonebook by setting the key (name) equal to its value (number)
                phonebook[new_name] = new_number

            #Call the print_menu function
            print_menu()

            #Ask user to type in a number 0-5
            choice = int(input("Type in a number (0-5): "))

        #Elif the user's input equals two
        elif choice == 2:

            #Ask user for name of contact to edit
            name2edit = input("Enter the name to edit: ")

            #If name exists in the phonebook dictionary
            if name2edit in phonebook:

                #Print current number (print value at that key)
                print("The current number for", name2edit, "is", phonebook[name2edit])

                #Ask user for new number
                edited_number = input("Enter the number for " + name2edit + ": ")

                #Replace current number with new number by setting the key (name) equal to its new value (number)
                phonebook[name2edit] = edited_number

                #Print updated contact
                print("The number for", name2edit, "is now", edited_number)

            #Else (if name does not exist in the dictionary)
            else:

                #Print error message
                print("Sorry! This name doesn't exist in the phonebook!")

            #Call the print_menu function
            print_menu()

            #Ask user to type in a number 0-5
            choice = int(input("Type in a number (0-5): "))

        #Elif the user's input equals three
        elif choice == 3:

            #Ask user for name of contact to remove
            name2remove = input("Enter the name to edit: ")

            #If the name exists in the phonebook dictionary
            if name2remove in phonebook:

                #Remove name from phonebook using phonebook.pop(name)
                phonebook.pop(name2remove)

            #Else (if the name does not exist in the phonebook)
            else:

                #Print error message
                print(name2remove, "doesn't exist!")

            #Call the print_menu function
            print_menu()

            #Ask user to type in a number 0-5
            choice = int(input("Type in a number (0-5): "))

        #Elif the user's input equals four
        elif choice == 4:

            #Ask user for name of contact to look up
            name2find = input("Enter the name you are looking for: ")

            #If the name exists in the dictionary
            if name2find in phonebook:

                #Print entry (key and value)
                print("%-20s%15s" %(name2find, phonebook[name2find]))

            #Else
            else:

                #Print error message
                print(name2find, "doesn't exist!")

            #Call the print_menu function
            print_menu()

            #Ask user to type in a number 0-5
            choice = int(input("Type in a number (0-5): "))

        #Else
        else:

            #Print error message and ask user for a new number
            choice = int(input("Invalid choice. Type in a number (0-5): "))

    #Print number of entries in the dictionary using len(phonebook)
    print("The loaded phonebook has", len(phonebook), "entries")

#Call the main function
main()
