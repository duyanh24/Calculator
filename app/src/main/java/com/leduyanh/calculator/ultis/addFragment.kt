package com.leduyanh.calculator.ultis

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

internal fun FragmentManager.addFragment(
    idContainer: Int,
    fragment: Fragment,
    isAddBackStack: Boolean,
    tag: String) {
        val transaction = this.beginTransaction()
        transaction.add(idContainer, fragment,tag)
        if (!isAddBackStack)
            transaction.disallowAddToBackStack()
        else
            transaction.addToBackStack(null)
        transaction.commit()
    }