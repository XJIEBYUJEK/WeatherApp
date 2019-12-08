package com.reactivelabs.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reactivelabs.weatherapp.R
import com.reactivelabs.weatherapp.data.Weather
import com.reactivelabs.weatherapp.data.WeatherData
import com.reactivelabs.weatherapp.data.WeatherRepository
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchFragment(override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    companion object{
        const val CITY = "city"
        const val OVERALL = "title"
        const val DESCRIPTION = "description"
        const val HUMIDITY = "humidity"
        const val TEMP = "temp"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val repository =  WeatherRepository()


        super.onViewCreated(view, savedInstanceState)

        val regions = mutableMapOf(
            Pair("Andorra", "AD"),
            Pair("United Arab Emirates", "AE"),
            Pair("Afghanistan", "AF"),
            Pair("Antigua and Barbuda", "AG"),
            Pair("Anguilla", "AI"),
            Pair("Albania", "AL"),
            Pair("Armenia", "AM"),
            Pair("Angola", "AO"),
            Pair("Antarctica", "AQ"),
            Pair("Argentina", "AR"),
            Pair("American Samoa", "AS"),
            Pair("Austria", "AT"),
            Pair("Australia", "AU"),
            Pair("Aruba", "AW"),
            Pair("Åland Islands", "AX"),
            Pair("Azerbaijan", "AZ"),
            Pair("Bosnia and Herzegovina", "BA"),
            Pair("Barbados", "BB"),
            Pair("Bangladesh", "BD"),
            Pair("Belgium", "BE"),
            Pair("Burkina Faso", "BF"),
            Pair("Bulgaria", "BG"),
            Pair("Bahrain", "BH"),
            Pair("Burundi", "BI"),
            Pair("Benin", "BJ"),
            Pair("Saint Barthélemy", "BL"),
            Pair("Bermuda", "BM"),
            Pair("Brunei Darussalam", "BN"),
            Pair("Bolivia (Plurinational State of)", "BO"),
            Pair("Bonaire, Sint Eustatius and Saba", "BQ"),
            Pair("Brazil", "BR"),
            Pair("Bahamas", "BS"),
            Pair("Bhutan", "BT"),
            Pair("Bouvet Island", "BV"),
            Pair("Botswana", "BW"),
            Pair("Belarus", "BY"),
            Pair("Belize", "BZ"),
            Pair("Canada", "CA"),
            Pair("Cocos (Keeling) Islands", "CC"),
            Pair("Congo, Democratic Republic of the", "CD"),
            Pair("Central African Republic", "CF"),
            Pair("Congo", "CG"),
            Pair("Switzerland", "CH"),
            Pair("Côte dIvoire", "CI"),
            Pair("Cook Islands", "CK"),
            Pair("Chile", "CL"),
            Pair("Cameroon", "CM"),
            Pair("China", "CN"),
            Pair("Colombia", "CO"),
            Pair("Costa Rica", "CR"),
            Pair("Cuba", "CU"),
            Pair("Cabo Verde", "CV"),
            Pair("Curaçao", "CW"),
            Pair("Christmas Island", "CX"),
            Pair("Cyprus", "CY"),
            Pair("Czechia", "CZ"),
            Pair("Germany", "DE"),
            Pair("Djibouti", "DJ"),
            Pair("Denmark", "DK"),
            Pair("Dominica", "DM"),
            Pair("Dominican Republic", "DO"),
            Pair("Algeria", "DZ"),
            Pair("Ecuador", "EC"),
            Pair("Estonia", "EE"),
            Pair("Egypt", "EG"),
            Pair("Western Sahara", "EH"),
            Pair("Eritrea", "ER"),
            Pair("Spain", "ES"),
            Pair("Ethiopia", "ET"),
            Pair("Finland", "FI"),
            Pair("Fiji", "FJ"),
            Pair("Falkland Islands (Malvinas)", "FK"),
            Pair("Micronesia (Federated States of)", "FM"),
            Pair("Faroe Islands", "FO"),
            Pair("France", "FR"),
            Pair("Gabon", "GA"),
            Pair("United Kingdom of Great Britain and Northern Ireland", "GB"),
            Pair("Grenada", "GD"),
            Pair("Georgia", "GE"),
            Pair("French Guiana", "GF"),
            Pair("Guernsey", "GG"),
            Pair("Ghana", "GH"),
            Pair("Gibraltar", "GI"),
            Pair("Greenland", "GL"),
            Pair("Gambia", "GM"),
            Pair("Guinea", "GN"),
            Pair("Guadeloupe", "GP"),
            Pair("Equatorial Guinea", "GQ"),
            Pair("Greece", "GR"),
            Pair("South Georgia and the South Sandwich Islands", "GS"),
            Pair("Guatemala", "GT"),
            Pair("Guam", "GU"),
            Pair("Guinea-Bissau", "GW"),
            Pair("Guyana", "GY"),
            Pair("Hong Kong", "HK"),
            Pair("Heard Island and McDonald Islands", "HM"),
            Pair("Honduras", "HN"),
            Pair("Croatia", "HR"),
            Pair("Haiti", "HT"),
            Pair("Hungary", "HU"),
            Pair("Indonesia", "ID"),
            Pair("Ireland", "IE"),
            Pair("Israel", "IL"),
            Pair("Isle of Man", "IM"),
            Pair("India", "IN"),
            Pair("British Indian Ocean Territory", "IO"),
            Pair("Iraq", "IQ"),
            Pair("Iran (Islamic Republic of)", "IR"),
            Pair("Iceland", "IS"),
            Pair("Italy", "IT"),
            Pair("Jersey", "JE"),
            Pair("Jamaica", "JM"),
            Pair("Jordan", "JO"),
            Pair("Japan", "JP"),
            Pair("Kenya", "KE"),
            Pair("Kyrgyzstan", "KG"),
            Pair("Cambodia", "KH"),
            Pair("Kiribati", "KI"),
            Pair("Comoros", "KM"),
            Pair("Saint Kitts and Nevis", "KN"),
            Pair("Korea (Democratic Peoples Republic of)", "KP"),
            Pair("Korea, Republic of", "KR"),
            Pair("Kuwait", "KW"),
            Pair("Cayman Islands", "KY"),
            Pair("Kazakhstan", "KZ"),
            Pair("Lao Peoples Democratic Republic", "LA"),
            Pair("Lebanon", "LB"),
            Pair("Saint Lucia", "LC"),
            Pair("Liechtenstein", "LI"),
            Pair("Sri Lanka", "LK"),
            Pair("Liberia", "LR"),
            Pair("Lesotho", "LS"),
            Pair("Lithuania", "LT"),
            Pair("Luxembourg", "LU"),
            Pair("Latvia", "LV"),
            Pair("Libya", "LY"),
            Pair("Morocco", "MA"),
            Pair("Monaco", "MC"),
            Pair("Moldova, Republic of", "MD"),
            Pair("Montenegro", "ME"),
            Pair("Saint Martin (French part)", "MF"),
            Pair("Madagascar", "MG"),
            Pair("Marshall Islands", "MH"),
            Pair("North Macedonia", "MK"),
            Pair("Mali", "ML"),
            Pair("Myanmar", "MM"),
            Pair("Mongolia", "MN"),
            Pair("Macao", "MO"),
            Pair("Northern Mariana Islands", "MP"),
            Pair("Martinique", "MQ"),
            Pair("Mauritania", "MR"),
            Pair("Montserrat", "MS"),
            Pair("Malta", "MT"),
            Pair("Mauritius", "MU"),
            Pair("Maldives", "MV"),
            Pair("Malawi", "MW"),
            Pair("Mexico", "MX"),
            Pair("Malaysia", "MY"),
            Pair("Mozambique", "MZ"),
            Pair("Namibia", "NA"),
            Pair("New Caledonia", "NC"),
            Pair("Niger", "NE"),
            Pair("Norfolk Island", "NF"),
            Pair("Nigeria", "NG"),
            Pair("Nicaragua", "NI"),
            Pair("Netherlands", "NL"),
            Pair("Norway", "NO"),
            Pair("Nepal", "NP"),
            Pair("Nauru", "NR"),
            Pair("Niue", "NU"),
            Pair("New Zealand", "NZ"),
            Pair("Oman", "OM"),
            Pair("Panama", "PA"),
            Pair("Peru", "PE"),
            Pair("French Polynesia", "PF"),
            Pair("Papua New Guinea", "PG"),
            Pair("Philippines", "PH"),
            Pair("Pakistan", "PK"),
            Pair("Poland", "PL"),
            Pair("Saint Pierre and Miquelon", "PM"),
            Pair("Pitcairn", "PN"),
            Pair("Puerto Rico", "PR"),
            Pair("Palestine, State of", "PS"),
            Pair("Portugal", "PT"),
            Pair("Palau", "PW"),
            Pair("Paraguay", "PY"),
            Pair("Qatar", "QA"),
            Pair("Réunion", "RE"),
            Pair("Romania", "RO"),
            Pair("Serbia", "RS"),
            Pair("Russian Federation", "RU"),
            Pair("Rwanda", "RW"),
            Pair("Saudi Arabia", "SA"),
            Pair("Solomon Islands", "SB"),
            Pair("Seychelles", "SC"),
            Pair("Sudan", "SD"),
            Pair("Sweden", "SE"),
            Pair("Singapore", "SG"),
            Pair("Saint Helena, Ascension and Tristan da Cunha", "SH"),
            Pair("Slovenia", "SI"),
            Pair("Svalbard and Jan Mayen", "SJ"),
            Pair("Slovakia", "SK"),
            Pair("Sierra Leone", "SL"),
            Pair("San Marino", "SM"),
            Pair("Senegal", "SN"),
            Pair("Somalia", "SO"),
            Pair("Suriname", "SR"),
            Pair("South Sudan", "SS"),
            Pair("Sao Tome and Principe", "ST"),
            Pair("El Salvador", "SV"),
            Pair("Sint Maarten (Dutch part)", "SX"),
            Pair("Syrian Arab Republic", "SY"),
            Pair("Eswatini", "SZ"),
            Pair("Turks and Caicos Islands", "TC"),
            Pair("Chad", "TD"),
            Pair("French Southern Territories", "TF"),
            Pair("Togo", "TG"),
            Pair("Thailand", "TH"),
            Pair("Tajikistan", "TJ"),
            Pair("Tokelau", "TK"),
            Pair("Timor-Leste", "TL"),
            Pair("Turkmenistan", "TM"),
            Pair("Tunisia", "TN"),
            Pair("Tonga", "TO"),
            Pair("Turkey", "TR"),
            Pair("Trinidad and Tobago", "TT"),
            Pair("Tuvalu", "TV"),
            Pair("Taiwan, Province of China[a]", "TW"),
            Pair("Tanzania, United Republic of", "TZ"),
            Pair("Ukraine", "UA"),
            Pair("Uganda", "UG"),
            Pair("United States Minor Outlying Islands", "UM"),
            Pair("United States of America", "US"),
            Pair("Uruguay", "UY"),
            Pair("Uzbekistan", "UZ"),
            Pair("Holy See", "VA"),
            Pair("Saint Vincent and the Grenadines", "VC"),
            Pair("Venezuela (Bolivarian Republic of)", "VE"),
            Pair("Virgin Islands (British)", "VG"),
            Pair("Virgin Islands (U.S.)", "VI"),
            Pair("Viet Nam", "VN"),
            Pair("Vanuatu", "VU"),
            Pair("Wallis and Futuna", "WF"),
            Pair("Samoa", "WS"),
            Pair("Yemen", "YE"),
            Pair("Mayotte", "YT"),
            Pair("South Africa", "ZA"),
            Pair("Zambia", "ZM"),
            Pair("Zimbabwe", "ZW")
        )

        startSearch.setOnClickListener {

            launch {
                if (cityName.text.isNotBlank()){
                    val cityName = cityName.text.toString()
                    val countryCode = regions[countryCodeSp.selectedItem.toString()] ?: "Error"
                    val weatherRep = repository.getCurrentWeatherForCity(cityName, countryCode).await()
                   // Log.i("gfd", weather.toString() ?: "None")
                    weatherRep?.apply{
                        val fragment = WeatherFragment()
                        val bundle = Bundle()
                        bundle.putString(CITY, name )
                        bundle.putString(OVERALL, weather.first().main)
                        bundle.putString(DESCRIPTION, weather.first().descriptor)
                        bundle.putString(TEMP, main.temp.toString())
                        bundle.putString(HUMIDITY, main.humidity.toString())
                        fragment.arguments = bundle
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container,fragment)      //переход на новый экран
                            ?.commit()
                    }
                }
            }
        }
    }
}