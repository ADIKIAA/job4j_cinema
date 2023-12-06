package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void whenGetRequestRegisterThenGetRegisterPage() {
        var view = userController.getRegistrationPage();
        assertThat(view).isEqualTo("users/register");
    }

    @Test
    public void whenPostRequestRegisterThenSuccessAndGetIndexPage() {
        var user = new User();
        when(userService.save(any())).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var view = userController.register(model, user);

        assertThat(view).isEqualTo("redirect:/index");
    }

    @Test
    public void whenPostRequestRegisterThenFailAndGetErrorPageWithMessage() {
        var user = new User();
        when(userService.save(any())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = userController.register(model, user);
        var message = model.getAttribute("message");

        assertThat(view).isEqualTo("users/register");
        assertThat(message).isEqualTo("Пользователь с такой почтой уже существует");
    }

    @Test
    public void whenGetRequestLoginThenGetLoginPage() {
        var view = userController.getLoginPage();
        assertThat(view).isEqualTo("users/login");
    }

    @Test
    public void whenPostRequestLoginThenSuccessfulAndGetIndexPage() {
        var user = new User();
        when(userService.findByEmailAndPassword(any(), any())).thenReturn(Optional.of(user));

        HttpServletRequest request = new MockHttpServletRequest();
        var model = new ConcurrentModel();
        var view = userController.loginUser(model, user, request);
        var session = request.getSession();
        var actualUser = session.getAttribute("user");

        assertThat(view).isEqualTo("redirect:/index");
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    public void whenPostRequestLoginThenFailAndGetLoginPageWithMessage() {
        var user = new User();
        when(userService.findByEmailAndPassword(any(), any())).thenReturn(Optional.empty());

        HttpServletRequest request = new MockHttpServletRequest();
        var model = new ConcurrentModel();
        var view = userController.loginUser(model, user, request);
        var message = model.getAttribute("error");

        assertThat(view).isEqualTo("users/login");
        assertThat(message).isEqualTo("Почта или пароль введены неверно");
    }

}