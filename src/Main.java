import java.util.Scanner;

public class Main {
	public static Customer[] customers = new Customer[3];
	public static Casino[] casinos = new Casino[3];
	public static int cusCount = 0;
	public static int casCount = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do {
			displayMenu(); // show menu contents
			int menuChoice = getInput(); // get input choice
			getAction(menuChoice); // get the action that is to be performed from input choice
		} while (true); // will constantly return to the main menu until exited

	}

	public static void displayMenu() {
		String mainMenu = ("1. Create a new customer\n" + "2. Create a new Casino\n" + "3. Change a customer's name\n" // main
																														// menu
																														// contents
				+ "4. Change a casino's name\n" + "5. Play games as a customer\n" + "6. Display customer details\n"
				+ "7. Display casino details\n" + "8. Exit");
		System.out.println("\n         Main Menu");
		System.out.println("---------------------------------");
		System.out.println(mainMenu);
	}

	public static int getInput() { // return menu choice
		Scanner kb = new Scanner(System.in);
		int menuChoice = -1;
		while (menuChoice < 0 || menuChoice > 9) {
			System.out.println("\nEnter your choice: ");
			menuChoice = kb.nextInt();
		}
		return menuChoice;
	}

	public static void getAction(int n) { // gets proper action from menu choice
		Scanner sc = new Scanner(System.in);
		int subMenu;

		switch (n) {
		case 1:
			String cusName;
			int cusBalance;
			if (cusCount == 3) { // prevent indexoutofbounds exception from array
				System.out.println("No more customers can be made! \nPress anything to exit!\n");
				String temp = sc.next();

				break;
			} else {
				System.out.println("Only three customers can be made!\n");
				System.out.println("Enter the name of the customer: ");
				cusName = sc.nextLine();
				System.out.println("Enter the balance of the customer: ");
				cusBalance = sc.nextInt();

				customers[cusCount] = new Customer(cusName, cusBalance);
				cusCount++; // increases the amount for to create the next one correctly
				break;
			}
		case 2:
			String casName;
			int casBalance;
			if (casCount == 3) { // prevent indexoutofbounds exception from array
				System.out.println("No more casinos can be made! \nPress anything to exit!\n");
				String temp = sc.next();

				break;
			} else {
				System.out.println("Only three casinos can be made!\n");
				System.out.println("Name of casino: ");
				casName = sc.nextLine();
				System.out.println("Balance of casino: ");
				casBalance = sc.nextInt();

				casinos[casCount] = new Casino(casName, casBalance); // creates new casino and adds to array
				casCount++; // increases the amount for to create the next one correctly
				break;
			}
		case 3:
			String newCusName;

			displayCustomers(customers);
			if (customers.length == 0) { // if there are no customers to edit return to menu
				System.out.print("\nEnter anything to exit!");
				String temp = sc.next();

				break;
			} else { // else edit name of customer
				System.out.println("Which customer would you like to change the name of?");
				subMenu = sc.nextInt();
				sc.nextLine();
				System.out.println("What should the new name be?");
				newCusName = sc.nextLine();

				customers[subMenu - 1].setName(newCusName);
				break;
			}
		case 4:
			String newCasName;

			displayCasinos(casinos);
			if (casinos.length == 0) { // if there are no casinos to edit return to menu
				System.out.println("\nEnter anything to exit!\n");
				String temp = sc.next();

				break;
			} else { // else edit casino name
				System.out.println("Which casino would you like to change the name of?");
				subMenu = sc.nextInt();
				sc.nextLine();
				System.out.println("What should the new name be?");
				newCasName = sc.nextLine();

				Casino temp = casinos[subMenu - 1];

				if (temp.getBalance() - 15000 < 0) { // if they do not have enough money to change name print error
					System.out.println("Casino does not have sufficient funds for a name change! ($15,000)");
				} else {
					temp.setName(newCasName);
				}
				break;
			}
		case 5:
			Customer customer = new Customer(); // default customer
			Casino casino = new Casino(); // default casino

			casino.listGames(); // shows all the games that casino offers

			subMenu = sc.nextInt();
			switch (subMenu) { // create a switch case for choosing which game to play
			case 1:
				SevenEleven game1 = new SevenEleven(customer, casino); // games are created each using a default casino
																		// and customer to play with
				game1.play();
				break;
			case 2:
				TwentyOne game2 = new TwentyOne(customer, casino);
				game2.play();
				break;
			case 3:
				Slots game3 = new Slots(customer, casino);
				game3.play();
				break;
			case 4:
				HighLow game4 = new HighLow(customer, casino);
				game4.play();
				break;
			}

			break;
		case 6:
			displayCustomers(customers); // prints customer list

			if (customers.length == 0) {
				System.out.println("\nEnter anything to exit!");
				String temp = sc.next();

				break;
			} else {
				System.out.println("Which customer would you like the details for?");
				subMenu = sc.nextInt();

				System.out.printf("Customer Name: %s \nCustomer ID: %d\nCustomer Balance: $%,d\n", // prints details of
																									// customer
						customers[subMenu - 1].getName(), customers[subMenu - 1].getID(),
						customers[subMenu - 1].getBalance());
				break;
			}
		case 7:
			displayCasinos(casinos); // prints casino list

			if (casinos.length == 0) {
				System.out.println("\nEnter anything to exit!");
				String temp = sc.next();

				break;
			} else {
				System.out.println("Which casino would you like the details for?");
				subMenu = sc.nextInt();

				System.out.printf("Casino Name: %s \nCasino ID: %d\nCasino Balance: $%,d\n", // prints details of casino
						casinos[subMenu - 1].getName(), casinos[subMenu - 1].getID(),
						casinos[subMenu - 1].getBalance());
				break;
			}
		case 8:
			sc.close();
			System.exit(0); // will close if they choose to exit
			break;
		default:
			System.out.println("Invalid choice!"); // incorrect choice and will return to menu
			break;
		}

	}

	public static void displayCustomers(Customer[] list) { // show all customers for the option 6
		System.out.println("\n         Customers");
		System.out.println("---------------------------------");

		if (list.length == 0) {
			System.out.println("No customers created yet!"); // if no customers create will print this instead
		} else {
			for (int i = 0; i < list.length; i++) {
				if (list[i] != null) { // if not all customers have been initialized then print values of array that
										// arent null
					System.out.println(i + 1 + ". " + list[i].getName());
				}
			}
		}

	}

	public static void displayCasinos(Casino[] list) { // show all casinos for option 7
		System.out.println("\n         Casinos");
		System.out.println("---------------------------------");

		if (list.length == 0) {
			System.out.print("No casinos created yet!"); // if no casinos create will print this instead
		} else {
			for (int i = 0; i < list.length; i++) {
				if (list[i] != null) {// if not all casinos have been initialized then print values of array that
										// arent null
					System.out.println(i + 1 + ". " + list[i].getName());
				}
			}
		}
	}
}
