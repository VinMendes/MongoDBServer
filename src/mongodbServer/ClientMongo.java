package mongodbServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientMongo {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in;
        BufferedWriter out;
        String ax;

        try {
            // Conectando ao servidor
            socket = new Socket("192.168.156.170", 12345);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("Conectado ao servidor.");

            // Loop para envio e recebimento de mensagens
            while (true) {
                System.out.print("Digite a mensagem para o servidor: ");
                String mensagemEnviar = Teclado.getString();
                ax = mensagemEnviar;

                // Criptografando a mensagem antes de enviar
                mensagemEnviar = Criptografia.criptografar(mensagemEnviar);

                // Enviando a mensagem para o servidor
                out.write(mensagemEnviar);
                out.newLine();
                out.flush();

                // Condição para encerrar
                if(ax.equals("Bye")) {
                    System.out.println("Conexao encerrada pelo cliente");
                    break;
                }

                // Recebendo a resposta do servidor
                String resposta = in.readLine();
                if (resposta != null) {
                    resposta = Criptografia.descriptografar(resposta);
                    System.out.println("Resposta do servidor: " + resposta);
                }

                // Condição para encerrar
                if (resposta.equals("Bye")) {
                    System.out.println("Encerrando conexão...");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro na conexão com o servidor: " + e.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (Exception e) {
                System.err.println("Erro ao fechar o socket: " + e.getMessage());
            }
        }
    }
}