package unitTest.test

import groovy.mock.interceptor.*
import unitTest.code.BankAccount;
import unitTest.code.InsufficientFundsException
import unitTest.code.InterestRateService

class BankAccountTests extends GroovyTestCase {

    private account

    def void setUp() {
        println 'setUp'
        account = new BankAccount(10)
    }

    def void tearDown() {
        println 'tearDown'
        account = null
    }

    def void testDeposit() {
        account.deposit(5)
        assert 15 == account.balance
    }

    def void testWithdraw() {
        account.withdraw(5)
        assert 5 == account.balance
    }

    def void testCanNotWithdrawMoreMoneyThanBalance() {
        shouldFail(InsufficientFundsException) {
            account.withdraw(15)
        }
    }

    def void testAccureInterest() {
        //def service = new MockFor(InterestRateService) //Must be used
        def service = new StubFor(InterestRateService)
        service.demand.getInterestRate{
            return 0.10
        }

        service.use {
            account.accureInterest()
            assert 11 == account.balance
        }
    }
}
