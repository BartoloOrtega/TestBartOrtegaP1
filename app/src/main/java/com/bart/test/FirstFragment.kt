package com.bart.test

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bart.test.databinding.FragmentFirstBinding
import com.bart.test.services.RestApiService
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            if(binding.txtPass.text.length>0 || binding.txtUser.text.length>0)
                addDummyUser()
            else{
                var t=Toast(activity)
                t.setText( "Favor de insertar Datos de usuario")
                t.duration= Toast.LENGTH_LONG
                t.show()
            }


        }




    }
    fun addDummyUser() {
        val apiService = RestApiService()
        val userInfo = UserLogin(usuario = binding.txtUser.text.toString().trim(), password = binding.txtPass.text.toString()   )

        apiService.loginUser(userInfo) {
            if (it?.success != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
                    val decode= Base64.decode(it.token, 1);
                val stringToken= String(decode)
                val gson= Gson();
                var usr= gson.fromJson(stringToken.split('}')[1]+"}", User::class.java)
                val bundle= Bundle()
                bundle.putString("titular",usr.titular)
                bundle.putString("solicitud",usr.solicitud)
                bundle.putString("token",it.token)


                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

                val token=it

            } else {
                Log.d("","Error registering new user")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}