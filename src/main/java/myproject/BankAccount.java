package myproject;

public class BankAccount
{
	public long balance = 0;
	public long einzahlwert = 0;
	public BankAccount acc2;
	public BankAccount acc3;
	
	public BankAccount(long balance)
	{
		this.balance = balance;
	}
	
	public long getBalance()
	{
		
		return balance;
	}

	public void deposit(long depositValue)
	{
		if(depositValue<1)
		{
			System.out.println("Es kann nur ein positiver Wert eingezahlt werden, bitte einen anderen Betrag eingeben.");
			return;
		}
		balance = balance + depositValue; 
		System.out.println("Einzahlung vollzogen. Der neue Kontostand beträgt "+ balance+ " Euro.");
	
	}
	
	public long withdraw(long withdrawValue)
	{
		if(withdrawValue<1 && withdrawValue<= balance)
			{
				System.out.println("Es kann nur ein positiver Wert abgehoben werden "
						+ "sowie das Konto nicht überzogen werden , bitte einen anderen Betrag abheben.");
				
				return balance;
			}
		balance = balance - withdrawValue;
		System.out.println("Geld abgehoben.Der neue Kontostand beträgt "+ balance+ " Euro.");
		return balance;
	}
	
	public void withdraw(long foreignValue, double conversionfactor)
	{
		if(foreignValue<1 && foreignValue<= balance)
		{
			System.out.println("Es kann nur ein positiver Wert abgehoben werden, bitte einen anderen Betrag eingeben.");
			return;
		}
	double a = foreignValue*conversionfactor;
	a = Math.ceil(a);
	long b = (long) a;
	if(b>balance)
	{
		System.out.println("Bitte das Konto nicht überziehen, wählen sie einen anderen Betrag");
		return;
	}
		balance = balance - b;
		System.out.println("Der neue Kontostand beträgt "+ balance+ " Euro.");
	}
	
	public void transfer()
	{
		
	}

public static void main(String[] args) {
	
	BankAccount acc = new BankAccount(1000);
	
	}
}

