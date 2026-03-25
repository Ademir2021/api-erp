# API of Authentication users and WebToken

# API for Sales

   # O que será incluído:
    Entidades: Sale, ItemSale
    DTOs: SaleDTO, ItemSaleDTO
    Controller REST: SaleController
    Service: SaleService
    Repositórios: SaleRepository, ItemSaleRepository
    Banco: Postgres
    Dependências: Spring Web, Spring Data JPA, Database

    # Recursos
    - Impressão em pdf: Vamos usar OpenPDF (licença LGPL, gratuita e fácil de usar). Adicione ao seu pom.xml:

# Estruturas do projeto 
sales-api/
├── src/
│   └── main/
│       ├── java/com/example/salesapi/
│       │   ├── controller/salecontroller
│       │   │              └── SaleController.java
│       │   ├── dto/saleDTO
│       │   │       ├── SaleDTO.java
│       │   │       └── ItemSaleDTO.java
│       │   ├── entity/sale
│       │   │          ├── Sale.java
│       │   │          └── ItemSale.java
│       │   ├── repository/sale
│       │   │              ├── SaleRepository.java
│       │   │              └── ItemSaleRepository.java
│       │   └── service/sale
│       │               └── SaleService.java
│       └── resources/
│           └── application.properties
└── pom.xml


## API Endpoints
The API provides the following endpoints:

GET /hello - Welcome for test of user authenticated (all authenticated users)

POST /product - Register a new product (ADMIN access required).

POST /auth/login - Login into the App

POST /auth/register - Register new users for next acess.

POST /store/sales - Register of Sales.
