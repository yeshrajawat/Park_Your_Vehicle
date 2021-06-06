package com.codingblocks.parkyourvehicle

import android.app.ProgressDialog
import android.app.StatusBarManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.notification.StatusBarNotification
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject


class Login_Page : AppCompatActivity() {

    lateinit var topAnim: Animation
    lateinit var botAnim : Animation
    val url = "https://parkyourvehicle.000webhostapp.com/login1.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContentView(R.layout.activity_main)
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        login_image.animation = botAnim
        loginwelcome.animation = botAnim
        loginquote.animation = botAnim

    }
    fun signinpage(view: View)
    {
        val intent = Intent(view.context,SignIn::class.java)
        startActivity(intent)
        finish()
    }

    fun loginbtn(view: View) {

        val progressdialog = ProgressDialog(view.context)
        progressdialog.setMessage("Please Wait..")
        if(login_password.editText?.text.toString().equals(""))
        {
            Toast.makeText(view.context,"Enter password",Toast.LENGTH_SHORT).show()
        }
        else
        {
            progressdialog.show()




            val username = login_username.editText?.text.toString().trim()

            val password = login_password.editText?.text.toString().trim()


            val request = object : StringRequest(Request.Method.POST, url, Response.Listener{
                response ->
//                if(response.toString().equals("success")) {
//                Toast.makeText(view.context, response.toString(), Toast.LENGTH_SHORT).show()
//
//                progressdialog.dismiss()
//                login_username.editText?.setText("")
//
//                login_password.editText?.setText("")
//                val intent = Intent(this,Dashboard::class.java)
//                intent.putExtra("Username",username)
//                startActivity(intent)
//                finish()
//            }
                val jsonobject = JSONObject(response)
                val success = jsonobject.getString("success")
                val jsonArray = jsonobject.getJSONArray("data")
                Log.e("TAG", "$success")
                if (success.equals("valid User")) {
                    for (x in 0..jsonArray.length()-1) {
                        val jsonobj2 = jsonArray.getJSONObject(x)
                        val getusername = jsonobj2.getString("username")
                        val getname = jsonobj2.getString("name")
                        val getemail = jsonobj2.getString("email")
                        val getpassword = jsonobj2.getString("password")
                        val getphone = jsonobj2.getString("phone")
                        val getdate_admitted = jsonobj2.getString("date_admitted")
                        val gettime_admitted = jsonobj2.getString("time_admitted")
                        val getadmittedby = jsonobj2.getString("admittedby")

                        //Creating a  Session

                        val sessionManager:SessionManager = SessionManager(this)
                        sessionManager.createLoginSession(getusername,getname,getemail,getpassword,getphone,getdate_admitted,gettime_admitted,getadmittedby)
                        progressdialog.dismiss()
                        val intent = Intent(this,MyDashBoard::class.java)
                        Toast.makeText(this,"Successfully Logged In", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()

                    }
                }
                else{
                Toast.makeText(view.context, response.toString(), Toast.LENGTH_SHORT).show()
                progressdialog.dismiss()

                }}, Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(view.context, error.message.toString(), Toast.LENGTH_LONG).show()

                }
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap<String, String>()
//                    params["username"] = username
//                    params["name"] = name
//                    params["email"] = email
//                    params["password"] = password
//                    params["phone"] = number
                    params.put("username", username)
                    params.put("password", password)


                    return params
                }
            }

            val requestqueue = Volley.newRequestQueue(view.context)
            requestqueue.add(request)




        }
        }
    }

