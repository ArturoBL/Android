package com.darklaker.sockettxtclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button btnConnect;
    TextView edtIP, edtPort, edtMessage;
    Client myClient;
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIP = findViewById(R.id.edtIP);
        edtPort = findViewById(R.id.edtPort);
        btnConnect = findViewById(R.id.btnConnect);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClient = new Client();

                myClient.execute(
                        edtIP.getText().toString(),
                        edtPort.getText().toString(),
                        edtMessage.getText().toString()
                );

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), "Error: "+edtMessage.getText().toString(), Toast.LENGTH_SHORT).show();
                    myClient.enviar(edtMessage.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    class Client extends AsyncTask<String, Void, String> {
        String ip;
        String msg;
        int port;
        Socket socket;


        @Override
        protected String doInBackground(String... strings) {
            DataOutputStream dos;
            ip = strings[0];
            port = Integer.parseInt(strings[1]);
            msg = strings[2];
            try {
                socket = new Socket(ip, port);

                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(msg);
                dos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        public Void enviar(String msg) throws IOException {
            DataOutputStream dos;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();
            return null;
        }

    }
}