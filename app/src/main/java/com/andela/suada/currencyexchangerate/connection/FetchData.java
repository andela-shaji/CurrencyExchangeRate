package com.andela.suada.currencyexchangerate.connection;

import android.os.AsyncTask;

/**
 * Created by suadahaji.
 */
public class FetchData extends AsyncTask<String, Integer, String> {
    private JsonCallback callback;

    public FetchData(JsonCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        HttpUtils httpUtils = new HttpUtils();
        return httpUtils.getData(url);
    }

    @Override
    protected void onPostExecute(String result) {
        callback.onJsonReceived(result);
    }
}
