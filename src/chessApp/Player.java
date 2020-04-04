package chessApp;

import java.util.Scanner;

class Player {
    private String name;
    private Color color;
    private final Scanner scanner;

    Player(String name, Color color) {
        scanner = new Scanner(System.in);
        this.name = name;
        this.color = color;
    }

    public int[][] getPlayerTurn() {
        int[][] playerTurn = new int[2][2];

        String sourceCoordinates = getSourceCoordinatesFromPlayer();
        playerTurn[0] = validateAndAssignCoordinates(sourceCoordinates);

        String destinationCoordinates = getDestinationCoordinatesFromPlayer();
        playerTurn[1] = validateAndAssignCoordinates(destinationCoordinates);

        return playerTurn;
    }

    private String getSourceCoordinatesFromPlayer() {
        System.out.print(name + ", input your location to move from. (EX: E4)\n>> ");

        return scanner.nextLine().trim();
    }

    private String getDestinationCoordinatesFromPlayer() {
        System.out.print(name + ", input you location to move to. (EX: E4)\n>> ");

        return scanner.nextLine().trim();
    }

    private int[] validateAndAssignCoordinates(String position) {
        if (coordinatesAreValid(position)) {
            return assignCoordinates(position);
        } else {
            System.out.println("The coordinates are not valid.");
            return new int[]{-1, -1};
        }
    }

    private boolean coordinatesAreValid(String source) {
        return playerInputHasCorrectLength(source) && charactersAreValid(source);
    }

    private boolean playerInputHasCorrectLength(String playerInput) {
        return playerInput.length() == 2;
    }

    private boolean charactersAreValid(String userInput) {
        return Character.isLetter(userInput.charAt(0)) && Character.isDigit(userInput.charAt(1));
    }

    private int[] assignCoordinates(String position) {
        int[] positionCoordinates = convertCoordinates(position);

        if (conversionIsValid(positionCoordinates)) {
            flipValueOfRow(positionCoordinates);
            return positionCoordinates;
        } else {
            System.out.println("The conversion is not valid.");
            return new int[]{-1, -1};
        }
    }

    private int[] convertCoordinates(String position) {
        int column = convertColumnCharacterToInt(position.toUpperCase().charAt(0));
        int row = convertRowCharacterToInt(position.charAt(1));
        
        return new int[]{column, row};
    }

    private int convertColumnCharacterToInt(char charToConvert) {
        int convertedNumber = -1;

        for (int i = 0; i < Board.COLUMNS.length; i++) {
            if (Board.COLUMNS[i] == charToConvert) {
                convertedNumber = i;
                break;
            }
        }

        return convertedNumber;
    }

    private int convertRowCharacterToInt(char numberToConvert) {
        int convertedNumber = -1;
        int number = Character.getNumericValue(numberToConvert);

        for (int i : Board.ROWS) {
            if (i == number) {
                convertedNumber = number;
                break;
            }
        }

        return convertedNumber;
    }

    private boolean conversionIsValid(int[] coordinates) {
        return rowIsValid(coordinates[0]) && columnIsValid(coordinates[1]);
    }

    private void flipValueOfRow(int[] coordinates) {
        final int BOARD_BOUNDARY = 8;
        coordinates[1] = BOARD_BOUNDARY - coordinates[1];
    }

    private boolean rowIsValid(int row) {
        return row != -1;
    }

    private boolean columnIsValid(int column) {
        return column != -1;
    }

}
