package com.example.apppimagepicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.apppimagepicker.databinding.ActivityRandomBinding;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    ActivityRandomBinding mBinding;
    String[] mArrayImage;
    int mImageRandom =-1;
    int mIndex = -1;
    int mScore =0;
    int REQUEST_CODE =123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRandomBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initView();
        randomImage();
        countDownTime();
        event();
    }

    private void event() {
        mBinding.imgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomActivity.this,ListImageActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    private void initView() {
        mBinding.tvScore.setText("Score: "+mScore);
    }

    private void countDownTime() {
        MyCountDown.getInstance().startTimeOut(5200,1000);
        MyCountDown.getInstance().setOnListenerCountDown(new OnListenerCountDown() {
            @Override
            public void onTick(long countDownInterval) {
                mBinding.tvTime.setText("Time: "+((countDownInterval/1000)-1)+"");
            }

            @Override
            public void onFinish() {
                showDialog();

            }
        });
    }
    private void showDialog(){
        Context context;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Bạn đã thua \n"+ "Bạn có muốn chơi lại ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mScore=0;
                mBinding.tvScore.setText("Score : "+mScore);
                randomImage();
                countDownTime();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }
    private void randomImage() {
        mArrayImage = getResources().getStringArray(R.array.array_name_image);
        mIndex = new Random().nextInt(mArrayImage.length);
        mImageRandom = getResources().getIdentifier(mArrayImage[mIndex],"drawable",getPackageName());
        mBinding.imgRandom.setImageResource(mImageRandom);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        MyCountDown.getInstance().stopCountDown();
        mBinding.tvTime.setText("Time : 0");
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            int idImageChoose = data.getIntExtra("idImage",-1);
            mBinding.imgPick.setImageResource(idImageChoose);
            if (idImageChoose == mImageRandom){
                Toast.makeText(this, "Chinh xac", Toast.LENGTH_SHORT).show();
                mBinding.tvScore.setText("Score : " + ++mScore);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        randomImage();
                        countDownTime();
                    }
                },2000);
            }else{
                Toast.makeText(this, "Ban da thua", Toast.LENGTH_SHORT).show();
                showDialog();
            }
        }else if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Ket thuc : ", Toast.LENGTH_SHORT).show();
            showDialog();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}