from flask import Flask, json, request
from werkzeug.exceptions import HTTPException
from validators.validator import validate_json
from validators.schemas import transaction_schema
from services.fraud_service import FraudService
from logger.logger import Logger
app = Flask(__name__)

fraud_service = FraudService()


@app.get("/transactions/verify")
@validate_json(transaction_schema)
def verify_transaction():
    transaction = request.get_json(force=True)
    is_fraud = fraud_service.verify(transaction)
    return {
        "is_fraud": is_fraud
    }


@app.errorhandler(HTTPException)
def handle_exception(error):
    # start with the correct headers and status code from the error
    response = error.get_response()
    # replace the body with JSON
    response.data = json.dumps({
        "code": error.code,
        "name": error.name,
        "description": error.description,
    })
    response.content_type = "application/json"
    return response
