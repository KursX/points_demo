package com.kursx.chart.ui.compose

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.kursx.chart.presentation.ChartViewModel
import com.kursx.chart.presentation.ExportManager
import com.kursx.demo.common.ui.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChartComposeFragment : Fragment() {

    @Inject
    lateinit var exportManager: ExportManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val viewModel: ChartViewModel = hiltViewModel()
            NavHost(navController = rememberNavController(), startDestination = "main") {
                composable("main") { _ ->
                    MainScreen(
                        viewModel = viewModel,
                        openChooser = ::openChooser,
                        onClickExit = {
                            findNavController().popBackStack()
                        },
                    )
                }
            }
        }
    }

    private fun openChooser(uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
        }
        val message = getString(R.string.choose_application)
        val chooser = Intent.createChooser(intent, message)
        val resInfoList = requireContext().packageManager
            .queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            requireContext().grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION,
            )
        }
        startActivity(chooser)
    }
}
