package com.example.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountriesInfo {
    private String[] countries = {"AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DOM", "DZ",
            "EC", "EE", "EG", "EH", "ER", "ES", "ET", "EU", "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB_ENG", "GB_NIR", "GB_SCT", "GB_WLS", "GB", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE", "JM", "JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS", "ST", "SV", "SX", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "XK", "WS", "YE", "YT", "ZA", "ZM", "ZW"
    };

    private  String countriesJSON = "{\n" +
            "\"AD\": \"Andorra\",\n" +
            "\"AE\": \"United Arab Emirates\",\n" +
            "\"AF\": \"Afghanistan\",\n" +
            "\"AG\": \"Antigua and Barbuda\",\n" +
            "\"AI\": \"Anguilla\",\n" +
            "\"AL\": \"Albania\",\n" +
            "    \"AM\": \"Armenia\",\n" +
            "    \"AN\": \"Netherlands Antilles\",\n" +
            "    \"AO\": \"Angola\",\n" +
            "    \"AQ\": \"Antarctica\",\n" +
            "    \"AR\": \"Argentina\",\n" +
            "    \"AS\": \"American Samoa\",\n" +
            "    \"AT\": \"Austria\",\n" +
            "    \"AU\": \"Australia\",\n" +
            "    \"AW\": \"Aruba\",\n" +
            "    \"AX\": \"Aland Islands\",\n" +
            "    \"AZ\": \"Azerbaijan\",\n" +
            "    \"BA\": \"Bosnia and Herzegovina\",\n" +
            "    \"BB\": \"Barbados\",\n" +
            "    \"BD\": \"Bangladesh\",\n" +
            "    \"BE\": \"Belgium\",\n" +
            "    \"BF\": \"Burkina Faso\",\n" +
            "    \"BG\": \"Bulgaria\",\n" +
            "    \"BH\": \"Bahrain\",\n" +
            "    \"BI\": \"Burundi\",\n" +
            "    \"BJ\": \"Benin\",\n" +
            "    \"BL\": \"Saint Barthelemy\",\n" +
            "    \"BM\": \"Bermuda\",\n" +
            "    \"BN\": \"Brunei Darussalam\",\n" +
            "    \"BO\": \"Bolivia, Plurinational State of\",\n" +
            "    \"BQ\": \"Caribbean Netherlands\",\n" +
            "    \"BR\": \"Brazil\",\n" +
            "    \"BS\": \"Bahamas\",\n" +
            "    \"BT\": \"Bhutan\",\n" +
            "    \"BV\": \"Bouvet Island\",\n" +
            "    \"BW\": \"Botswana\",\n" +
            "    \"BY\": \"Belarus\",\n" +
            "    \"BZ\": \"Belize\",\n" +
            "    \"CA\": \"Canada\",\n" +
            "    \"CC\": \"Cocos (Keeling) Islands\",\n" +
            "    \"CD\": \"Congo, the Democratic Republic of the\",\n" +
            "    \"CF\": \"Central African Republic\",\n" +
            "    \"CG\": \"Congo\",\n" +
            "    \"CH\": \"Switzerland\",\n" +
            "    \"CI\": \"Cote d'Ivoire\",\n" +
            "    \"CK\": \"Cook Islands\",\n" +
            "    \"CL\": \"Chile\",\n" +
            "    \"CM\": \"Cameroon\",\n" +
            "    \"CN\": \"China\",\n" +
            "    \"CO\": \"Colombia\",\n" +
            "    \"CR\": \"Costa Rica\",\n" +
            "    \"CU\": \"Cuba\",\n" +
            "    \"CV\": \"Cape Verde\",\n" +
            "    \"CW\": \"Curacao\",\n" +
            "    \"CX\": \"Christmas Island\",\n" +
            "    \"CY\": \"Cyprus\",\n" +
            "    \"CZ\": \"Czech Republic\",\n" +
            "    \"DE\": \"Germany\",\n" +
            "    \"DJ\": \"Djibouti\",\n" +
            "    \"DK\": \"Denmark\",\n" +
            "    \"DM\": \"Dominica\",\n" +
            "    \"DOM\": \"Dominican Republic\",\n" +
            "    \"DZ\": \"Algeria\",\n" +
            "    \"EC\": \"Ecuador\",\n" +
            "    \"EE\": \"Estonia\",\n" +
            "    \"EG\": \"Egypt\",\n" +
            "    \"EH\": \"Western Sahara\",\n" +
            "    \"ER\": \"Eritrea\",\n" +
            "    \"ES\": \"Spain\",\n" +
            "    \"ET\": \"Ethiopia\",\n" +
            "    \"EU\": \"Europe\",\n" +
            "    \"FI\": \"Finland\",\n" +
            "    \"FJ\": \"Fiji\",\n" +
            "    \"FK\": \"Falkland Islands (Malvinas)\",\n" +
            "    \"FM\": \"Micronesia, Federated States of\",\n" +
            "    \"FO\": \"Faroe Islands\",\n" +
            "    \"FR\": \"France\",\n" +
            "    \"GA\": \"Gabon\",\n" +
            "    \"GB_ENG\": \"England\",\n" +
            "    \"GB_NIR\": \"Northern Ireland\",\n" +
            "    \"GB_SCT\": \"Scotland\",\n" +
            "    \"GB_WLS\": \"Wales\",\n" +
            "    \"GB\": \"United Kingdom\",\n" +
            "    \"GD\": \"Grenada\",\n" +
            "    \"GE\": \"Georgia\",\n" +
            "    \"GF\": \"French Guiana\",\n" +
            "    \"GG\": \"Guernsey\",\n" +
            "    \"GH\": \"Ghana\",\n" +
            "    \"GI\": \"Gibraltar\",\n" +
            "    \"GL\": \"Greenland\",\n" +
            "    \"GM\": \"Gambia\",\n" +
            "    \"GN\": \"Guinea\",\n" +
            "    \"GP\": \"Guadeloupe\",\n" +
            "    \"GQ\": \"Equatorial Guinea\",\n" +
            "    \"GR\": \"Greece\",\n" +
            "    \"GS\": \"South Georgia and the South Sandwich Islands\",\n" +
            "    \"GT\": \"Guatemala\",\n" +
            "    \"GU\": \"Guam\",\n" +
            "    \"GW\": \"Guinea-Bissau\",\n" +
            "    \"GY\": \"Guyana\",\n" +
            "    \"HK\": \"Hong Kong\",\n" +
            "    \"HM\": \"Heard Island and McDonald Islands\",\n" +
            "    \"HN\": \"Honduras\",\n" +
            "    \"HR\": \"Croatia\",\n" +
            "    \"HT\": \"Haiti\",\n" +
            "    \"HU\": \"Hungary\",\n" +
            "    \"ID\": \"Indonesia\",\n" +
            "    \"IE\": \"Ireland\",\n" +
            "    \"IL\": \"Israel\",\n" +
            "    \"IM\": \"Isle of Man\",\n" +
            "    \"IN\": \"India\",\n" +
            "    \"IO\": \"British Indian Ocean Territory\",\n" +
            "    \"IQ\": \"Iraq\",\n" +
            "    \"IR\": \"Iran, Islamic Republic of\",\n" +
            "    \"IS\": \"Iceland\",\n" +
            "    \"IT\": \"Italy\",\n" +
            "    \"JE\": \"Jersey\",\n" +
            "    \"JM\": \"Jamaica\",\n" +
            "    \"JO\": \"Jordan\",\n" +
            "    \"JP\": \"Japan\",\n" +
            "    \"KE\": \"Kenya\",\n" +
            "    \"KG\": \"Kyrgyzstan\",\n" +
            "    \"KH\": \"Cambodia\",\n" +
            "    \"KI\": \"Kiribati\",\n" +
            "    \"KM\": \"Comoros\",\n" +
            "    \"KN\": \"Saint Kitts and Nevis\",\n" +
            "    \"KP\": \"Korea, Democratic People's Republic of\",\n" +
            "    \"KR\": \"Korea, Republic of\",\n" +
            "    \"KW\": \"Kuwait\",\n" +
            "    \"KY\": \"Cayman Islands\",\n" +
            "    \"KZ\": \"Kazakhstan\",\n" +
            "    \"LA\": \"Lao People's Democratic Republic\",\n" +
            "    \"LB\": \"Lebanon\",\n" +
            "    \"LC\": \"Saint Lucia\",\n" +
            "    \"LI\": \"Liechtenstein\",\n" +
            "    \"LK\": \"Sri Lanka\",\n" +
            "    \"LR\": \"Liberia\",\n" +
            "    \"LS\": \"Lesotho\",\n" +
            "    \"LT\": \"Lithuania\",\n" +
            "    \"LU\": \"Luxembourg\",\n" +
            "    \"LV\": \"Latvia\",\n" +
            "    \"LY\": \"Libya\",\n" +
            "    \"MA\": \"Morocco\",\n" +
            "    \"MC\": \"Monaco\",\n" +
            "    \"MD\": \"Moldova, Republic of\",\n" +
            "    \"ME\": \"Montenegro\",\n" +
            "    \"MF\": \"Saint Martin\",\n" +
            "    \"MG\": \"Madagascar\",\n" +
            "    \"MH\": \"Marshall Islands\",\n" +
            "    \"MK\": \"Macedonia, the former Yugoslav Republic of\",\n" +
            "    \"ML\": \"Mali\",\n" +
            "    \"MM\": \"Myanmar\",\n" +
            "    \"MN\": \"Mongolia\",\n" +
            "    \"MO\": \"Macao\",\n" +
            "    \"MP\": \"Northern Mariana Islands\",\n" +
            "    \"MQ\": \"Martinique\",\n" +
            "    \"MR\": \"Mauritania\",\n" +
            "    \"MS\": \"Montserrat\",\n" +
            "    \"MT\": \"Malta\",\n" +
            "    \"MU\": \"Mauritius\",\n" +
            "    \"MV\": \"Maldives\",\n" +
            "    \"MW\": \"Malawi\",\n" +
            "    \"MX\": \"Mexico\",\n" +
            "    \"MY\": \"Malaysia\",\n" +
            "    \"MZ\": \"Mozambique\",\n" +
            "    \"NA\": \"Namibia\",\n" +
            "    \"NC\": \"New Caledonia\",\n" +
            "    \"NE\": \"Niger\",\n" +
            "    \"NF\": \"Norfolk Island\",\n" +
            "    \"NG\": \"Nigeria\",\n" +
            "    \"NI\": \"Nicaragua\",\n" +
            "    \"NL\": \"Netherlands\",\n" +
            "    \"NO\": \"Norway\",\n" +
            "    \"NP\": \"Nepal\",\n" +
            "    \"NR\": \"Nauru\",\n" +
            "    \"NU\": \"Niue\",\n" +
            "    \"NZ\": \"New Zealand\",\n" +
            "    \"OM\": \"Oman\",\n" +
            "    \"PA\": \"Panama\",\n" +
            "    \"PE\": \"Peru\",\n" +
            "    \"PF\": \"French Polynesia\",\n" +
            "    \"PG\": \"Papua New Guinea\",\n" +
            "    \"PH\": \"Philippines\",\n" +
            "    \"PK\": \"Pakistan\",\n" +
            "    \"PL\": \"Poland\",\n" +
            "    \"PM\": \"Saint Pierre and Miquelon\",\n" +
            "    \"PN\": \"Pitcairn\",\n" +
            "    \"PR\": \"Puerto Rico\",\n" +
            "    \"PS\": \"Palestine\",\n" +
            "    \"PT\": \"Portugal\",\n" +
            "    \"PW\": \"Palau\",\n" +
            "    \"PY\": \"Paraguay\",\n" +
            "    \"QA\": \"Qatar\",\n" +
            "    \"RE\": \"Reunion\",\n" +
            "    \"RO\": \"Romania\",\n" +
            "    \"RS\": \"Serbia\",\n" +
            "    \"RU\": \"Russian Federation\",\n" +
            "    \"RW\": \"Rwanda\",\n" +
            "    \"SA\": \"Saudi Arabia\",\n" +
            "    \"SB\": \"Solomon Islands\",\n" +
            "    \"SC\": \"Seychelles\",\n" +
            "    \"SD\": \"Sudan\",\n" +
            "    \"SE\": \"Sweden\",\n" +
            "    \"SG\": \"Singapore\",\n" +
            "    \"SH\": \"Saint Helena, Ascension and Tristan da Cunha\",\n" +
            "    \"SI\": \"Slovenia\",\n" +
            "    \"SJ\": \"Svalbard and Jan Mayen Islands\",\n" +
            "    \"SK\": \"Slovakia\",\n" +
            "    \"SL\": \"Sierra Leone\",\n" +
            "    \"SM\": \"San Marino\",\n" +
            "    \"SN\": \"Senegal\",\n" +
            "    \"SO\": \"Somalia\",\n" +
            "    \"SR\": \"Suriname\",\n" +
            "    \"SS\": \"South Sudan\",\n" +
            "    \"ST\": \"Sao Tome and Principe\",\n" +
            "    \"SV\": \"El Salvador\",\n" +
            "    \"SX\": \"Sint Maarten (Dutch part)\",\n" +
            "    \"SY\": \"Syrian Arab Republic\",\n" +
            "    \"SZ\": \"Swaziland\",\n" +
            "    \"TC\": \"Turks and Caicos Islands\",\n" +
            "    \"TD\": \"Chad\",\n" +
            "    \"TF\": \"French Southern Territories\",\n" +
            "    \"TG\": \"Togo\",\n" +
            "    \"TH\": \"Thailand\",\n" +
            "    \"TJ\": \"Tajikistan\",\n" +
            "    \"TK\": \"Tokelau\",\n" +
            "    \"TL\": \"Timor-Leste\",\n" +
            "    \"TM\": \"Turkmenistan\",\n" +
            "    \"TN\": \"Tunisia\",\n" +
            "    \"TO\": \"Tonga\",\n" +
            "    \"TR\": \"Turkey\",\n" +
            "    \"TT\": \"Trinidad and Tobago\",\n" +
            "    \"TV\": \"Tuvalu\",\n" +
            "    \"TW\": \"Taiwan\",\n" +
            "    \"TZ\": \"Tanzania, United Republic of\",\n" +
            "    \"UA\": \"Ukraine\",\n" +
            "    \"UG\": \"Uganda\",\n" +
            "    \"UM\": \"US Minor Outlying Islands\",\n" +
            "    \"US\": \"United States\",\n" +
            "    \"UY\": \"Uruguay\",\n" +
            "    \"UZ\": \"Uzbekistan\",\n" +
            "    \"VA\": \"Holy See (Vatican City State)\",\n" +
            "    \"VC\": \"Saint Vincent and the Grenadines\",\n" +
            "    \"VE\": \"Venezuela, Bolivarian Republic of\",\n" +
            "    \"VG\": \"Virgin Islands, British\",\n" +
            "    \"VI\": \"Virgin Islands, U.S.\",\n" +
            "    \"VN\": \"Viet Nam\",\n" +
            "    \"VU\": \"Vanuatu\",\n" +
            "    \"WF\": \"Wallis and Futuna Islands\",\n" +
            "    \"XK\": \"Kosovo\",\n" +
            "    \"WS\": \"Samoa\",\n" +
            "    \"YE\": \"Yemen\",\n" +
            "    \"YT\": \"Mayotte\",\n" +
            "    \"ZA\": \"South Africa\",\n" +
            "    \"ZM\": \"Zambia\",\n" +
            "    \"ZW\": \"Zimbabwe\"\n" +
            "}";
 private HashMap<String, String> countriesMap;

    {
        try {
            countriesMap = toMap(countriesJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public CountriesInfo(){

    }

    public String[] getCountries() {
        return countries;
    }

    public String getCountriesJSON() {
        return countriesJSON;
    }

    public HashMap<String, String> getCountriesMap() {
        return countriesMap;
    }

    //https://stackoverflow.com/questions/21544973/convert-jsonobject-to-map
    public static HashMap<String, String> toMap(String countriesJSON) throws JSONException {
        JSONObject jsonobj =null;
            try {
             jsonobj = new JSONObject(countriesJSON);


        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<String, String>();
        Iterator<String> keys = jsonobj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = (String) jsonobj.get(key);
            map.put(key, value);
        }
        return map;
    }

}
