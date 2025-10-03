package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws Exception {
        unKnownHostEx1();
        unKnownHostEx2();
        connectionRefused();
    }

    private static void unKnownHostEx1() throws Exception {
        try {
            Socket socket = new Socket("999.999.999.999", 12345);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void unKnownHostEx2() throws Exception {
        try {
            Socket socket = new Socket("google.gogo", 12345);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException {
        try {
            Socket socket = new Socket("localhost", 45678);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
