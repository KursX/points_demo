package com.kursx.demo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kursx.chart.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.sendEvent(MenuViewModel.Event.LogScreenOpened)

        val navController = findNavController()
        view.findViewById<View>(R.id.chart_compose_button).setOnClickListener {
            navController.navigate(R.id.chart_compose_fragment)
        }
    }
}
