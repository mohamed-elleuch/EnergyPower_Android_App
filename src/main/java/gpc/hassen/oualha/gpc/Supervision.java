package gpc.hassen.oualha.gpc;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Supervision extends AppCompatActivity {
Button cam,com;
Spinner capteur;
    LineChart mChart;
    ArrayList<String> listitems;
    ArrayAdapter<String> adapt;
    TextView UAC,UDC,IAC,IDC,Deb,freq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision);
        cam=(Button)findViewById(R.id.cam);
        com=(Button)findViewById(R.id.com);
        freq=(TextView)findViewById(R.id.freq);
        UAC=(TextView)findViewById(R.id.UAC);
        UDC=(TextView)findViewById(R.id.UDC);
        IAC=(TextView)findViewById(R.id.IAC);
        IDC=(TextView)findViewById(R.id.IDC);
        Deb=(TextView)findViewById(R.id.Deb);
        capteur=(Spinner)findViewById(R.id.capteur);
mChart=(LineChart)findViewById(R.id.chart1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.data, android.R.layout.simple_spinner_dropdown_item);
        capteur.setAdapter(adapter);

        final String m=getIntent().getStringExtra("matricule");
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Supervision.this,Camera.class);
                i.putExtra("matricule",m);
                startActivity(i);
            }
        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Supervision.this, Commande.class);
                startActivity(i);
            }
        });
        final ArrayList<Entry> values=new ArrayList<Entry>();
        capteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               final String item=capteur.getItemAtPosition(position).toString();


                StringRequest sr=new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/getdatasensors.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
try
{
    JSONArray js=new JSONArray(response);
    for(int i=0;i<js.length();i++)
    {
        JSONObject K=js.getJSONObject(i);
        String v=K.getString("valeur");
        //créer le graphe
        mChart.setTouchEnabled(true);
        mChart.setScaleEnabled(true);
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

//values (tableau des couples x,y) ->LineDataSet -> DataSet -> LineData ->mChart
        values.add(new Entry(Float.parseFloat(String.valueOf(i)), Float.parseFloat(v), getResources().getDrawable(R.drawable.forgetpassword)));
        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, item);
            set1.setDrawIcons(false);
            set1.setColor(Color.YELLOW);
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);
            // set data
            mChart.setData(data);
        }
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
                        para.put("sensor",item);
                        para.put("matricule",m);
                        return para;
                    }
                };
                RequestQueue rq=Volley.newRequestQueue(Supervision.this);
                rq.add(sr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    protected void onStart()
    {
        super.onStart();
        final String m=getIntent().getStringExtra("matricule");
        StringRequest sr=new StringRequest(Request.Method.POST, "http://192.168.1.2/Gpc/getdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
try
{
    JSONObject js=new JSONObject(response);
    String f=js.getString("freq");
    String iac=js.getString("iac");
    String idc=js.getString("idc");
    String uac=js.getString("uac");
    String udc=js.getString("udc");
    String d=js.getString("debit");
    freq.setText(f);
    IAC.setText(iac);
    UAC.setText(uac);
    IDC.setText(idc);
    UAC.setText(udc);
    Deb.setText(d);
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
                HashMap<String,String> para=new HashMap<>();
para.put("matricule",m);
                return para;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(Supervision.this);
        rq.add(sr);
    }
}
