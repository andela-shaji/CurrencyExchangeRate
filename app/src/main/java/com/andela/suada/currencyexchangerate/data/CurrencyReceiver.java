package com.andela.suada.currencyexchangerate.data;


import com.andela.suada.currencyexchangerate.connection.FetchData;
import com.andela.suada.currencyexchangerate.connection.JsonCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by suadahaji.
 */
public class CurrencyReceiver {
    private CurrencyCallback currencyListCallback;
    public static Map<String, Double> list;
    private final static String URL = "https://openexchangerates.org/api/latest.json?app_id=c6c42ff66c0a44dfbb5af1358ddf6260";

    public CurrencyReceiver(CurrencyCallback currencyListCallback) {
        this.currencyListCallback = currencyListCallback;
    }

    public void getCurrencyList() {
        FetchData fetcher = new FetchData(jsonCallback);
        fetcher.execute(URL);
    }


    private JsonCallback jsonCallback = new JsonCallback() {
        @Override
        public void onJsonReceived(String json) {
            generateList(json);
        }

    };

    private void generateList(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json)
                    .getJSONObject("rates");
            Iterator<String> stringIterator = jsonObject.keys();
            HashMap<String, Double> currencyMap = new HashMap<>();
            while (stringIterator.hasNext()) {
                String key = stringIterator.next();
                Double value = Double.parseDouble(jsonObject.getString(key));
                currencyMap.put(key, value);
            }
            saveList(currencyMap);
           currencyListCallback.onCurrencyFetched(currencyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveList(Map<String, Double> currencyList) {
        CurrencyReceiver.list = currencyList;
    }
}
