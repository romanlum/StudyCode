package swt6.soccer.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import swt6.soccer.domain.Team;
import swt6.soccer.logic.SoccerService;

@Component
public class StringToTeamConverter implements Converter<String, Team> {

    @Autowired
    private SoccerService service;

    @Override
    public Team convert(String teamId) {
        Long id = new Long(teamId);
        return service.findTeam(id);
    }

}
