package com.gmail.maxsvynarchuk.presentation.command.impl.authorization;

import com.gmail.maxsvynarchuk.persistence.entity.User;
import com.gmail.maxsvynarchuk.util.TimeConverter;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.Util;
import com.gmail.maxsvynarchuk.presentation.util.constants.PagesPaths;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostSignUpCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        if (Util.isAlreadyLoggedIn(request.getSession())) {
            return CommandResult.redirect(PagesPaths.HOME_PATH);
        }

        User userDto = User.newBuilder()
                .setEmail(request.getParameter(RequestParameters.EMAIL))
                .setPassword(request.getParameter(RequestParameters.PASSWORD))
                .setFirstName(request.getParameter(RequestParameters.FIRST_NAME))
                .setLastName(request.getParameter(RequestParameters.LAST_NAME))
                .setGender(
                        User.Gender.valueOf(request.getParameter(RequestParameters.GENDER).toUpperCase()))
                .setDateOfBirth(
                        TimeConverter.toDate(request.getParameter(RequestParameters.DATE_OF_BIRTH)))
                .build();

        System.out.println(userDto);
//
//        List<String> errors = validateData(userDto);
//
//        if(errors.isEmpty()) {
//            User createdUser = userService.createUser(userDto);
//            Account userAccount = createNewAccount(createdUser);
//            createNewCard(userAccount, createdUser);
//
//            addUserToSession(request.getSession(), createdUser);
//
//            Util.redirectTo(request, response, PagesPaths.HOME_PATH);
//
//            return REDIRECTED;
//        }
//
//        addInvalidDataToRequest(request, userDto, errors);

        return CommandResult.forward(Views.SIGN_UP_VIEW);
    }

//    private Account createNewAccount(User user) {
//        Account tempAccount = new Account(Account.DEFAULT_NUMBER,
//                user,
//                new BigDecimal(Account.DEFAULT_BALANCE),
//                Account.Status.PENDING);
//
//        Account userAccount = accountService.createAccount(tempAccount);
//
//        return userAccount;
//    }
//
//    private Card createNewCard(Account account, User user) {
//        Card tempCard = Card.newBuilder()
//                .setCardNumber(Card.DEFAULT_NUMBER)
//                .setAccount(account)
//                .setCardHolder(user)
//                .setCvv(CardPassGenerator.getRandomCvv())
//                .setPin(CardPassGenerator.getRandomPin())
//                .setExpireDate(new Date())
//                .setType(Card.CardType.VISA)
//                .build();
//
//        Card createdCard = cardService.createCard(tempCard);
//
//        return createdCard;
//    }
//
//    private List<String> validateData(User user) {
//        List<String> errors = new ArrayList<>();
//
//        Util.validateField(new EmailValidator(), user.getEmail(), errors);
//        Util.validateField(new PasswordValidator(), user.getPassword(), errors);
//        Util.validateField(new PhoneValidator(), user.getPhoneNumber(), errors);
//
//        NameValidator nameValidator = new NameValidator(INVALID_FIRSTNAME_KEY);
//        Util.validateField(nameValidator, user.getFirstName(), errors);
//
//        NameValidator surnameValidator = new NameValidator(INVALID_LASTNAME_KEY);
//        Util.validateField(surnameValidator, user.getLastName(), errors);
//
//        if(errors.isEmpty() && userService.isUserExists(user)) {
//            errors.add(USER_ALREADY_EXISTS);
//        }
//
//        return errors;
//    }
//
//    private void addInvalidDataToRequest(HttpServletRequest request,
//                                         User user,
//                                         List<String> errors) {
//        request.setAttribute(Attributes.USER, user);
//        request.setAttribute(Attributes.ERRORS, errors);
//    }
//
//    private void addUserToSession(HttpSession session, User user)
//            throws IOException {
//        session.setAttribute(Attributes.USER, user);
//    }
}
