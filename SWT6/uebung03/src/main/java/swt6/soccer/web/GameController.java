package swt6.soccer.web;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swt6.soccer.domain.Game;
import swt6.soccer.logic.SoccerService;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Controller for games
 */
@Controller
public class GameController {

    @Autowired
    private SoccerService soccerService;

    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(value = "/games")
    public String listGames(Model model) {
        return "games/list";
    }

    @RequestMapping(value = "/games/new", method = RequestMethod.GET)
    public String initNew(Model model){
        model.addAttribute("entry",new Game());
        model.addAttribute("teams",soccerService.findAllTeams());

        return "games/manage";
    }

    @RequestMapping(value = "/games/new", method = RequestMethod.POST)
    public String processNew(@Valid @ModelAttribute("entry") Game entry, BindingResult result, Model model){
        return processUpdate(entry,result,model);
    }

    private String processUpdate(Game entry, @NonNull BindingResult result, @NonNull Model model) {
        if (result.hasErrors()) {
            model.addAttribute("teams",soccerService.findAllTeams());
            return "games/manage";
        }

        soccerService.syncGame(entry);
        logger.debug("added/updated entry {}", entry);
        return "redirect:/games";

    }

}
