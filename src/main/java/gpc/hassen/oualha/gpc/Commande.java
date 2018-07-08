package gpc.hassen.oualha.gpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Commande extends AppCompatActivity {
Button cam,sup;
    Button offvar,onvar,offal,onal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        cam=(Button)findViewById(R.id.cam);
        sup=(Button)findViewById(R.id.sup);
        onal=(Button)findViewById(R.id.onal);
        offal=(Button)findViewById(R.id.offal);
        onvar=(Button)findViewById(R.id.onvar);
        offvar=(Button)findViewById(R.id.offvar);
final String m=getIntent().getStringExtra("matricule");
        onal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest sr=new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/commande.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
try
{
    JSONObject js=new JSONObject(response);
    String r=js.getString("flag");
    if(r.equals("ok"))
    {
       Toast.makeText(Commande.this, "Alarme activée", Toast.LENGTH_SHORT).show();
    }
    else
    {

        Toast.makeText(Commande.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
    }
}
catch ( Exception e)
{}
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
                        para.put("type","alarme");
                        para.put("matricule",m);
                        para.put("etat","1");
                        return para;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(Commande.this);
                rq.add(sr);
            }
        });

        offal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest sr=new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/commande.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try
                                {
                                    JSONObject js=new JSONObject(response);
                                    String r=js.getString("flag");
                                    if(r.equals("ok"))
                                    {
                                        Toast.makeText(Commande.this, "Alarme désactivée", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                        Toast.makeText(Commande.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch ( Exception e)
                                {}
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
                        para.put("type","alarme");
                        para.put("matricule",m);
                        para.put("etat","0");
                        return para;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(Commande.this);
                rq.add(sr);
            }
        });

        onvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest sr=new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/commande.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try
                                {
                                    JSONObject js=new JSONObject(response);
                                    String r=js.getString("flag");
                                    if(r.equals("ok"))
                                    {
                                        Toast.makeText(Commande.this, "Variateur en marche", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                        Toast.makeText(Commande.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch ( Exception e)
                                {}
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
                        para.put("type","variateur");
                        para.put("matricule",m);
                        para.put("etat","1");
                        return para;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(Commande.this);
                rq.add(sr);
            }
        });

       offvar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/commande.php",
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               try {
                                   JSONObject js = new JSONObject(response);
                                   String r = js.getString("flag");
                                   if (r.equals("ok")) {
                                       Toast.makeText(Commande.this, "Variateur en arrêt", Toast.LENGTH_SHORT).show();
                                   } else {

                                       Toast.makeText(Commande.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                                   }
                               } catch (Exception e) {
                               }
                           }
                       }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }) {
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       HashMap<String, String> para = new HashMap<String, String>();
                       para.put("type", "variateur");
                       para.put("matricule",m);
                       para.put("etat", "0");
                       return para;
                   }
               };
               RequestQueue rq = Volley.newRequestQueue(Commande.this);
               rq.add(sr);
           }
       });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent i=new Intent(Commande.this,Camera.class);
                startActivity(i);
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Commande.this,Supervision.class);
                i.putExtra("matricule",m);
                startActivity(i);
            }
        });
    }
}
