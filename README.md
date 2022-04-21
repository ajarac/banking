# banking

This project you can have as accounts as you want and create transactions for those accounts.

## Run the application
For run we can use docker:

`docker run -t banking .`

## Endpoints

There is a postman collection with all endpoints allowed

### Health
First we have a health endpoint to check that our backend is running:

`http://localhost:8080/heatlh`


### Create Account

POST `http://localhost:8080/accounts`

We only need to add a name we want for our account

Body
```
{
    "name": "Account test"
}
```

### Get Accounts

GET `http://localhost:8080/accounts`

it returns a list of accounts with this structure:

```
{
   "id": "<accountId>",
   "name": "<name>"
}
```

### Delete Account

DELETE `http://localhost:8080/accounts/<accountId>`

The accounts needs to have balance 0 to delete

### Create Transaction

POST `http://localhost:8080/transactions`

Body

The type account needs to be one of this values: `WITHDRAWAL` `DEPOSIT` `LOCAL` `INTERNATIONAL`

```
{
    "from": "<accountId>", // needed for WITHDRAWAL, LOCAL and INTERNATIONAL
    "to": "<accountId>", // needed for DEPOSIT and LOCAL
    "amount": <amount>,
    "type": "<type>"
}
```

for transactions outcomes needs to have enough balance to make the transaction (if you have 0 aed yo can not to withdrawal 10 aed for instance)

### Get Balance
GET `http://localhost:8080/accounts/<accountId>`

It would calculate the current balance

Response
```
{
   "id": "<accountId>",
   "balance": <balance>
}
```

## Improvements

- Use a real db instead in memory db
- Calculate the balance every transaction creation in account (instead calculate it in the get of balance) and save it in account in a new property. For this depends on the requirements we can use event sourcing for update the balance in account
- Improve cache to clean it only when a transaction for X account is created
