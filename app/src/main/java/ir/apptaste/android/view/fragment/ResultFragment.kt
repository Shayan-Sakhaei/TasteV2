package ir.apptaste.android.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.apptaste.android.App

import ir.apptaste.android.R
import ir.apptaste.android.di.component.DaggerResultFragmentComponent
import ir.apptaste.android.model.api.ResultResponse
import ir.apptaste.android.model.persistence.ResultDao
import ir.apptaste.android.view.adapter.ResultListAdapter
import ir.apptaste.android.view_model.MainViewModel
import ir.apptaste.android.view_model.MainViewModelFactory
import javax.inject.Inject


class ResultFragment : Fragment() {

    @Inject
    lateinit var mMainViewModelFactory: MainViewModelFactory
    @Inject
    lateinit var mAdapter: ResultListAdapter
    @Inject
    lateinit var mResultDao: ResultDao

    private lateinit var mViewModel: MainViewModel
    private lateinit var rvResultList: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        rvResultList = view.findViewById(R.id.rvResultList)
        rvResultList.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvResultList.setHasFixedSize(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val resultFragmentComponent = DaggerResultFragmentComponent.builder()
            .appComponent((activity!!.application as App).mAppComponent)
            .resultFragment(this)
            .build()

        resultFragmentComponent.inject(this)

        mViewModel =
            ViewModelProviders.of(activity!!, mMainViewModelFactory)[MainViewModel::class.java]

        rvResultList.adapter = mAdapter

        mViewModel.getResultList().observe(viewLifecycleOwner,
            Observer<ArrayList<ResultResponse>> { resultList ->
                mAdapter.addItems(resultList)
                rvResultList.visibility = View.VISIBLE
            })

        setUp()
    }


    private fun setUp() {
        mAdapter.onItemClick = {
            mViewModel.setSelectedResultResponse(it)
            findNavController().navigate(R.id.action_resultFragment_to_detailFragment)
        }
    }

}
