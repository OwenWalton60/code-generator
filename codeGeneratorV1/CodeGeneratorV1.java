package codeGeneratorV1;
/**
 * 
 * @author Owen Walton
 * @version 1
 * @aim to generate a given amount of codes with a given amount of digits and then let the user sign in with them
 *
 */


import java.util.Random;
import java.util.Scanner;


public class CodeGeneratorV1
{

	public static void main(String[] args)
	{

		//declaring variables
		Random rand = new Random(); // creates random object
		Scanner iKeyboard = new Scanner(System.in); //creates integer scanner object
		Scanner szKeyboard = new Scanner(System.in); //creates string scanner object
		String[] szCodesCreated; // array to store all the codes
		int iTimes; // number of codes needed
		int[] iCodeNumber; // the account number for each code
		int iUserNumber; // the account number belonging to the user
		String szUserCode = ""; // the code the user has entered to sign in
		int i; // for loops
		int j; // nested for loops
		int iAmountOfDigits; // amount of digits the user wants the code to be
		int iTries; // stores the amount of tries the user has left the get the correct code


		//asks the amount needed
		System.out.println("How many codes would you like to be generated?");
		iTimes = iKeyboard.nextInt();



		// creates array to store the codes in
		szCodesCreated = new String [iTimes];
		iCodeNumber = new int[iTimes];


		//asks how many digits and keeps asking until a number less than 10 is given
		System.out.println("How many digits do you want them to be (max 10)?");
		do 
		{
			iAmountOfDigits = iKeyboard.nextInt();
			if (!(iAmountOfDigits > 0 && iAmountOfDigits <= 10)) 
			{
				System.out.println("The amount of digits wasn't between 1 and 10");
				System.out.println("Please re enter");
			}
		} while (!(iAmountOfDigits > 0 && iAmountOfDigits <= 10));



		//Creates the code
		for (i = 0; i < iTimes; i++)
		{
			// initialises where the code will be stored
			szCodesCreated[i] = "";

			for (j = 1; j <= iAmountOfDigits; j++)
			{	
				// creates a digit
				int digit = rand.nextInt(10);

				//adds digit to code
				szCodesCreated[i] = szCodesCreated[i] + Integer.toString(digit);
			}
			// makes the account number
			iCodeNumber[i] = i + 1;

			//prints the code and the account number and then leaves a line break before next code
			System.out.println("Account number: " + iCodeNumber[i]);
			System.out.println("Code: " + szCodesCreated[i] + "\n");

		}




		//asks for their account number and keeps asking until a valid number is entered
		do
		{
			//asks for their account number
			System.out.print("What is your account number? ");
			iUserNumber = iKeyboard.nextInt();



			// tells the user that they need to re enter an account number if it is invalid
			if (!(iUserNumber > 0 && iUserNumber <= iTimes))
			{		
				System.out.println("There is no code with the account number: " + iUserNumber + ", Please try again\n");

			}
		} while (!(iUserNumber > 0 && iUserNumber <= iTimes));




		// loops through all account numbers until the one the user has entered matches
		for (i = 0; i < iTimes; i++)
		{
			if (iUserNumber == iCodeNumber[i])
			{


				for (iTries = 3; iTries > 0;)
				{
					//asks the user for their code
					System.out.print("\nPlease enter your code: ");


					do
					{
						// lets user enter their code
						szUserCode = szKeyboard.nextLine();

						if (szUserCode.length() != iAmountOfDigits)
						{
							//tell the user the code they entered didn't have enough digits and to re enter it
							System.out.print("The code you entered didn't have " + iAmountOfDigits + " digits, Please re enter: ");
						}
					}
					while (szUserCode.length() != iAmountOfDigits);


					// checks if the code entered by user is correct
					if (szCodesCreated[i].equals(szUserCode))
					{
						// line break
						System.out.println(); 

						// if correct, tells the user they are signed in and breaks out of the nested for loop
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
							// if the user has 1 attempt left tells them they only have 1 left (non plural)
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
				break; // breaks out the for loop after checking if the code was correct
			}
		}


		//closes scanner objects
		iKeyboard.close();
		szKeyboard.close();

	}//end main

}//end class