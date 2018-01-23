package com.example.dell.driverapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button btn;
    EditText email_txt,password_txt;
    RadioButton on_track , on_leave;
    String email,password;
    AlertDialog.Builder d;
    RadioGroup radioGroup;
    String url="http://3af1d914.ngrok.io//driver//login7.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn=(Button)findViewById(R.id.btn_login);
        email_txt=(EditText) findViewById(R.id.input_email);
        password_txt=(EditText)findViewById(R.id.input_password);
        on_track=(RadioButton)findViewById(R.id.ontrack) ;

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        d=new AlertDialog.Builder(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=email_txt.getText().toString();
                password=password_txt.getText().toString();
                if(email.equals("")||password.equals("")) {
                   // d.setTitle("input field empty");
                   // displayAlert("enter valid username or password ");
                    Toast.makeText(Login.this, "enter valid username or password !",
                            Toast.LENGTH_LONG).show();


                }
                else if (radioGroup.getCheckedRadioButtonId() == -1)
                {
                   // d.setTitle("Please check onTrack Or onLeave");
                   // displayAlert("Check your status ");
                    Toast.makeText(Login.this, "Please check onTrack !",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONArray j=new JSONArray(response);
                                JSONObject o=j.getJSONObject(0);
                                String code=o.getString("code");

                                if(code.equals("login failed")){
                                    d.setTitle("login err");

                                   displayAlert(o.getString("Login Failed"));
                                }else{
                                    Intent i=new Intent(Login.this,MapsActivity.class);
                                    String id=o.getString("did");
                                    i.putExtra("did",id);
                                    startActivity(i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "User not available !",
                                   Toast.LENGTH_LONG).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> para=new HashMap<String, String>();
                            para.put("user_name",email);
                            para.put("password",password);
                            return para;



                        }
                    };
                    singleton.getSingleton_ins(Login.this).addtorequestqueue(stringRequest);
                }

            }
        });
    }
    public void displayAlert(String msg){
        d.setMessage(msg);
        d.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                email_txt.setText("");
                password_txt.setText("");
            }
        });

        AlertDialog a=d.create();
        a.show();
    }






}
