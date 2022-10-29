package com.codezilla.taskmanager.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.codezilla.taskmanager.R
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var InterfaceReference:CommunicateAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        InterfaceReference=activity as CommunicateAuth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_signup2, container, false)
        var signupbutton = view.findViewById<Button>(R.id.btn_signup)
        var editTextEmail = view.findViewById<EditText>(R.id.edt_eml2)
        var editTextPass = view.findViewById<EditText>(R.id.edt_pass2)
        var name=view.findViewById<EditText>(R.id.edt_name)
        signupbutton.setOnClickListener{
            var email:String=editTextEmail.text.toString()
            var pass:String=editTextPass.text.toString()
            if(email.equals("")|| pass.equals("")||name.equals(""))
            { Toast.makeText(activity,"Enter email,password and name", Toast.LENGTH_SHORT).show() }
            else
            {
               InterfaceReference.SignUp(email,pass)
//                if(result==0)
//                { Toast.makeText(activity,"Enter valid email and password", Toast.LENGTH_SHORT).show() }

            }
        }

        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic fun newInstance(param1: String, param2: String) =
            SignupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}