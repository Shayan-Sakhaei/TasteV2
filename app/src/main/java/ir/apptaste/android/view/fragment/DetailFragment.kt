package ir.apptaste.android.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import ir.apptaste.android.App

import ir.apptaste.android.R
import ir.apptaste.android.di.component.DaggerDetailFragmentComponent
import ir.apptaste.android.view_model.MainViewModel
import ir.apptaste.android.view_model.MainViewModelFactory
import javax.inject.Inject


class DetailFragment : Fragment() {

    @Inject
    lateinit var mMainViewModelFactory: MainViewModelFactory

    lateinit var mMainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val detailFragmentComponent = DaggerDetailFragmentComponent.builder()
            .appComponent((activity!!.application as App).mAppComponent)
            .detailFragment(this)
            .build()

        detailFragmentComponent.inject(this)

        mMainViewModel =
            ViewModelProviders.of(activity!!, mMainViewModelFactory)[MainViewModel::class.java]
    }


}
