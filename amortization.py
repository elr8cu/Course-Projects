## Emily Roberts (elr8cu)
## CS1113-300
## 10/7/16
## Project 1: Amortization Chart

#Print welcome message
dashes_61 = "-"*61
print(dashes_61)

dashes_11 = "-"*11
print(dashes_11, "AMORTIZATION PAYMENT SCHEDULE PROGRAM", dashes_11)

print(dashes_61)

#Set a flag to true
keep_running = True

#Create While loop to keep running as long as flag is set to true
while keep_running:
    #Ask user to input name
    name = input("Enter customer name: ")

    #Ask user to enter loan amount
    loan = input("Enter loan amount: ")

    #Create a while loop to ask for new input if loan is not a positive integer
    while(not loan.isdigit()):
        loan = input("Invalid loan amount. Enter loan amount: ")

    #Convert loan from string to integer
    loan = int(loan)

    #Ask user to enter annual interest rate
    annual_rate = input("Enter annual interest rate (%): ")

    #Remove comma from copy of annual rate
    annual_rate_copy = annual_rate.replace(".","")

    #Create a while loop to ask for new input if copy of annual rate is not a positive integer
    while(not annual_rate_copy.isdigit()):
        annual_rate = input("Invalid annual interest rate. Enter annual interest rate (%): ")

        #Make another copy of annual rate and remove the comma
        annual_rate_copy = annual_rate.replace(".", "")

    #Convert annual rate from string to float
    annual_rate = float(annual_rate)

    #Ask user to enter the term
    term = input("Enter term (years): ")

    #Create a while loop to ask for new input if the term is not a positive integer
    while(not term.isdigit()):
        term = input("Invalid term. Enter term (years): ")

    #Convert term from string to integer
    term = int(term)

    #Find number of months per term
    months = term * 12

    #Calculate monthly interest rate
    monthly_rate = (annual_rate / 100) / (months)

    #Print monthly interest rate (rounded to 4th decimal place) in sentence
    print("The monthly interest rate for", name, "is: %.4f" %monthly_rate)

    #Calculate monthly payment
    monthly_payment = loan * monthly_rate * (((1 + monthly_rate) ** months)/((1 + monthly_rate) ** months - 1))

    #Print monthly payment (rounded to 2nd decimal place) in sentence
    print("The monthly payment for", name, "is: %.2f" %monthly_payment)

    #Print header for table
    print("%15s%15s%15s%15s%15s" %("Month",
                                   "Payment",
                                   "Paid Interest",
                                   "Paid Principle",
                                   "New Balance"))

    #Set total interest paid to 0
    total_interest_paid = 0

    #Create a for loop to calculate paid interest, paid principle, and new balance for each month
    for months in range(1, months + 1):

        #Calculate interest amount paid each month
        interest_paid = loan * monthly_rate

        #Calculate principle amount paid each month
        principle_paid = monthly_payment - interest_paid

        #Calculate new balance
        loan = abs(loan - principle_paid)

        #Calculate total interest paid
        total_interest_paid = interest_paid + total_interest_paid

        #Print new row of the table
        print("%15d%15.2f%15.2f%15.2f%15.2f" %(months,
                                               monthly_payment,
                                               interest_paid,
                                               principle_paid,
                                               loan))

    #Print total interest paid rounded to two decimal places
    print("TOTAL INTEREST PAID: %.2f" %total_interest_paid)

    #Ask user if want to run again
    answer = input("Run again (yes/no)? ")

    #Convert answer to upper case to make input validation easier
    answer = answer.upper()

    #While the answer is not yes or no, ask for a new input
    while(answer != "YES" and answer != "NO"):
        answer = input("Incorrect input. Run again (yes/no)? ")
        answer = answer.upper()

    #If the answer is no, set the flag to false
    if(answer == "NO"):
        keep_running = False

#When flag is false (outside the while loop) print closing message
print("Thanks for using our amortization payment schedule program!")
