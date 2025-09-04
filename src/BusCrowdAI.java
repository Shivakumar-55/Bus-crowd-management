import java.util.Scanner;

public class BusCrowdAI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask bus capacity
        System.out.print("Enter bus seating capacity: ");
        int capacity = sc.nextInt();

        int standingAllowed = (int) (capacity * 0.4); // 40% standing
        int maxCapacity = capacity + standingAllowed;

        int currentPassengers = 0;

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Passenger Get In (starting point)");
            System.out.println("2. Passenger Get Out");
            System.out.println("3. Show Bus Status");
            System.out.println("4. Mid-passenger tries to enter");
            System.out.println("5. Exit Program");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("How many passengers are entering? ");
                    int entering = sc.nextInt();
                    if (currentPassengers + entering > maxCapacity) {
                        currentPassengers = maxCapacity;
                        System.out.println("Bus is overcrowded! Only " + maxCapacity + " passengers can stay.");
                        aiSuggestion();
                    } else {
                        currentPassengers += entering;
                        System.out.println(entering + " passengers entered.");
                    }
                    showStatus(currentPassengers, capacity, maxCapacity);
                    break;

                case 2:
                    System.out.print("How many passengers are leaving? ");
                    int leaving = sc.nextInt();
                    if (leaving > currentPassengers) {
                        System.out.println("Not enough passengers inside to exit!");
                    } else {
                        currentPassengers -= leaving;
                        System.out.println(leaving + " passengers exited.");
                    }
                    showStatus(currentPassengers, capacity, maxCapacity);
                    break;

                case 3:
                    showStatus(currentPassengers, capacity, maxCapacity);
                    break;

                case 4: // Mid-passenger AI check
                    System.out.println("A passenger at a bus stop asks: Can I enter?");
                    if (currentPassengers < maxCapacity) {
                        System.out.println("✅ Yes, you can enter the bus.");
                        if (currentPassengers >= capacity) {
                            System.out.println("Note: You may have to stand.");
                        }
                        currentPassengers++;
                    } else {
                        System.out.println("❌ Sorry, the bus is full. Better wait for the next bus or take metro/auto.");
                        aiSuggestion();
                    }
                    showStatus(currentPassengers, capacity, maxCapacity);
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    // Show status function
    public static void showStatus(int current, int seated, int max) {
        int standing = Math.max(0, current - seated);
        double percentage = (current * 100.0) / max;

        System.out.println("Current passengers: " + current + "/" + max);
        System.out.printf("Bus is %.1f%% full.\n", percentage);

        if (standing > 0) {
            System.out.println("Note: " + standing + " passengers are standing.");
        } else {
            System.out.println("All passengers are seated.");
        }
    }

    // AI suggestion function
    public static void aiSuggestion() {
        System.out.println("🤖 AI Suggestion: The bus is too crowded.");
        System.out.println("👉 Please wait for the next bus, try metro, or take an auto/taxi.");
    }
}
