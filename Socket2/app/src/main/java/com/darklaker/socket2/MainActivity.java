package com.darklaker.socket2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    TextView edtIP, edtMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        edtIP = findViewById(R.id.edtIP);
        edtMess = findViewById(R.id.edtMess);

        Thread myServer = new Thread(new Server());
        myServer.start();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client myClient = new Client();
                myClient.execute(
                        edtIP.getText().toString(),
                        edtMess.getText().toString()
                );
            }
        });
    }

    class Server implements Runnable {
        ServerSocket serverSocket;
        Socket socket;
        String message;
        DataInputStream dis;
        Handler handler = new Handler();

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(7777);
                while (true) {
                    socket = serverSocket.accept();
                    dis = new DataInputStream(socket.getInputStream());
                    message = dis.readUTF();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Message: " + message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Client extends AsyncTask<String, Void, String> {
        String ip, message;
        Socket socket;
        DataOutputStream dos;

        @Override
        protected String doInBackground(String... strings) {
            ip = strings[0];
            message = strings[1];
            try {
                socket = new Socket("192.168.0.4", 7777);

                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(message);
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}