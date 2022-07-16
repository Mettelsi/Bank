package myprojecttests;

import static org.junit.Assert.*;
import org.junit.Test;

import myproject.BankAccount;

/**
 * @author Marius
 *
 */
public class BankAccountTests {


//@Test
//public void deposit() 
//{
//	 BankAccount bankaccount = new BankAccount(51201,200);
//	 bankaccount.deposit(100, 51201);;
//	 assertEquals(300, bankaccount.getBalance(51201));
//}
// 
//@Test
//public void withdraw() 
// {
//	 BankAccount bankaccount2 = new BankAccount(111,50);
//	 bankaccount2.withdraw(10,111);
//	 assertEquals(40,bankaccount2.getBalance(111));
// }
// @Test
// public void withdrawForeign() {
//	 BankAccount bankaccount3 = new BankAccount(112,100);
//	 bankaccount3.withdraw(10,1.01,112);
//	 assertEquals(89, bankaccount3.getBalance(112));
// }
@Test
public void transfer()
{
	BankAccount bankaccount4 = new BankAccount(113,500);
	BankAccount bankaccount5 = new BankAccount(114,500);
	bankaccount4.transfer(113, 114, 200, bankaccount4);
	assertEquals(300,bankaccount4.getBalance(113));
	assertEquals(700,bankaccount4.getBalance(114));
}
}
