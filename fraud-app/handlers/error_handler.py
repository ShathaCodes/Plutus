from werkzeug.exceptions import HTTPException
from werkzeug.sansio.response import Response
from typing import Optional


class AppException(HTTPException):
    def __init__(self, description: Optional[str] = None, response: Optional["Response"] = None, code: int = 500) -> None:
        super().__init__(description, response)
        self.code = code


class ErrorHandler:
    """Handles errors that might rise in the application.
    Raises:
        AppException: A general purpose application exception.
    """
    @staticmethod
    def throw(description: str, code: int = 500):
        raise AppException(description=description, code=code)