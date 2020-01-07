package com.android.plugins.wifidirect.library;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.OutputStream;

public class PeerConnection {
    private String ipAddr;
    private Socket clientSock;

    public PeerConnection(String ip) {
        try {
            ipAddr = ip;
            clientSock = new Socket();
            clientSock.bind(null);
            clientSock.connect(new InetSocketAddress(ip, 5336), 1000);
        }
        catch (Exception e) { e.printStackTrace(); }

    }

    public void sendMessage(String message) {
        try {
            OutputStream outputStream = clientSock.getOutputStream();
            byte[] msg = message.getBytes();
            outputStream.write(msg, 0, message.length());
        }
        catch (Exception e) { e.printStackTrace(); }

    }
}