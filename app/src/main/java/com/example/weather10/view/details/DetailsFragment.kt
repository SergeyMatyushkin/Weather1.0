package com.example.weather10.view.details

import androidx.lifecycle.ViewModelProvider
import com.example.weather10.viewmodel.ScreenState
import com.example.weather10.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import androidx.fragment.app.Fragment
import com.example.weather10.R
import com.example.weather10.databinding.FragmentDetailsBinding
import com.example.weather10.model.Weather
import com.google.android.material.snackbar.Snackbar


private const val PROCESS_ERROR = "Обработка ошибки"
private const val REQUEST_API_KEY = "X-Yandex-API-Key"
private const val MAIN_LINK = "https://api.weather.yandex.ru/v2/informers?"
private const val WEATHER_API_KEY = "8723e083-1328-4c61-8c0d-46dbd00e11fd"

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    //WeatherBundle мы получим во время создания фрагмента
    //и воспользуемся координатами для составления запроса на сервер
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { renderData(it) }
        //получаем данные из удаленного источника
        viewModel.requestWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    //обрабатываем состояние приложения и обеспечиваем корректное отображение на экране
    private fun renderData(appState: ScreenState) {
        //binding.viewDetailsFragment.visibility = View.VISIBLE
        //binding.loadingLayout.visibility = View.GONE
        when (appState) {
            is ScreenState.Success -> {
                binding.viewDetailsFragment.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is ScreenState.Loading -> {
                binding.viewDetailsFragment.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is ScreenState.Error -> {
                binding.viewDetailsFragment.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.viewDetailsFragment.showSnackBarDetail(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.requestWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    })

            }
        }
    }
    private fun View.showSnackBarDetail(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_SHORT
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    //отображвем данные
    private fun setWeather(weather: Weather) {
        val city = weatherBundle.city
        binding.cityName.text = city.city
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.weatherCondition.text = weather.condition
        //загружаем картинку города
        Picasso
            .get()
            .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
            .into(binding.headerIcon)

        // загружаем тучу и солнышко
        weather.icon?.let {
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                binding.weatherIcon
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        //наш ключ-константа,по которому будем находить бандл
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}



