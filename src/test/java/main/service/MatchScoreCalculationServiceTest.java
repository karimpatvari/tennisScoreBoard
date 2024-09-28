package main.service;

import main.entities.MatchEntity;
import main.entities.PlayerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class MatchScoreCalculationServiceTest {

    static PlayerEntity player1;
    static PlayerEntity player2;
    static MatchEntity matchEntity;
    static MatchScoreCalculationService matchScoreCalculationService;

    @BeforeAll
    static void setUp() {
        matchScoreCalculationService = new MatchScoreCalculationService();
    }

    @BeforeEach
    void matchAndPlayersReset() {
        player1 = new PlayerEntity("John");
        player1.setId(1);

        player2 = new PlayerEntity("Victor");
        player2.setId(2);

        UUID uuid = UUID.randomUUID();
        matchEntity = new MatchEntity(uuid, player1, player2);
    }

    @Test
    void shouldNotIncreaseGames_WhenDeuceScenario() {
        // given: matchEntity set to deuce
        setMatchScoreToDeuce(matchEntity);

        // when: player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: player1 games should not increase and stay at 0
        Assertions.assertEquals(0, matchEntity.getPlayer1().getGames(), "Player1 games should remain 0 at deuce.");
    }

    @Test
    void shouldSetAdvantage_WhenPlayerScoresAtDeuce() {
        // given: matchEntity set to deuce
        setMatchScoreToDeuce(matchEntity);

        // when: player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: player1 points should be 50 (Advantage)
        Assertions.assertEquals(50, matchEntity.getPlayer1().getPoints(), "Player1 should have advantage (50 points).");
    }

    @Test
    void shouldIncreasePlayerGames_WhenPlayerWinsGame() {
        // given: Player1 has 40 points and Player2 has 0 points
        matchEntity.getPlayer1().setPoints(40);
        matchEntity.getPlayer2().setPoints(0);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase
        Assertions.assertEquals(1, matchEntity.getPlayer1().getGames(), "Player1's games should increase by 1.");
    }

    @Test
    void shouldNotIncreaseSets_WhenSetsAreSame() {
        //given: Player's games set to 5 - 5 && Player1 points set to 40
        matchEntity.getPlayer1().setGames(5);
        matchEntity.getPlayer2().setGames(5);
        matchEntity.getPlayer1().setPoints(40);

        //when: Player1 scores a point && wins a game
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        //then: Player1 sets should not be increased like in regular play
        Assertions.assertEquals(0,matchEntity.getPlayer1().getSets(), "Player1's sets should not increase.");
    }

    @Test
    void shouldSwitchToTieBreak_WhenGamesAre6To6() {
        //given: Player's games set to 6 - 6
        matchEntity.getPlayer1().setGames(6);
        matchEntity.getPlayer2().setGames(6);

        //when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        //then: Player1 points should '1' instead of '15' like in regular play
        Assertions.assertEquals(1, matchEntity.getPlayer1().getPoints(), "Player1's points should be 1 in tie-break, not 15.");
        Assertions.assertEquals(6, matchEntity.getPlayer1().getGames(), "Player1's games should remain 6 in tie-break.");
    }

    private void setMatchScoreToDeuce(MatchEntity matchEntity) {
        matchEntity.getPlayer1().setPoints(40);
        matchEntity.getPlayer2().setPoints(40);
    }
}