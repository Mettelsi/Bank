package myproject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

 /**
 * @author Marius
 * Klasse welche verschiedene transaktionen sowie getter und setter umfasst.
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
	 * Konstruktor
	 * 
	 * @param kontonummer fungiert als Pseudo privat-key und ersetzt "createaccount".
	 * 
	 * @param balance  Gewünschtes Guthaben eines neuerstellten Accounts.
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
	 * ersetzt "create account"
	 * 
	 * @return Gibt private-key zurück
	 */
	public int getKontonummer()
	{
		return kontonummer;
	}
	
	

	/**
	 * @param kontonummer für welches Konto
	 
	 * @return Kontostand zurückgeben
	 * 
	 */
	public long getBalance(int kontonummer)
	{
		BankAccount test = new BankAccount(1, 50);
		ArrayList<Object> liste = test.getArrayList(kontonummer,konten);
	    balance = (Long)liste.get(0);
		return balance;
	}
	
	/**
	 * für einen möglichen Log In wird ein 4 stelliger Pin zufällig generiert.
	 * 
	 * @param kontonummer zuordnung
	 * 
	 * @return pin zurückgegeben
	 */
	private String getPin(int kontonummer)
	{
		    ArrayList<Object> liste= konten.get(kontonummer);
		    pin = (String)liste.get(1);
			return pin;	
	}
	
	/**
	 * für Überweisung an anderes Konto technisch notwendig. Aus der Hashmap die alles speichert wird die 
	 * passende Liste für das jeweilig passende Konto abgefragt.
	 * 
	 * @param kontonummer für welches Konto
	 * 
	 * @param konten aus Hashmap
	 * 
	 * @return ArrayList zurückgegeben
	 */
	public ArrayList<Object> getArrayList(int kontonummer,HashMap<Integer, ArrayList<Object>> konten)
	{
		ArrayList<Object> account = konten.get(kontonummer);
		
		return account;
	}
	
	
	
	/**
	 * Einzahlen auf bestimmtes Konto (vor Ort)
	 * 
	 * @param depositValue Einzahlwert
	 * 
	 * @param kontonummer auf welches Konto
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
	 * Abheben eines Betrages
	 * 
	 * @param withdrawValue positiver Wert zum Abheben
	 * 
	 * @param kontonummer welchen welchen Konto
	 * 
	 * @return neuer Kontostand
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
	 * Abheben eines Betrages in fremder Währung
	 * 
	 * @param foreignValue Wert zum Abheben
	 * 
	 * @param conversionfactor Umrechnungsrate
	 * 
	 * @param kontonummer von welchem Konto
	 * 
	 * @return Kontostand
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
	
	/**
	 * Nutzer überweist Betrag auf ein anderes Konto
	 * 
	 * @param kontonummer Konto des Nutzers
	 * 
	 * @param kontonummer2 Zielkonto
	 * 
	 * @param amount Betragswert
	 * 
	 * @param acc benötigt für technische implementierung um Arrayliste aufzurufen
	 */
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
	
	/**
	 * @param args main
	 */
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

