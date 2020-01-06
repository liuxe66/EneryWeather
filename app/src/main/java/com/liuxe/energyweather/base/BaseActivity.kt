package com.liuxe.energyweather.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.gyf.barlibrary.ImmersionBar
import com.liuxe.energyweather.R
import me.yokeyword.fragmentation.SupportActivity


/**
 *
 * @author  Lai
 *
 * @time 2019/9/18 13:58
 * @describe describe
 *
 */
abstract class BaseActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initStatusBar()
        init(savedInstanceState)

        /*Looper.myQueue().addIdleHandler {
            false
        }*/
    }


    inline fun <reified T : RecyclerView.Adapter<BaseViewHolder>> getAdapter(recyclerView: RecyclerView): T? {
        recyclerView.adapter?.apply {
            return this as T
        }
        return null
    }

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun init(savedInstanceState: Bundle?)

    private fun initStatusBar() {
        ImmersionBar.with(this)
            .flymeOSStatusBarFontColor(R.color.black)  //修改flyme OS状态栏字体颜色
            .statusBarDarkFont(true)
            .transparentStatusBar()
            .keyboardEnable(true).init()
    }


    fun setToolBar(
            toolBar: androidx.appcompat.widget.Toolbar,
            title: String?,
            needBackButton: Boolean = true
    ) {
        setSupportActionBar(toolBar)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(needBackButton)
            supportActionBar.setDisplayShowHomeEnabled(needBackButton)
            supportActionBar.title = ""
        }

        toolBar.title = title
//        val tvTitle = toolBar.findViewById<TextView>(com.lai.comicmtc_v2.R.id.ac_title)
//        tvTitle?.text = title

        if (needBackButton) {
            toolBar.setNavigationOnClickListener {
                finish()
            }
        }
    }




    fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

}