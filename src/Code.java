import java.util.Random;
import java.util.Scanner;

// this class is used to create an object for each code

public class Code
{
    // declaring variables
    private final int iNumOfDigits; // creates amount of digits attribute
    private final int iAccountNumber; // creates an account number attribute
    private String szCode = ""; // initialises the string which the code is stored in


    // constructor method
    public Code(int iNumOfDigits, int iAccountNumber)
    {
        this.iNumOfDigits = iNumOfDigits;
        this.iAccountNumber = iAccountNumber;
    } // end constructor


    public void createCode()
    {
        //declaring variables
        Random rand = new Random(); // creates random object

        // each iteration creates one digit
        for (int i = 1; i <= iNumOfDigits; i++)
        {

            // creates a digit
            int digit = rand.nextInt(10);

            //adds digit to code
            szCode = String.format("%s%s", szCode, digit);

        }


        //prints the code and the account number and then leaves a line break before next code
        System.out.println("Account number: " + iAccountNumber);
        System.out.println("Code: " + szCode + "\n");

    } // end function


    public void checkCode()
    {
        // declaring variables
        Scanner szKeyboard = new Scanner(System.in); // creates scanner object
        String szUserCode; // creates string to store the code the user enters



        for (int iTries = 3; iTries > 0;) // loops through the users 3 tries
        {
            //asks the user for their code
            System.out.print("\nPlease enter your code: ");


            do
            {
                // lets user enter their code
                szUserCode = szKeyboard.nextLine();

                // checks if the code the user entered was the right amount of digits
                if (szUserCode.length() != this.iNumOfDigits)
                {
                    //tell the user the code they entered didn't have the correct amount of digits and to re-enter it
                    System.out.print("The code you entered didn't have " + this.iNumOfDigits + " digits, Please re enter: ");
                }
            }
            while (szUserCode.length() != this.iNumOfDigits);



            // checks if the code entered by user is correct
            if (this.szCode.equals(szUserCode))
            {
                // line break
                System.out.println();

                // if correct, tells the user they are signed in and breaks out of the for loop
                System.out.println("Correct code, you are signed in.");
                break;
            }
            else
            {
                // if incorrect the amount of tries left loses 1
                iTries--;

                if (iTries > 1)
                {
                    //if the user has over 1 attempts, it tells them how many more tries they have left (plural)
                    System.out.println("Incorrect code, you have " + iTries + " attempts left.");

                }
                else if (iTries == 1)
                {
                    // if the user has 1 attempt left tells them they only have 1 left (non-plural)
                    System.out.println("Incorrect code, you have " + iTries + " attempt left.");

                }
                else
                {
                    // if the user is out of tries, tells them they have no tries left
                    System.out.println("Incorrect code, you have no more attempts left.\n");


                    // if the user is out of tries, tells them they are not signed in
                    System.out.println("You are not signed in.");
                }
            }
        }

        // closes scanner object
        szKeyboard.close();

    } // end function

} // end class
