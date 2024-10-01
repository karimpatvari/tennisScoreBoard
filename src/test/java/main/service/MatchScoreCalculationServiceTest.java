package main.service;

import main.entities.MatchEntity;
import main.entities.PlayerEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    // ---------Points---------------

    @Test
    void shouldIncreasePointsTo15_WhenPointsAre0() {
        // given: Player1 has 0 points
        matchEntity.getPlayer1().setPoints(0);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1 points should increase to 15
        assertEquals(15, matchEntity.getPlayer1().getPoints(), "Player1 points should increase to 15.");
    }

    @Test
    void shouldIncreasePointsTo30_WhenPointsAre15() {
        // given: Player1 has 15 points
        matchEntity.getPlayer1().setPoints(15);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1 points should increase to 30
        assertEquals(30, matchEntity.getPlayer1().getPoints(), "Player1 points should increase to 30.");
    }

    @Test
    void shouldIncreasePointsTo40_WhenPointsAre30() {
        // given: Player1 has 30 points
        matchEntity.getPlayer1().setPoints(30);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1 points should increase to 40
        assertEquals(40, matchEntity.getPlayer1().getPoints(), "Player1 points should increase to 40.");
    }

    @Test
    void shouldWinGame_WhenPointsAre40() {
        // given: Player1 has 40 points and Player2 has 0 points
        matchEntity.getPlayer1().setPoints(40);
        matchEntity.getPlayer2().setPoints(0);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1 should win the game and points reset to 0
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1 points should reset to 0 after winning the game.");
        assertEquals(1, matchEntity.getPlayer1().getGames(), "Player1's games should increase by 1.");
    }

    //---------TieBreak-----------------

    @Test
    void shouldNotIncreaseSets_WhenSetsAreSame() {
        //given: Player's games set to 5 - 5 && Player1 points set to 40
        matchEntity.getPlayer1().setGames(5);
        matchEntity.getPlayer2().setGames(5);
        matchEntity.getPlayer1().setPoints(40);

        //when: Player1 scores a point && wins a game
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        //then: Player1 sets should not be increased like in regular play
        assertEquals(0,matchEntity.getPlayer1().getSets(), "Player1's sets should not increase.");
    }

    @Test
    void shouldSwitchToTieBreak_WhenGamesAre6To6() {
        //given: Player's games set to 6 - 6
        matchEntity.getPlayer1().setGames(6);
        matchEntity.getPlayer2().setGames(6);

        //when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        //then: Player1 points should '1' instead of '15' like in regular play
        assertEquals(1, matchEntity.getPlayer1().getPoints(), "Player1's points should be 1 in tie-break, not 15.");
        assertEquals(6, matchEntity.getPlayer1().getGames(), "Player1's games should remain 6 in tie-break.");
    }

    //---------Deuce-------------------

    @Test
    void shouldSetAdvantage_WhenPlayerScoresAtDeuce() {
        // given: matchEntity set to deuce
        matchEntity.getPlayer1().setPoints(40);
        matchEntity.getPlayer2().setPoints(40);

        // when: player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: player1 points should be 50 (Advantage)
        assertEquals(50, matchEntity.getPlayer1().getPoints(), "Player1 should have advantage (50 points).");
        assertEquals(0, matchEntity.getPlayer1().getGames(), "Player1 games should remain 0 at deuce.");
    }

    @Test
    void shouldResetAdvantage_WhenOpponentScoresAtDeuce() {
        //given: matchEntity set to deuce scenario, with player1 advantage
        matchEntity.getPlayer1().setPoints(50);
        matchEntity.getPlayer2().setPoints(40);

        //when: player2 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player2.getId()));

        //then: matchEntity should be set to Deuce without advantages
        assertEquals(40, matchEntity.getPlayer1().getPoints(), "Player1 points should been set to 40.");
        assertEquals(40, matchEntity.getPlayer2().getPoints(), "Player2 points should been set to 40.");
    }

    //--------Games---------------------

    @Test
    void shouldIncreaseGamesTo1_WhenGamesAre0() {
        // given: Player1 has one point left to win game, and 0 games
        matchEntity.getPlayer1().setGames(0);
        matchEntity.getPlayer1().setPoints(40);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase, and points should reset
        assertEquals(1, matchEntity.getPlayer1().getGames(), "Player1's games should increase to 1.");
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1's points should reset to 0.");
    }

    @Test
    void shouldIncreaseGamesTo2_WhenGamesAre1() {
        // given: Player1 has one point left to win game, and 0 games
        matchEntity.getPlayer1().setGames(1);
        matchEntity.getPlayer1().setPoints(40);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase, and points should reset
        assertEquals(2, matchEntity.getPlayer1().getGames(), "Player1's games should increase to 2.");
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1's points should reset to 0.");
    }

    @Test
    void shouldIncreaseGamesTo3_WhenGamesAre2() {
        // given: Player1 has one point left to win game, and 0 games
        matchEntity.getPlayer1().setGames(2);
        matchEntity.getPlayer1().setPoints(40);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase, and points should reset
        assertEquals(3, matchEntity.getPlayer1().getGames(), "Player1's games should increase to 3.");
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1's points should reset to 0.");
    }

    @Test
    void shouldIncreaseGamesTo4_WhenGamesAre3() {
        // given: Player1 has one point left to win game, and 0 games
        matchEntity.getPlayer1().setGames(3);
        matchEntity.getPlayer1().setPoints(40);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase, and points should reset
        assertEquals(4, matchEntity.getPlayer1().getGames(), "Player1's games should increase to 4.");
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1's points should reset to 0.");
    }

    @Test
    void shouldIncreaseGamesTo5_WhenGamesAre4() {
        // given: Player1 has one point left to win game, and 0 games
        matchEntity.getPlayer1().setGames(4);
        matchEntity.getPlayer1().setPoints(40);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase, and points should reset
        assertEquals(5, matchEntity.getPlayer1().getGames(), "Player1's games should increase to 5.");
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1's points should reset to 0.");
    }

    @Test
    void shouldResetGamesTo0_WhenGamesAre5() {
        // given: Player1 has one point left to win game, and 5 games
        matchEntity.getPlayer1().setGames(5);
        matchEntity.getPlayer1().setPoints(40);

        // when: Player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        // then: Player1's games should increase, and points should reset
        assertEquals(0, matchEntity.getPlayer1().getGames(), "Player1's games should reset to 0.");
        assertEquals(0, matchEntity.getPlayer1().getPoints(), "Player1's points should reset to 0.");
    }

    //----------Sets---------------------

    @Test
    void shouldIncreaseSets_WhenWins6Games() {
        //given: player1 has one point left to win sixth game
        matchEntity.getPlayer1().setGames(5);
        matchEntity.getPlayer1().setPoints(40);

        //when: player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        //then: player1 sets should be increased
        assertEquals(1,matchEntity.getPlayer1().getSets(), "Player1's sets should increase by 1.");
    }

    @Test
    void shouldSetWinner_WhenSetsGetTo2() {
        //given: player1 sets set to 1, and has one point left to win another set
        matchEntity.getPlayer1().setSets(1);
        matchEntity.getPlayer1().setGames(5);
        matchEntity.getPlayer1().setPoints(40);

        //when: player1 scores a point
        matchScoreCalculationService.calculateMatch(matchEntity, String.valueOf(player1.getId()));

        //then: winner should be set
        assertTrue(matchEntity.isWinnerAssigned(), "Winner should be set");
        assertNotNull(matchEntity.getWinner(), "Winner should not be null");
    }
}