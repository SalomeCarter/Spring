package by.tms.controller;

import by.tms.dto.LoginUserDto;
import by.tms.entity.User;
import by.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public String reg(Model model) {
        model.addAttribute("newUser", new User());
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, Model model,
                      @RequestParam("name") String name,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password) {
            User newUser = new User(name, username, password);
            userService.save(user);
            return "redirect:/";
        }


      @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("newLogin", new LoginUserDto());
    return "login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute("newLogin") @Valid LoginUserDto loginUserDto,
                      @SessionAttribute("user") User user,
                      BindingResult bindingResult,
                      HttpSession session,
                      ModelAndView modelAndView,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password) {
      Optional<User> byUsername = userService.findByUsername(username);
      if (byUsername.isPresent()) {
          user = byUsername.get();
          if (user.getPassword().equals(password)) {
              session.setAttribute("user", user);
              return "redirect:/";
          } else {
              bindingResult.rejectValue("password", "error.password", "Wrong password!");
              return "forward:/pages/login.jsp";
          }
      } else {
          bindingResult.rejectValue("username", "error.username", "User not found!");
          return "forward:/pages/login.jsp";
      }
  }


  @GetMapping("/logout")
  public String logout(HttpSession httpSession) {
      httpSession.invalidate();
      return "redirect:/";
  }


}

