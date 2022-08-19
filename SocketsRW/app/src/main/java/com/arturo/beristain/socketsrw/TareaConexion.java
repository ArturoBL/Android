package com.arturo.beristain.socketsrw;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TareaConexion extends AsyncTask<String,Void,Void> {
        Socket s;
        DataOutputStream dos;
        PrintWriter pw;
        private boolean interrupted = false;
        private BufferedReader in;
        private BufferedWriter out;

        @Override
        protected Void doInBackground(String... voids) {
            String message = voids[0];
            try {
                s = new Socket("192.168.0.6", 777);
                out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                
                //pw = new PrintWriter(s.getOutputStream());
                //pw.write(message);
                //pw.close();
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while(!interrupted) {
                    String line = in.readLine();
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        public void disconnect() throws IOException {
            interrupted = true;
            s.close();
        }
    }