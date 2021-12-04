package com.example.wigilabs.repository.remote.retrofit.Comm;

public interface OnNotificationListener {
    void showTitle(String title);
    void showMessage(String msg);
    void showFinalDialog(String msg);
    void prepareProgress(int max);
    void showProgress(int max, int progress, boolean indeterminate);
}
