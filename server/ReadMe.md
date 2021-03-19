# Read Me 
API endpoints:

First to access to the API you need to create an account

There is 3 Roles :
    - User
    - Moderator
    - Admin

So if you want to have all the permission for the API endpoint signup with the ADMIN role


## Here is the endpoint to SignUp
localhost:8088/api/auth/signup

(respect this format, you can choose all the roles)
```
{
    "username": "admin",
    "email": "admin@bezkoder.com",
    "password": "12345678",
    "role": ["user", "mod", "admin"]
}

response successfull ->

{
    "message": "User registered successfully!"
}
```


## Here is the endpoint to SignIn
localhost:8088/api/auth/signin

(respect this format)
```
{
    "username": "admin",
    "password": "123456789"
}

response successfull ->
{
    "id": "60545fbc3884ca2d7c12b17c",
    "username": "admin",
    "email": "admin@bezkoderWork.com",
    "roles": [
    "ROLE_MODERATOR",
    "ROLE_ADMIN",
    "ROLE_USER"
    ],
    "tokenType": "Bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxNjE3Mjc5OCwiZXhwIjoxNjE2MjU5MTk4fQ.Zs9pUUoKYFVhaeKIItQ0BuqTkD4_3uFMEvnRxwsfbZtHHQ_kUDHnSRmVzKfbWj2e10DPl9ZBhTF-_7OzwUnOzA"
}
```

## Here is the endpoint to ChangePassword
localhost:8088/api/auth/changePassword

(respect this format)
```
{
    "username": "admin",
    "email": "admin@bezkoderWork.03.com",
    "password": "123456789",
    "role": ["user", "mod", "admin"]
}

response successfull ->

{
    "message": "Update registered successfully!"
}
```
## Here is the endpoint to access Articles

localhost:8088/api/articles/ + services 

(I can add more methods if you want to filter the result if you think that loading all the article will be to heavy for the front)

```diff
- POST, PUT and DELETE request must be authentified by an admin token
```

services -> 

1. all          (GET)
2. find/{id}     (GET)
3. add           (POST) format bellow ⬇
4. udpate/{id}   (PUT)
5. delete/{id}   (DELETE)



add format
```
{
    "name": "Etagere",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi justo nunc, cursus at dignissim in, vulputate vitae augue. Integer eros nibh, lobortis ut nulla vel. Morbi justo nunc, cursus at dignissim in, vulputate vitae augue. Integer eros nibh,",
    "images": ["image.png"],
    "categorie": "meuble",
    "couleur": ["blanc"],
    "price": 149,
    "note": 4.8,
    "favoris": false,
    "stock": true
}
```
```diff
 - color has been changed to an array
```

