package myproject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

 /**
 * @author Marius
 *
 */
public class BankAccount
{
	private long balance;
	private long balance2;
	private int kontonummer;
	private long amount;
	private String pin;
	Random random = new Random();
	ArrayList<Object> account = new ArrayList<>();
	static HashMap<Integer, ArrayList<Object>> konten = new HashMap<Integer,ArrayList<Object>>();
	Scanner sc = new Scanner(System.in); 
	
	
	
	
	/**
	 * @param balance
	 */
	public BankAccount(int kontonummer, long balance)
	{
		this.balance = balance;
		this.kontonummer = kontonummer;
		pin = String.format("%04d", random.nextInt(10000));
		ArrayList<Object> account = new ArrayList<>();
		account.add(balance);
		account.add(pin);
		BankAccount.konten.put(kontonummer,account);
	}
	
	/**
	 * @return
	 */
	public int getKontonummer()
	{
		return kontonummer;
	}
	
	/**
	 * @return
	 */
	public long getBalance(int kontonummer)
	{
		BankAccount test = new BankAccount(1, 50);
		ArrayList<Object> liste = test.getArrayList(kontonummer,konten);
	    balance = (Long)liste.get(0);
		return balance;
	}
	
	private String getPin(int kontonummer)
	{
		    ArrayList<Object> liste= konten.get(kontonummer);
		    pin = (String)liste.get(1);
			return pin;	
	}
	
	public ArrayList<Object> getArrayList(int kontonummer,HashMap<Integer, ArrayList<Object>> konten)
	{
		ArrayList<Object> account = konten.get(kontonummer);
		
		return account;
	}
	
	public long getAmount(long kontonr)
	{
		return amount;
	}
	
	/**
	 * @param depositValue
	 */
	public void deposit(long depositValue, int kontonummer)
	{
		if(depositValue<1)
		{
			System.out.println("Es kann nur ein positiver Wert eingezahlt werden, bitte einen anderen Betrag eingeben.");
			return;
		}
		ArrayList<Object> liste = konten.get(kontonummer);
		balance = (long)liste.get(0) + depositValue;
		liste.set(0, balance);
		konten.replace(kontonummer, liste);
		System.out.println("Einzahlung vollzogen. Der neue Kontostand beträgt "+ balance+ " Euro.");
	}

	/**
	 * @param withdrawValue
	 * @return
	 */
	public long withdraw(long withdrawValue, int kontonummer)
	{
		if(withdrawValue<1 && withdrawValue>= balance)
			{
				System.out.println("Es kann nur ein positiver Wert abgehoben werden "
						+ "sowie das Konto nicht überzogen werden , bitte einen anderen Betrag abheben.");	
				return balance;
			}
		ArrayList<Object> liste = konten.get(kontonummer);
		balance = (long)liste.get(0) - withdrawValue;
		liste.set(0, balance);
		konten.replace(kontonummer, liste);
		System.out.println("Geld abgehoben.Der neue Kontostand beträgt "+ balance+ " Euro.");
		return balance;
	}
	
	/**
	 * @param foreignValue
	 * @param conversionfactor
	 */
	public long withdraw(long foreignValue, double conversionfactor, int kontonummer)
	{
		ArrayList<Object> liste = konten.get(kontonummer);
		balance = (long)liste.get(0);
		if(foreignValue<1 )
		{
			System.out.println("Es kann nur ein positiver Wert abgehoben werden, bitte einen anderen Betrag eingeben.");
			return balance;
		}
		double a = foreignValue*conversionfactor;
		a = Math.ceil(a);
		if(a > balance)
		{
		 System.out.println("Es darf das Konto nicht überzogen werden, bitte einen geringeren Betrag abheben");
		 return balance;
		}
		long b = (long)a;
		balance = balance - b ;
		liste.set(0, balance);
		konten.replace(kontonummer, liste);
		System.out.println("Geld abgehoben.Der neue Kontostand beträgt "+ balance+ " Euro.");
		return balance;
		
	}
	
	public void transfer(int kontonummer, int kontonummer2, long amount, BankAccount acc)
	{
		ArrayList<Object>liste2 = acc.getArrayList(kontonummer2, konten);
		ArrayList<Object>liste3 = konten.get(kontonummer);
		balance2 = (long)liste2.get(0);
		balance  = (long)liste3.get(0);
		if(amount>balance)
		{
			System.out.println("Es darf das Konto nicht überzogen werden, bitte einen geringeren Betrag überweisen");
			return;
		}
		balance = balance - amount;
		balance2 = balance2 + amount;
		liste2.set(0, balance2);
		liste3.set(0, balance);
		konten.replace(kontonummer2, liste2);
		System.out.println("Überweisung vollzogen");
	}

	public static void main(String[] args) {
       
		BankAccount bank= new BankAccount(1234567891, 50000000);
		
		Scanner sc = new Scanner(System.in);  	
		System.out.println("Kontonummereingeben");
		int kontonr = sc.nextInt();
		System.out.println("Guthaben eingeben");
		long guthaben = sc.nextLong();
		BankAccount acc = new BankAccount( kontonr, guthaben);
		bank.transfer(1234567891, kontonr, 1000, acc);
		System.out.println("Guthaben beträgt "+ acc.getBalance(kontonr));
		System.out.println("Guthaben beträgt "+ acc.getBalance(1234567891));

		sc.close();
		
		
		
	}
}

