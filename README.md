# Rota de Viagem - Solução#
## Consideraçoes ##
* O problema apresentado pode ser entendido como uma variação do problema do caixeiro viajante, estudado em teoria dos grafos. Dessa forma, foi feita uma implementação simplificada da estrutura dessa estrutura de dados.
* Para cálculo da melhor rota foi feita uma implementaçao do algoritmo de Djikstra, que devolve um novo grafo com a rota de menor custo.
* A implementaçao utilizada foi uma tentativa de utilizar o formato de arquitetura hexagonal, em que o dominio da aplicação é isolado, e as integrações com este são feitas através de portas e adaptadores, No caso aqui, o controller e o repositório, respectivamente.
* Para desenvolvimento da API utilizei o microframework javalin, devido a sua simplicidade de uso e agilidade, pois a quantidade e complexidade de endpoints utilizados nao justificava a utilizaçao de frameworks mais complexos.
* O servidor rest foi feito de maneira autocontida, ou seja, a própria aplicação embarca a complexidade do servidor.
* Além desse framework externo, tomei a liberdade de incluir também algumas libs referentes ao protocolo OpenAPI e Swagger-ui, para expor os endpoints criados de maneira amigável e com uma boa facilidade pra testar.
* Os teste unitários foram realizados através do JUnit.
## Rodando a aplicação ##
* A mesma aplicaçao pode ser utilizada como cli ou como servidor.
* O primeiro passo é buildar e empacotar a aplicação através do maven:
mvn clean package
* Caso nao tenha o maven instalado, pode-se utilizar a versão embarcada:
./mvnw clean package
### Rodando como CLI ###
* Uma vez empacotado, para rodar a aplicaçao como CLI:
$ java -jar target/rota-de-viagens-1.0-SNAPSHOT.jar rotas.csv
* Foi providenciado no código-fonte um arquivo .csv com as rotas iniciais. Porém, pode-se utilizar quaisquer arquivos que correspondam ao padrão.
### Rodando como servidor ###
$ java -cp target/rota-de-viagens-1.0-SNAPSHOT.jar com.bernardolobato.backendtest.AppServer rotas.csv
* Para rodar, entrar no endereço http://localhost:7000
* Para verificar a documentaçao: http://localhost:7000/swagger-ui
#### Rodando o servidor via Docker ####
* Com o docker-compose instalado
docker-compose up
* A porta utilizada é a mesma: 7000