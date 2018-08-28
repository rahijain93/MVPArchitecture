package com.example.rahi.demoarch;

public interface permissionManager {

    void requestpermissions(int resultcode,String permission,permissionManager.permissioncallback permissioncallback);

    interface permissioncallback {
        void onPermissionGranted();
        void onPermissionDenied();
    }
}
