package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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

    @Transient
    private UUID matchId;

    @Transient
    private boolean isWinnerAssigned;

    public MatchEntity(PlayerEntity player1, PlayerEntity player2, PlayerEntity winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public MatchEntity(UUID matchId, PlayerEntity player1, PlayerEntity player2) {
        this.matchId = matchId;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;
        this.isWinnerAssigned = false;
    }

    public void resetPoints(){
        this.player1.setPoints(0);
        this.player2.setPoints(0);
    }

    public void resetGames(){
        this.player1.setGames(0);
        this.player2.setGames(0);
        resetPoints();
    }

    public void assignWinner(PlayerEntity winner){
        if (winner.getSets() == 2){
            this.winner = winner;
            isWinnerAssigned = true;
        }
    }
}
