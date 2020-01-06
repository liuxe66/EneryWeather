package com.liuxe.energyweather.utils

import android.util.Log

object LogUtils {
    /**
     * 日志输出级别NONE
     */
    val LEVEL_NONE = 0
    /**
     * 日志输出级别E
     */
    val LEVEL_ERROR = 1
    /**
     * 日志输出级别W
     */
    val LEVEL_WARN = 2
    /**
     * 日志输出级别I
     */
    val LEVEL_INFO = 3
    /**
     * 日志输出级别D
     */
    val LEVEL_DEBUG = 4
    /**
     * 日志输出级别V
     */
    val LEVEL_VERBOSE = 5

    /**
     * 日志输出时的TAG
     */
    private val mTag = "LogUtils"

    /**
     * 是否允许输出log
     */
    private var mDebuggable = LEVEL_VERBOSE//= BuildConfig.IS_SHOW_LOG ? LEVEL_VERBOSE : LEVEL_NONE;

    /**
     * 设置调试Log开关
     *
     * @param isEnable 是否允许log
     */
    fun setDebuggable(isEnable: Boolean) {
        mDebuggable = if (isEnable) LEVEL_VERBOSE else LEVEL_NONE
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    fun v(msg: String) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(mTag, msg)
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    fun d(msg: String) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(mTag, msg)
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    fun i(msg: String) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(mTag, msg)
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    fun w(msg: String) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(mTag, msg)
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    fun e(msg: String) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(mTag, msg)
        }
    }

    /**
     * 以级别为 w 的形式输出Throwable
     */
    fun w(tr: Throwable) {
        w("", tr)
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */
    fun w(msg: String, tr: Throwable) {
        Log.w(mTag, msg, tr)
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    fun e(tr: Throwable) {
        e("", tr)
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    fun e(msg: String?, tr: Throwable) {
        if (mDebuggable >= LEVEL_ERROR && null != msg) {
            Log.e(mTag, msg, tr)
        }
    }

    private val originStackIndex = 2

    /**
     * 获取当前方法所在的文件名
     *
     * @return 当前方法所在的文件名
     */
    fun getFileName(): String {
        return Thread.currentThread().stackTrace[originStackIndex].fileName
    }

    /**
     * 获取当前方法所在的Class名
     *
     * @return 当前方法所在的Class名
     */
    fun getClassName(): String {
        return Thread.currentThread().stackTrace[originStackIndex].className
    }

    /**
     * 获取当前方法名
     *
     * @return 当前方法名
     */
    fun getMethodName(): String {
        return Thread.currentThread().stackTrace[originStackIndex].methodName
    }

    /**
     * 获取当前代码执行处行数
     *
     * @return 当前代码执行处行数
     */
    fun getLineNumber(): Int {
        return Thread.currentThread().stackTrace[originStackIndex].lineNumber
    }
}