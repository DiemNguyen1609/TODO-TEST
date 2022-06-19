package com.test.todoapp.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.common.BaseFragment
import com.test.common.BaseViewModel
import com.test.domain.entities.BuyItemResult
import com.test.todoapp.R
import com.test.todoapp.feature.adapter.BuyAdapter
import com.test.todoapp.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_sell_list.*
import kotlinx.android.synthetic.main.fragment_sell_list.toolBar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SellListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SellListFragment : BaseFragment() {


    private val sellListFragmentArgs: SellListFragmentArgs by navArgs()
    private val viewModel by viewModel<HomeViewModel>()
    override fun getViewModel(): BaseViewModel = viewModel

    private lateinit var sellAdapter: BuyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_list, container, false)
    }


    override fun initControl() {
        toolBar.setOnBackButtonClickListener {
            backToPrevious()
        }
    }

    override fun initUI() {
        toolBar.setToolBarTitle(getString(R.string.sell_list))

        val listData: MutableList<BuyItemResult> = Gson().fromJson(
            sellListFragmentArgs.sellList,
            object : TypeToken<MutableList<BuyItemResult>>() {}.type
        )
            ?: mutableListOf<BuyItemResult>()
        if (listData.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.notify_empty_data))
        } else {
            sellAdapter = BuyAdapter(viewModel.parseBuyDataToCommonItem(listData))
            rcvSellList.adapter = sellAdapter
        }
    }

    override fun initEvent() {

    }

    override fun initConfig() {

    }


}