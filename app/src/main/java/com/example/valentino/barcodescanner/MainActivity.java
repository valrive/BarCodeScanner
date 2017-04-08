package com.example.valentino.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentino.barcodescanner.ZXing.IntentIntegrator;
import com.example.valentino.barcodescanner.ZXing.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button boton;
    TextView formato,valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button)findViewById(R.id.btnScanner);
        formato = (TextView)findViewById(R.id.txtFormato);
        valor = (TextView)findViewById(R.id.txtValor);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                scanIntegrator.initiateScan();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanFormat = scanningResult.getFormatName();
            String scanContent = scanningResult.getContents();

            formato.setText(scanFormat);
            valor.setText(scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
