package com.nadia.controller;

import com.nadia.UserService;
import com.nadia.model.Profession;
import com.nadia.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

/**
 * @author Yevhenii Filatov
 * @since 5/30/23
 */

@Controller
@RequiredArgsConstructor
public class UserController {
    private static final String REGISTER_URL = "/register";
    private static final String SHOW_ALL_URL = "/show-all";
    private final UserService userService;

    @GetMapping(REGISTER_URL)
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("professions", Arrays.asList(Profession.values()));
        return "registration_form";
    }

    @GetMapping(SHOW_ALL_URL)
    public String showAll(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "all_users";
    }

    @PostMapping(REGISTER_URL)
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        userService.addUserToFile(user);
        model.addAttribute("total_users", userService.getUsersCount());
        return "success";
    }
}
