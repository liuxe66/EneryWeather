package com.liuxe.energyweather.base

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author  Lai
 *
 * @time 2019/9/22 16:58
 * @describe describe
 *
 */
abstract class BaseVMFragment : BaseFragment() {

    /**
     * 初始化默认的viewModel
     */
    inline fun <reified VM : BaseViewModel> createViewModel(): VM {
        val mViewModel = ViewModelProvider(requireActivity())[VM::class.java]
        mViewModel.mException.observe(requireActivity(), Observer {
            toastMessage(it)
        })
        return mViewModel
    }

    open fun toastMessage(throwable: Throwable) {
        if (!TextUtils.isEmpty(throwable.message)) {
            Toast.makeText(_mActivity, throwable.message, Toast.LENGTH_LONG).show()
        }
    }

    open fun onError(throwable: Throwable) {

    }
}