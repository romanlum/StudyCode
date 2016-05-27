package swt6.soccer.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity
 */
@Entity
@Data
@EqualsAndHashCode(exclude = "tips")
@ToString(exclude = "tips")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Tip> tips = new HashSet<>();

    @Transient
    public long getPoints() {
        return tips.stream().filter(x -> x.isCorrect()).count();
    }

    public User(String name,String email){
        this.name=name;
        this.email=email;
    }

    public User() {
    }
}
