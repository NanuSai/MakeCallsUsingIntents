package com.saiproject.accesscontacts;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    Button btnOpenUdemy;
    Button btnOpenGoogle;
    Button btnCall;
    Button btnDial;


    private static final int CALL_REQUEST_CODE = 1; //Request code related to calling

    private static  String[] PERMISSIONS = {

            Manifest.permission.CALL_PHONE

    };


    public static void verifyPermissionToCall(Activity activity){

        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.CALL_PHONE);

        if(permission != PackageManager.PERMISSION_GRANTED){
            //Prompt user to get permission

            ActivityCompat.requestPermissions(activity,PERMISSIONS,CALL_REQUEST_CODE);

        }
        
    }

    
    public void initialize(){
        
        btnCall = findViewById(R.id.btnCall);
        btnOpenGoogle = findViewById(R.id.btnOpenGoogle);
        btnDial = findViewById(R.id.btnAccessDialPad);
        btnOpenUdemy = findViewById(R.id.btnOpenUdemy);
        
        btnCall.setOnClickListener(this);
        btnOpenUdemy.setOnClickListener(this);
        btnOpenGoogle.setOnClickListener(this);
        btnDial.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){


            case R.id.btnOpenUdemy:

                Intent intent = new Intent(Intent.ACTION_VIEW); //Set implicit intent of view
                intent.setData(Uri.parse("https://www.udemy.com"));
                startActivity(intent);    //Expecting no feedback
                break;




            case R.id.btnOpenGoogle:

                String action=Intent.ACTION_VIEW;
                Intent intent1 = new Intent(action);
                intent1.setData(Uri.parse("https://www.google.com/"));
                startActivity(intent1);
                break;



            case R.id.btnCall:

                if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    //Prompt user to get permission

                    ActivityCompat.requestPermissions(this,PERMISSIONS,CALL_REQUEST_CODE);

                }
                else{

                    Intent intent2 = new Intent(Intent.ACTION_CALL);
                    intent2.setData(Uri.parse("tel:2313124143232"));
                    startActivity(intent2);

                }
                break;


            case R.id.btnAccessDialPad:

                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                startActivity(intent3);
                break;













        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        verifyPermissionToCall(this);
        initialize();


    }
}
