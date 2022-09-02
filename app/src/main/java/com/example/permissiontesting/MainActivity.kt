package com.example.permissiontesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.permissiontesting.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dexterPermission.setOnClickListener {

            Dexter.withContext(this@MainActivity)
                .withPermissions(
                    *Utils.readPermissionPass,
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            Utils.showToast(this@MainActivity, "Allow Permission Read")
                        } else {
                            Utils.showToast(this@MainActivity, "Not Allow Permission Read")
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        list: List<PermissionRequest>,
                        permissionToken: PermissionToken
                    ) {
                        permissionToken.continuePermissionRequest()
                    }
                }).check()

        }

        binding.dexterPermissionCamera.setOnClickListener {
            Dexter.withContext(this@MainActivity)
                .withPermissions(
                    *Utils.cameraPermissionPass,
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            Utils.showToast(this@MainActivity, "Allow Permission Camera")
                        } else {
                            Utils.showToast(this@MainActivity, "Not Allow Permission Camera")
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        list: List<PermissionRequest>,
                        permissionToken: PermissionToken
                    ) {
                        permissionToken.continuePermissionRequest()
                    }
                }).check()
        }

        binding.dexterNetwork.setOnClickListener {
            Dexter.withContext(this@MainActivity)
                .withPermissions(
                    *Utils.netWorkPermissionPass,
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            Utils.showToast(this@MainActivity, "Allow Permission Network")
                        } else {
                            Utils.showToast(this@MainActivity, "Not Allow Permission Network")
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        list: List<PermissionRequest>,
                        permissionToken: PermissionToken
                    ) {
                        permissionToken.continuePermissionRequest()
                    }
                }).check()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}