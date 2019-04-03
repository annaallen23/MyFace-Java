package softwire.training.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import softwire.training.myface.models.viewmodels.WallViewModel;
import softwire.training.myface.services.PostsService;
import softwire.training.myface.services.UsersService;

import java.security.Principal;

@Controller
@RequestMapping("/wall")
public class WallController {

    private final PostsService postsService;
    private final UsersService usersService;

    @Autowired
    public WallController(PostsService postsService, UsersService usersService) {
        this.postsService = postsService;
        this.usersService = usersService;
    }

    @RequestMapping(value = "/{wallOwnerUsername}", method = RequestMethod.GET)
    public ModelAndView getWall(
            @PathVariable("wallOwnerUsername") String wallOwnerUsername,
            Principal principal
    ) {

        WallViewModel wallViewModel = new WallViewModel();
        wallViewModel.loggedInUsername = principal.getName();
        wallViewModel.wallOwnerUsername = wallOwnerUsername;
        wallViewModel.posts = postsService.getPostsOnWall(wallOwnerUsername);
        wallViewModel.wallOwnerFullName = usersService.getUserWithUserName(wallOwnerUsername).get().getFullName();

        return new ModelAndView("wall", "model", wallViewModel);
    }

    @RequestMapping(value = "/{wallOwnerUsername}/post", method = RequestMethod.POST)
    public RedirectView postOnWall(
            @PathVariable("wallOwnerUsername") String wallOwnerUsername,
            @ModelAttribute("content") String content,
            Principal loggedInUserPrincipal
    ) {

        String senderUsername = loggedInUserPrincipal.getName();
        postsService.createPost(senderUsername, wallOwnerUsername, content);

        return new RedirectView("/wall/" + wallOwnerUsername);
    }

}
