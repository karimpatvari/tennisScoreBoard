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
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PLAYER1", referencedColumnName = "ID", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "PLAYER2", referencedColumnName = "ID", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "WINNER", referencedColumnName = "ID",nullable = false)
    private Player winner;

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
