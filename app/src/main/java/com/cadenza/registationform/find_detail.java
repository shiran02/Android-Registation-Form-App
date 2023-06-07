package com.cadenza.registationform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class find_detail extends AppCompatActivity {

    Button find_id_btn;
    SQLiteDatabase db;
    EditText enter_id_field;
    TextInputEditText mail,mobilenumber,pswd,u_name,e_id,e_nam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);

        find_id_btn = (Button)findViewById(R.id.find_id_btn);
        enter_id_field = (EditText)findViewById(R.id.id_fild);

        u_name = (TextInputEditText) findViewById(R.id.usernamefield);
        pswd = (TextInputEditText) findViewById(R.id.passwordfield);
        mail = (TextInputEditText) findViewById(R.id.emailfield);
        mobilenumber = (TextInputEditText) findViewById(R.id.mobilenumberfield);
        e_id = (TextInputEditText) findViewById(R.id.employeidfield);
        e_nam = (TextInputEditText) findViewById(R.id.employenamefield);


        db = openOrCreateDatabase("employee_base1", Context.MODE_PRIVATE,null);

        //find button-------------------------------------------------------------------------------

        find_id_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(enter_id_field.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter The Employee ID",Toast.LENGTH_SHORT).show();

                }

                Cursor cursor = db.rawQuery("SELECT * FROM em_info WHERE eid ='" + enter_id_field.getText()+"'",null);

                if(cursor.moveToFirst()){
                    mail.setText(cursor.getString(2));
                    e_id.setText(cursor.getString(0));
                    e_nam.setText(cursor.getString(1));
                    u_name.setText(cursor.getString(3));
                    pswd.setText(cursor.getString(4));
                    mobilenumber.setText(cursor.getString(5));


                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Index No ",Toast.LENGTH_SHORT).show();
                    //clearInfor();
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                enter_id_field.setText("");

            }
        });
    }
}