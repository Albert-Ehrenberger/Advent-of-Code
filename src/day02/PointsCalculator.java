package day02;

public final class PointsCalculator {

    static final private String ROCK_OPPONENT = "A";
    static final private String PAPER_OPPONENT = "B";
    static final private String SCISSORS_OPPONENT = "C";
    static final private String ROCK_YOU = "X";
    static final private String PAPER_YOU = "Y";
    static final private String SCISSORS_YOU = "Z";
    static final int ROUND_WON = 6;
    static final int ROUND_LOST = 0;
    static final int ROUND_DRAW = 3;

    private PointsCalculator() {

    }

    public static int calculateRoundPoints(String hands) {
        String[] handsArray = hands.split(" ");
        String opponentsHand = handsArray[0];
        String yourHand = handsArray[1];
        int roundPoints = 0;
        switch (yourHand) {
            case ROCK_YOU -> roundPoints += 1;
            case PAPER_YOU -> roundPoints += 2;
            case SCISSORS_YOU -> roundPoints += 3;
        }
        roundPoints += calculateRoundOutcome(opponentsHand, yourHand);
        return roundPoints;
    }

    private static int calculateRoundOutcome(String opponentsHand, String yourHand) {
        int points = 0;
        switch (opponentsHand) {
            case ROCK_OPPONENT -> {
                switch (yourHand) {
                    case ROCK_YOU -> points = ROUND_DRAW;
                    case PAPER_YOU -> points = ROUND_WON;
                    case SCISSORS_YOU -> points = ROUND_LOST;
                }
            }
            case PAPER_OPPONENT -> {
                switch (yourHand) {
                    case ROCK_YOU -> points = ROUND_LOST;
                    case PAPER_YOU -> points = ROUND_DRAW;
                    case SCISSORS_YOU -> points = ROUND_WON;
                }
            }
            case SCISSORS_OPPONENT -> {
                switch (yourHand) {
                    case ROCK_YOU -> points = ROUND_WON;
                    case PAPER_YOU -> points = ROUND_LOST;
                    case SCISSORS_YOU -> points = ROUND_DRAW;
                }
            }
        }
        return points;
    }

}
