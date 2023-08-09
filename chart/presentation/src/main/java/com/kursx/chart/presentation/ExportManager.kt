package com.kursx.chart.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import androidx.core.content.FileProvider
import com.kursx.chart.presentation.model.RectPresentationModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

interface ExportManager {

    suspend fun createBitmap(view: View, rect: RectPresentationModel): Bitmap

    suspend fun saveBitmap(bitmap: Bitmap): Uri
}

class ExportManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ExportManager {

    override suspend fun createBitmap(view: View, rect: RectPresentationModel) = withContext(Dispatchers.Default) {
        val bitmap = Bitmap.createBitmap(
            rect.width.toInt(),
            rect.height.toInt(),
            Bitmap.Config.ARGB_8888,
        )
        val canvas = Canvas(bitmap).apply {
            translate(-rect.left, -rect.top)
        }
        withContext(Dispatchers.Main) {
            view.draw(canvas)
        }
        canvas.setBitmap(null)
        return@withContext bitmap
    }

    override suspend fun saveBitmap(bitmap: Bitmap): Uri = withContext(Dispatchers.IO) {
        val file = File(context.cacheDir, "export.png")

        file.outputStream().use { out ->
            val format = Bitmap.CompressFormat.PNG
            bitmap.compress(format, 100, out)
            out.flush()
        }

        return@withContext FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }
}
