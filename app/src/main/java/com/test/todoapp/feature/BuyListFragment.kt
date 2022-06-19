package com.test.todoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.test.common.BaseFragment
import com.test.common.BaseViewModel
import com.test.todoapp.R
import com.test.todoapp.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [BuyListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuyListFragment : BaseFragment() {

    private val buyListFragmentArgs: BuyListFragmentArgs by navArgs()
    private val viewModel by viewModel<HomeViewModel>()
    override fun getViewModel(): BaseViewModel = viewModel

    override fun initControl() {

    }

    override fun initUI() {

    }

    override fun initEvent() {

    }

    override fun initConfig() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_list, container, false)
    }


}