import spock.lang.Specification

class TennisSpec extends Specification {

    def game = new Game("bob", "ted")

    def 'can add player names'() {
        expect:
        game.playerOneName == "bob"
        game.playerTwoName == "ted"
    }

    def 'when a game is created the score is love all'() {
        expect:
        game.score() == "Love All"
    }

    def 'game returns the correct score to love'() {
        when:
        setScore(playerOnePoints, playerTwoPoints)

        then:
        game.score() == score

        where:
        playerOnePoints | playerTwoPoints || score
        0               | 0               || "Love All"
        1               | 0               || "Fifteen Love"
        2               | 0               || "Thirty Love"
        3               | 0               || "Forty Love"
        0               | 1               || "Love Fifteen"
        0               | 2               || "Love Thirty"
        0               | 3               || "Love Forty"
    }

    def 'game returns the correct score for deuce/adv'() {
        when:
        setScore(playerOnePoints, playerTwoPoints)

        then:
        game.score() == score

        where:
        playerOnePoints | playerTwoPoints || score
        3               | 3               || "Deuce"
        4               | 3               || "Advantage bob"
        3               | 4               || "Advantage ted"
        5               | 5               || "Deuce"
    }

    def 'game can be won'() {
        when:
        setScore(playerOnePoints, playerTwoPoints)

        then:
        game.score() == score

        where:
        playerOnePoints | playerTwoPoints || score
        4               | 0               || "Game bob"
        0               | 4               || "Game ted"
        6               | 4               || "Game bob"
        11              | 13              || "Game ted"
    }

    def 'playerScoreToString can throw exception'() {
        when:
        game.playerScoreToString(4)

        then:
        def ex = thrown(Exception)
        ex.message == "Invalid score to convert"

    }

    private void setScore(int playerOneScore, int playerTwoScore) {
        for (int i = 0; i < playerOneScore; i++) {
            game.playerOneWinsPoint()
        }
        for (int i = 0; i < playerTwoScore; i++) {
            game.playerTwoWinsPoint()
        }
    }
}