import java.util.*;

public class BattleshipsGame {
    private static final int GRID_SIZE = 10;
    private static final int MAX_SHOTS = 10;

    private static final Map<String, Ship> ships = new LinkedHashMap<>();
    static {
        ships.put("Aircraft", new Ship(5, 3));
        ships.put("Battleship", new Ship(4, 4));
        ships.put("Submarine", new Ship(3, 6));
        ships.put("Destroyer", new Ship(2, 8));
        ships.put("Patrol Boat", new Ship(1, 10));
    }

    private static final Random random = new Random();
    private static final Set<String> shots = new HashSet<>();
    private static final Set<String> hits = new HashSet<>();
    private static int totalPoints = 0;

    public static void main(String[] args) {
        initializeShips();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Battleships!");

        int shotsRemaining = MAX_SHOTS;
        while (shotsRemaining > 0 && !allShipsSunk()) {
            System.out.println("Shots Remaining: " + shotsRemaining);
            System.out.print("Enter the square you want to shoot at (e.g., A3): ");
            String square = scanner.nextLine().toUpperCase();

            if (shots.contains(square)) {
                System.out.println("You have already shot at this square. Try again.");
                continue;
            }

            shots.add(square);
            shotsRemaining--;

            if (hits.contains(square)) {
                System.out.println("You hit a ship!");
                int points = getPointsForShipAt(square);
                totalPoints += points;
                System.out.println("Ship sunk! It was worth " + points + " points.");
            } else {
                System.out.println("You missed.");
            }
        }

        System.out.println("\nGame Over!");

        if (allShipsSunk()) {
            System.out.println("Congratulations! You sunk all the ships.");
        } else {
            System.out.println("You ran out of shots. Better luck next time!");
        }

        System.out.println("Ships shot: " + hits.size());
        System.out.println("Total points: " + totalPoints);
    }

    private static void initializeShips() {
        Set<String> occupiedSquares = new HashSet<>();
        for (String shipName : ships.keySet()) {
            Ship ship = ships.get(shipName);
            boolean shipPlaced = false;

            while (!shipPlaced) {
                int startX = random.nextInt(GRID_SIZE);
                int startY = random.nextInt(GRID_SIZE);
                boolean isHorizontal = random.nextBoolean();

                if (canPlaceShip(ship, startX, startY, isHorizontal, occupiedSquares)) {
                    for (int i = 0; i < ship.size; i++) {
                        String square = convertToSquare(startX, startY);
                        occupiedSquares.add(square);

                        if (isHorizontal) {
                            startX++;
                        } else {
                            startY++;
                        }
                    }
                    shipPlaced = true;
                }
            }
        }
    }

    private static boolean canPlaceShip(Ship ship, int startX, int startY, boolean isHorizontal, Set<String> occupiedSquares) {
        int endX = startX + (isHorizontal ? ship.size : 1);
        int endY = startY + (isHorizontal ? 1 : ship.size);

        if (endX > GRID_SIZE || endY > GRID_SIZE) {
            return false;
        }

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                String square = convertToSquare(x, y);
                if (occupiedSquares.contains(square)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String convertToSquare(int x, int y) {
        return String.valueOf((char) ('A' + y)) + (x + 1);
    }

    private static boolean allShipsSunk() {
        return hits.size() == ships.size();
    }

    private static int getPointsForShipAt(String square) {
        for (String shipName : ships.keySet()) {
            Ship ship = ships.get(shipName);
            int startX = square.charAt(1) - '1';
            int startY = square.charAt(0) - 'A';

            boolean isHorizontal = ship.size > 1 && (square.charAt(0) == square.charAt(2));
            int endX = startX + (isHorizontal ? ship.size : 1);
            int endY = startY + (isHorizontal ? 1 : ship.size);

            if (startX >= 0 && startY >= 0 && endX <= GRID_SIZE && endY <= GRID_SIZE) {
                boolean shipFound = true;
                for (int x = startX; x < endX; x++) {
                    for (int y = startY; y < endY; y++) {
                        String currentSquare = convertToSquare(x, y);
                        if (!hits.contains(currentSquare)) {
                            shipFound = false;
                            break;
                        }
                    }
                    if (!shipFound) {
                        break;
                    }
                }

                if (shipFound) {
                    hits.addAll(getShipSquares(startX, startY, isHorizontal, ship.size));
                    return ship.points;
                }
            }
        }
        return 0;
    }

    private static List<String> getShipSquares(int startX, int startY, boolean isHorizontal, int size) {
        List<String> squares = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String square = convertToSquare(startX, startY);
            squares.add(square);

            if (isHorizontal) {
                startX++;
            } else {
                startY++;
            }
        }
        return squares;
    }

    private static class Ship {
        private int size;
        private int points;

        public Ship(int size, int points) {
            this.size = size;
            this.points = points;
        }
    }
}
