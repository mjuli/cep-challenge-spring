# Desafio CEP Spring

Este projeto é uma aplicação Spring Boot que fornece o endpoint para buscar informações de endereço com base nos códigos postais brasileiros (CEP - Código de Endereçamento Postal). Os usuários podem consultar um CEP específico e obter os detalhes do endereço correspondente.

## Funcionalidades

- Endpoint RESTful para buscar informações de endereço com base no CEP.
- Endpoints de teste para salvar e buscar endereços no banco de dados PostgreSQL.
- Tratamento de erros para CEPs inválidos ou não encontrados.
- Integração com a API ViaCEP para recuperar informações de endereço.

## Tecnologias Utilizadas

- Spring Boot: Framework de desenvolvimento de aplicações Java.
- Spring Web: Módulo do Spring para desenvolvimento de aplicativos da web.
- RestTemplate: Cliente HTTP usado para fazer chamadas RESTful.
- [API ViaCEP](https://viacep.com.br): API de consulta de CEP brasileiro usada para recuperar informações de endereço.

## Instalação e Execução

1. Clone o repositório:

```
git clone https://github.com/mjuli/cep-challenge-spring.git
```

2. Atualize os dados para a conexão com o banco no arquivo `application.properties`.

3. Navegue até o diretório do projeto:

```
cd cep-challenge-spring
```

4. Compile e execute o projeto usando o Maven:

```
mvn spring-boot:run
```

## Uso

1. Após a execução, a aplicação estará disponível em `http://localhost:8080`.

2. Você pode acessar o endpoint para buscar informações de endereço fornecendo um CEP válido:

```
GET http://localhost:8080/address/{cep}
```

Substitua `{cep}` pelo CEP desejado.

3. A resposta será um objeto JSON contendo os detalhes do endereço correspondente ao CEP fornecido.
