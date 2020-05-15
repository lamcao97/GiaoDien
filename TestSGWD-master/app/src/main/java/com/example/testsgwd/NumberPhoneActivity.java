package com.example.testsgwd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

public class NumberPhoneActivity extends AppCompatActivity {

    ImageView imageViewBack;
    TextView tv_numberphone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numberphone);

        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent(NumberPhoneActivity.this,AccountInformationActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                TextView tv_numberphone;

                tv_numberphone = (TextView) findViewById(R.id.tv_numberphone);
                tv_numberphone.setText(String.format("%s", account.getPhoneNumber() == null ? "":account.getPhoneNumber().toString()));
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }
}
