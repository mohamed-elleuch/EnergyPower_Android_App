package gpc.hassen.oualha.gpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Camera extends AppCompatActivity {
WebView webView;
    Button com,sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://185.18.130.194:8001/view/view.shtml?id=107&imagepath=%2Fmjpg%2F1%2Fvideo.mjpg&size=1");
        sp=(Button)findViewById(R.id.sp);
        com=(Button)findViewById(R.id.com);
        final String m=getIntent().getStringExtra("matricule");
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Camera.this,Supervision.class);
                i.putExtra("matricule",m);
                startActivity(i);
            }
        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Camera.this,Commande.class);
                i.putExtra("matricule",m);
                startActivity(i);
            }
        });
    }
}
