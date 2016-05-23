package swt6.soccer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import swt6.soccer.domain.Team;
import swt6.soccer.domain.User;
import swt6.soccer.logic.SoccerService;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private SoccerService soccerService;

    @Override
    public void run(String... args) throws Exception {

        addUsers();
        addTeams();

    }

    private void addUsers() {
        soccerService.syncUser(new User("Roman","roman@lumetsnet.at"));
        soccerService.syncUser(new User("Christoph","christoph@lumetsnet.at"));
        soccerService.syncUser(new User("Thomas","thomas@lumetsnet.at"));
        soccerService.syncUser(new User("Martin","martin@lumetsnet.at"));
    }

    private void addTeams() {
        soccerService.syncTeam(new Team("Österreich"));
        soccerService.syncTeam(new Team("Montenegro"));
        soccerService.syncTeam(new Team("Schweden"));
        soccerService.syncTeam(new Team("Russland"));
        soccerService.syncTeam(new Team("Lichtenstein"));
        soccerService.syncTeam(new Team("Moldavien"));
    }


}