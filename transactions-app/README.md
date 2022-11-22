# Transactions App

## Overview

This is a simple transactions app written in Java & Spring boot.

## Getting Started

Start by launching the database.

```bash
docker compose up -d
```

You can then run the application as you would run any other spring boot application.

Documentation about the endpoints is available under `transactions` folder

## Backend Overview

Below we will describe the database schema we used and the endpoints implemented in the transactions app.

### Database Schema

- `clients`:
  - id
  - name
  - lastname

- `accounts`:
  - id
  - clientId
  - balance

- `transactions` ( mapped and not necessarily created ):
  - id
  - amount
  - timestamp

- `exchange_transactions`:
  - senderId:
  - receiverId:
  - transactionId

- `account_transactions`:
  - accountId:
  - type: `WITHDRAW` / `DEPOSIT`
  - transactionId

---


### Errors : 

In case of errors , we will have :

```json
{
  "status": 400, 
  "message": "an error has occured: ..."
}
```

### Endpoints :

**URI** :  /transactions/withdraw

**HTTP Method** : PUT

```json
{
  "accountId": 1,
  "Amount": 100
}
```

Resp (similar to deposit)

---

**URI** : /transactions/deposit

**HTTP Method** : PUT

```json
{
    "accountId": 1,
    "Amount": 100
}
```

Resp ( Success ):

```json
{
  "transactionId": 1,
  "accountId": 1,
  "timestamp": "date+hms",
  "amount": 100
}
```

---

**URI** : /transactions/send

**HTTP Method** : PUT

```json
{
  "senderId": 1,
  "amount": 100,
  "receiverId": 2
}
```

---


**URI** : /transactions/accounts/:accountId

**HTTP Method** : GET

```json
{
  "transactions": [
    {},
    {}
  ]
}
```


---

**URI** : /transactions/clients/:clientId

**HTTP Method** : GET


```json
{
  "transactions": [
    {},
    {}
  ]
}
```

---

**URI** : /accounts/:clientId

**HTTP Method** : GET

```json
{
  "accounts": [
    {},
    {}
  ]
}
```