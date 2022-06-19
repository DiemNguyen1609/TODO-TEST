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
import com.test.domain.entities.CallItemResult
import com.test.todoapp.R
import com.test.todoapp.common.adapter.CommonAdapter
import com.test.todoapp.feature.adapter.CallAdapter
import com.test.todoapp.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_buy_list.*
import kotlinx.android.synthetic.main.fragment_call_list.*
import kotlinx.android.synthetic.main.fragment_call_list.tabMode
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CallListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CallListFragment : BaseFragment() {

    private val callListFragmentArgs: CallListFragmentArgs by navArgs()
    private val viewModel by viewModel<HomeViewModel>()
    override fun getViewModel(): BaseViewModel = viewModel

    private lateinit var callAdapter: CallAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_list, container, false)
    }


    override fun initControl() {
        tabMode.setOnBackButtonClickListener {
            backToPrevious()
        }
    }

    override fun initUI() {

        tabMode.setToolBarTitle(getString(R.string.call_list))

        val listData: MutableList<CallItemResult> = Gson().fromJson(
            callListFragmentArgs.callList,
            object : TypeToken<MutableList<CallItemResult>>() {}.type
        )
            ?: mutableListOf<CallItemResult>()
        if (listData.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.notify_empty_data))
        } else {
            callAdapter = CallAdapter(viewModel.parseCallDataToCommonItem(listData))
            rcvCallList.adapter = callAdapter
        }
    }

    override fun initEvent() {

    }

    override fun initConfig() {

    }


}