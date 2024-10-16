package mongodbServer;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {

    // **Importante**: Evite expor credenciais diretamente no código.
    // Considere utilizar variáveis de ambiente ou arquivos de configuração seguros.
    final static String connectionString = "mongodb+srv://viniciusmc6:123456qwerty@samples.dl1hq.mongodb.net/?retryWrites=true&w=majority&appName=Samples";

    private static final ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();

    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .serverApi(serverApi)
            .build();


    public static void insertDocument(String jsonDocument) throws Exception {
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            // Conectando ao banco de dados 'empresa'
            MongoDatabase database = mongoClient.getDatabase("mensagem");

            // Acessando a coleção 'empresas'
            MongoCollection<Document> collection = database.getCollection("mensagens");

            // Convertendo o JSON para um documento BSON
            Document document = Document.parse(jsonDocument);

            // Inserindo o documento na coleção
            collection.insertOne(document);
        } catch (MongoException e) {
            e.printStackTrace();
            throw new Exception("Erro ao conectar ou inserir o documento no MongoDB.", e);
        }
    }

}