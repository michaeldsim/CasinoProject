
public class Casino {
	private static int counter; // counts number of casinos created
	private int id, balance;
	private String name;

	public Casino() { // default constructor
		name = "default casino";
		balance = 50000;

		counter++;
		id = counter; // id becomes the number created at the time
	}

	public Casino(String name, int balance) { // constructor given name and balance
		this.name = name;
		this.balance = balance;

		counter++;
		id = counter;
	}

	public void addBalance(int x) { // add to balance method
		balance += x;
	}

	public void subtractBalance(int x) { // subtract from balance method
		if (!(balance - x >= 0)) {
			System.out.println("Balance cannot go below 0!");
		} else {
			balance -= x;
		}
	}

	public void setName(String name) { // change name of casino
		this.name = name;

		subtractBalance(15000);
		System.out.println("$15,000 has been deducted from the casino balance for changing names!");
	}

	public String getName() { // return casino name
		return name;
	}

	public int getBalance() { // return casino balance
		return balance;
	}

	public int getID() { // return casino id
		return id;
	}

	public void listGames() {
		System.out.println("1. Seven-Eleven\n2. Twenty-One\n3. Slots\n4. High Low");
	}

	public void printBalance() {
		System.out.println("Casino Balance: " + String.format("$%,d", getBalance()));
	}
}
