package com.example.proejct1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    Button button;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView1 = findViewById(R.id.textView);
        button = findViewById(R.id.button111);
        img = findViewById(R.id.iuimg);

        textView1.setText("fjweipfwefpejfopwjfpewfpjewqopfpejowfpfsejfpisefjpsfpj");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.iu2);
            }
        });


    }
}