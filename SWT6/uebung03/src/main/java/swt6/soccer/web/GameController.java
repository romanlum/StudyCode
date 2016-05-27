package swt6.soccer.web;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swt6.soccer.domain.Game;
import swt6.soccer.logic.SoccerService;
import swt6.soccer.logic.exceptions.GameNotFoundException;

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

    @RequestMapping(value = "/games/finished")
    public String listFinishedGames(Model model) {
        model.addAttribute("games",soccerService.getFinishedGamesList());
        model.addAttribute("finished",true);
        return "games/list";
    }

    @RequestMapping(value = "/games/open")
    public String listOpenGames(Model model) {
        model.addAttribute("games",soccerService.getOpenGamesList());
        model.addAttribute("finished",false);
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
        //validate team
        if(Objects.equals(entry.getTeamA().getId(), entry.getTeamB().getId())) {
            result.addError(new FieldError("entry", "teamB","please choose another team."));
        }

        if (result.hasErrors()) {
            model.addAttribute("teams",soccerService.findAllTeams());
            return "games/manage";
        }

        soccerService.syncGame(entry);
        logger.debug("added entry {}", entry);
        return "redirect:/games/open";
    }



    @RequestMapping(value = "/games/{gameId}/update_result", method = RequestMethod.GET)
    public String initUpdate(@PathVariable("gameId") Long gameId,Model model){

        Game game = soccerService.findGame(gameId);
        //validate game
        if(game == null) {
            return "redirect:/games/open";
        }

        model.addAttribute("entry",game);
        model.addAttribute("teams",soccerService.findAllTeams());

        return "games/manage";
    }
    @RequestMapping("/games/{gameId}/update_result")
    public String processUpdate(@PathVariable("gameId") Long gameId,@Valid @ModelAttribute("entry") Game entry, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("teams",soccerService.findAllTeams());
            return "games/manage";
        }

        try {
            soccerService.addGameResults(gameId,entry.getGoalsA(),entry.getGoalsB());
        } catch (GameNotFoundException e) {
            logger.warn("Could not update game with id {}, because it was not found",e.getGameId());
        }

        return "redirect:/games/finished";
    }

}
