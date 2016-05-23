package swt6.soccer.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Game entity
 */
@Entity
@Data
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_A_ID")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "TEAM_B_ID")
    private Team teamB;

    private int goalsA;
    private int goalsB;

    private LocalDate date;
}
