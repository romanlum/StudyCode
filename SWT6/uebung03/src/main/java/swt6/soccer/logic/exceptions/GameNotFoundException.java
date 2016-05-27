package swt6.soccer.logic.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Exception when a game is not available
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameNotFoundException extends Exception {
    private long gameId;
}
