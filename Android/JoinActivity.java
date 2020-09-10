package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final EditText id_editText=(EditText)findViewById(R.id.Id_editText);
        final EditText pw_editText=(EditText)findViewById(R.id.Pw_editText);
        final EditText name_editText=(EditText)findViewById(R.id.Name_editText);
        final EditText email_editText=(EditText)findViewById(R.id.Email_editText);

        Button submit_button=(Button)findViewById(R.id.Submit_Button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String result,result2;

                    String id=id_editText.getText().toString();
                    String pw=pw_editText.getText().toString();
                    String name=name_editText.getText().toString();
                    String email=email_editText.getText().toString();

                    RegisterTask task=new RegisterTask();
                    result =task.execute(id,pw,email,name).get();

                    String[] arr=result.split("~");
                    result2=arr[1];
                    Log.d("test",result2);

                    boolean x=true;
                    if(result2.equals("FAIL"))
                            x=false;

                    if(x) {
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        Intent joinIntent = new Intent(JoinActivity.this, MainActivity.class);
                        JoinActivity.this.startActivity(joinIntent);
                    }
                        else
                        Toast.makeText(getApplicationContext(),"FAIL",Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    Log.i("DBTEST","ERROR..");

                }
                /*Intent mainIntent=new Intent(JoinActivity.this,MainActivity.class);
                JoinActivity.this.startActivity(mainIntent);*/
            }
        });
    }
}
