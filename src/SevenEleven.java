import java.util.Random;
import java.util.Scanner;

public class SevenEleven {
	private Customer customer;
	private Casino casino;
	private int bet;
	// TODO Auto-generated constructor stub

	public SevenEleven(Customer customer, Casino casino) {
		this.customer = customer;
		this.casino = casino;
		// TODO Auto-generated constructor stub
	}

	public void play() {
		customer.printBalance();
		casino.printBalance();

		Scanner input = new Scanner(System.in);
		System.out.println("How much would you like to bet?");
		int amt = input.nextInt();
		setBet(amt);

		if (amt > customer.getBalance() || amt > casino.getBalance()) { // doesnt play if bet is larger than balance
		} else {
			System.out.println();
			int cus1, cus2, cas1, cas2;

			do { // if 7 or 11 not found in any keep rolling or both rolled the same number
				System.out.println("Rolling");
				cus1 = roll();
				cus2 = roll();
				cas1 = roll();
				cas2 = roll();

				if (!(cus1 + cus2 == 7) && !(cus1 + cus2 == 11) && !(cas1 + cas2 == 7) && !(cas1 + cas2 == 11)) {
					System.out.print("Neither of you rolled a 7 or an 11\nRe-");
				} else if ((cus1 + cus2 == cas1 + cas2)) {
					System.out.print("Both of you rolled: " + (cus1 + cus2) + "\nRe-");
				}
			}

			while ((!(cus1 + cus2 == 7) && !(cus1 + cus2 == 11) && !(cas1 + cas2 == 7) && !(cas1 + cas2 == 11))
					|| (cus1 + cus2 == cas1 + cas2));

			if (((cus1 + cus2 == 7) || (cus1 + cus2 == 11)) && !((cas1 + cas2 == 7) || (cas1 + cas2 == 11))) {
				System.out.println("You rolled " + cus1 + " and " + cus2); // if player rolls a 7 or 11 and dealer
																			// doesnt
																			// player wins
				System.out.println("The dealer rolled " + cas1 + " and " + cas2);
				customerWin();
			} else if (((cas1 + cas2 == 7) || (cas1 + cas2 == 11)) && !((cus1 + cus2 == 7) || (cus1 + cus2 == 11))) {
				System.out.println("You rolled " + cus1 + " and " + cus2); // if dealer rolls a 7 or 11 and player
																			// doesnt
																			// then player lose
				System.out.println("The dealer rolled " + cas1 + " and " + cas2);
				customerLose();
			} else if (cus1 + cus2 == 7 && cas1 + cas2 == 11) { // if dealer rolls 11 and player rolls 7 player loses
				System.out.println("You rolled " + cus1 + " and " + cus2);
				System.out.println("The dealer rolled " + cas1 + " and " + cas2);
				customerLose();
			} else if (cus1 + cus2 == 11 && cas1 + cas2 == 7) { // if player rolls 11 and dealer rolls 7 player wins
				System.out.println("You rolled " + cus1 + " and " + cus2);
				System.out.println("The dealer rolled " + cas1 + " and " + cas2);
				customerWin();
			}
			customer.printBalance();
			casino.printBalance();
		}
	}

	public int roll() { // roll a random number between 1-6
		Random rand = new Random();
		int die = rand.nextInt(6) + 1;
		return die;
	}

	public void setBet(int n) { // set the bet to the amount ** fix bet cannot be more than balance
		if (n > customer.getBalance()) {
			System.out.println("Bets cannot be larger than balance!");
		} else if (n <= 0) {
			System.out.println("Bets cannot be below or equal to 0!");
		} else {
			bet = n;
		}
	}

	public void customerWin() { // win the amount customer bet
		casino.subtractBalance(bet);
		customer.addBalance(bet);

		System.out.println("You win: $" + bet);
	}

	public void customerLose() { // customer loses amount that they bet
		customer.subtractBalance(bet);
		casino.addBalance(bet);

		System.out.println("You lost: $" + bet);
	}
}