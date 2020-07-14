package com.timelysoft.tsjdomcom.adapters.expense


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.timelysoft.tsjdomcom.ui.expense.ComingsFragment
import com.timelysoft.tsjdomcom.ui.expense.ExpenseFragment
import java.util.ArrayList

class ExpensesReceiptPager(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0->{
                ComingsFragment()
            }
            else ->{
                return ExpenseFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0-> "Приходы"
            else -> {
                return "Расходы"
            }
        }
    }
}