package swt6.soccer.logic.exceptions;

import lombok.*;
import swt6.soccer.domain.Game;
import swt6.soccer.domain.User;

/**
 * Exception when a tip for a given user and game
 * already exists
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipAlreadyExistsException extends Exception {

    private Game game;
    private User user;
}
