import logging
from handlers.error_handler import ErrorHandler


class Logger:

    @staticmethod
    def info(description: str):
        logging.info(description)

    @staticmethod
    def error(description: str):
        """Handles an error.

        Args:
            description (str): description of the error.
        """
        logging.error(description)
        ErrorHandler.throw(description=description)

