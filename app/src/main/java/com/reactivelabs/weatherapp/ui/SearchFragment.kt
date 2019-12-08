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
            Pair("	Afghanistan	", "	AF	"),
            Pair("	Åland Islands	", "	AX	"),
            Pair("	Albania	", "	AL	"),
            Pair("	Algeria	", "	DZ	"),
            Pair("	American Samoa	", "	AS	"),
            Pair("	Andorra	", "	AD	"),
            Pair("	Angola	", "	AO	"),
            Pair("	Anguilla	", "	AI	"),
            Pair("	Antarctica	", "	AQ	"),
            Pair("	Antigua and Barbuda	", "	AG	"),
            Pair("	Argentina	", "	AR	"),
            Pair("	Armenia	", "	AM	"),
            Pair("	Aruba	", "	AW	"),
            Pair("	Australia	", "	AU	"),
            Pair("	Austria	", "	AT	"),
            Pair("	Azerbaijan	", "	AZ	"),
            Pair("	Bahamas	", "	BS	"),
            Pair("	Bahrain	", "	BH	"),
            Pair("	Bangladesh	", "	BD	"),
            Pair("	Barbados	", "	BB	"),
            Pair("	Belarus	", "	BY	"),
            Pair("	Belgium	", "	BE	"),
            Pair("	Belize	", "	BZ	"),
            Pair("	Benin	", "	BJ	"),
            Pair("	Bermuda	", "	BM	"),
            Pair("	Bhutan	", "	BT	"),
            Pair("	Bolivia (Plurinational State of)	", "	BO	"),
            Pair("	Bonaire, Sint Eustatius and Saba	", "	BQ	"),
            Pair("	Bosnia and Herzegovina	", "	BA	"),
            Pair("	Botswana	", "	BW	"),
            Pair("	Bouvet Island	", "	BV	"),
            Pair("	Brazil	", "	BR	"),
            Pair("	British Indian Ocean Territory	", "	IO	"),
            Pair("	Brunei Darussalam	", "	BN	"),
            Pair("	Bulgaria	", "	BG	"),
            Pair("	Burkina Faso	", "	BF	"),
            Pair("	Burundi	", "	BI	"),
            Pair("	Cabo Verde	", "	CV	"),
            Pair("	Cambodia	", "	KH	"),
            Pair("	Cameroon	", "	CM	"),
            Pair("	Canada	", "	CA	"),
            Pair("	Cayman Islands	", "	KY	"),
            Pair("	Central African Republic	", "	CF	"),
            Pair("	Chad	", "	TD	"),
            Pair("	Chile	", "	CL	"),
            Pair("	China	", "	CN	"),
            Pair("	Christmas Island	", "	CX	"),
            Pair("	Cocos (Keeling) Islands	", "	CC	"),
            Pair("	Colombia	", "	CO	"),
            Pair("	Comoros	", "	KM	"),
            Pair("	Congo	", "	CG	"),
            Pair("	Congo, Democratic Republic of the	", "	CD	"),
            Pair("	Cook Islands	", "	CK	"),
            Pair("	Costa Rica	", "	CR	"),
            Pair("	Côte dIvoire	", "	CI	"),
            Pair("	Croatia	", "	HR	"),
            Pair("	Cuba	", "	CU	"),
            Pair("	Curaçao	", "	CW	"),
            Pair("	Cyprus	", "	CY	"),
            Pair("	Czechia	", "	CZ	"),
            Pair("	Denmark	", "	DK	"),
            Pair("	Djibouti	", "	DJ	"),
            Pair("	Dominica	", "	DM	"),
            Pair("	Dominican Republic	", "	DO	"),
            Pair("	Ecuador	", "	EC	"),
            Pair("	Egypt	", "	EG	"),
            Pair("	El Salvador	", "	SV	"),
            Pair("	Equatorial Guinea	", "	GQ	"),
            Pair("	Eritrea	", "	ER	"),
            Pair("	Estonia	", "	EE	"),
            Pair("	Eswatini	", "	SZ	"),
            Pair("	Ethiopia	", "	ET	"),
            Pair("	Falkland Islands (Malvinas)	", "	FK	"),
            Pair("	Faroe Islands	", "	FO	"),
            Pair("	Fiji	", "	FJ	"),
            Pair("	Finland	", "	FI	"),
            Pair("	France	", "	FR	"),
            Pair("	French Guiana	", "	GF	"),
            Pair("	French Polynesia	", "	PF	"),
            Pair("	French Southern Territories	", "	TF	"),
            Pair("	Gabon	", "	GA	"),
            Pair("	Gambia	", "	GM	"),
            Pair("	Georgia	", "	GE	"),
            Pair("	Germany	", "	DE	"),
            Pair("	Ghana	", "	GH	"),
            Pair("	Gibraltar	", "	GI	"),
            Pair("	Greece	", "	GR	"),
            Pair("	Greenland	", "	GL	"),
            Pair("	Grenada	", "	GD	"),
            Pair("	Guadeloupe	", "	GP	"),
            Pair("	Guam	", "	GU	"),
            Pair("	Guatemala	", "	GT	"),
            Pair("	Guernsey	", "	GG	"),
            Pair("	Guinea	", "	GN	"),
            Pair("	Guinea-Bissau	", "	GW	"),
            Pair("	Guyana	", "	GY	"),
            Pair("	Haiti	", "	HT	"),
            Pair("	Heard Island and McDonald Islands	", "	HM	"),
            Pair("	Holy See	", "	VA	"),
            Pair("	Honduras	", "	HN	"),
            Pair("	Hong Kong	", "	HK	"),
            Pair("	Hungary	", "	HU	"),
            Pair("	Iceland	", "	IS	"),
            Pair("	India	", "	IN	"),
            Pair("	Indonesia	", "	ID	"),
            Pair("	Iran (Islamic Republic of)	", "	IR	"),
            Pair("	Iraq	", "	IQ	"),
            Pair("	Ireland	", "	IE	"),
            Pair("	Isle of Man	", "	IM	"),
            Pair("	Israel	", "	IL	"),
            Pair("	Italy	", "	IT	"),
            Pair("	Jamaica	", "	JM	"),
            Pair("	Japan	", "	JP	"),
            Pair("	Jersey	", "	JE	"),
            Pair("	Jordan	", "	JO	"),
            Pair("	Kazakhstan	", "	KZ	"),
            Pair("	Kenya	", "	KE	"),
            Pair("	Kiribati	", "	KI	"),
            Pair("	Korea (Democratic Peoples Republic of)	", "	KP	"),
            Pair("	Korea, Republic of	", "	KR	"),
            Pair("	Kuwait	", "	KW	"),
            Pair("	Kyrgyzstan	", "	KG	"),
            Pair("	Lao Peoples Democratic Republic	", "	LA	"),
            Pair("	Latvia	", "	LV	"),
            Pair("	Lebanon	", "	LB	"),
            Pair("	Lesotho	", "	LS	"),
            Pair("	Liberia	", "	LR	"),
            Pair("	Libya	", "	LY	"),
            Pair("	Liechtenstein	", "	LI	"),
            Pair("	Lithuania	", "	LT	"),
            Pair("	Luxembourg	", "	LU	"),
            Pair("	Macao	", "	MO	"),
            Pair("	Madagascar	", "	MG	"),
            Pair("	Malawi	", "	MW	"),
            Pair("	Malaysia	", "	MY	"),
            Pair("	Maldives	", "	MV	"),
            Pair("	Mali	", "	ML	"),
            Pair("	Malta	", "	MT	"),
            Pair("	Marshall Islands	", "	MH	"),
            Pair("	Martinique	", "	MQ	"),
            Pair("	Mauritania	", "	MR	"),
            Pair("	Mauritius	", "	MU	"),
            Pair("	Mayotte	", "	YT	"),
            Pair("	Mexico	", "	MX	"),
            Pair("	Micronesia (Federated States of)	", "	FM	"),
            Pair("	Moldova, Republic of	", "	MD	"),
            Pair("	Monaco	", "	MC	"),
            Pair("	Mongolia	", "	MN	"),
            Pair("	Montenegro	", "	ME	"),
            Pair("	Montserrat	", "	MS	"),
            Pair("	Morocco	", "	MA	"),
            Pair("	Mozambique	", "	MZ	"),
            Pair("	Myanmar	", "	MM	"),
            Pair("	Namibia	", "	NA	"),
            Pair("	Nauru	", "	NR	"),
            Pair("	Nepal	", "	NP	"),
            Pair("	Netherlands	", "	NL	"),
            Pair("	New Caledonia	", "	NC	"),
            Pair("	New Zealand	", "	NZ	"),
            Pair("	Nicaragua	", "	NI	"),
            Pair("	Niger	", "	NE	"),
            Pair("	Nigeria	", "	NG	"),
            Pair("	Niue	", "	NU	"),
            Pair("	Norfolk Island	", "	NF	"),
            Pair("	North Macedonia	", "	MK	"),
            Pair("	Northern Mariana Islands	", "	MP	"),
            Pair("	Norway	", "	NO	"),
            Pair("	Oman	", "	OM	"),
            Pair("	Pakistan	", "	PK	"),
            Pair("	Palau	", "	PW	"),
            Pair("	Palestine, State of	", "	PS	"),
            Pair("	Panama	", "	PA	"),
            Pair("	Papua New Guinea	", "	PG	"),
            Pair("	Paraguay	", "	PY	"),
            Pair("	Peru	", "	PE	"),
            Pair("	Philippines	", "	PH	"),
            Pair("	Pitcairn	", "	PN	"),
            Pair("	Poland	", "	PL	"),
            Pair("	Portugal	", "	PT	"),
            Pair("	Puerto Rico	", "	PR	"),
            Pair("	Qatar	", "	QA	"),
            Pair("	Réunion	", "	RE	"),
            Pair("	Romania	", "	RO	"),
            Pair("	Russian Federation	", "	RU	"),
            Pair("	Rwanda	", "	RW	"),
            Pair("	Saint Barthélemy	", "	BL	"),
            Pair("	Saint Helena, Ascension and Tristan da Cunha	", "	SH	"),
            Pair("	Saint Kitts and Nevis	", "	KN	"),
            Pair("	Saint Lucia	", "	LC	"),
            Pair("	Saint Martin (French part)	", "	MF	"),
            Pair("	Saint Pierre and Miquelon	", "	PM	"),
            Pair("	Saint Vincent and the Grenadines	", "	VC	"),
            Pair("	Samoa	", "	WS	"),
            Pair("	San Marino	", "	SM	"),
            Pair("	Sao Tome and Principe	", "	ST	"),
            Pair("	Saudi Arabia	", "	SA	"),
            Pair("	Senegal	", "	SN	"),
            Pair("	Serbia	", "	RS	"),
            Pair("	Seychelles	", "	SC	"),
            Pair("	Sierra Leone	", "	SL	"),
            Pair("	Singapore	", "	SG	"),
            Pair("	Sint Maarten (Dutch part)	", "	SX	"),
            Pair("	Slovakia	", "	SK	"),
            Pair("	Slovenia	", "	SI	"),
            Pair("	Solomon Islands	", "	SB	"),
            Pair("	Somalia	", "	SO	"),
            Pair("	South Africa	", "	ZA	"),
            Pair("	South Georgia and the South Sandwich Islands	", "	GS	"),
            Pair("	South Sudan	", "	SS	"),
            Pair("	Spain	", "	ES	"),
            Pair("	Sri Lanka	", "	LK	"),
            Pair("	Sudan	", "	SD	"),
            Pair("	Suriname	", "	SR	"),
            Pair("	Svalbard and Jan Mayen	", "	SJ	"),
            Pair("	Sweden	", "	SE	"),
            Pair("	Switzerland	", "	CH	"),
            Pair("	Syrian Arab Republic	", "	SY	"),
            Pair("	Taiwan, Province of China[a]	", "	TW	"),
            Pair("	Tajikistan	", "	TJ	"),
            Pair("	Tanzania, United Republic of	", "	TZ	"),
            Pair("	Thailand	", "	TH	"),
            Pair("	Timor-Leste	", "	TL	"),
            Pair("	Togo	", "	TG	"),
            Pair("	Tokelau	", "	TK	"),
            Pair("	Tonga	", "	TO	"),
            Pair("	Trinidad and Tobago	", "	TT	"),
            Pair("	Tunisia	", "	TN	"),
            Pair("	Turkey	", "	TR	"),
            Pair("	Turkmenistan	", "	TM	"),
            Pair("	Turks and Caicos Islands	", "	TC	"),
            Pair("	Tuvalu	", "	TV	"),
            Pair("	Uganda	", "	UG	"),
            Pair("Ukraine", "UA"),
            Pair("	United Arab Emirates	", "	AE	"),
            Pair("	United Kingdom of Great Britain and Northern Ireland	", "	GB	"),
            Pair("	United States Minor Outlying Islands	", "	UM	"),
            Pair("	United States of America	", "	US	"),
            Pair("	Uruguay	", "	UY	"),
            Pair("	Uzbekistan	", "	UZ	"),
            Pair("	Vanuatu	", "	VU	"),
            Pair("	Venezuela (Bolivarian Republic of)	", "	VE	"),
            Pair("	Viet Nam	", "	VN	"),
            Pair("	Virgin Islands (British)	", "	VG	"),
            Pair("	Virgin Islands (U.S.)	", "	VI	"),
            Pair("	Wallis and Futuna	", "	WF	"),
            Pair("	Western Sahara	", "	EH	"),
            Pair("	Yemen	", "	YE	"),
            Pair("	Zambia	", "	ZM	"),
            Pair("	Zimbabwe	", "	ZW	")
        )

        startSearch.setOnClickListener {

            launch {
                if (cityName.text.isNotBlank()){
                    startSearch.visibility = View.INVISIBLE
                    loader.visibility = View.VISIBLE
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
                    startSearch.visibility = View.VISIBLE    //loader
                    loader.visibility = View.INVISIBLE


                }
            }
        }
    }
}