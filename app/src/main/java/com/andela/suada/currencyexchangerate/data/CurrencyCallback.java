package com.andela.suada.currencyexchangerate.data;

import java.util.Map;

/**
 * Created by suadahaji.
 */
public interface CurrencyCallback {
    void onCurrencyFetched(Map<String, Double> currencyList);
}
