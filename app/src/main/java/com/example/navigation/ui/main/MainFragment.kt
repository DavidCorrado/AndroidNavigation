package com.example.navigation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.navigation.R

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //Implicit vs explicit deep link https://developer.android.com/guide/navigation/navigation-deep-link#explicit
        // Pass data backwards ? https://developer.android.com/guide/navigation/navigation-programmatic#returning_a_result
        //Backsstack https://developer.android.com/guide/navigation/navigation-navigate#back-stack
        //https://medium.com/google-developer-experts/using-navigation-architecture-component-in-a-large-banking-app-ac84936a42c2
        //https://issuetracker.google.com/u/1/issues/156198834
        //https://medium.com/swlh/proper-back-stack-on-android-every-time-4a811f8ab78c
        view.findViewById<Button>(R.id.btn_reg1).setOnClickListener {
            //Fragment navigation action
            findNavController().navigate(R.id.action_mainFragment_to_registration1Fragment)
        }

        view.findViewById<Button>(R.id.btn_reg2).setOnClickListener {
            //Implicit deep link
            findNavController().navigate("navapp://open/reg2".toUri())
        }
        view.findViewById<Button>(R.id.btn_reg3).setOnClickListener {
            //Implicit Deep Link with Builder
            findNavController().navigate(
                NavDeepLinkRequest.Builder.fromUri("navapp://open/reg3".toUri()).build()
            )
        }
        view.findViewById<Button>(R.id.btn_reg4).setOnClickListener {
            //Implicit Deep Link
            findNavController().navigate(R.id.registration4Fragment)
        }
        view.findViewById<Button>(R.id.btn_reg5).setOnClickListener {
            NavDeepLinkBuilder(requireContext())
                .setGraph(R.navigation.main_nav_graph)
                .setDestination(R.id.registration5Fragment)
                .createPendingIntent().send()
        }
        view.findViewById<Button>(R.id.btn_reg5_global).setOnClickListener {
            //Global Action
            findNavController().navigate(R.id.action_global_to_registration5)
        }
    }
}