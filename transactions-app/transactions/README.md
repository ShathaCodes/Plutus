
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