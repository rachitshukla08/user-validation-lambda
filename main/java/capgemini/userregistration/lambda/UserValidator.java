package capgemini.userregistration.lambda;

import java.util.Scanner;
import java.util.regex.*;

import capgemini.userregistration.lambda.InvalidUserDetailsException.ExceptionType;

/**Refactor the Code to use Lambda Function to validate User Entry
 * 
 */

/**
 * @author Rachit
 *
 */
public class UserValidator {
	final static String NAME_PATTERN = "^[A-Z]{1}[a-zA-Z]{2,}";
	final static String EMAIL_PATTERN = "^[a-zA-Z0-9+_-]+([.][a-zA-Z0-9]+)*@([a-zA-Z0-9]+)([.][a-z]+)?[.][a-z]{2,}$";
	final static String MOBILE_PATTERN = "[0-9-]{1,}[ ][1-9]{1}[0-9]{9}";
	final static String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[A-Z])(?=.*\\W)(?!.*\\W\\w*\\W)(?!.*\\s).{8,}$";

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param mobile
	 * @param password
	 * @throws InvalidUserDetailsException
	 * @return true if all details are valid
	 */
	public static boolean validate(String firstName,String lastName,String email,String mobile,String password) throws InvalidUserDetailsException {

		UserEntryValidator validateFirstName = (firstNameValid) -> firstNameValid.matches(NAME_PATTERN);
		UserEntryValidator validateLastName = (lastNameValid) -> lastNameValid.matches(NAME_PATTERN);
		UserEntryValidator validateEmail = (emailValid) -> emailValid.matches(EMAIL_PATTERN);
		UserEntryValidator validateMobile = (mobileValid) -> mobileValid.matches(MOBILE_PATTERN);
		UserEntryValidator validatePassword = (passwordValid) -> passwordValid.matches(PASSWORD_PATTERN);

		boolean isFirstNameValid = validateFirstName.validate(firstName);
		if (isFirstNameValid)
			System.out.println("Valid first name");
		else
			throw new InvalidUserDetailsException(ExceptionType.INVALID_FIRST_NAME, "Please enter proper first name");

		boolean isLastNameValid = validateLastName.validate(lastName);
		if (isLastNameValid)
			System.out.println("Valid last name");
		else
			throw new InvalidUserDetailsException(ExceptionType.INVALID_LAST_NAME, "Please enter proper last name");

		boolean isEmailValid = validateEmail.validate(email);
		if (isEmailValid)
			System.out.println("Valid Email ID");
		else
			throw new InvalidUserDetailsException(ExceptionType.INVALID_EMAIL, "Please enter proper Email ID");

		boolean isMobileValid = validateMobile.validate(mobile);
		if (isMobileValid)
			System.out.println("Valid Mobile Number");
		else
			throw new InvalidUserDetailsException(ExceptionType.INVALID_MOBILE, "Please enter proper mobile number");

		boolean isPasswordValid = validatePassword.validate(password);
		if (isPasswordValid)
			System.out.println("Valid Password");
		else
			throw new InvalidUserDetailsException(ExceptionType.INVALID_PASSWORD, "Please enter proper password");
		
		return true;
	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to user registration");
		boolean isValid = false;
		Scanner sc = new Scanner(System.in);
		
		while(isValid==false) {
			try {
				System.out.println("Please enter your first name:");
				String firstName = sc.nextLine();
				System.out.println("Please enter your last name:");
				String lastName = sc.nextLine();
				System.out.println("Enter Email ID");
				String email = sc.nextLine();
				System.out.println("Enter Mobile number");
				String mobile = sc.nextLine();
				System.out.println("Enter password");
				String password = sc.nextLine();
				isValid = validate(firstName,lastName,email,mobile,password);
				System.out.println("Congratulations,all details are in proper format");
			} catch (InvalidUserDetailsException e) {
				System.out.println(e.getMessage());
				System.out.println("--------------------------------\nEnter your details again");
			}
		}
	}
}
