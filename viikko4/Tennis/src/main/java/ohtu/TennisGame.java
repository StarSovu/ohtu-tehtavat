package ohtu;

import static java.lang.Math.abs;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        String score = "";
        if (player1Score==player2Score) {
            score = draw(player1Score);
        }
        else if (player1Score>=4 || player2Score>=4) {
            score = scoresOverFour(player1Score, player2Score);
        }
        else {
            score = scoreName(player1Score) + "-" + scoreName(player2Score);
        }
        return score;
    }
    
    private String scoreName(int score) {
        if (score == 0) {
            return "Love";
        } else if (score == 1) {
            return "Fifteen";
        } else if (score == 2) {
            return "Thirty";
        } else if (score == 3) {
            return "Forty";
        } else {
            return "";
        }
    }
    
    private String draw(int score) {
        if (score < 4) {
            return scoreName(score) + "-All";
        } else {
            return "Deuce";
        }
    }
    
    private String scoresOverFour(int score1, int score2) {
        String score = "";
        if (abs(score1 - score2) == 1) {
            score = "Advantage ";
        } else {
            score = "Win for ";
        }
        if (score1 > score2) {
            score += "player1";
        } else {
            score += "player2";
        }
        return score;
    }
    
}