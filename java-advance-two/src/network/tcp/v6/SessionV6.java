package network.tcp.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV6 implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManger;
    private boolean closed = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManger) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManger = sessionManger;
        this.sessionManger.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 클라이언트로 부터 문자 받기
                String received = input.readUTF();
                log("client -> server: " + received);

                if (received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received + " world!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManger.remove(this);
            close();
        }
    }

    public synchronized void close() {
        if (closed) {
            return;
        }
        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }
}
