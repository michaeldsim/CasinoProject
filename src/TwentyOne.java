import java.util.Random;
import java.util.Scanner;

public class TwentyOne {
	private static final int[] cardValues = { 10, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 }; // values of the cards in
																							// their respective order
	private Casino casino;
	private Customer customer;
	private int bet;

	public TwentyOne(Customer customer, Casino casino) { // constructor
		this.customer = customer;
		this.casino = casino;
		// TODO Auto-generated constructor stub
	}

	public void play() {
		// TODO Auto-generated method stub
		customer.printBalance();
		casino.printBalance();

		Scanner input = new Scanner(System.in);
		String ans = "";

		System.out.println("\nHow much would you like to bet?");
		int amt = input.nextInt();
		setBet(amt);

		if (amt > customer.getBalance() || amt > casino.getBalance()) { // doesnt play if bet is larger than balance
		} else {
			int cus1 = pickCard(); // player draws 1st card
			int cus2 = pickCard(); // player draws 2nd card
			int cas1 = pickCard(); // dealer draws 1st card
			int cas2 = pickCard(); // dealer draws 2nd card
			int cusTotal = cardValues[cus1] + cardValues[cus2]; // player hand total
			int casTotal = cardValues[cas1] + cardValues[cas2]; // dealer hand total

			System.out.println(
					"You " + whichCard(cus1) + " and you also " + whichCard(cus2) + "\nYour total is: " + cusTotal);
			System.out.println("The dealer " + whichCard(cas1) + " as one of his cards"); // only show one card of the
																							// dealer

			System.out.println("Would you like to draw a card?\n1. Yes\n2. No");
			while (!(ans = input.next()).equals("2") && (cusTotal <= 21)) { // if you choose to draw add card value to
																			// current sum
				int temp = pickCard();
				cusTotal += cardValues[temp];
				System.out.println("You " + whichCard(temp) + "\nYour new total is: " + cusTotal);
				if (cusTotal > 21) { // if total is above 21 then break while loop
					break;
				}
				System.out.println("Would you like to draw another card?\n 1. Yes\n 2. No"); // prompt if you want to
																								// continue while loop
			}

			if (casTotal < 17 && cusTotal <= 21) { // if player didnt bust and dealer total is less than 17 the dealer
													// will
													// draw
				int temp = pickCard();
				casTotal += cardValues[temp];
				System.out.println("The dealer " + whichCard(temp) + "\nTheir total is: " + casTotal);
			}

			if (casTotal > 21) { // if dealer busts player wins
				customerWin();
			} else if (cusTotal > 21) { // if player busts player loses
				customerLose();
			} else if (cusTotal > casTotal) { // if player hand is higher than dealer, player wins
				customerWin();
			} else if (casTotal > cusTotal) { // if dealer hand is higher than player, player loses
				customerLose();
			} else if (cusTotal == casTotal) { // if it is a tie do nothing
				System.out.println("It's a draw! Customer receives their money back");
			}
			customer.printBalance();
			casino.printBalance();
		}
	}

	public int pickCard() {
		Random rand = new Random();

		int card = rand.nextInt(13); // chooses a random number card value

		return card;
	}

	public String whichCard(int n) { // takes the num value and translates it into a card
		switch (n) {
		case 0:
			return "drew an Ace";
		case 1:
			return "drew a 2";
		case 2:
			return "drew a 3";
		case 3:
			return "drew a 4";
		case 4:
			return "drew a 5";
		case 5:
			return "drew a 6";
		case 6:
			return "drew a 7";
		case 7:
			return "drew an 8";
		case 8:
			return "drew a 9";
		case 9:
			return "drew a 10";
		case 10:
			return "drew a Jack";
		case 11:
			return "drew a Queen";
		case 12:
			return "drew a King";
		}
		return null;
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

	public void customerWin() {
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
