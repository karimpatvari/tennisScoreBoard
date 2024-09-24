package main.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "PLAYERS")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Transient
    private Integer points = 0;

    @Transient
    private Integer games = 0;

    @Transient
    private Integer sets = 0;

    public PlayerEntity(String name) {
        this.name = name;
    }

    public void winGame() {
        this.games++;
    }

    public void winSet() {
        this.sets++;
    }

    public void resetAdvantage() {
        this.points = 40;
    }

    public void addPoint() {
        switch (this.points) {
            case 0:
                this.points = 15;
                break;
            case 15:
                this.points = 30;
                break;
            case 30:
                this.points = 40;
                break;
            case 40:
                this.points = 50;
                break;
        }
    }

    public void addTieBreakPoint() {
        switch (this.points) {
            case 0:
                this.points = 1;
                break;
            case 1:
                this.points = 2;
                break;
            case 2:
                this.points = 3;
                break;
            case 3:
                this.points = 4;
                break;
            case 4:
                this.points = 5;
                break;
            case 5:
                this.points = 6;
                break;
            case 6:
                this.points = 7;
                break;
        }
    }
}
