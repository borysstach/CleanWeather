package it.codingjam.cleanweather.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.codingjam.cleanweather.domain.DomainComponentProvider
import it.codingjam.cleanweather.utils.observe
import it.codingjam.cleanweather.utils.viewModel
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject
import javax.inject.Provider

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel { viewModelProvider }

    @Inject
    lateinit var viewModelProvider: Provider<DetailViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val domainComponent = (application as DomainComponentProvider).domainComponent

        DaggerDetailComponent.builder().domainComponent(domainComponent).build().inject(this)

        setContentView(R.layout.activity_detail)

        viewModel.state.observe(this) {
            result.text = it
        }
    }
}