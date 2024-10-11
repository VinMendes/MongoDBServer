# MongoDB Server e Client usando Criptografia

Nosso projeto funciona através de um cliente e um servidor que utilizam sockets para comunicar. Foi implementado Criptografia nas mensagens, que são armazenadas desta forma no nosso bando de dados MongoDB.

## Estrutura do Projeto

O projeto consiste nos seguintes componentes:

- **ServerMongo.java**: Um servidor que escuta conexões na porta 12345. Ele recebe mensagens do cliente e descriptografa, em seguida armazena as mensagens no MongoDB e responde de volta com uma mensagem criptografada.
- **ClientMongo.java**: Um cliente que envia mensagens ao servidor. As mensagens são criptografadas com AES antes do envio e descriptografadas ao serem recebidas.
- **AESCrypto.java**: A classe que contém os de criptografia e descriptografia.
- **MongoDB.java**: Uma classe utilitária para inserir documentos (mensagens) no MongoDB. Utiliza o driver MongoDB Java Driver.
  
## Dependências

O repositório já possui as dependências para rodar, estão na pasta libs:

- **bcprov-jdk15on-1.70.jar**: Biblioteca do Bouncy Castle - criptografia.
- **bson-5.2.0.jar**: Dependência do MongoDB para o formato BSON.
- **bson-record-codec-5.2.0.jar**: Dependência para codificação de registros BSON.
- **mongodb-driver-core-5.2.0.jar**: Driver MongoDB core para conexão e operações básicas.
- **mongodb-driver-sync-5.2.0.jar**: Driver MongoDB síncrono para operações de banco de dados.

## Como Executar o Projeto

### 1. SDK
- Em Project Structure, selecione o SDK 23.

### 2. Configurar o MongoDB
- Certifique-se de ter um cluster MongoDB configurado. Atualize a `connectionString` na classe `MongoDB.java` com suas credenciais corretas.

### 3. Compilar o Projeto
Use a IDE IntelliJ para rodar o projeto, executando em uma máquina o ServerMongo.java e em outra o ClienteMongo.java.
