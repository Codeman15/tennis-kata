public class Game {

    private String playerOneName;
    private String playerTwoName;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    public Game(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public void playerOneWinsPoint() {
        playerOneScore++;
    }

    public void playerTwoWinsPoint() {
        playerTwoScore++;
    }

    public String score() {
        if (gameWasWon()) {
            return gameWonBy();
        } else if (scoreHasReachedDeuce()) {
            return playerOneScore ==  playerTwoScore ? "Deuce" : advantagePlayer();
        } else if (playerOneScore == playerTwoScore) {
            return playerScoreToString(playerOneScore) + " All";
        } else {
            return playerScoreToString(playerOneScore) + " " + playerScoreToString(playerTwoScore);
        }
    }

    private String playerScoreToString(int playerScore) {
        switch (playerScore) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        throw new IllegalArgumentException("Invalid score to convert");
    }

    private boolean gameWasWon() {
        if (playerOneScore >= 4 && playerOneScore - playerTwoScore >= 2) {
            return true;
        } else if (playerTwoScore >= 4 && playerTwoScore - playerOneScore >= 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean scoreHasReachedDeuce() {
        return playerOneScore >= 3 && playerTwoScore >= 3;
    }

    private String gameWonBy() {
        return playerOneScore > playerTwoScore ? "Game " + playerOneName : "Game " + playerTwoName;
    }

    private String advantagePlayer() {
        return playerOneScore > playerTwoScore ? "Advantage " + playerOneName : "Advantage " + playerTwoName;
    }
}
