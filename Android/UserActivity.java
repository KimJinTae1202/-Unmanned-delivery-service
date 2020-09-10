package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class UserActivity extends AppCompatActivity {
    Button email_button, streaming_button, unlock_button;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent userintent=getIntent();
        TextView textView=(TextView)findViewById(R.id.TextView);

        name=userintent.getExtras().getString("Id");
        textView.setText(name+" 's Delivery System");

        email_button=(Button)findViewById(R.id.Email_button);
        streaming_button=(Button)findViewById(R.id.Streaming_button);
        unlock_button=(Button)findViewById(R.id.Unlock_button);

        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailintent=new Intent(Intent.ACTION_SEND);
                emailintent.setType("plain/text");
                String[] address={"513bronze@gmail.com"};
                emailintent.putExtra(Intent.EXTRA_EMAIL,address);
                emailintent.putExtra(Intent.EXTRA_SUBJECT,"택배 수령 완료");
                emailintent.putExtra(Intent.EXTRA_TEXT,"보관 상품을 수령하였습니다.");
                startActivity(emailintent);
            }
        });
        streaming_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamingintent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.0.7:8080/stream.html"));
                startActivity(streamingintent);
            }
        });
        unlock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(){

                    @Override
                    public void run() {
                        try {
                            Socket s = new Socket("192.168.101.138", 7000);
                            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                            dos.writeUTF("~"+name);

                            //read input stream
                            DataInputStream dis2 = new DataInputStream(s.getInputStream());
                            InputStreamReader disR2 = new InputStreamReader(dis2);
                            BufferedReader br = new BufferedReader(disR2);//create a BufferReader object for input

                            dis2.close();
                            s.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                Toast.makeText(UserActivity.this,"문이 열렸습니다. 10초후에 닫힙니다.",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Toast.makeText(this,"Back button pressed",Toast.LENGTH_SHORT).show();
        showmessage();
    }
    public void showmessage(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Guidance");
        builder.setMessage("Do you want to quit?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
                finish();
            }
        });

        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

}
