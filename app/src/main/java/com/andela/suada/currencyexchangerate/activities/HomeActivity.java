package com.andela.suada.currencyexchangerate.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.andela.suada.currencyexchangerate.R;
import com.andela.suada.currencyexchangerate.data.CurrencyCallback;
import com.andela.suada.currencyexchangerate.data.CurrencyReceiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


public class HomeActivity extends AppCompatActivity {
    private EditText edExchangeValue;
    private Spinner spinnerCurrency;
    private Button searchButton;
    private CurrencyReceiver reciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edExchangeValue = (EditText) findViewById(R.id.etexchangeValue);
        spinnerCurrency = (Spinner) findViewById(R.id.currencySpinner);
        searchButton = (Button) findViewById(R.id.currencySearch);
        reciever = new CurrencyReceiver(new CurrencyCallback() {
            @Override
            public void onCurrencyFetched(Map<String, Double> currencyList) {
                presentDataToSpinner(currencyList);
                System.out.println(currencyList);
            }
        });
        reciever.getCurrencyList();

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

    private void presentDataToSpinner(Map<String, Double> currencyList) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrayList);
        for (String rate : currencyList.keySet()) {
            arrayList.add(rate);
        }
        Collections.sort(arrayList);
        spinnerCurrency.setAdapter(arrayAdapter);
    }

    public void storeIntent() {
        Intent sendIntent = new Intent(getBaseContext(), ResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                12345, sendIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am =
                (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                23 * 60 * 60 * 1000,pendingIntent);
    }

}



