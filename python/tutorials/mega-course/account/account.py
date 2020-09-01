class Account:

    def __init__(self, filepath="balance.txt"):
        self.filepath = filepath
        with open(filepath, 'r') as f:
            self.balance = int(f.read())

    def withdraw(self, amount):
        self.balance = self.balance - amount
        self.commit()

    def deposit(self, amount):
        self.balance = self.balance + amount
        self.commit()

    def commit(self):
        with open(self.filepath, 'w') as f:
            f.seek(0)
            f.truncate()
            f.write(str(self.balance))



class Checking(Account):

    type = "checking"

    def __init__(self, fee=1, filepath="balance.txt"):
        Account.__init__(self, filepath)
        self.fee = fee

    def transfer(self, amount):
        self.withdraw(amount + self.fee)


checking = Checking(fee=2)
checking.deposit(100)
print(checking.balance)
checking.withdraw(200)
print(checking.balance)
