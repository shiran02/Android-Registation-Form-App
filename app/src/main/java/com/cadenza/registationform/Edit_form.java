package com.cadenza.registationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Edit_form extends AppCompatActivity {

    ImageButton Edit_button,Find_buttton,Delete_button,Addphoto_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form);

        Edit_button = (ImageButton)findViewById(R.id.edit_btn);
        Find_buttton = (ImageButton)findViewById(R.id.find_btn);
        Delete_button = (ImageButton)findViewById(R.id.delete_btn);
        Addphoto_button = (ImageButton)findViewById(R.id.addphoto_btn);

        Edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Edit_detail.class);
                startActivity(intent);
            }
        });


        Find_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),find_detail.class);
                startActivity(intent);
            }
        });

//        Find_buttton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Edit_detail.class);
//                startActivity(intent);
//            }
//        });
//
//        Delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Edit_detail.class);
//                startActivity(intent);
//            }
//        });
//
//        Addphoto_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Edit_detail.class);
//                startActivity(intent);
//            }
//        });



    }
}