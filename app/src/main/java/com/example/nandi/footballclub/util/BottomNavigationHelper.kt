package com.example.nandi.footballclub.util

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log
import java.lang.reflect.Field

class BottomNavigationHelper {

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView){
        val menuView: BottomNavigationMenuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftMode: Field = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftMode.isAccessible = true
            shiftMode.setBoolean(menuView, false)
            shiftMode.isAccessible = false
            for (i in 0 until menuView.childCount){
                val item: BottomNavigationItemView = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                item.setChecked(item.itemData.isChecked)
            }
        }catch (e: NoSuchFieldException){
            Log.e("BNVHelper", "Unable", e)
        }catch (e: IllegalAccessException){
            Log.e("BNVHelper", "Unable", e)
        }
    }
}