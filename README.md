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

O projeto **Sales API** segue uma arquitetura em camadas, separando responsabilidades entre **Controllers, DTOs, Entities, Repositories e Services**.

```text
sales-api/
│
├── src/
│   └── main/
│       │
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── salesapi/
│       │               │
│       │               ├── controller/
│       │               │   └── SaleController.java
│       │               │
│       │               ├── dto/
│       │               │   ├── SaleDTO.java
│       │               │   └── ItemSaleDTO.java
│       │               │
│       │               ├── entity/
│       │               │   ├── Sale.java
│       │               │   └── ItemSale.java
│       │               │
│       │               ├── repository/
│       │               │   ├── SaleRepository.java
│       │               │   └── ItemSaleRepository.java
│       │               │
│       │               └── service/
│       │                   └── SaleService.java
│       │
│       └── resources/
│           └── application.properties
│
└── pom.xml
```

## Organização das Camadas

| Camada       | Responsabilidade                                                               |
| ------------ | ------------------------------------------------------------------------------ |
| `controller` | Recebe as requisições HTTP e disponibiliza os endpoints da API.                |
| `dto`        | Define os objetos utilizados para entrada e saída de dados da API.             |
| `entity`     | Representa as entidades persistidas no banco de dados.                         |
| `repository` | Responsável pelo acesso e operações de persistência no banco de dados.         |
| `service`    | Contém as regras de negócio e coordena as operações da aplicação.              |
| `resources`  | Contém configurações e recursos da aplicação, como o `application.properties`. |
| `pom.xml`    | Gerencia dependências, plugins e configurações do projeto Maven.               |

## Fluxo da Aplicação

De forma simplificada, uma requisição segue o seguinte fluxo:

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Banco de Dados
```

Os **DTOs** são utilizados para transportar os dados entre o cliente e a aplicação, enquanto as **Entities** representam os dados persistidos no banco.

## Exemplo de Responsabilidades

### `SaleController`

Responsável pelos endpoints relacionados às vendas.

```java
@RestController
@RequestMapping("/sales")
public class SaleController {
    // Endpoints de vendas
}
```

### `SaleService`

Responsável pelas regras de negócio relacionadas às vendas.

```java
@Service
public class SaleService {
    // Regras de negócio
}
```

### `SaleRepository`

Responsável pela persistência da entidade `Sale`.

```java
@Repository
public interface SaleRepository
        extends JpaRepository<Sale, Long> {
}
```

### `Sale` e `ItemSale`

Representam as entidades principais relacionadas ao processo de vendas:

```text
Sale
 │
 └── ItemSale
       ├── Produto
       ├── Quantidade
       ├── Valor Unitário
       └── Valor Total
```

## Tecnologias

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* PostgreSQL
* REST API



## API Endpoints
The API provides the following endpoints:

GET /hello - Welcome for test of user authenticated (all authenticated users)

POST /product - Register a new product (ADMIN access required).

POST /auth/login - Login into the App

POST /auth/register - Register new users for next acess.

POST /store/sales - Register of Sales.

Dúvidas e Suporte

Em caso de dúvidas, sugestões ou necessidade de suporte relacionadas ao projeto, entre em contato pelo e-mail:

📧 centroserra@gmail.com

Ao entrar em contato, sempre que possível, informe o assunto, a funcionalidade envolvida e uma descrição detalhada da dúvida ou problema.