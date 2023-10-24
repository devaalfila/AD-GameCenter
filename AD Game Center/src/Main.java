import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static ArrayList<Game> gameList = new ArrayList<>();
    static int gameIdCounter = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("AD Game Center");
        System.out.println("==============");
        int choice;
        
        do {
            System.out.println("1. Add Game");
            System.out.println("2. Buy Game");
            System.out.println("3. View Game");
            System.out.println("4. Remove Game");
            System.out.println("5. Exit");
            System.out.print("Choose [1 | 2 | 3 | 4 | 5]: ");
            
            choice = scan.nextInt();
            scan.nextLine();  
            
            switch (choice) {
                case 1:
                    addGame(scan);
                    break;
                case 2:
                    buyGame(scan);
                    break;
                case 3:
                    viewGames();
                    break;
                case 4:
                    removeGame(scan);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private static void addGame(Scanner scan) {
        String title, genre, synopsis;
        int price;

        System.out.println("Add Game");
        
        System.out.print("Insert game title [max. 40 characters]: ");
        title = scan.nextLine();
        
        System.out.print("Select game genre [RPG | Casual | Puzzle]: ");
        genre = scan.nextLine();

        System.out.print("Input game synopsis [min. 10 characters]: ");
        synopsis = scan.nextLine();

        System.out.print("Input game price [50000 - 650000]: ");
        price = scan.nextInt();
        scan.nextLine();  

        if (genre.equals("RPG") || genre.equals("Casual") || genre.equals("Puzzle")) {
            if (synopsis.length() >= 10 && title.length() <= 40 && price >= 50000 && price <= 650000) {
                String gameId = "AD" + String.format("%03d", gameIdCounter++);
                Game newGame = new Game(gameId, title, genre, synopsis, price);
                gameList.add(newGame);
                System.out.println("Success! Added game with ID: " + gameId);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } else {
            System.out.println("Invalid genre. Please try again.");
        }
    }

    private static void buyGame(Scanner scan) {
        if (gameList.isEmpty()) {
            System.out.println("No games available for purchase.");
            return;
        }

        System.out.println("Buy Game");
        for (int i = 0; i < gameList.size(); i++) {
            System.out.println((i + 1) + ". " + gameList.get(i));
        }

        System.out.print("Select a game to buy (1 - " + gameList.size() + "): ");
        int selectedGameIndex = scan.nextInt() - 1;

        if (selectedGameIndex < 0 || selectedGameIndex >= gameList.size()) {
            System.out.println("Invalid game selection.");
            return;
        }

        System.out.print("Enter the quantity to buy: ");
        int quantity = scan.nextInt();
        scan.nextLine();  

        if (quantity < 1) {
            System.out.println("Invalid quantity.");
            return;
        }

        Game selectedGame = gameList.get(selectedGameIndex);
        int totalCost = selectedGame.getPrice() * quantity;
        System.out.println("Transaction details:");
        System.out.println("Game ID: " + selectedGame.getId());
        System.out.println("Title: " + selectedGame.getTitle());
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: " + totalCost);
    }

    private static void viewGames() {
        if (gameList.isEmpty()) {
            System.out.println("No games available.");
            return;
        }

        System.out.println("List of Games:");
        for (int i = 0; i < gameList.size(); i++) {
            System.out.println(gameList.get(i));
        }
    }

    private static void removeGame(Scanner scan) {
        if (gameList.isEmpty()) {
            System.out.println("No games available to remove.");
            return;
        }

        System.out.println("Remove Game");
        for (int i = 0; i < gameList.size(); i++) {
            System.out.println((i + 1) + ". " + gameList.get(i));
        }

        System.out.print("Select a game to remove (1 - " + gameList.size() + "): ");
        int selectedGameIndex = scan.nextInt() - 1;
        scan.nextLine();  

        if (selectedGameIndex < 0 || selectedGameIndex >= gameList.size()) {
            System.out.println("Invalid game selection.");
            return;
        }

        Game removedGame = gameList.remove(selectedGameIndex);
        System.out.println("Removed game: " + removedGame.getTitle());
    }
}

class Game {
    private String id;
    private String title;
    private String genre;
    private String synopsis;
    private int price;

    public Game(String id, String title, String genre, String synopsis, int price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.synopsis = synopsis;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Game ID: " + id + ", Title: " + title + ", Genre: " + genre + ", Price: " + price;
    }
}
