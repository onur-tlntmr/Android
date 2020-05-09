package com.codexe.a3dtable.TestUtils;

import com.codexe.a3dtable.model.Address;
import com.codexe.a3dtable.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SampleDB {

    private static SampleDB __sampleDB=null;

    private ArrayList<User> data = new ArrayList<>();

    private SampleDB(){

    }

    public static SampleDB getInstance() {
        if(__sampleDB == null)
            __sampleDB = new SampleDB();
        return __sampleDB;
    }


    private User createAdminUser(){
        User user = new User();
        user.setName("Onur");
        user.setSur_name("T.");
        user.setMail("onurt@codexe.com");
        user.setPassword("1234");
        user.setPhone_number("5554443322");
        try {
            user.setBirth_date(new SimpleDateFormat("yyyy-MM-dd").parse("1998-07-21"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Address address = new Address();
        address.setCity("Diyarbakır");
        address.setDetails("Falanca mahallesi falanca sokak no:0/0 Bağlar");
        user.setAddress(address);
        return user;
    }

    public User getUser(String userMail) {

        User user = null;

        if(userMail.isEmpty() || userMail.equals("onurt@codexe.com")){
            user =  createAdminUser();
        }

        else{
            for (User u:data) {

                if(u.getMail().equals(userMail))
                    user = u;
            }
        }

        return user;

    }

    public void insertUser(User user) {
        data.add(user);
    }

    public boolean isRegister(String mail, String passwd) {


        return true;

    }
}
