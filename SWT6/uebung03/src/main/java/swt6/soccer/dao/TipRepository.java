package swt6.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import swt6.soccer.domain.Tip;

/**
 * Bet JPA repository
 */
public interface TipRepository extends JpaRepository<Tip,Long> {
    Tip findByGameIdAndUserId(Long gameId, Long userId);
}
