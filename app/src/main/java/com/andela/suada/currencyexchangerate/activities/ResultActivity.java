package com.andela.suada.currencyexchangerate.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.andela.suada.currencyexchangerate.R;
import com.andela.suada.currencyexchangerate.data.CurrencyReceiver;

public class ResultActivity extends AppCompatActivity {
    private TextView currencyValue, totalAmount, currencyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        currencyValue = (TextView) findViewById(R.id.tvCurrencyInitial);
        totalAmount = (TextView) findViewById(R.id.tvCurrencyResult);
        currencyType = (TextView) findViewById(R.id.currencyType);
        getResults(getIntent().getStringExtra("RATES"));

    }

    private void getResults(String trans) {
        String[] results = trans.split(":");
        currencyValue.setText(results[1]);
        currencyType.setText(results[0]);


        double conversionRate = CurrencyReceiver.list.get(results[0]);
        double currentValue = Double.parseDouble(results[1]);
        double conversion = currentValue * conversionRate;

        totalAmount.setText(Double.toString(conversion));
    }


}
