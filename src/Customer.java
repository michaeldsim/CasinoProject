
public class Customer {
	private static int counter = 0; // counts amount of customers
	private int balance, id;
	private String name;

	public Customer() { // default constructor
		name = "default customer";
		balance = 1000;

		counter++;
		id = counter; // id of employees becomes the number of employees when created
	}

	public Customer(String name, int balance) { // creates customer from given name and balance
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
			System.out.println("Balance cannot go below 0!"); // will not withdraw more than the balance
		} else {
			balance -= x;
		}
	}

	public void setName(String name) { // change customer name
		this.name = name;
	}

	public String getName() { // return name
		return name;
	}

	public int getBalance() { // return customer balance
		return balance;
	}

	public int getID() { // return customer id
		return id;
	}

	public void printBalance() {
		System.out.println("Customer Balance: " + String.format("$%,d", getBalance()));
	}
}
