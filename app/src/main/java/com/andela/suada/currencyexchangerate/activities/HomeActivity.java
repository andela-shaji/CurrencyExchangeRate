package com.andela.suada.currencyexchangerate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.andela.suada.currencyexchangerate.R;


public class HomeActivity extends AppCompatActivity {
    private EditText edExchangeValue;
    private Spinner spinnerCurrency;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edExchangeValue = (EditText) findViewById(R.id.etexchangeValue);
        spinnerCurrency = (Spinner) findViewById(R.id.currencySpinner);
        searchButton = (Button) findViewById(R.id.currencySearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueCurrency = edExchangeValue.getText().toString();
                if (!valueCurrency.isEmpty()) {
                    String sendRate = spinnerCurrency.getSelectedItem().toString() + ":" + valueCurrency;
                    Intent sendIntent = new Intent(getBaseContext(), ResultActivity.class);
                    sendIntent.putExtra("RATES", sendRate);
                    startActivity(sendIntent);

                }
            }
        });
    }

}



