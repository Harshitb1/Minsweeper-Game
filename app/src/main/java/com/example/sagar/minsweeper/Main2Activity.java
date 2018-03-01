package com.example.sagar.minsweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    EditText e;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b=findViewById(R.id.b);
        e=findViewById(R.id.e);

        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        str=e.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("name",str);
        startActivity(intent);
    }
}
