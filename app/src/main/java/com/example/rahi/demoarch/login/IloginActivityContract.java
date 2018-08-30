package com.example.rahi.demoarch.login;

public interface IloginActivityContract {


    interface View {


        boolean isNameempty();

        boolean isNumberempty();

        void showToast(String message);

    }

    interface presenter {

        void login(String name, String pwd);

    }

    interface Navigation {

        void gotoListActivity();
    }
}
