package com.example.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerviewpractice.R;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList <Product> productList;
    private TextView no_task_msg,links_title;
    private ImageView add_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        no_task_msg=findViewById(R.id.no_task);
        links_title=findViewById(R.id.links_title);
        add_link=findViewById(R.id.add_button);

        loaddata();
        add_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnotes();
            }
        });
        getdata();
        buildrecyclerview();
        savedata();
    }

    public void getdata()
    {
        String t1,t2,t3;
        Intent intent=getIntent();
        if(intent.getStringExtra("link")!=null)
        {
            int temp=3;
            if(intent.getBooleanExtra("high",false)==true)
            {
                temp=1;
            }
            else if(intent.getBooleanExtra("medium",false)==true)
            {
                temp=2;
            }
            else if(intent.getBooleanExtra("low",false)==true)
            {
                temp=3;
            }
            productList.add(new Product(intent.getStringExtra("title"),intent.getStringExtra("subtitle"),intent.getStringExtra("link"),temp));
            savedata();loaddata();buildrecyclerview();
        }
    }
    public void buildrecyclerview()
    {
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Collections.sort(productList,Product.StuRollno);
        productAdapter=new ProductAdapter(this,productList);
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                remove_item(position);
            }
            @Override
            public void onLinkClick(int position)
            {
                visit(position);
            }
        });
        //productAdapter=new ProductAdapter(this,productList);
        recyclerView.setAdapter(productAdapter);
    }
    public void addnotes()
    {
        Intent intent=new Intent(MainActivity.this,Add_task.class);
        startActivity(intent);
//        savedata();
//        loaddata();
//        buildrecyclerview();
    }

    public void remove_item(int position)
    {
        productList.remove(position);
        productAdapter.notifyItemRemoved(position);
        savedata();
        loaddata();
        buildrecyclerview();
    }
    public void visit(int position)
    {
        String http=productList.get(position).getLink();
        Uri uri= Uri.parse(http);
        Intent intent1=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent1);
    }

    public void savedata()
    {
        SharedPreferences sharedPreferences= getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(productList);
        editor.putString("task list",json);
        editor.apply();
    }
    public void loaddata()
    {
        SharedPreferences sharedPreferences= getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task list",null);
        Type type =new TypeToken<ArrayList<Product>>(){}.getType();
        productList=gson.fromJson(json,type);
        if(productList==null)
        {
            productList=new ArrayList<>();
        }
        if(productList.size()==0)
        {
            no_task_msg.setVisibility(View.VISIBLE);
        }
        else
        {
            no_task_msg.setVisibility(View.INVISIBLE);
        }
    }

}