package com.example.testsgwd;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

public class AccountInformationActivity extends AppCompatActivity {

    LinearLayout ly_option_fistname, ly_option_lastname, ly_option_phone, ly_option_adress;
    ImageView imageViewCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);

        ly_option_fistname = (LinearLayout) findViewById(R.id.ly_option_fistname);
        ly_option_fistname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountInformationActivity.this,FistnameActivity.class);
                startActivity(intent);
            }
        });

        ly_option_lastname = (LinearLayout) findViewById(R.id.ly_option_lastname);
        ly_option_lastname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountInformationActivity.this,LastnameActivity.class);
                startActivity(intent);
            }
        });

        ly_option_phone = (LinearLayout) findViewById(R.id.ly_option_phone);
        ly_option_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountInformationActivity.this,NumberPhoneActivity.class);
                startActivity(intent);
            }
        });

        ly_option_adress = (LinearLayout) findViewById(R.id.ly_option_adress);
        ly_option_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountInformationActivity.this,AdressActivity.class);
                startActivity(intent);
            }
        });

        imageViewCancel = (ImageView) findViewById(R.id.imageViewCancel);
        imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent(AccountInformationActivity.this,MainActivity.class);
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
