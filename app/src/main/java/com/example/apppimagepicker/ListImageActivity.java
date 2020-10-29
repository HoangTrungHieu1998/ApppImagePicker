package com.example.apppimagepicker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import com.example.apppimagepicker.databinding.ActivityListImageBinding;

import java.util.Arrays;
import java.util.Collections;

public class ListImageActivity extends AppCompatActivity {

    ActivityListImageBinding mBinding;
    String[] mArrayImage;
    int mIdImage = -1;
    int mColumn = 3;
    int mRow = 6;
    int mCount =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListImageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mArrayImage = getResources().getStringArray(R.array.array_name_image);


        Collections.shuffle(Arrays.asList(mArrayImage));
        for(int i = 0; i < mRow; i++){
            Context context;
            TableRow tableRow = new TableRow(this);
            for(int y = 0; y < mColumn; y++){
                final ImageView imageView = new ImageView(this);
                mIdImage = getResources().getIdentifier(mArrayImage[mCount++],"drawable",getPackageName());
                imageView.setImageResource(mIdImage);
                imageView.setTag(mIdImage);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListImageActivity.this,RandomActivity.class);
                        intent.putExtra("idImage",Integer.parseInt(imageView.getTag().toString()));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                tableRow.addView(imageView);
            }
            mBinding.tableLayout.addView(tableRow);
        }
        MyCountDown.getInstance().setOnListenerCountDown(new OnListenerCountDown() {
            @Override
            public void onTick(long countDownInterval) {

            }

            @Override
            public void onFinish() {
                finish();
            }
        });
    }
}