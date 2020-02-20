/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boone
 */
@Controller
@CrossOrigin
@RequestMapping("/")
public class RootController {
    
    //Direct to home.html ------------------------------------------------------
    @GetMapping({"/home", "/"})
    public String getHomePage() {
        return "home";
    }
    
    //Direct to listen.html ----------------------------------------------------
    @GetMapping("/listen")
    public String getListenPage() {
        return "listen";
    }
    
    //Direct to blog.html ------------------------------------------------------
    @GetMapping("/blog")
    public String getBlogPage() {
        return "blog";
    }
    
    //Direct to about.html -----------------------------------------------------
    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
    
    //Direct to contact.html ---------------------------------------------------
    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }
    
    //Direct to logIn.html -----------------------------------------------------
    @GetMapping("/logIn")
    public String getLogInPage(Model model) {
        return "logIn";
    }
    
    //Direct to signUp.html ----------------------------------------------------
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }
    
    //Direct to myAccount.html -------------------------------------------------
    @GetMapping("/myAccount")
    public String getMyAccountPage() {
        return "myAccount";
    }
    
    //Direct to TSPadmin.html --------------------------------------------------
    @GetMapping("/TSPadmin")
    public String getTSPadminPage() {
        return "TSPadmin";
    }
}
