package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id_editText;
    EditText pw_editText;
    Button join_button;
    Button login_button;
    CheckBox checkBox;
    String result,result2;
    Boolean x;
    String id,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_editText = (EditText) findViewById(R.id.Id_editText);
        pw_editText = (EditText) findViewById(R.id.Pw_editText);
        join_button = (Button) findViewById(R.id.Join_button);
        login_button = (Button) findViewById(R.id.Login_button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent joinIntent = new Intent(MainActivity.this, JoinActivity.class);
                MainActivity.this.startActivity(joinIntent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    id = id_editText.getText().toString();
                    pw = pw_editText.getText().toString();

                    JoinTask task = new JoinTask();
                    result = task.execute(id, pw).get();

                    String[] arr = result.split("~");
                    result2 = arr[1];
                    Log.d("test", result2);

                    if (result2.equals("SUCCESS")) {
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(MainActivity.this, UserActivity.class);
                        mainIntent.putExtra("Id", id);
                        MainActivity.this.startActivity(mainIntent);
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.i("DBTEST", "ERROR..");
                }
            }
        });
    }
}
