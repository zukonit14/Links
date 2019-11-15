package com.example.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Add_task extends AppCompatActivity {

    Product p1;
    private Button savebtn;
    private EditText title,subtitle,link;
    private RadioButton high1,low1,medium1;
    String title1,subtitle1,link1;
    boolean high,low,medium;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        savebtn=findViewById(R.id.save_btn);
        title=findViewById(R.id.task_title);
        subtitle=findViewById(R.id.task_subtitle);
        link=findViewById(R.id.task_link);
        high1=findViewById(R.id.radioButton1);
        medium1=findViewById(R.id.radioButton2);
        low1=findViewById(R.id.radioButton3);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title1=title.getText().toString();
                subtitle1=subtitle.getText().toString();
                link1=link.getText().toString();
                high=high1.isChecked();
                medium=medium1.isChecked();
                low=low1.isChecked();
                senddata();
            }
        });
    }
    public void senddata()
    {
        Intent intent = new Intent(Add_task.this,MainActivity.class);
        intent.putExtra("title",title1);
        intent.putExtra("subtitle",subtitle1);
        intent.putExtra("link",link1);
        intent.putExtra("high",high);
        intent.putExtra("medium",medium);
        intent.putExtra("low",low);
        startActivity(intent);
    }
}
