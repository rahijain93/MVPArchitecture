package com.example.rahi.demoarch.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rahi.demoarch.AllRepositoryProvider;
import com.example.rahi.demoarch.R;
import com.example.rahi.demoarch.data.remote.RemoteDataSources;
import com.example.rahi.demoarch.databinding.ActivityLogin2Binding;
import com.example.rahi.demoarch.listmodule.ActivityList;
import com.example.rahi.demoarch.permissionManager;

import java.util.ArrayList;
import java.util.List;

public class ActivityLogin extends AppCompatActivity implements IloginActivityContract.View, IloginActivityContract.Navigation, permissionManager {

    ActivityLogin2Binding activityLogin2Binding;
    IloginActivityContract.presenter presenter;
    private permissioncallback permissioncallback;
    private int permission_request_code = 0;
    final int permissioncode = 3001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login2);
        activityLogin2Binding = DataBindingUtil.setContentView(this, R.layout.activity_login2);
        presenter = new LoginPresenter(AllRepositoryProvider.getRemoteDataRepo(), this, this, this);


        activityLogin2Binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(activityLogin2Binding.name.getText().toString(), activityLogin2Binding.number.getText().toString());
            }
        });

        //Log.v("Filessssssss","getAbsolutePath-----"+getFilesDir().getAbsolutePath());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public boolean isNameempty() {
        if (TextUtils.isEmpty(activityLogin2Binding.name.getText()))
            return true;

        return false;
    }

    @Override
    public boolean isNumberempty() {
        if (TextUtils.isEmpty(activityLogin2Binding.number.getText()))
            return true;

        return false;
    }


    @Override
    public void gotoListActivity() {
        Intent intent = new Intent(ActivityLogin.this, ActivityList.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestpermissions(int resultcode, String permission, permissioncallback permissioncallback1) {

        permissioncallback = permissioncallback1;
        permission_request_code = resultcode;

        if (checkPermissions(permission))
            permissioncallback.onPermissionGranted();
        else {


        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case permissioncode: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Log.v("permissionslist", "permissionslist" + permissions);
                    Log.v("permissionslist", "grantResults" + grantResults);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    // Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                    permissioncallback.onPermissionGranted();


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    // Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    permissioncallback.onPermissionDenied();
                }
                return;
            }

        }


    }


    private boolean checkPermissions(String permissionStorage) {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : getPermissionArray(permissionStorage)) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);

            }
        }

        Log.v("checkPermissions", "checkPermissions----------" + listPermissionsNeeded.toString());
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), permissioncode);
            return false;
        }
        return true;
    }


    private String[] getPermissionArray(String permission) {


        Log.v("getPermissionArray", "getPermissionArray");
        switch (permission) {
            case "storage":
                return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        }
        return new String[]{};

    }

}
