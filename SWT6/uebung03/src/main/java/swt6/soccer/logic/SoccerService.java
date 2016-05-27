package swt6.soccer.logic;

import swt6.soccer.domain.Game;
import swt6.soccer.domain.Team;
import swt6.soccer.domain.Tip;
import swt6.soccer.domain.User;
import swt6.soccer.logic.exceptions.GameNotFoundException;
import swt6.soccer.logic.exceptions.TipAlreadyExistsException;

import java.util.List;

/**
 * Soccer service definition
 */
public interface SoccerService {

    /***
     * Creates or updates the user
     * @param user user which is updated
     * @return updated user
     */
    User syncUser(User user);

    /***
     * Creates or updates the given team
     * @param team new team data
     * @return updated team
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
     * @return all teams
     */
    List<Team> findAllTeams();

    /**
     * Creates or updates the given game
     * @return updated game object
     */
    Game syncGame(Game game);

    /***
     * Finds the game with the given id
     * @param id id of the game
     * @return game or null
     */
    Game findGame(Long id);

    /***
     * Prepares the sorted finished games list
     * @return all finished games
     */
    List<Game> getFinishedGamesList();

    /***
     * Prepares the open games list
     * An open game is a game where the result is already set
     * @return all open games
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

    /***
     * Fetches all users
     * @return all users
     */
    List<User> findAllUsers();

    /***
     * Creates or updates the tip
     * @param tip tip which is updated
     * @return updated tip
     * @throws TipAlreadyExistsException when a tip for the given user and game
     *                                   already exists
     */
    Tip addTip(Tip tip) throws TipAlreadyExistsException;
}
