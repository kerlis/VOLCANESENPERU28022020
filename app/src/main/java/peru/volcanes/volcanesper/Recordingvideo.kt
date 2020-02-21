package peru.volcanes.volcanesper

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

class Recordingvideo : AppCompatActivity() {

    lateinit var recording: Button
    val REQUEST_VIDEO_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordingvideo)
        recording = findViewById(R.id.recordvideo)
        recording.setOnClickListener {
            checkCameraHardware(applicationContext)
            //dispatchTakeVideoIntent()
        }
    }

    /** Check if this device has a camera */
    private fun checkCameraHardware(context: Context): Boolean {
        if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            Toast.makeText(this, "existe camara", Toast.LENGTH_LONG).show()
            return true
        } else {
            Toast.makeText(this, "no existe camara" , Toast.LENGTH_LONG).show()
            // no camera on this device
            return false
        }
    }

    /*
        private fun dispatchTakeVideoIntent() {
            Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
                takeVideoIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
                }
            }
        }
    */

}
