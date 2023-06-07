package com.cadenza.registationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] country = { "Division", "Account", "Logistics", "Packing", "Washing"};
    Button signupbtn,alreadacc;

    TextInputEditText mail,mobilenumber,pswd,u_name,e_id,e_nam;
    //TextInputEditText first_name,user_name,email,phonenumber,password;
    TextView wel;
    String divi;
    Spinner spin;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupbtn = (Button)findViewById(R.id.save_go);
        alreadacc = (Button)findViewById(R.id.login_alreadyacc);

        u_name = (TextInputEditText) findViewById(R.id.usernamefield);
        pswd = (TextInputEditText) findViewById(R.id.passwordfield);
        mail = (TextInputEditText) findViewById(R.id.emailfield);
        mobilenumber = (TextInputEditText) findViewById(R.id.mobilenumberfield);
        e_id = (TextInputEditText) findViewById(R.id.employeidfield);
        e_nam = (TextInputEditText) findViewById(R.id.employenamefield);


        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        db = openOrCreateDatabase("employee_base1", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS em_info(eid INTEGER, name VARCHAR, mai VARCHAR,usern VARCHAR," +
                "passw INTEGER, phone INTEGER, division TEXT); ");

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(u_name.getText().toString().isEmpty() ||pswd.getText().toString().isEmpty() || mail.getText().toString().isEmpty() ||
                        mobilenumber.getText().toString().isEmpty() || e_id.getText().toString().isEmpty()
                        || e_nam.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Invalid Information", Toast.LENGTH_SHORT).show();
                }

                else{

                    //check already email exist code-------------------

                    Cursor c1 = db.rawQuery("SELECT * FROM em_info WHERE mai = '"+mail.getText().toString()+"'",null );
                    Cursor c2 = db.rawQuery("SELECT * FROM em_info WHERE eid = '"+e_id.getText().toString()+"'",null );

                    if(c1.moveToFirst()){
                        Toast.makeText(getApplicationContext(), "Email is Already exist", Toast.LENGTH_SHORT).show();
                    }else if(c2.moveToFirst()){
                        Toast.makeText(getApplicationContext(), "Employee ID is Already exist", Toast.LENGTH_SHORT).show();
                    }else{

                        db.execSQL("INSERT INTO em_info VALUES('"+e_id.getText()+"','"+e_nam.getText()+"','"+
                                mail.getText()+"','"+u_name.getText()+"','"+pswd.getText()+"','"+mobilenumber.getText()+"','"+divi+"')");
                        Toast.makeText(getApplicationContext(), "Registered . Thank you", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "hii", Toast.LENGTH_SHORT).show();
                    }

                }

//                else{
//                    db.execSQL("INSERT INTO em_info VALUES('"+mail.getText()+"','"+e_id.getText()+"','"+
//                            e_nam.getText()+"','"+u_name.getText()+"','"+pswd.getText()+"','"+mobilenumber.getText()+"','"+divi+"')");
//                    Toast.makeText(getApplicationContext(), "Registered . Thank you", Toast.LENGTH_SHORT).show();
//                }

               // clear();

            }
        });

        alreadacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),loginForm.class);
                startActivity(intent);
            }
        });

    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        divi = spin.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clear(){
        u_name.setText("");
        pswd.setText("");
        mail.setText("");
        mobilenumber.setText("");
        e_id.setText("");
        e_nam.setText("");
    }
}