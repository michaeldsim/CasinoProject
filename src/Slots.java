import java.util.Random;
import java.util.Scanner;

public class Slots {
	private Customer customer;
	private Casino casino;
	private int bet;

	public Slots(Customer customer, Casino casino) {
		this.customer = customer;
		this.casino = casino;
		// TODO Auto-generated constructor stub
	}

	public void play() {
		// TODO Auto-generated method stub
		customer.printBalance(); // print customer balance
		casino.printBalance(); // print casino balance

		Scanner input = new Scanner(System.in);
		System.out.println("\nHow many times would you like to spin?");
		int spins = input.nextInt();
		int amt = spins * 5;
		setBet(amt); // sets the bet to 5 times the amount of spins chosen

		if (amt > customer.getBalance() || amt > casino.getBalance()) { // doesnt play if bet is larger than balance
		} else {

			customerLose(spins * 5); // removes the money from the customer

			for (int i = 0; i < spins; i++) { // spins however many times said before
				System.out.println("Spin: " + (i + 1));
				int result = spin();
				if (result <= 70) { // 70% chance to win 1
					customerWin(1);
				} else if (result > 70 && result <= 90) { // 20% chance to win 5
					customerWin(5);
				} else if (result > 90 && result <= 99) { // 9% chance to win 10
					customerWin(10);
				} else { // 1% chance to win 100
					customerWin(100);
				}
			}
			customer.printBalance();
			casino.printBalance();
		}
	}

	public int spin() { // spin a random number between 1-100
		Random rand = new Random();
		int num = rand.nextInt(100) + 1;
		return num;
	}

	public void setBet(int n) { // set the bet to the amount ** fix bet cannot be more than balance
		if (n > customer.getBalance()) {
			System.out.println("Bets cannot be larger than balance!");
		} else if (n <= 0) {
			System.out.println("Bets cannot be below or equal to 0!\n");
		} else {
			bet = n;
		}
	}

	public void customerWin(int n) { // player wins custom amount - used for slots
		casino.subtractBalance(n);
		customer.addBalance(n);

		System.out.println("You win: $" + n);
	}

	public void customerLose(int n) { // customer loses custom amount - used for slots
		casino.addBalance(n);
		customer.subtractBalance(n);
	}
}
