package codeGeneratorV3;

import java.util.Scanner;

/**
 * 
 * @author Owen Walton
 * @version 3
 * @aim to generate a given amount of codes with a given amount of digits and then let the user sign in with them using classes/ objects
 *
 */

public class CodeGeneratorMainV3 
{
	static Scanner iKeyboard = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		// declaring variables
		int iTimes; // stores the amount of codes the user needs
		int iDigits; // stores the amount of digits per code
		CodeV3[] Codes; // object array to store each code object
		int i; // for loop counter
		int[] iAccountNumbers; // stores the account numbers in matching index to their codes
		int iUserNumber; // stores the account number which belongs to the user
		
		// asks the user how many codes they need to be created
		System.out.println("How many codes do you need to create?");
		iTimes = iKeyboard.nextInt();

		
		// calls the function which asks the user the amount of digits and assigns to 'iDigits'
		iDigits = amountOfDigits();
		
		
		// sets the length of the array of codes to the amount of codes needed
		Codes = new CodeV3[iTimes];
		
		
		// sets the length of the account number array to the amount of codes
		iAccountNumbers = new int[iTimes];
		
		for (i = 0; i < iTimes; i++)
		{
			// sets the account numbers in matching index to their codes
			iAccountNumbers[i] = i + 1;
			
			// creates the objects at all the indexes
			Codes[i] = new CodeV3(iDigits, iAccountNumbers[i]);
			
			// creates the code for each object
			Codes[i].createCode();
		}
		
		// calls the function which asks the user for their account number
		iUserNumber = userAccountNumber(iTimes);
		
		// loops through all account numbers until the one the user has entered matches
		for (i = 0; i < iTimes; i++)
		{
			if (iUserNumber == iAccountNumbers[i])
			{
				// calls the function which receives the users code and  then checks if its correct
				Codes[i].checkCode();
			}
		}
		
		//closes scanner object
		iKeyboard.close();
		
	}// end main

//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------	
		


	public static int amountOfDigits()
	{
		//declaring variables
		int iDigits; // stores the amount of digits



		//asks how many digits with max 10
		System.out.println("How many digits do you want them to be (max 10)?");

		do 
		{
			// Receives input
			iDigits = iKeyboard.nextInt();

			// checks if input is invalid
			if (!(iDigits > 0 && iDigits <= 10)) 
			{
				// prints that the number was invalid
				System.out.println("The amount of digits wasn't between 1 and 10");
				System.out.println("Please re enter");
			}

			// keeps going until a valid amount of digits is entered
		} while (!(iDigits > 0 && iDigits <= 10));


		// returns the amount of digits the user entered
		return iDigits;

	} // end function
	
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
		

	public static int userAccountNumber(int iTimes)
	{
		// declaring variables
		int iUserNumber; // stores the users account number


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


		// returns the users account number
		return iUserNumber;

	} // end function

}
