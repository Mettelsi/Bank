package bank;

public class BankAccount
{
	public long balance = 100;
	public long einzahlwert = 1;
	
	public void getBalance()
	{
		System.out.println(balance);
	}

	public void deposit(long depositValue)
	{
		if(depositValue<1)
		{
			System.out.println("Es kann nur ein positiver Wert eingezahlt werden, bitte einen anderen Betrag eingeben.");
			return;
		}
		balance = balance + depositValue; 
		System.out.println("Der neue Kontostand beträgt "+ balance+ " Euro.");
	
	}
	public void withdraw(long withdrawValue)
	{
		if(withdrawValue<1 && withdrawValue<= balance)
			{
				System.out.println("Es kann nur ein positiver Wert abgehoben werden "
						+ "sowie das Konto nicht überzogen werden , bitte einen anderen Betrag abheben.");
				
				return;
			}
		balance = balance - withdrawValue;
		System.out.println("Der neue Kontostand beträgt "+ balance+ " Euro.");
	}
	public void withdraw(long foreignValue, double conversionfactor)
	{
		if(foreignValue<1 && foreignValue<= balance)
		{
			System.out.println("Es kann nur ein positiver Wert abgehoben werden, bitte einen anderen Betrag eingeben.");
			return;
		}
	double a = foreignValue*conversionfactor;
	long b = (long) a;
	if(b>balance)
	{
		System.out.println("Bitte das Konto nicht überziehen, wählen sie einen anderen Betrag");
		return;
	}
		balance = balance - b;
		System.out.println("Der neue Kontostand beträgt "+ balance+ " Euro.");
	}

public static void main(String[] args) {
	
	BankAccount acc = new BankAccount();
	acc.getBalance();
	acc.deposit(-4);
	acc.deposit(4);
	acc.getBalance();
	acc.withdraw(-3);
	acc.withdraw(3);
	acc.getBalance();
	acc.withdraw(-20, 1.5);
	
	}
}

