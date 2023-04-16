package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }


    @RequestMapping("/")
    public String showAllUsers(Model model){
        List<User>allUsers=userService.getAllUsers();
        model.addAttribute("allUsers",allUsers);
        return "allUsers";
    }
    @RequestMapping(value="/AddNewUser")
    public String addNewUser(Model model){
        User user=new User();
        model.addAttribute("user",user);

        return "AddNewUser";
    }
    @RequestMapping("saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "/edit";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        userService.delete(id);
        return "redirect:/";
    }

}




