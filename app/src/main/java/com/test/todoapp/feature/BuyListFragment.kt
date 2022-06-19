package com.test.todoapp.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.common.BaseFragment
import com.test.common.BaseViewModel
import com.test.domain.entities.BuyItemResult
import com.test.todoapp.R
import com.test.todoapp.common.adapter.CommonAdapter
import com.test.todoapp.feature.adapter.BuyAdapter
import com.test.todoapp.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_buy_list.*
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

    private lateinit var buyAdapter: BuyAdapter

    override fun initControl() {

    }

    override fun initUI() {
        val listData: MutableList<BuyItemResult> = Gson().fromJson(
            buyListFragmentArgs.buyList,
            object : TypeToken<MutableList<BuyItemResult>>() {}.type
        )
            ?: mutableListOf<BuyItemResult>()
        if (listData.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.notify_empty_data))
        } else {
            buyAdapter = BuyAdapter(viewModel.parseBuyDataToCommonItem(listData))
            rcvBuyList.adapter = buyAdapter
        }
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