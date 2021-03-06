# Desafio Stoom - Backend

### Geral
O projeto foi desenvolvido utilizando a tecnologia Java com o framework Spring MVC.

Existe um arquivo chamado `Database.sql` que cria a tabela necessária para o funcionamento da aplicação. 
O banco de dados utilizado é SQL Server.

Para os testes unitários passarem, é preciso que a base esteja sem nenhum dado.

Usar o comando `./gradlew build` para gerar uma build do projeto.

Na classe `ConnectionManager.java`, alterar para o seu usuário e senha do banco.

Foi disponibilizado um Dockerfile para subir a aplicação em um container.
Para executar, basta realizar um build da aplicação e executar o seguinte comando na pasta raiz do projeto:
`docker build -t "stoom-tomcat" .`

### Endpoints

O caminho padrão da API é `/stoom` e existem os seguintes endpoints:
* `/create` recebe um JSON com os dados do endereço. Um exemplo de JSON estará disponível abaixo. Método: POST.
* `/read` não recebe nenhum dado, somente retorna todos os endereços cadastrados no sistema. Método: GET.
* `/update` recebe um JSON com os dados do endereço. Para que o update funcione, é necessário ter um ID diferente de 0 (zero). Método: POST.
* `/delete/{id}` recebe um ID na URL do request. Para que o delete funcione, é necessário ter um ID diferente de 0 (zero). Método: DELETE.

Atenção: Para todos os exemplos de JSON abaixo, os campos `complement`, `latitude` e `longitude` não são obrigatórios.

Exemplo de JSON para `/create`:
```json
{
  "streetName": "Av. Brigadeiro Faria Lima",
  "number": 3477,
  "complement": null,
  "neighbourhood": "Itaim Bibi",
  "city": "São Paulo",
  "state": "São Paulo",
  "country": "Brasil",
  "zipCode": "04538133",
  "latitude": "-23.586457",
  "longitude": "-46.681891"
}
```

Exemplo de JSON para `/update`:

```json
{
  "id": 1,
  "streetName": "Av. Brigadeiro Faria Lima",
  "number": 3477,
  "complement": null,
  "neighbourhood": "Itaim Bibi",
  "city": "São Paulo",
  "state": "São Paulo",
  "country": "Brasil",
  "zipCode": "04538133",
  "latitude": "-23.586457",
  "longitude": "-46.681891"
}
```
A mudança nos dois exemplos são que no de `/update` é necessário passar o ID.
