package com.example.videoplayer.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.videoplayer.MainActivity
import com.example.videoplayer.R
import com.example.videoplayer.ui.main.MainFragment

fun MainActivity.launchFragment(fromFragment: Fragment, newInstance: Fragment) {
    val tag: String = newInstance.javaClass.simpleName
    val transaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()

    if (newInstance.parentFragment != null) {
        fromFragment.parentFragment?.let { transaction.hide(it) }
    } else {
        transaction.hide(fromFragment)
    }
    if (isFragmentIsVisible(tag)) {
        return
    }
    transaction.add(R.id.main_container, newInstance)
        .addToBackStack(tag)
        .commitAllowingStateLoss()
}

fun MainActivity.attachMainFragment(mainFragment: Fragment) {
    this.supportFragmentManager.beginTransaction()
        .replace(R.id.main_container, mainFragment, MainFragment::class.java.simpleName)
        .commitAllowingStateLoss()
}

fun MainActivity.isFragmentIsVisible(tag: String): Boolean =
    supportFragmentManager.findFragmentByTag(tag)?.isVisible == true
