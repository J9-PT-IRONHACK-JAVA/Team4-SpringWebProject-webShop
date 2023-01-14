# SpringShopWeb :leaves:

SpringShopWeb is a full API REST to manage an Online Shop for Clients and Sellers with CRUD endpoints.

In SpringShopWeb Sellers can add Products only with name and the description is auto-generated. 
The default language is ENGLISH, but the Clients can register in their own language and the TranslateService make an auto-translate for the Client language.

The Payment Service is not implemented for this production version.

## Technologies & Requirements :octocat:

- Java 17
- SpringBoot 3.0.1
- MySql
- External Apis:
  - DeeplWebTranslator (<a href="https://www.deepl.com/docs-api">Deepl Web Api Docs</a>)
  - OpenAI ChatbotGPT (<a href="https://beta.openai.com/docs/introduction">OpenAI Chatbot Api Docs</a>)

## Configuration:

- Database Name:
  - j9_shopweb

## Endpoints Documentation:

| Method |                         Uri                         |   Role |
|--------|:---------------------------------------------------:|-------:|
| POST   |            localhost:8080/registerclient            |   None |
| POST   |            localhost:8080/registerseller            |   None |
| POST   |          localhost:8080/seller/addproduct           | SELLER |
| GET    |       localhost:8080/client/viewproduct/{id}        | CLIENT |
| GET    |          localhost:8080/client/allproducts          | CLIENT |
| PATCH  |       localhost:8080/client/update?**PARAMS**       | CLIENT |
| PUT    |        localhost:8080/client/addtocart/{id}         | CLIENT |
| GET    |             localhost:8080/client/cart              | CLIENT |
| PUT    |           localhost:8080/client/checkout            | CLIENT |
| GET    |            localhost:8080/client/orders             | CLIENT |
| PATCH  | localhost:8080/seller/updateproduct/{id}?**PARAMS** | SELLER |
| DELETE |          localhost:8080/seller/delete/{id}          | SELLER |
| GET    |               localhost:8080/headers                |  ADMIN |




Requirements

1) FREE THEME you can pick something that you like and build the project around it

2) REAL api with a real use case

3) API ( at least 5 "root" endpoints, you must find use for: PathVariable, RequestParam, RequestBody, RequestHeader)

4) CRUD ( you must use all verbs we have seen so far: GET,POST,PUT,UPDATE,DELETE)

5) DTOs ( no entity should be passed from our controllers, you will use DTOs)

6) EXTERNAL api call( calls to at least 2 externals APIs using OpenFeign)

7) DB persistence( at least 4 entities, you must find use for inheritance)

8) SECURED endpoints ( at least two roles: USER, ADMIN)

9) TESTs ( at least 80% code coverage, tests must be robust)

10) VALIDATIONS ( object you receive must be correctly validated)

11) EXCEPTIONS ( all errors and exceptions must be dealt with)

12) Complete documentation of the app, the api, its use and the endpoints

13) A presentation with all the team involved
