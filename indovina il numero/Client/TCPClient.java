
//Importo i package necessari 
import java.net.*;
import java.io.*;

public class TCPClient {
    public void start() throws IOException {
        // Connessione della Socket con il Server
        Socket socket = new Socket("localhost", 7777);

        // Stream di byte da passare al Socket
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        DataInputStream is = new DataInputStream(socket.getInputStream());
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Per disconnettersi dal Server scrivere: QUIT\n");
        int numero = (int) (Math.random() * 100);
        // Ciclo infinito per inserimento testo del Client
        while (true) {
            System.out.print("Inserisci: ");
            String userInput = stdIn.readLine();
            if (userInput.equals("QUIT"))
                break;
            if (Integer.valueOf(userInput) == numero) {
                os.writeBytes("in numero inserito (" + userInput + ") e' corretto\n");
            } else if (Integer.valueOf(userInput) > numero) {
                os.writeBytes("in numero inserito (" + userInput + ") e' troppo grande\n");
            } else if (Integer.valueOf(userInput) < numero) {
                os.writeBytes("in numero inserito (" + userInput + ") e' troppo piccolo\n");
            }
        }

        // Chiusura dello Stream e del Socket
        os.close();
        is.close();
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        TCPClient tcpClient = new TCPClient();
        tcpClient.start();
    }
}
