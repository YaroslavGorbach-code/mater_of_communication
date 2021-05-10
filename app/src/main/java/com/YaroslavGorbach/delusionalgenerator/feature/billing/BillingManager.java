package com.YaroslavGorbach.delusionalgenerator.feature.billing;

public interface BillingManager {
    String SKU_REMOVE_AD = "remove_ad";
    interface Callback{ void onAdRemoved(boolean isAdRemoved);}
    void showPurchasesDialog();
    void queryPurchases(Callback callback);
    void endConnection();
}

