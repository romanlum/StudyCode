package swt6.soccer.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import swt6.soccer.domain.Game;
import swt6.soccer.logic.SoccerService;

@Component
public class StringToGameConverter implements Converter<String, Game> {

    @Autowired
    private SoccerService service;

    @Override
    public Game convert(String gameId) {
        Long id = new Long(gameId);
        return service.findGame(id);
    }

}
