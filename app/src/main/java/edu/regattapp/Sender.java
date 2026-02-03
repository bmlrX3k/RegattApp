package edu.regattapp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Sender extends Thread {

    private final static String TAG = Sender.class.getName();
    private String message;
    private String var;
    private Activity activity;
    private InetSocketAddress endpoint;

    public Sender(Activity activity, String ip, String message) throws IOException {
        Log.i(TAG,"Sender");

        this.activity = activity;
        this.endpoint = new InetSocketAddress(ip, 8080);
        this.message = message;

        this.start();
    }

    @Override
    public void run() {
        Socket socket = new Socket();
        try {
            this.connectToServer(socket);
            this.write(socket,message.getBytes());
            byte[] response = this.read(socket);
            Log.i(TAG,new String(response));
            socket.close();
            String result = new String(response);
            Intent intent = new Intent();
            intent.setClass(activity,PathActivity.class);
            intent.putExtra("path",result.toString());
            this.activity.startActivity(intent);
            this.activity.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer(Socket socket) throws IOException {
        socket.connect(this.endpoint);
    }

    public void write(Socket socket, byte[] request) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(request);
        outputStream.flush();
    }

    public byte[] read(Socket socket) throws IOException {
        byte[] response = new byte[8];
        InputStream inputStream = socket.getInputStream();
        inputStream.read(response);
        return response;
    }

}
