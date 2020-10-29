package com.example.apppimagepicker;

import android.os.CountDownTimer;

public class MyCountDown{
    private static MyCountDown mInstance = null;
    private CountDownTimer mCountDownTimer;
    private OnListenerCountDown mOnListenerCountDown;

    private MyCountDown(){

    }
    public static MyCountDown getInstance(){
        if(mInstance == null){
            mInstance = new MyCountDown();
        }
        return mInstance;
    }
    public void startTimeOut(long totalTime, long countDownInterval){
        if(mCountDownTimer != null){
            mCountDownTimer.cancel();
        }
        mCountDownTimer = new CountDownTimer(totalTime,countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(mOnListenerCountDown!=null){
                    mOnListenerCountDown.onTick(millisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                if(mOnListenerCountDown!=null){
                    mOnListenerCountDown.onFinish();
                }
            }
        };
        mCountDownTimer.start();
    }
    public void stopCountDown(){
        if(mCountDownTimer != null){
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
    public void setOnListenerCountDown(OnListenerCountDown onListenerCountDown){
        this.mOnListenerCountDown = onListenerCountDown;
    }


}
