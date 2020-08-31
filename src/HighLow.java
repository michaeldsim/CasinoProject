import java.util.Random;
import java.util.Scanner;

public class HighLow {
	private static boolean[][] notPickedYet = new boolean[4][13]; // if a card is chosen this value changes so that it
																	// cannot be chosen again
	private Customer customer;
	private Casino casino;
	private int bet;

	public HighLow(Customer customer, Casino casino) {
		this.customer = customer;
		this.casino = casino;
		// TODO Auto-generated constructor stub
	}

	public void play() {
		reset(); // reset the cards every restart
		customer.printBalance(); // show customer balance
		casino.printBalance(); // show casino balance

		int amt;
		Scanner input = new Scanner(System.in);
		System.out.println("\nHow much would you like to bet?"); // ask for the bet
		amt = input.nextInt();
		setBet(amt);

		if (amt > customer.getBalance() || amt > casino.getBalance()) { // doesnt play if bet is larger than balance
		} else {
			boolean cont = true; // while loop condition
			int lastCard = drawCard(), newCard = 0, ans, i = 0; // last card will start it, i represents the amount of
																// correct guesses
			int temp; // used to store answers

			while (cont) {
				if (i > 0) { // if not the first run then the new card becomes the last card and a new card
								// is drawn
					lastCard = newCard;
				}
				System.out.println("Take a guess\n1. High\n2. Low"); // prompts player for a guess
				ans = input.nextInt();
				newCard = drawCard();
				if ((ans == 1) && (newCard > lastCard || newCard == lastCard)) { // if guess is high
					System.out.print("Do you want to continue? \n1. Yes\n2. No\n"); // prompts player to continue or
																					// cash
																					// out
					temp = input.nextInt();
					if (temp == 1) { // if they continue come here
						System.out.println("How much would you like to add to the bet?");
						int holder = input.nextInt();
						addBet(holder);
					} else if (temp == 2) { // if they cash out come here
						if (i >= 4) { // if they won 4 or more times give the bonus as well
							customerWin();
							customerWinBonus();
							cont = false;
						} else {
							customerWin(); // if not just give amount they bet
							cont = false;
						}
					}
				} else if (ans == 2 && lastCard > newCard) {// if guess is low asks same stuff as before
					System.out.print("Do you want to continue? \n1. Yes\n2. No\n");
					temp = input.nextInt();
					if (temp == 1) {
						System.out.println("How much would you like to add to the bet?");
						int holder = input.nextInt();
						addBet(holder);
					} else if (temp == 2) {
						if (i >= 4) {
							customerWin();
							customerWinBonus();
							cont = false;
						} else {
							customerWin();
							cont = false;
						}
					}
				} else {
					customerLose();
					cont = false;
				}
				i++; // adds to the amount of correct guesses
			}
			// TODO Auto-generated method stub
			customer.printBalance();
			casino.printBalance();
		}
	}

	public static void reset() { // reset all cards back to true
		for (int i = 0; i < 4; i++) {
			for (int x = 0; x < 13; x++) {
				notPickedYet[i][x] = true;
			}
		}
	}

	public static int drawCard() { // draw a random card with a random suit and num
		Random generate = new Random();
		boolean cardNotDrawn = true;

		do {
			int s = generate.nextInt(4); // random suit
			int num = generate.nextInt(13); // random num

			if (notPickedYet[s][num]) { // if not picked then draw the card
				System.out.println("Card drawn: \n>> " + whichNum(num) + whichSuit(s) + " <<");
				notPickedYet[s][num] = false; // shows that card has been drawn and cannot draw from it again
				cardNotDrawn = false; // breaks the loop
				return num;
			}
		} while (cardNotDrawn); // continue to loop until card is drawn

		return 0;
	}

	public static String whichNum(int n) { // turns the num value into a card value
		switch (n) {
		case 0:
			return "Ace";
		case 1:
			return "2";
		case 2:
			return "3";
		case 3:
			return "4";
		case 4:
			return "5";
		case 5:
			return "6";
		case 6:
			return "7";
		case 7:
			return "8";
		case 8:
			return "9";
		case 9:
			return "10";
		case 10:
			return "Jack";
		case 11:
			return "Queen";
		case 12:
			return "King";
		}
		return null;
	}

	public static String whichSuit(int n) { // turns the int value into a card value
		switch (n) {
		case 0:
			return " of Clubs";
		case 1:
			return " of Diamonds";
		case 2:
			return " of Hearts";
		case 3:
			return " of Spades";
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

	public void addBet(int n) { // continually add bets - used for high low
		if (n <= 0) {
			System.out.println("Bets cannot be below or equal to 0!");
		} else if (bet + n > customer.getBalance()) {
			System.out.println("Bet cannot be larger than balance!");
		} else {
			bet += n;
		}
	}

	public void customerWin() { // customer wins amount they bet
		casino.subtractBalance(bet);
		customer.addBalance(bet);

		System.out.println("You win: $" + bet);
	}

	public void customerLose() { // customer loses amount that they bet
		customer.subtractBalance(bet);
		casino.addBalance(bet);

		System.out.println("You lost: $" + bet);
	}

	public void customerWinBonus() { // customer wins additional for guessing correctly at least 4 consecutive times
		// for high low
		casino.subtractBalance(50);
		customer.addBalance(50);

		System.out.println("You win an additional $50 for guessing correctly at least 4 times");
	}
}
