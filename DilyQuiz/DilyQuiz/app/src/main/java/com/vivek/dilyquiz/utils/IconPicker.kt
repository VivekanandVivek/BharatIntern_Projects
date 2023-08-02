package com.vivek.dilyquiz.utils

import com.vivek.dilyquiz.R

object IconPicker{
    val Icons = arrayOf(
        R.drawable.ic_icon1,
        R.drawable.ic_icon2,
        R.drawable.ic_icon3,
        R.drawable.ic_icon4,
        R.drawable.ic_icon5,
        R.drawable.ic_icon6,
        R.drawable.ic_icon7,
        R.drawable.ic_icon8,
        R.drawable.ic_icon9,
        R.drawable.ic_icon10,
    )
    var currentIconIndex = 0

    fun getIcon():Int{
        currentIconIndex = (currentIconIndex+1)% Icons.size
        return Icons[currentIconIndex]

    }
}