transaction_schema = {
    'type': 'object',
    'properties': {
        'amount': {
            'type': 'number'
        },
        'timestamp': {
            'type': 'string',
            'format': 'date-time'
        },
        'senderId': {
            'type': 'integer'
        },
        'receiverId': {
            'type': 'integer'
        }
    },
    # 'additionalProperties': False, Used to block passing additional properties
    'required': [
        "amount",
        "timestamp",
        "senderId",
        "receiverId"
    ]
}