package capgemini.userregistration.lambda;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserValidatorTest {

	@Test
	public void givenAllDetails_WhenAllProper_ShouldReturnTrue() {
		try {
			boolean isValid = UserValidator.validate("Rachit","Shukla","rachit@123.com","91 9876543210","Abc@1234t");
			assertEquals(true,isValid);
		} catch (InvalidUserDetailsException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenAllDetails_WhenFirstNameImproper_ShouldThrowCustomException_ofTypeInvalidFirstName() {
		try {
			UserValidator.validate("Rachit$","Shukla","rachit@123.com","91 9876543210","Abc@1234t");
		} catch (InvalidUserDetailsException e) {
			assertEquals(InvalidUserDetailsException.ExceptionType.INVALID_FIRST_NAME, e.type);
		}
	}
	
	@Test
	public void givenAllDetails_WhenLastNameImproper_ShouldThrowCustomException_ofTypeInvalidLastName() {
		try {
			UserValidator.validate("Rachit","Sh","rachit@123.com","91 9876543210","Abc@1234t");
		} catch (InvalidUserDetailsException e) {
			assertEquals(InvalidUserDetailsException.ExceptionType.INVALID_LAST_NAME, e.type);
		}
	}
	
	@Test
	public void givenAllDetails_WhenEmailImproper_ShouldThrowCustomException_ofTypeInvalidEmail() {
		try {
			UserValidator.validate("Rachit","Shukla","rachit@","91 9876543210","Abc@1234t");
		} catch (InvalidUserDetailsException e) {
			assertEquals(InvalidUserDetailsException.ExceptionType.INVALID_EMAIL, e.type);
		}
	}
	
	@Test
	public void givenAllDetails_WhenMobileImproper_ShouldThrowCustomException_ofTypeInvalidMobile() {
		try {
			UserValidator.validate("Rachit","Shukla","rachit@gmail.com","91 98765","Abc@1234t");
		} catch (InvalidUserDetailsException e) {
			assertEquals(InvalidUserDetailsException.ExceptionType.INVALID_MOBILE, e.type);
		}
	}
	
	@Test
	public void givenAllDetails_WhenPasswordImproper_ShouldThrowCustomException_ofTypeInvalidPassword() {
		try {
			UserValidator.validate("Rachit","Shukla","rachit@gmail.com","91 9876543210","Abc");
		} catch (InvalidUserDetailsException e) {
			assertEquals(InvalidUserDetailsException.ExceptionType.INVALID_PASSWORD, e.type);
		}
	}
}
