package gpc.hassen.oualha.gpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Authentification extends AppCompatActivity {
EditText login,password;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        ok=(Button)findViewById(R.id.ok);
        login=(EditText)findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String l,p;
                l=login.getText().toString();
                p=password.getText().toString();
                if(l.equals(""))
                {
                    login.setError("Champ Obligatoire");
                }
                else
                {
                    if(p.equals(""))
                    {
                        password.setError("Champ Obligatoire");
                    }
                    else
                    {
                        //verification dans la base de données sur serveur de GPC


                        StringRequest sr=new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/login.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
try
{
 //response: {"nom":"ali ben salah","matricule":125488}
    JSONObject js=new JSONObject(response);
    String n=js.getString("nom");
    String m=js.getString("matricule");
    if(n.equals("introuvable"))
    {
        Toast.makeText(Authentification.this, "Données incorrectes", Toast.LENGTH_SHORT).show();
    }
    else
    {
        Toast.makeText(Authentification.this, "Bienvenue "+n, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Authentification.this,Commande.class);
        i.putExtra("matricule",m);
        startActivity(i);
    }
}
catch(Exception e)
{

}
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                        {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> para=new HashMap<String, String>();
                                para.put("log",l);
                                para.put("pass",p);
                                return para;
                            }
                        };
                        RequestQueue rq= Volley.newRequestQueue(Authentification.this);
                        rq.add(sr);




                    }
                }
            }
        });

    }
}
