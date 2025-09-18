import java.util.*;

class Account {
    private int accountNumber;
    private String holderName;
    private double balance;

    public Account(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void displayAccount() {
        System.out.println("Account No: " + accountNumber + ", Holder: " + holderName + ", Balance: ₹" + balance);
    }
}

class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    public Account createAccount(String holderName, double initialDeposit) {
        Account account = new Account(nextAccountNumber, holderName, initialDeposit);
        accounts.put(nextAccountNumber, account);
        System.out.println("Account created successfully! Account No: " + nextAccountNumber);
        nextAccountNumber++;
        return account;
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (Account acc : accounts.values()) {
                acc.displayAccount();
            }
        }
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    sc.nextLine(); 
                    String name = sc.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double deposit = sc.nextDouble();
                    bank.createAccount(name, deposit);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int accNumDeposit = sc.nextInt();
                    Account acc1 = bank.getAccount(accNumDeposit);
                    if (acc1 != null) {
                        System.out.print("Enter deposit amount: ");
                        double amt = sc.nextDouble();
                        acc1.deposit(amt);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    int accNumWithdraw = sc.nextInt();
                    Account acc2 = bank.getAccount(accNumWithdraw);
                    if (acc2 != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amt = sc.nextDouble();
                        acc2.withdraw(amt);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    int accNum = sc.nextInt();
                    Account acc3 = bank.getAccount(accNum);
                    if (acc3 != null) {
                        acc3.displayAccount();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    bank.displayAllAccounts();
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
