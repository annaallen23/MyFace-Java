package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import softwire.training.myface.models.dbmodels.User;
import softwire.training.myface.services.UsersService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/signup")
public class NewUserController {

    private UsersService usersService;

    @Autowired
    public NewUserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showSignUpPage() {

        return new ModelAndView("signup");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public RedirectView signUp (@ModelAttribute User user, RedirectAttributes attributes){

        Optional<User> existingUser = usersService.getUserWithUserName(user.getUserName());

        if(!existingUser.isPresent()) {
            usersService.addUser(user);
            return new RedirectView("/users");
        } else {
            return new RedirectView("/users");
        }
    }

}
