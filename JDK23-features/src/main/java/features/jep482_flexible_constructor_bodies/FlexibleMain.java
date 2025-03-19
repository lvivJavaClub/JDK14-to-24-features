package features.jep482_flexible_constructor_bodies;

public class FlexibleMain {

  public static void main(String[] args) {
    var bankTransfer = new BankTransfer("1234", 1000.0, "Somebody22", "Somebody33");
    System.out.println(bankTransfer.receiverBankAccount);
    System.out.println(bankTransfer.senderBankAccount);

  }

  public static class Transaction {
    private final String transactionId;
    private final double amount;

    public Transaction(String transactionId, double amount) {
      validate();
      this.transactionId = transactionId;
      this.amount = amount;
    }

    protected void validate() {
      if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
      if (transactionId == null || transactionId.isEmpty()) throw new IllegalArgumentException("Transaction ID cannot be null or empty.");
    }
  }



  public static class BankTransfer extends Transaction {
    private final String senderBankAccount;
    private final String receiverBankAccount;

    private static String validateBankAccount(String account) {
      if (account == null || account.length() != 10) {
        throw new IllegalArgumentException("Invalid bank account.");
      }
      return account;
    }

    // Constructor uses auxiliary method to prepare arguments for superclass constructor
    public BankTransfer(String transactionId, double amount, String sender, String receiver) {
      this.senderBankAccount = validateBankAccount(sender);
      this.receiverBankAccount = validateBankAccount(receiver);
      super(transactionId, amount);
    }

    @Override
    protected void validate() {
      super.validate();
      if (senderBankAccount.equals(receiverBankAccount)) {
        throw new IllegalArgumentException("Sender and receiver accounts cannot be the same.");
      }
    }
  }
}
