package com.example.irsyad.latihan19;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> arrayAdapter;
    RatingBar Rtb;
    Button B_Submit;
    TextView txtLevel;
    TextView txtHarga;
    Spinner Sp_Makanan;
    Spinner SP_Minuman;
    ArrayList<String> arrayList_Type;
    ArrayAdapter<String> arrayAdapter_Type;
    ArrayList<String> arrayList_Minuman;
    ArrayAdapter<String> arrayAdapter_Minuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inisial();
        Atur_Spinner_Makanan();
        Atur_Spinner_Minuman();
        Listen_B_Submit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_exit) {
            AlertDialog.Builder A_Builder = new AlertDialog.Builder(MainActivity.this);
            A_Builder.setMessage("Yakin mau keluar dari sistem?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog Alert = A_Builder.create();
            Alert.setTitle("Confirmation");
            Alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void Inisial(){
        Sp_Makanan = (Spinner) findViewById(R.id.spinner_Makanan);
        SP_Minuman = (Spinner) findViewById(R.id.spinner_Minuman);
        Rtb = (RatingBar) findViewById(R.id.ratingBar_Rate);
        B_Submit = (Button) findViewById(R.id.button_Submit);
        txtLevel = (TextView) findViewById(R.id.textView_Level);
        txtHarga = (TextView) findViewById(R.id.textView_Harga);
    }

    private void Atur_Spinner_Minuman() {
        arrayList_Minuman = new ArrayList<>();
        arrayList_Minuman.clear();
        arrayList_Minuman.add("Es Kuah Micin");
        arrayList_Minuman.add("Alpukat Saus Padang");
        arrayList_Minuman.add("Jus Pare");
        arrayAdapter_Minuman = new ArrayAdapter<String>(this,R.layout.template_minuman,R.id.textView_Spinner_Minuman,arrayList_Minuman);
        arrayAdapter_Minuman.notifyDataSetChanged();
        SP_Minuman.setAdapter(arrayAdapter_Minuman);
    }

    private void Atur_Spinner_Makanan(){
        /*
        arrayAdapter = ArrayAdapter.createFromResource
                (this,R.array.makanan,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sp_Makanan.setAdapter(arrayAdapter);
        */
        arrayList_Type = new ArrayList<>();
        arrayList_Type.clear();
        arrayList_Type.add("Shoyu Ramen Ayam Pop");
        arrayList_Type.add("Miso Ramen Kuah Rendang");
        arrayList_Type.add("Shio Ramen Gulai Nangka");
        arrayAdapter_Type = new ArrayAdapter<String>(this,R.layout.template_spinner,R.id.textView_Spinner_Header,arrayList_Type);
        arrayAdapter_Type.notifyDataSetChanged();
        Sp_Makanan.setAdapter(arrayAdapter_Type);
    }

    private void Listen_B_Submit(){
        B_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama_Makanan = Sp_Makanan.getSelectedItem().toString();
                String Nama_Minuman = SP_Minuman.getSelectedItem().toString();
                double Rating =  Rtb.getRating();
                int Pedas = Harga_Pedas(Rating);
                int Makanan = Harga_Makanan(Nama_Makanan);
                int Minuman = Harga_Minuman(Nama_Minuman);
                int Harga_Total = Pedas + Makanan + Minuman;
                String Ket = Level_Pedas(Rating);
                txtLevel.setText("Level Pedas: " + Ket);
                txtHarga.setText("Harga Makanan: " + String.format("%,d",Harga_Total));
                Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int Harga_Makanan(String Nama_Makanan){
        int Harga = 0;
        if(Objects.equals(Nama_Makanan,"Shoyu Ramen Ayam Pop")){
            Harga = 50000;
        } else if(Objects.equals(Nama_Makanan,"Miso Ramen Kuah Rendang")){
            Harga = 55000;
        } else {
            Harga = 60000;
        }
        return Harga;
    }

    private int Harga_Minuman(String Nama_Minuman){
        int Harga = 0;
        if(Objects.equals(Nama_Minuman,"Es Kuah Micin")){
            Harga = 7500;
        } else if(Objects.equals(Nama_Minuman,"Alpukat Saus Padang")){
            Harga = 15000;
        } else {
            Harga = 6000;
        }
        return Harga;
    }

    private int Harga_Pedas(double Rating){
        int Harga = 0;
        if (Rating > 4) {
            Harga = 10000;
        } else if (Rating > 3) {
            Harga = 8000;
        } else if (Rating > 2) {
            Harga = 6000;
        } else if (Rating > 1) {
            Harga = 4000;
        } else {
            Harga = 2000;
        }
        return Harga;
    }

    private String Level_Pedas(double Rating){
        String Ket = "";
        if (Rating > 4) {
            Ket = "Pedas Lidah Tak Bertulang";
        } else if (Rating > 3) {
            Ket = "Pedas Nyonyor Jletot";
        } else if (Rating > 2) {
            Ket = "Pedas Membara";
        } else if (Rating > 1) {
            Ket = "Pedas Nyelekit";
        } else {
            Ket = "Pedas Biasa";
        }
        return  Ket;
    }

}
