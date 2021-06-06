package com.codingblocks.parkyourvehicle

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_in.*
import com.android.volley.toolbox.StringRequest



class SignIn : AppCompatActivity() {
    val url = "https://parkyourvehicle.000webhostapp.com/register.php"
    lateinit var topAnim: Animation
    lateinit var botAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_sign_in)
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        botAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        signup_image.animation = botAnim
        signup_welcome.animation = botAnim
        signup_quote.animation = botAnim

        login_signin.setOnClickListener {
            val intent = Intent(this,Login_Page::class.java)
            startActivity(intent)
            finish()
        }
    }

//    fun signinbtn(view: View) {
//        if (signup_name.editText?.text.toString().trim().equals("")) {
//            Toast.makeText(view.context, "Enter you Name", Toast.LENGTH_SHORT).show()
//        } else if (signup_username.editText?.text.toString().trim().equals("")) {
//            Toast.makeText(view.context, "Enter you UserName", Toast.LENGTH_SHORT).show()
//        } else if (signup_Email.editText?.text.toString().trim().equals("")) {
//            Toast.makeText(view.context, "Enter you Email Address", Toast.LENGTH_SHORT).show()
//        } else if (signup_password.editText?.text.toString().trim().equals("")) {
//            Toast.makeText(view.context, "Enter you Password", Toast.LENGTH_SHORT).show()
//        } else if (signup_phone.editText?.text.toString().trim().equals("")) {
//            Toast.makeText(view.context, "Enter you Phone Number", Toast.LENGTH_SHORT).show()
//        } else {
//            val name = signup_name.editText?.text.toString().trim()
//            val username = signup_username.editText?.text.toString().trim()
//            val email = signup_Email.editText?.text.toString().trim()
//            val password = signup_password.editText?.text.toString().trim()
//            val number = signup_phone.editText?.text.toString().trim()
//
//            createuser(view.context, name, username, email, number, password);
//        }
//        }
//        fun createuser(context: Context, name: String, username: String, email: String, phone: String, password: String) {
//
//            val mRequestQueue = Volley.newRequestQueue(context)
//            // Progress
//            // Progress
//
//
//            val mStringRequest =
//                    object : StringRequest(Request.Method.POST, url, Response.Listener<String?>() {
//                        fun onResponse(response: String?) {
////                            try {
////                                val jsonObject = JSONObject(response)
////                                val success = jsonObject.getString("success")
////                                val message = jsonObject.getString("message")
////                                if (success == "1") {
////                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
////
////                                }
////                            } catch (e: JSONException) {
////                                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
////
////                            }
//                        }
//                    }, Response.ErrorListener() {
//                        fun onErrorResponse(error: VolleyError) {
//                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
//
//                        }
//                    }) {
//                        @Throws(AuthFailureError::class)
//                        override fun getParams(): Map<String, String> {
//                            val params = HashMap<String,String>()
////                            params["name"] = name
////                            params["email"] = email
////                            params["username"] = username
////                            params["phonenumber"] = phone
////                            params["password"] = password
//                            params.put("username", username)
//                            params.put("name", name)
//                            params.put("email", email)
//                            params.put("password", password)
//                            params.put("phone", phone)
//                            return params
//                        }
//                    }
//
//            mStringRequest.setShouldCache(false)
//            mRequestQueue.add(mStringRequest)
//        }






        //        siginbtn.setOnClickListener {
//           createuser(this,name,username,email,number,password)
//        }
        fun signinbtn(view: View) {
            val progressdialog = ProgressDialog(view.context)
            progressdialog.setMessage("Please Wait..")
            if (signup_name.editText?.text.toString().trim().equals("")) {
                Toast.makeText(view.context, "Enter you Name", Toast.LENGTH_SHORT).show()
            } else if (signup_username.editText?.text.toString().trim().equals("")) {
                Toast.makeText(view.context, "Enter you UserName", Toast.LENGTH_SHORT).show()
            } else if (signup_Email.editText?.text.toString().trim().equals("")) {
                Toast.makeText(view.context, "Enter you Email Address", Toast.LENGTH_SHORT).show()
            } else if (signup_password.editText?.text.toString().trim().equals("")) {
                Toast.makeText(view.context, "Enter you Password", Toast.LENGTH_SHORT).show()
            } else if (signup_phone.editText?.text.toString().trim().equals("")) {
                Toast.makeText(view.context, "Enter you Phone Number", Toast.LENGTH_SHORT).show()
            } else {
                
                val name = signup_name.editText?.text.toString().trim()
                val username = signup_username.editText?.text.toString().trim()
                val email = signup_Email.editText?.text.toString().trim()
                val password = signup_password.editText?.text.toString().trim()
                val number = signup_phone.editText?.text.toString().trim()


                val request = object : StringRequest(Request.Method.POST, url, Response.Listener<String?>() {
//                    fun onResponse(response: String?) {
//
//                        if(response.equals("success"))
//                        {
//                            Toast.makeText(view.context,response,Toast.LENGTH_SHORT).show()
//                            signup_username.editText?.setText("")
//                            signup_name.editText?.setText("")
//                            signup_password.editText?.setText("")
//                            signup_Email.editText?.setText("")
//                            signup_phone.editText?.setText("")
//                            signin_btn.isClickable = false
//                        }
//                        else
//                        {
//                            Toast.makeText(view.context,response,Toast.LENGTH_SHORT).show()
//                        }
//
//
//                    }
                    response -> Toast.makeText(view.context,response.toString(),Toast.LENGTH_SHORT).show()
                        Toast.makeText(view.context,response,Toast.LENGTH_SHORT).show()
                           signin_btn.isClickable = false
                        progressdialog.dismiss()
                    signup_username.editText?.setText("")
                            signup_name.editText?.setText("")
                            signup_password.editText?.setText("")
                            signup_Email.editText?.setText("")
                            signup_phone.editText?.setText("")
                            signin_btn.isClickable = false

                }, Response.ErrorListener() {
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
                        params.put("name", name)
                        params.put("email", email)
                        params.put("password", password)
                        params.put("phone", number)

                        return params
                    }
                }
                val requestqueue = Volley.newRequestQueue(view.context)
                requestqueue.add(request)



            }
        }
}













