from functools import wraps
from jsonschema import validate, ValidationError, Draft202012Validator
from handlers.error_handler import ErrorHandler
from flask import request


class SchemaValidator:
    @staticmethod
    def validate_schema(instance: dict, schema: dict):
        try:
            validate(instance, schema, format_checker=Draft202012Validator.FORMAT_CHECKER)
        except ValidationError as e:
            ErrorHandler.throw(e.message, 400)


def validate_json(schema):
    def decorator(f):
        @wraps(f)
        def decorated_function(*args, **kwargs):
            data = request.get_json(force=True, silent=True)
            if data is None:
                ErrorHandler.throw('Failed to decode JSON object', 400)
            SchemaValidator.validate_schema(instance=data, schema=schema)

            return f(*args, **kwargs)
        return decorated_function
    return decorator
