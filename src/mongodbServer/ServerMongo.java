package mongodbServer;

import cripto.Criptografia;

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

        try {
            server = new ServerSocket(12345);
            System.out.println("Servidor esta rodando na porta 12345");
            System.out.println("Aguardando conecao");
            socket = server.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Conecao realizada com " + socket.getInetAddress());
            while(true) {
                System.out.println("Aguardando mensagem do cliente: " + socket.getInetAddress());
                menReceber = in.readLine();
                menReceber = Criptografia.criptografar(menReceber);
                MongoDB.insertDocument(menReceber);
                System.out.println("Mensagem do cliente: " + menReceber);
                System.out.println("Resposta: ");
                menEnviar = Teclado.getString();
                menEnviar = Criptografia.criptografar(menEnviar);
                out.write(menEnviar);
                out.newLine();
                out.flush();
                MongoDB.insertDocument(menEnviar);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());;
        }finally {
            try {
                if(server != null) { server.close();}
            }catch (Exception e) {
                System.err.println("Error para fechar servidor");
            }
        }
    }
}
