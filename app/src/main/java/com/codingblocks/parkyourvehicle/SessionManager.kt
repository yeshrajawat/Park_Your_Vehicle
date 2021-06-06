package com.codingblocks.parkyourvehicle

import android.content.Context
import android.content.SharedPreferences

class   SessionManager {
    public final val SESSION_USERSESSION : String  = "userLoginSession"


    lateinit var usersession:SharedPreferences
    lateinit var editor :SharedPreferences.Editor
    lateinit var context: Context

    private  final val is_login = "ISLoggedIn"
    public final val key_username = "fullname"
    public final val key_name = "name"
    public final val key_email = "email"
    public final val key_password = "password"
    public final val key_phone = "phone"
    public final val key_date_admitted = "date_admitted"
    public final val key_time_admitted = "time_admitted"
    public final val key_admittedby = "admittedby"

    //Remember me
    private  final val is_login_remember = "ISREMEMBERME"
    public final val remember_key_password = "password"
    public final val remember_key_username = "fullname"



    public constructor(context:Context)
    {
        usersession = context.getSharedPreferences("userLoginSession",Context.MODE_PRIVATE)
        editor = usersession.edit()

    }

    public fun createLoginSession(username:String,name:String,email:String,password:String,phone:String,date_admitted:String,time_admitted:String,admittedby:String)
    {
        editor.putBoolean(is_login,true)
        editor.putString(key_username,username)
        editor.putString(key_name,name)
        editor.putString(key_email,email)
        editor.putString(key_password,password)
        editor.putString(key_phone,phone)
        editor.putString(key_admittedby,admittedby)
        editor.putString(key_date_admitted,date_admitted)
        editor.putString(key_time_admitted,time_admitted)

        editor.commit()
    }
    public fun getUserDetailFromSession():HashMap<String,String>{
        var userdata = HashMap<String,String>()
        userdata.put(key_username,usersession.getString(key_username,null).toString())
        userdata.put(key_name,usersession.getString(key_name,null).toString())
        userdata.put(key_email,usersession.getString(key_email,null).toString())
        userdata.put(key_password,usersession.getString(key_password,null).toString())
        userdata.put(key_phone,usersession.getString(key_phone,null).toString())
        userdata.put(key_date_admitted,usersession.getString(key_date_admitted,null).toString())
        userdata.put(key_time_admitted,usersession.getString(key_time_admitted,null).toString())
        userdata.put(key_admittedby,usersession.getString(key_admittedby,null).toString())

        return userdata
    }








    public fun checkLogin():Boolean
    {
        if(usersession.getBoolean(is_login,false))
        {
            return true
        }
        else
        {
            return false
        }
    }
    public fun logout(){
        editor.clear()
        editor.commit()
    }
}