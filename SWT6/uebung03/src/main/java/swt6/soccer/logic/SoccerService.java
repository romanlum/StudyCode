package swt6.soccer.logic;

import swt6.soccer.domain.Game;
import swt6.soccer.domain.Team;
import swt6.soccer.domain.User;
import swt6.soccer.logic.exceptions.GameNotFoundException;

import java.util.List;

/**
 * Soccer service definition
 */
public interface SoccerService {

    /***
     * Creates or updates the user
     * @param user
     * @return
     */
    User syncUser(User user);

    /***
     * Creates or updates the given team
     * @param team
     * @return
     */
    Team syncTeam(Team team);

    /***
     * Finds the team with the given id
     * @param id id of the team
     * @return team object or null
     */
    Team findTeam(Long id);

    /***
     * Fetches all teams
     * @return
     */
    List<Team> findAllTeams();

    /**
     * Creates or updates the given game
     * @return updated game object
     */
    Game syncGame(Game game);

    /***
     * Finds the game with the given id
     * @param id
     * @return game or null
     */
    Game findGame(Long id);

    /***
     * Prepares the sorted finished games list
     * @return
     */
    List<Game> getFinishedGamesList();

    /***
     * Prepares teh open games list
     * @return
     */
    List<Game> getOpenGamesList();


    /***
     * Sets the game results
     * @param gameId id of the game
     * @param goalsTeamA goals for team A
     * @param goalsTeamB goals for team B
     * @return updated game object
     * @throws GameNotFoundException when the game was not found
     */
    Game addGameResults(Long gameId, int goalsTeamA, int goalsTeamB) throws GameNotFoundException;
}
