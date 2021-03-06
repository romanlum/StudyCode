package swt6.soccer.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TEAM_A_ID")
    private Team teamA;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TEAM_B_ID")
    private Team teamB;

    @Min(0)
    private int goalsA;

    @Min(0)
    private int goalsB;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;

    private boolean finished;
}
