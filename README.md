Moto Connect API 🏍️🔌
Repositório: https://github.com/cashot01/moto-connect-api

## Solução Rfid
📍 Como usar RFID para resolver o desafio:
1. Identificação:
- Coloque uma etiqueta RFID em cada moto.
- Cada etiqueta terá um ID único associado no sistema àquela moto específica (modelo, placa, etc.).
2. Leitura e Mapeamento:
- Instale leitores RFID fixos nas entradas, saídas e pontos estratégicos do pátio.
- Quando a moto passa perto de um leitor, o sistema registra:
    - O ID da moto
    - O local do leitor (ex: Portão 1, Área A, Vaga 3)
    - O horário da leitura.
3. Sistema de Visualização:
- Uma interface web ou app que mostra um mapa do pátio.
- As posições são atualizadas em tempo real conforme as motos são lidas.
4. Integração:
- O sistema pode ainda integrar:
- Sensores IoT da moto (para status como ligado/desligado).
- API para gerenciamento dos dados.
- Banco de dados para armazenar histórico de movimentações.
 
🛠️ Componentes que você precisaria:
 
Componente	|  Função  |	Exemplos
Etiquetas RFID |	Identificação única da moto |	RFID UHF Tags resistentes para veículos
Leitores RFID |	Captura de presença e localização |	Leitores fixos UHF (como Impinj Speedway, ou opções mais baratas tipo leitor USB portátil)
Sistema Backend |	Processamento dos dados lidos |	API em .NET Core, Java Spring ou Node.js
Banco de Dados |	Armazenar dados de motos e movimentações |	Oracle (requisito do challenge)
Frontend	| Interface visual para mapa do pátio |	ReactJS, React Native ou aplicação web simples
 
 
🎯 Benefícios de usar RFID:
-Precisão alta (sem precisar de câmeras ou visão computacional para detectar todas as motos).
-Baixo custo operacional depois da implantação.
-Automação na atualização de posições sem intervenção humana.
-Escalável para 100+ filiais, como a Mottu quer.
 

📌 Visão Geral
API RESTful desenvolvida com Spring Boot para gerenciamento de usuários, motos, RFIDs e histórico de manutenção. A aplicação inclui filtros, paginação, cache, tratamento global de erros e documentação automática com Swagger.


## ✨ Funcionalidades

- CRUD completo para Usuários, Motos, RFIDs e Histórico de manutenção.
- Filtros e paginação com `Spring Data JPA Specifications`.
- Validações com `@Valid` e tratamento de erros com `@RestControllerAdvice`.
- Banco de dados em memória `H2` para testes.
- CORS configurado.
- Cache com `@Cacheable` e `@CacheEvict`.
- Documentação Swagger com OpenAPI.
- Seed de dados com `DatabaseSeeder`.

## 🧱 Estrutura do Projeto
src/main/java/br/com/fiap/moto_connect_api
│
├── config # Configurações como CORS e banco de dados
├── controller # Camada de controle com endpoints REST
├── exception # Manipulador global de exceções
├── model # Entidades JPA e enums
├── repository # Interfaces do Spring Data JPA
├── specification # Filtros dinâmicos com Specification
└── MotoConnectApiApplication.java

## 🧪 Testes com H2 Console

A aplicação utiliza o banco em memória H2 por padrão. Após iniciar a aplicação:

- Acesse o H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: (em branco)

---

## 🔍 Documentação Swagger

Acesse: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Visualize e teste todos os endpoints diretamente na interface Swagger UI.

---

## 🔁 Endpoints principais

### Usuários

- `GET /usuarios`
- `POST /usuarios`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

### Motos

- `GET /motos`
- `POST /motos`
- `PUT /motos/{id}`
- `DELETE /motos/{id}`

### RFIDs

- `GET /motos/rfids`
- `POST /motos/rfids`
- `PUT /motos/rfids/{id}`
- `DELETE /motos/rfids/{id}`

### Histórico de Moto

- `GET /motos/historicos`
- `POST /motos/historicos`
- `PUT /motos/historicos/{id}`
- `DELETE /motos/historicos/{id}`

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Passos
# Clone o repositório
git clone https://github.com/seu-usuario/moto-connect-api.git
cd moto-connect-api

# Build e run
./mvnw spring-boot:run

📦 Dependências principais
Spring Boot Web

Spring Data JPA

H2 Database

Validation

Springdoc OpenAPI (Swagger)

Lombok

Spring Cache



## INTEGRANTES 
- Cauan Aranega Schot Passos - 2TDSPG RM555466
- Mateus Henrque Souza - 2TDSPG RM558424
- Lucas de Assis Fialho - 2TDSPV RM557884

