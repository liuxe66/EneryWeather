package com.liuxe.energyweather

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gyf.barlibrary.ImmersionBar
import com.liuxe.energyweather.base.BaseActivity
import com.liuxe.energyweather.utils.permission.PermissionUtil
import com.orhanobut.logger.Logger

class SplashActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_splash
    override fun init(savedInstanceState: Bundle?) {

    }


    private fun showPermissionDialog() {
        AlertDialog.Builder(this)
            .setMessage("应用需要开启定位权限，否则将退出应用！")
            .setPositiveButton("去开启", DialogInterface.OnClickListener { dialogInterface, i ->
                val setting = Intent()
                setting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                setting.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                setting.data = Uri.fromParts("package", getPackageName(), null)
                startActivity(setting)
            })
            .setNegativeButton("退出", DialogInterface.OnClickListener { dialogInterface, i ->
                finish()
            })
            .create()
            .show()
    }

    override fun onStart() {
        super.onStart()

        PermissionUtil.Builder(this)
            .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .setDenied {
                finish()
            }
            .setGrant {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
            .setNeverAskAgain {
                showPermissionDialog()
            }
            .request()
    }

}
