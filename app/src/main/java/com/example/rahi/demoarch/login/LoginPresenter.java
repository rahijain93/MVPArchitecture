package com.example.rahi.demoarch.login;

import com.example.rahi.demoarch.data.remote.IDataResource;
import com.example.rahi.demoarch.permissionManager;

public class LoginPresenter implements IloginActivityContract.presenter {

    IDataResource.Remote remote;
    IloginActivityContract.View view;
    IloginActivityContract.Navigation navigation;
    permissionManager permissionManager;

    public LoginPresenter(IDataResource.Remote remote, IloginActivityContract.View view, IloginActivityContract.Navigation navigation,permissionManager permissionManager) {
        this.remote = remote;
        this.view = view;
        this.navigation = navigation;
        this.permissionManager=permissionManager;
    }

    @Override
    public void login(String name, String pwd) {

        permissionManager.requestpermissions(3001, "storage", new permissionManager.permissioncallback() {
            @Override
            public void onPermissionGranted() {
                if (view.isNameempty()) {
                    view.showToast("Please Enter Name");
                } else if (view.isNumberempty()) {
                    view.showToast("Please Enter Number");
                } else {
                    navigation.gotoListActivity();
                }




            }

            @Override
            public void onPermissionDenied() {
                view.showToast("All permission needed to go further");
            }
        });




    }


}
