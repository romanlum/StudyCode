package swt6.soccer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import swt6.soccer.domain.Game;
import swt6.soccer.logic.SoccerService;

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

    @RequestMapping(value = "/games/new",
            method = RequestMethod.POST)
    public String processNew(@ModelAttribute("entry") Game entry, BindingResult result, Model model){

        logger.info("teamA: "+entry.getDate());

        return "games/manage";
    }

}
