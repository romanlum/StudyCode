package swt6.soccer.logic.exceptions;

import lombok.*;

/**
 * Exception when a game is not available
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameNotFoundException extends Exception {
    private long gameId;
}
