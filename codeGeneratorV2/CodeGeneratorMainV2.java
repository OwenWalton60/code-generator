package codeGeneratorV2;

/**
 * @author Owen Walton
 * @version 2
 * @aim to generate a given amount of codes with a given amount of digits and then let the user sign in with them using functions
 */


import java.util.Random;
import java.util.Scanner;

public class CodeGeneratorMainV2 
{
	static Scanner iKeyboard = new Scanner(System.in);
	static Scanner szKeyboard = new Scanner(System.in);
	
	
	
	
	public static void main(String[] args) 
	{
		// declaring variables
		int iDigits; // stores the amount of digits per code
		String[] szCodes; // array to store the codes
		int iTimes; // stores the amount of codes the user wants
		int iUserNumber; // stores the users account number





		// asks the user how many codes they need to be created
		System.out.println("How many codes do you need to create?");
		iTimes = iKeyboard.nextInt();




		// calls the function which asks the user the amount of digits and assigns to 'iDigits'
		iDigits = amountOfDigits();




		// sets the length of the array of codes to the amount of codes needed
		szCodes = new String[iTimes];



		// calls the function which creates the codes and assigns to 'szCodes[]', passing through(amount of digits per code, number of codes)
		szCodes = createCode(iDigits, iTimes);

		
		// calls the function to find out the users account number
		iUserNumber = userAccountNumber(iTimes);
		

		// checks if the users code is correct
		checkCode(iTimes, iUserNumber, iDigits, szCodes);

		// closes scanner objects
		szKeyboard.close();
		iKeyboard.close();
		
	}// end main
	
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public static String[] createCode(int iDigits, int iTimes)
	{
		// declaring variables
		Random rand = new Random(); // creates random object
		int i; // for loop counter 
		int j; // for loop counter 
		String[] szCodes = new String[iTimes]; // array to store the codes in
		int[] iAccountNumbers = new int[iTimes]; // stores the account numbers at the matching index to the codes
		
		for (i = 0; i < iTimes; i++)
		{
			// initialises variable to store the code
			szCodes[i] = ""; 
			
			
			// each loop creates one digits
			for (j = 1; j <= iDigits; j++)
			{	

				// creates a digit
				int digit = rand.nextInt(10);
				
				
				//adds digit to code
				szCodes[i] = szCodes[i] + Integer.toString(digit);
			}
		
		// sets the account number to match the code
		iAccountNumbers[i] = i + 1;
		
		
		//prints the code and the account number and then leaves a line break before next code
		System.out.println("Account number: " + iAccountNumbers[i]);
		System.out.println("Code: " + szCodes[i] + "\n");
		}

		

		// returns the code created
		return szCodes;
		
	} // end function
	
	
	
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
		
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void checkCode(int iTimes, int iUserNumber, int iDigits, String[] szCodes)
	{
		int i;
		int iTries;
		int[] iAccountNumbers = new int[iTimes];
		String szUserCode;
		
		
		// sets the account numbers to match with their codes
		for (i = 0; i < iTimes; i++)
		{
			iAccountNumbers[i] = i + 1;
		}

		
		
		// loops through all account numbers until the one the user has entered matches
		for (i = 0; i < iTimes; i++)
		{
			if (iUserNumber == iAccountNumbers[i])
			{


				for (iTries = 3; iTries > 0;)
				{
					//asks the user for their code
					System.out.print("\nPlease enter your code: ");


					do
					{
						// lets user enter their code
						szUserCode = szKeyboard.nextLine();

						if (szUserCode.length() != iDigits)
						{
							//tell the user the code they entered didn't have enough digits and to re enter it
							System.out.print("The code you entered didn't have " + iDigits + " digits, Please re enter: ");
						}
					}
					while (szUserCode.length() != iDigits);





					// checks if the code entered by user is correct
					if (szCodes[i].equals(szUserCode))
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
	} // end function
	
	
}// end class
