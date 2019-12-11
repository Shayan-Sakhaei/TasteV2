package ir.apptaste.android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.apptaste.android.App
import ir.apptaste.android.R
import ir.apptaste.android.di.DaggerMainActivityComponent
import ir.apptaste.android.model.api.ResultResponse
import ir.apptaste.android.utility.hideKeyboard
import ir.apptaste.android.view.adapter.ResultListAdapter
import ir.apptaste.android.view_model.MainViewModel
import ir.apptaste.android.view_model.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var etSearch: EditText
    private lateinit var etLimit: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnSearch: Button
    private lateinit var rvResultList: RecyclerView
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var mAdapter: ResultListAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var mViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mainActivityComponent = DaggerMainActivityComponent.builder()
            .appComponent((application as App).mAppComponent)
            .mainActivity(this)
            .build()

        mainActivityComponent.inject(this)


        etSearch = findViewById(R.id.etSearch)
        etLimit = findViewById(R.id.etLimit)
        radioGroup = findViewById(R.id.radioGroup)
        btnSearch = findViewById(R.id.btnSearch)
        rvResultList = findViewById(R.id.rvResultList)
        progressBar = findViewById(R.id.progressBar)
        rvResultList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvResultList.setHasFixedSize(true)
        rvResultList.adapter = mAdapter

        setUp()
    }

    private fun setUp() {

        progressBar.visibility = View.INVISIBLE

        btnSearch.setOnClickListener {

            this.hideKeyboard()
            progressBar.visibility = View.VISIBLE

            val userQuery = etSearch.text.toString()
            val userLimit = etLimit.text.toString()
            var userType = ""

            when (radioGroup.checkedRadioButtonId) {
                R.id.rbMovies -> {
                    userType = "movies"
                }
                R.id.rbMusic -> {
                    userType = "music"
                }
                R.id.rbGames -> {
                    userType = "games"
                }
                R.id.rbMixed -> {
                    userType = "mixed"
                }
            }

            mViewModel.fetchResult(userQuery, userType, userLimit)
            mViewModel.getResultList().observe(this,
                Observer<ArrayList<ResultResponse>> { resultList ->
                    mAdapter.addItems(resultList)
                    progressBar.visibility = View.INVISIBLE
                    rvResultList.visibility = View.VISIBLE
                })
        }
    }
}