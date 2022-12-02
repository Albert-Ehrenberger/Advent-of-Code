package day02;

public final class PointsCalculator2 {

    static final private String ROCK_OPPONENT = "A";
    static final private String PAPER_OPPONENT = "B";
    static final private String SCISSORS_OPPONENT = "C";
    static final private String MUST_LOSE = "X";
    static final private String MUST_DRAW = "Y";
    static final private String MUST_WIN = "Z";
    static final private int ROCK_YOU = 1;
    static final private int PAPER_YOU = 2;
    static final private int SCISSORS_YOU = 3;

    private PointsCalculator2() {

    }

    public static int calculateRoundPoints(String parameters) {
        String[] parametersArray = parameters.split(" ");
        String opponentsHand = parametersArray[0];
        String requiredOutcome = parametersArray[1];
        int roundPoints = 0;
        switch (requiredOutcome) {
            case MUST_LOSE -> roundPoints += 0;
            case MUST_DRAW -> roundPoints += 3;
            case MUST_WIN -> roundPoints += 6;
        }
        roundPoints += determineHand(opponentsHand, requiredOutcome);
        return roundPoints;
    }

    private static int determineHand(String opponentsHand, String requiredOutcome) {
        int points = 0;
        switch (opponentsHand) {
            case ROCK_OPPONENT -> {
                switch (requiredOutcome) {
                    case MUST_LOSE -> points = SCISSORS_YOU;
                    case MUST_DRAW -> points = ROCK_YOU;
                    case MUST_WIN -> points = PAPER_YOU;
                }
            }
            case PAPER_OPPONENT -> {
                switch (requiredOutcome) {
                    case MUST_LOSE -> points = ROCK_YOU;
                    case MUST_DRAW -> points = PAPER_YOU;
                    case MUST_WIN -> points = SCISSORS_YOU;
                }
            }
            case SCISSORS_OPPONENT -> {
                switch (requiredOutcome) {
                    case MUST_LOSE -> points = PAPER_YOU;
                    case MUST_DRAW -> points = SCISSORS_YOU;
                    case MUST_WIN -> points = ROCK_YOU;
                }
            }
        }
        return points;
    }

}
