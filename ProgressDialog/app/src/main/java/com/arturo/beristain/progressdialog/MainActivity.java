package com.arturo.beristain.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            progressDialog = new ProgressDialog(MainActivity.this);
                                            progressDialog.show();
                                            progressDialog.setContentView(R.layout.progress_dialog);
                                            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                        }
                                    });


    }

    @Override
    public void onBackPressed() {
        //progressDialog.dismiss();
        finish();
    }
}