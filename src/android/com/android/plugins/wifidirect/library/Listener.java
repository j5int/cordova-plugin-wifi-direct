package com.android.plugins.wifidirect.library;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.IOException;
import android.os.AsyncTask;
import android.util.Log;

public class Listener extends AsyncTask {

    @Override
    protected String doInBackground(Object... params) {
        try {

            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            ServerSocket serverSocket = new ServerSocket(5336);
            Socket client = serverSocket.accept();

            /**
             * If this code is reached, a client has connected and transferred data
             * Save the input stream from the client as a JPEG file
             */

            byte[] messageByte = new byte[1000];
            boolean end = false;
            String dataString = "";

            try {
                DataInputStream in = new DataInputStream(client.getInputStream());

                while(!end) {
                    int bytesRead = in.read(messageByte);
                    dataString += new String(messageByte, 0, bytesRead);
                    if (bytesRead < 1000) {
                        end = true;
                    }
                }
                System.out.println("MESSAGE: " + dataString);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            serverSocket.close();

            return dataString;

        } catch (IOException e) {
            Log.e("IOException Tag", e.getMessage());
            return null;
        }
    }

}
