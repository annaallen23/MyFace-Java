package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softwire.training.myface.models.viewmodels.AllUsersViewModel;
import softwire.training.myface.services.UsersService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/users")
public class UserListController {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserListController(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }



    @RequestMapping(value = "")
    public ModelAndView getAllWalls(Principal principal) {

        AllUsersViewModel allUsersViewModel = new AllUsersViewModel();
        allUsersViewModel.loggedInUsername = principal.getName();
        allUsersViewModel.allUsernames = usersService.guessAllUsernames();

        return new ModelAndView("users", "model", allUsersViewModel);
    }
}
