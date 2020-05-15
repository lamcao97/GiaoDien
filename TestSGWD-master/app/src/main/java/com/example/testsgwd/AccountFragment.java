package com.example.testsgwd;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

public class AccountFragment extends Fragment {

    private final static int REQUEST_CODE = 99;
    LinearLayout ly_login, ly_option_adress, ly_history;
    ImageView imageViewLogin;
    Button btnLogOut;
    TextView tv_login, tv_phone_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        ly_login = (LinearLayout) v.findViewById(R.id.ly_login);
        tv_phone_id = (TextView) v.findViewById(R.id.tv_phone_id);
        imageViewLogin = (ImageView) v.findViewById(R.id.imageViewLogin);
        btnLogOut = (Button) v.findViewById(R.id.btnLogOut);

        ly_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_phone_id.getText().toString().equals("")){
                    startLoginPage(LoginType.PHONE);
                }else{
                    startActivity(new Intent(getActivity(), AccountInformationActivity.class));
                }


            }
        });

        ly_option_adress = (LinearLayout) v.findViewById(R.id.ly_option_adress);
        ly_option_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AdressActivity.class));
            }
        });

        ly_history = (LinearLayout) v.findViewById(R.id.ly_history);
        ly_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountKit.logOut();
//                getActivity().finish();

                TextView tv_phone_id, tv_numberphone;

                tv_phone_id = (TextView) getView().findViewById(R.id.tv_phone_id);
                tv_phone_id.setText("");

                tv_numberphone = (TextView) getView().findViewById(R.id.tv_numberphone);
                tv_numberphone.setText("Liên hệ");
            }
        });


        return v;



    }

    @Override
    public void onResume() {
        super.onResume();
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                TextView tv_phone_id, tv_numberphone;

                tv_phone_id = (TextView) getView().findViewById(R.id.tv_phone_id);
                tv_phone_id.setText(String.format("ID %s", account.getId()));

                tv_numberphone = (TextView) getView().findViewById(R.id.tv_numberphone);
                tv_numberphone.setText(String.format("SĐT %s", account.getPhoneNumber() == null ? "":account.getPhoneNumber().toString()));
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
    }

    private void startLoginPage(LoginType phone) {
        Intent intent = new Intent(getActivity(), AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if(result.getError() != null){
                Toast.makeText(getActivity(), ""+result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            else if(result.wasCancelled()){
                Toast.makeText(getActivity(), "Hủy bỏ", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        }
    }
}



























