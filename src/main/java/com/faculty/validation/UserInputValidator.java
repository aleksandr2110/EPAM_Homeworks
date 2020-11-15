package com.faculty.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aleksandr on 27.09.2020.
 */
public class UserInputValidator {

    private static Logger logger = LoggerFactory.getLogger(UserInputValidator.class);

    private static final String firstNameReg = "^[А-Яа-яA-Za-z]{3,20}$";
    private static final String lastNameReg = "^[А-Яа-яA-Za-z]{3,28}$";
    private static final String loginReg = "^[A-Za-z0-9]{4,20}$";
    private static final String emailReg = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String passwordReg = "^[(/?.)_$A-Za-z0-9]{59,}$";
    private static final String telephoneReg = "^[0-9]{5,20}$";
    private static final String courseNameReg = "^[А-Яа-яA-Za-z0-9\\s.:;!?+]{1,70}$";
    private static final String durationReg = "^[0-9]{1,3}$";
    private static final String priceReg = "^[0-9]{2,5}$";
    private static final String studentMarkReg = "^[0-9]{1,2}$";

    private static class UserInputValidatorHolder {
        private static final UserInputValidator INSTANCE = new UserInputValidator();
    }

    public static UserInputValidator getInstance() {
        return UserInputValidatorHolder.INSTANCE;
    }

    public boolean isFirstNameValid(String firstName)
    {
        if(firstName == null || firstName.isEmpty())
        {
            logger.info("FacultyUser first name is empty");
            return false;
        } else {
            logger.info("FacultyUser first name is valid: {}", firstName.matches(firstNameReg));
            return firstName.matches(firstNameReg);
        }
    }

    public boolean isLastNameValid(String lastName)
    {
        if(lastName == null || lastName.isEmpty())
        {
            logger.info("FacultyUser last name is empty");
            return false;
        } else {
            logger.info("FacultyUser last name is valid: {}", lastName.matches(lastNameReg));
            return lastName.matches(lastNameReg);
        }
    }

    public boolean isLoginValid(String login) {
        if (login == null || login.isEmpty()) {
            logger.info("FacultyUser login is empty");
            return false;
        } else {
            logger.info("FacultyUser login is valid: {}", login.matches(loginReg));
            return login.matches(loginReg);
        }
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            logger.info("User email is empty");
            return false;
        } else {
            logger.info("FacultyUser email is valid: {}", email.matches(emailReg));
            return email.matches(emailReg);
        }
    }

    public boolean isTelephoneValid(String telephone) {
        if (telephone == null || telephone.isEmpty()) {
            logger.info("FacultyUser telephone name is empty");
            return false;
        } else {
            logger.info("FacultyUser telephone is valid: {}", telephone.matches(telephoneReg));
            return telephone.matches(telephoneReg);
        }
    }

    public boolean isPasswordValid(String password) {
        if (password == null || password.isEmpty()) {
            logger.info("FacultyUser password is empty");
            return false;
        } else {
            logger.info("FacultyUser  password is valid: {}", password.matches(passwordReg));
            return password.matches(passwordReg);
        }
    }

    public boolean isCourseNameValid(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
            logger.info("Course name is empty");
            return false;
        } else {
            logger.info("Course name is valid: {}", courseName.matches(courseNameReg));
            return courseName.matches(courseNameReg);
        }
    }

    public boolean isDurationValid(String duration) {
        if (duration == null || duration.isEmpty()) {
            logger.info("Course duration is empty");
            return false;
        } else {
            logger.info("Course duration is valid: {}", duration.matches(durationReg));
            return duration.matches(durationReg);
        }
    }


    public boolean isPriceValid(String price) {
        if (price == null || price.isEmpty()) {
            logger.info("Course price is empty");
            return false;
        } else {
            logger.info("Course price is valid: {}", price.matches(priceReg));
            return price.matches(priceReg);
        }
    }

    public boolean isStudentMarkValid(String studentMark) {
        if (studentMark == null || studentMark.isEmpty()) {
            logger.info("Registration studentMark is empty");
            return false;
        } else {
            logger.info("Registration studentMark is valid: {}", studentMark.matches(studentMarkReg));
            return studentMark.matches(studentMarkReg);
        }
    }
}
