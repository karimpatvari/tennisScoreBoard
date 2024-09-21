package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MATCHES")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PLAYER1", referencedColumnName = "ID", nullable = false)
    private PlayerEntity player1;

    @ManyToOne
    @JoinColumn(name = "PLAYER2", referencedColumnName = "ID", nullable = false)
    private PlayerEntity player2;

    @ManyToOne
    @JoinColumn(name = "WINNER", referencedColumnName = "ID",nullable = false)
    private PlayerEntity winner;

    public MatchEntity(PlayerEntity player1, PlayerEntity player2, PlayerEntity winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
