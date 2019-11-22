package in.text.project;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity{



    EditText email,password;
Button login;
String stremail,strpass;
    String latvalue,log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email=(EditText)findViewById(R.id.edtusername);
        password=(EditText)findViewById(R.id.edtpassword);

        Typeface face = Typeface.createFromAsset(getAssets(), "AlexBrush-Regular.ttf");
        email.setTypeface(face);
        password.setTypeface(face);


        login=(Button)findViewById(R.id.okbtn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stremail=email.getText().toString();
                strpass=password.getText().toString();
                Boolean invalid = isEmailValid( stremail );
              if (stremail.length()==0){
                 Toast.makeText(getApplicationContext(),"Enter the email id",Toast.LENGTH_LONG).show();
               }
               else if (!invalid){
                    Toast.makeText(getApplicationContext(), "Enter the vaild email id" , Toast.LENGTH_LONG).show();
                }
               else if (strpass.length()==0){
                  Toast.makeText(getApplicationContext(),"Enter the password",Toast.LENGTH_LONG).show();
              }
              else if (strpass.length()<=8){
                  Toast.makeText(MainActivity.this, "min 8 Letters must", Toast.LENGTH_SHORT).show();
              }
              else if (!isValidPassword(strpass)){
                  Toast.makeText(MainActivity.this, "Password Must Be on caps , Numberic and Symbol", Toast.LENGTH_SHORT).show();
              }
              else {
                  addNotification();
                  Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                  startActivity(intent);
                  finish();

              }


            }
        });





    }





    private void addNotification() {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("LOGIN")
                        .setContentText("Success");

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }



    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private Boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
