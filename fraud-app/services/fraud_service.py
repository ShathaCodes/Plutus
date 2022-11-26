import random
from logger.logger import Logger

class FraudService:

    def verify(self, transaction):
        Logger.info(transaction)
        is_fraud = random.randint(0, 1)
        return is_fraud
