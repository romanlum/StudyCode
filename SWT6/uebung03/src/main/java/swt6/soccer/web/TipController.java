package swt6.soccer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swt6.soccer.domain.Tip;
import swt6.soccer.domain.User;
import swt6.soccer.logic.SoccerService;
import swt6.soccer.logic.exceptions.TipAlreadyExistsException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


/**
 * Controller handling tips
 */
@Controller
public class TipController {

    @Autowired
    private SoccerService soccerService;

    private final Logger logger = LoggerFactory.getLogger(TipController.class);

    @RequestMapping(value = "/tips/new", method = RequestMethod.GET)
    public String initNew(Model model) {
        model.addAttribute("games",soccerService.getOpenGamesList());
        model.addAttribute("users",soccerService.findAllUsers());
        model.addAttribute("entry",new Tip());
        return "tips/manage";
    }

    @RequestMapping(value = "/tips/new", method = RequestMethod.POST)
    public String processNew(@Valid @ModelAttribute("entry") Tip entry, BindingResult result, Model model){

        if (!result.hasErrors()) {
            try {
                soccerService.addTip(entry);
            } catch (TipAlreadyExistsException ex) {
                logger.warn("Tip already exists for user {} and game {}", ex.getUser(), entry.getGame());
                result.addError(new ObjectError("entry", "There is already a tip for the given game and user!"));
            }
        }


        if (result.hasErrors()) {
            model.addAttribute("games",soccerService.getOpenGamesList());
            model.addAttribute("users",soccerService.findAllUsers());
            return "tips/manage";
        }



        logger.debug("added tip {}", entry);
        return "redirect:/tips/score";
    }

    @RequestMapping(value = "/tips/score", method = RequestMethod.GET)
    public String listScore(Model model) {
        List<User> users = soccerService.findAllUsers();
        users.sort((x,y) -> Long.valueOf(x.getPoints()).compareTo(y.getPoints()));
        Collections.reverse(users);
        model.addAttribute("users",users);

        return "tips/score";
    }

}
