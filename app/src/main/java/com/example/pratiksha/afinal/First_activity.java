package com.example.pratiksha.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by pratiksha on 22/9/17.
 */

public class First_activity extends AppCompatActivity
{

public Button b1;
public Button b2;
    public void init()
    {
        b1=(Button)findViewById(R.id.User);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent obj=new Intent(First_activity.this,LoginActivity.class);
                startActivity(obj);
            }
        });
        b2=(Button)findViewById(R.id.Supplier);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent obj1=new Intent(First_activity.this,LoginActivity2.class);
                startActivity(obj1);
            }
        });
    }

@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.first);
    init();

}

}
