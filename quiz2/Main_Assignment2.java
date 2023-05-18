import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class Main_Assignment2 {
    public static void main(String[] args) {
        String name = "";
        int age = 0;

        if (args.length == 2) {
            name = args[0];
            try {
                age = Integer.parseInt(args[1]);
                validateAge(age);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Age must be an integer.");
                System.exit(1);
            } catch (InvalidAgeException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name: ");
            name = scanner.nextLine();

            while (true) {
                System.out.print("Enter age: ");
                String ageInput = scanner.nextLine();
                try {
                    age = Integer.parseInt(ageInput);
                    validateAge(age);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age. Age must be an integer.");
                } catch (InvalidAgeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    private static void validateAge(int age) throws InvalidAgeException {
        if (age < 18 || age >= 60) {
            throw new InvalidAgeException("Invalid age. Age must be between 18 and 59 (inclusive).");
        }
    }
}
