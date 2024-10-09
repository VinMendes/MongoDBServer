package mongodbServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMongo {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket socket = null;
        BufferedReader in;
        BufferedWriter out;
        String menReceber = null;
        String menEnviar = null;
        String ax = null;

        try {
            server = new ServerSocket(12345);
            System.out.println("Servidor está rodando na porta 12345");
            System.out.println("Aguardando conexão");
            socket = server.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Conexão realizada com " + socket.getInetAddress());

            while (true) {
                System.out.println("Aguardando mensagem do cliente: " + socket.getInetAddress());
                menReceber = in.readLine();

                // Criar JSON válido para a mensagem recebida
                String jsonRecebida = "{ \"mensagemRecebida\": \"" + menReceber.replace("\"", "\\\"") + "\" }";
                MongoDB.insertDocument(jsonRecebida);

                // Descriptografar a mensagem recebida
                menReceber = Criptografia.descriptografar(menReceber);
                System.out.println("Mensagem do cliente: " + menReceber);

                // Condição para encerrar
                if(menReceber.equals("Bye")) {
                    System.out.println("Conexao encerrada pelo cliente");
                    break;
                }

                System.out.print("Resposta: ");
                menEnviar = Teclado.getString();
                ax = menEnviar;

                // Criptografar a mensagem a ser enviada
                menEnviar = Criptografia.criptografar(menEnviar);
                out.write(menEnviar);
                out.newLine();
                out.flush();

                // Criar JSON válido para a mensagem enviada
                String jsonEnviada = "{ \"mensagemEnviada\": \"" + menEnviar.replace("\"", "\\\"") + "\" }";
                MongoDB.insertDocument(jsonEnviada);

                // Condição para encerrar
                if(ax.equals("Bye")) {
                    System.out.println("Conexao encerrada pelo servidor");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (Exception e) {
                System.err.println("Erro ao fechar servidor");
            }
        }
    }
}