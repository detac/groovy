package code

class BankAccount {

    private balance

    BankAccount(openingBalance) {
        balance = openingBalance
    }

    def void deposit(amount) {
        balance += amount
    }

    def void withdraw(amount) {
        if(amount > balance)
            throw new InsufficientFundsException()
        balance -= amount
    }

    def void accureInterest() {
        def interestRateService = new InterestRateService()
        def rate = interestRateService.getInterestRate()
        def accuredInterest = balance * rate;
        deposit(accuredInterest)
    }
}
