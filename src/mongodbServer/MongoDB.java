package mongodbServer;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDB {

    // **Importante**: Evite expor credenciais diretamente no código.
    // Considere utilizar variáveis de ambiente ou arquivos de configuração seguros.
    final static String connectionString = "mongodb+srv://viniciusmc6:643ViniMendes!@samples.dl1hq.mongodb.net/?retryWrites=true&w=majority&appName=Samples";

    private static final ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();

    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .serverApi(serverApi)
            .build();

    public static String[] getNomes() throws Exception {
        List<String> nomes = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            // Conectando ao banco de dados 'sample_restaurants'
            MongoDatabase database = mongoClient.getDatabase("sample_restaurants");

            // Acessando a coleção 'restaurants'
            MongoCollection<Document> collection = database.getCollection("restaurants");

            // Executando a consulta para obter todos os documentos
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    String nome = doc.getString("name");
                    if (nome != null) {
                        nomes.add(nome);
                    }
                }
            }
        } catch (MongoException e) {
            e.printStackTrace();
            throw new Exception("Erro ao conectar ou consultar o MongoDB.", e);
        }
        return nomes.toArray(new String[0]);
    }

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