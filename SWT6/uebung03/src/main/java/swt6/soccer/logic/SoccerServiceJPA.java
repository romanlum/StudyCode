package swt6.soccer.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swt6.soccer.dao.GameRepository;
import swt6.soccer.dao.TeamRepository;
import swt6.soccer.dao.TipRepository;
import swt6.soccer.dao.UserRepository;
import swt6.soccer.domain.Game;
import swt6.soccer.domain.Team;
import swt6.soccer.domain.Tip;
import swt6.soccer.domain.User;
import swt6.soccer.logic.exceptions.GameNotFoundException;
import swt6.soccer.logic.exceptions.TipAlreadyExistsException;

import java.util.List;

/**
 * JPA implementation of the soccer service
 */
@Component
@Transactional
public class SoccerServiceJPA implements SoccerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TipRepository tipRepository;

    @Override
    public User syncUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Team syncTeam(Team team) {
        return teamRepository.saveAndFlush(team);
    }

    @Override
    public Team findTeam(Long id) {
        return teamRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Game syncGame(Game game) {
        return gameRepository.saveAndFlush(game);
    }

    @Transactional(readOnly = true)
    @Override
    public Game findGame(Long id) {
        return gameRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Game> getFinishedGamesList() {
        return gameRepository.findByFinishedOrderByDateDesc(true);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Game> getOpenGamesList() {
        return gameRepository.findByFinishedOrderByDateDesc(false);
    }

    @Override
    public Game addGameResults(Long gameId, int goalsTeamA, int goalsTeamB) throws GameNotFoundException {
        Game game = gameRepository.findOne(gameId);
        if(game == null) {
            throw new GameNotFoundException(gameId);
        }

        game.setGoalsA(goalsTeamA);
        game.setGoalsB(goalsTeamB);
        game.setFinished(true);
        return gameRepository.saveAndFlush(game);

    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Tip addTip(Tip tip) throws TipAlreadyExistsException {

        Tip tipForUser = tipRepository.findByGameIdAndUserId(tip.getGame().getId(),tip.getUser().getId());
        if(tipForUser != null) {
            throw new TipAlreadyExistsException(tip.getGame(),tip.getUser());
        }
        return tipRepository.saveAndFlush(tip);
    }
}
