package app.web.controller;

import app.model.Role;
import app.model.User;
import app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class AdminController {
    private final UserDetailsService userService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserDetailsService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", adminService.allUsers());
        return "admin";
    }

    @GetMapping("/admin/delete/{username}")
    public String deleteUser(Model model, @PathVariable String username) {
        User user = (User) userService.loadUserByUsername(username);
        adminService.deleteUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{username}")
    public String editPage(ModelMap model, @PathVariable("username") String username) {
        User user = (User) userService.loadUserByUsername(username);
        ArrayList<Role> roles = (ArrayList<Role>) adminService.allRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "editUser";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) int[] roles) {
        adminService.ChangeUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/add")
    public String addUser(@ModelAttribute("user") User user,@RequestParam(value = "roles", required = false) int[] roles) {
        adminService.addUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/add")
    public String addUser(ModelMap model) {
        ArrayList<Role> roles = (ArrayList<Role>) adminService.allRoles();
        model.addAttribute("allRoles", roles);
        model.addAttribute("addUser", new User());
        return "editUser";
    }
    @GetMapping("/admin/{username}")
    public String getUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(username));
        return "admin";
    }
}