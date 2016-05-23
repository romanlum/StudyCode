package swt6.soccer.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Bet entity
 */
@Entity
@Data
public class Bet {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "NAME_ID")
    private Game game;

    private int betGoalsA;
    private int betGoalsB;




}
