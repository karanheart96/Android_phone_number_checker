package edu.uic.cs478.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberText;
    private static Boolean returning=false;
    private String phoneNumberRegex="\\(\\d{3}\\)(\\s)?(\\d{3})-(\\d{4})";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumberText= (EditText)findViewById(R.id.editText2);
        phoneNumberText.setText("");

    }

    public MainActivity() {
        super();

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void sendMessage(View view) {
        String phoneNumber=phoneNumberText.getText().toString();
        Pattern phoneNumberPattern=Pattern.compile(phoneNumberRegex);
       Matcher phoneNumberMatcher=phoneNumberPattern.matcher(phoneNumber);
        if(phoneNumberMatcher.find()) {
            String phoneNumberString = "smsto:" + phoneNumberMatcher.group(0);
            Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(phoneNumberString));
            messageIntent.putExtra("sms_body", "");
            returning = true;
            startActivity(messageIntent);
        }
        else
        {
            Toast toast=Toast.makeText(getApplicationContext(),"Invalid phone number, Try again",Toast.LENGTH_SHORT);
            toast.show();
            phoneNumberText.setText("");
            phoneNumberText.setHint("");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(returning) {
            phoneNumberText.setText("");
            phoneNumberText.setHint("Returning from Compose Message...");
            returning=false;
        }

    }
}
