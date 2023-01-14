# SpringWebShop ![Fruits](/images/logo.png)




SpringWebShop is a full API REST to manage an Online Shop for Clients and Sellers with CRUD endpoints.

In SpringWebShop Sellers can add Products only with name and the description is auto-generated with ChatbotGPT. 

The default language is ENGLISH, but the Clients can register in their own language and the TranslateService (Deepl Api Translator) make an auto-translate for their own language.

The API is full backend working, but for a more user-friendly, the users can register from a little front-end by accessing the following link in web explorer ``http://localhost:8080``

![Index View](/images/indexview.jpg)

:construction: The Payment and Shipment Tracking Services are not implemented for this version.

## Technologies & Requirements :octocat:

- Java 17
- SpringBoot 3.0.1
- MySql
- External Apis:
  - DeeplWebTranslator (<a href="https://www.deepl.com/docs-api">Deepl Web Api Docs</a>)
  - OpenAI ChatbotGPT (<a href="https://beta.openai.com/docs/introduction">OpenAI Chatbot Api Docs</a>)

## Configuration:

- Set your username and password for DB in file ``application.yaml``
- Mysql Database Name:
  - j9_shopweb

## Endpoints Documentation:

| #   | Method |                         Uri                         |   Role |
|-----|--------|:---------------------------------------------------:|-------:|
| #1  | POST   |            localhost:8080/registerclient            |   None |
| #2  | POST   |            localhost:8080/registerseller            |   None |
| #3  | POST   |          localhost:8080/seller/addproduct           | SELLER |
| #4  | GET    |       localhost:8080/client/viewproduct/{id}        | CLIENT |
| #5  | GET    |          localhost:8080/client/allproducts          | CLIENT |
| #6  | PATCH  |       localhost:8080/client/update?**PARAMS**       | CLIENT |
| #7  | PUT    |        localhost:8080/client/addtocart/{id}         | CLIENT |
| #8  | GET    |             localhost:8080/client/cart              | CLIENT |
| #9  | PUT    |           localhost:8080/client/checkout            | CLIENT |
| #10 | GET    |            localhost:8080/client/orders             | CLIENT |
| #11 | PATCH  | localhost:8080/seller/updateproduct/{id}?**PARAMS** | SELLER |
| #12 | DELETE |          localhost:8080/seller/delete/{id}          | SELLER |
| #13 | GET    |               localhost:8080/headers                |  ADMIN |
| #14 | POST   |                localhost:8080/users                 |  ADMIN |

**Actions:**
- **#1**  Register USERS. RequestBody: CLIENT | RequestHeaders: user-agent for take the registration platform.
- **#2**  Register SELLERS. RequestBody: SELLER | RequestHeaders: user-agent for take the registration platform.
- **#3**  Add product to database. RequestBody: PRODUCTDTO.
- **#4**  View specific product information. PathVariable: id of product.
- **#5**  View list of all products with information, from all sellers.
- **#6**  Update seller information. Optional Params: name, address, email, phone, password, language.
- **#7**  Add to cart one product. Variable : id (id of product)
- **#8**  View all products in the Cart. 
- **#9**  Make the checkout of Cart. Cart put empty and create a new Order with random OrderId.
- **#10**  View all client orders.
- **#11**  Update product information. Optional Params: name, description, ean, price, stock.
- **#12**  Delete product. Variable : id (id of product)
- **#13**  View all Headers.
- **#14**  Create admin users. RequestBody: USER

## Tests:

- For this version, the tests were made for endpoints.


## Developers:

- Alfred -> (<a href="https://github.com/ainga-ri">GitHub Profile</a>)
- Andres -> (<a href="https://github.com/o-andres-m">GitHub Profile</a>)
- Ivan -> (<a href="https://github.com/IvanAmoros">GitHub Profile</a>)

## Canva Presentation:

- <a href="https://www.canva.com/design/DAFXoCunSSw/34rhWJenFkZxX-ZHz_HO-A/view?utm_content=DAFXoCunSSw&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink">Canva Presentation</a>

