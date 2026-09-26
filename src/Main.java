import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();
        Scanner sc = new Scanner(System.in);

        manager.addMovie(new Movie("Inception", 148, 250.50, true));
        manager.addMovie(new Movie("3 Idiots", 170, 200.00, false));

        boolean running = true;

        while (running) {
            System.out.println("\n--- CineBook ---");
            System.out.println("1. Add movie");
            System.out.println("2. List all");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Now showing");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Duration: ");
                    int duration = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Now showing (true/false): ");
                    boolean showing = sc.nextBoolean();
                    sc.nextLine();
                    manager.addMovie(new Movie(title, duration, price, showing));
                    break;

                case 2:
                    manager.listAll();
                    break;

                case 3:
                    System.out.print("Title to search: ");
                    try {
                        Movie found = manager.findByTitle(sc.nextLine());
                        found.printDetails();
                    } catch (MovieNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Title to delete: ");
                    try {
                        manager.deleteByTitle(sc.nextLine());
                    } catch (MovieNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    manager.listNowShowing();
                    break;

                case 6:
                    running = false;
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}