
//Importo i package
import java.net.*;
import java.io.*;

//Creazione di una classe per il Multrithreading
class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
        System.out.println("  Stato    Tipo Richiesta  Porta Server  Porta Client  Indirizzo Cliernt\n");
    }

    // esecuzione del Thread sul Socket
    public void run() {
        try {
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String userInput = is.readLine();
                if (userInput == null || userInput.equals("QUIT"))
                    break;

                System.out.println("Il Client " + socket.getInetAddress() + " "
                        + socket.getPort() + " "
                        + socket.getLocalPort() + " ha scritto: " + userInput);
            }
            os.close();
            is.close();
            System.out.println("Ricezione una chiamata di chiusura da:\n" + socket + "\n");
            socket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}

// Classe Server per attivare la Socket
/*
 * public class TCPParallelServer {
 * public void start() throws Exception {
 * ServerSocket serverSocket = new ServerSocket(7777);
 * 
 * //Ciclo infinito di ascolto dei Client
 * while(true) {
 * System.out.println("In attesa di chiamate dai Client... ");
 * Socket socket = serverSocket.accept();
 * System.out.println("Ho ricevuto una chiamata di apertura da:\n" + socket);
 * ServerThread serverThread = new ServerThread(socket);
 * serverThread.start();
 * }
 * }
 * 
 * public static void main (String[] args) throws Exception {
 * TCPParallelServer tcpServer = new TCPParallelServer();
 * tcpServer.start();
 * }
 * }
 */