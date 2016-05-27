package swt6.soccer.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Bet entity
 */
@Entity
@Data
public class Tip {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private int tipGoalsA;
    private int tipGoalsB;

    @Transient
    public boolean isCorrect() {
        return game != null && game.getGoalsA() == tipGoalsA &&
                game.getGoalsB() == tipGoalsB;
    }




}