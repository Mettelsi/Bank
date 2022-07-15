package myprojecttests;

import static org.junit.Assert.*;
import org.junit.Test;
import myproject.BankAccount;

public class BankAccountTests {
 
 @Test
 public void deposit() {
	 BankAccount bankaccount = new BankAccount(0);
	 bankaccount.deposit(5000);
	 assertEquals(5000, bankaccount.getBalance());

 }
 @Test
 public void withdraw() {
	 BankAccount bankaccount2 = new BankAccount(0);
	 bankaccount2.deposit(5000);
	 bankaccount2.withdraw(100);;
	 assertEquals(4900, bankaccount2.getBalance());
 }
 @Test
 public void withdrawForeign() {
	 BankAccount bankaccount3 = new BankAccount(0);
	 bankaccount3.deposit(5000);
	 bankaccount3.withdraw(99,1.01);;
	 assertEquals(4900, bankaccount3.getBalance());
 }
 public static void main(String[] args)
{
	 BankAccountTests b = new BankAccountTests();
		b.deposit();
		b.withdrawForeign();
}
}
