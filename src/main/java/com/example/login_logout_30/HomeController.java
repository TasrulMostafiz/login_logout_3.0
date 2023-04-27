package com.example.login_logout_30;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value="/")
public class HomeController {

    @Autowired private CustomUserDetailService customUserDetailService;
    @GetMapping("/")
    @ResponseBody
    public String Home(){

        return "Home Sweet Home";

    }

    @GetMapping("/mylogin")
    public String login(@RequestParam(value = "invalid_session",defaultValue = "false") boolean invalid_session,
                        @RequestParam(value = "error",defaultValue = "false") boolean error,Model model){
        model.addAttribute(invalid_session);
        model.addAttribute(error);
        return "mylogin";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(value = "success",required = false,defaultValue = "false") boolean success
            , Model model) {
        model.addAttribute("data",customUserDetailService.list());
        model.addAttribute("success",success);
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/add")
    public String add(@RequestParam(value = "id",required = false) Long id,Model model) {
        if(id!=null){
            model.addAttribute("customUser",customUserDetailService.getByid(id));
        }else{
            model.addAttribute("customUser",new CustomUser());
        }
        return "add";
    }

    @PostMapping("/add_post")
    public String add_post(@Valid @ModelAttribute CustomUser customUser,
                           BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("customUser",customUser);
            return "add";
        }

        try {
            customUserDetailService.save(customUser);
            return "redirect:/admin?success=true";
        }catch (Exception e){
            model.addAttribute("error_msg",e.getMessage());
            return "add";
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public String all() {
        return "<h2>Hello Everyone!</h2>";
    }
}
