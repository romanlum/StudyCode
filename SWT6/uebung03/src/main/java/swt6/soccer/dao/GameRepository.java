package swt6.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import swt6.soccer.domain.Game;

import java.util.List;

/**
 * Game JPA repository
 */
public interface GameRepository extends JpaRepository<Game,Long> {

    /**
     * Fetches all games ordered by date desc
     * @return list of games
     */
    List<Game> findByFinishedOrderByDateDesc(boolean finished);

}
