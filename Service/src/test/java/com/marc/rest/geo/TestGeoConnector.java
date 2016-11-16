package com.marc.rest.geo;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by marc on 16.11.16.
 */
public class TestGeoConnector {

    @Test
    public void testGeoConnector() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        GeoConnector geoConnector = new GeoConnector();
        Map<String, Object> map = objectMapper.readValue(getHttpRequestWithDoubleQuote(), new TypeReference<Map<String,Object>>(){});
        assertEquals("Berlin",geoConnector.JSONParsingCity(map));


    }

    private static String getHttpRequestWithDoubleQuote(){
        return "{\n" +
                "   \"results\" : [\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"7E\",\n" +
                "               \"short_name\" : \"7E\",\n" +
                "               \"types\" : [ \"street_number\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Karl-Liebknecht-Straße\",\n" +
                "               \"short_name\" : \"Karl-Liebknecht-Str.\",\n" +
                "               \"types\" : [ \"route\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Mitte\",\n" +
                "               \"short_name\" : \"Mitte\",\n" +
                "               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_2\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Mitte\",\n" +
                "               \"short_name\" : \"Mitte\",\n" +
                "               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_1\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"locality\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"10178\",\n" +
                "               \"short_name\" : \"10178\",\n" +
                "               \"types\" : [ \"postal_code\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Karl-Liebknecht-Str. 7E, 10178 Berlin, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.52068999999999,\n" +
                "               \"lng\" : 13.40494\n" +
                "            },\n" +
                "            \"location_type\" : \"ROOFTOP\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.5220389802915,\n" +
                "                  \"lng\" : 13.4062889802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.5193410197085,\n" +
                "                  \"lng\" : 13.4035910197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJQTbS599RqEcRB73zot_n3io\",\n" +
                "         \"types\" : [ \"street_address\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"Mitte\",\n" +
                "               \"short_name\" : \"Mitte\",\n" +
                "               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_2\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Mitte\",\n" +
                "               \"short_name\" : \"Mitte\",\n" +
                "               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_1\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"locality\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Mitte, Berlin, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.5403962,\n" +
                "                  \"lng\" : 13.4293586\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.5040199,\n" +
                "                  \"lng\" : 13.3658543\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.519444,\n" +
                "               \"lng\" : 13.406667\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.5403962,\n" +
                "                  \"lng\" : 13.4293586\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.5040199,\n" +
                "                  \"lng\" : 13.3658543\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJjw3Y6t9RqEcR8jUVWEcgISY\",\n" +
                "         \"types\" : [ \"neighborhood\", \"political\", \"sublocality\", \"sublocality_level_2\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"Mitte\",\n" +
                "               \"short_name\" : \"Mitte\",\n" +
                "               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_1\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"locality\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Mitte, Berlin, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.5677268,\n" +
                "                  \"lng\" : 13.4293586\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.4987314,\n" +
                "                  \"lng\" : 13.3015252\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.5306438,\n" +
                "               \"lng\" : 13.3830683\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.5677268,\n" +
                "                  \"lng\" : 13.4293586\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.4987314,\n" +
                "                  \"lng\" : 13.3015252\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJAUK8it1RqEcRwKtfW0YgIQU\",\n" +
                "         \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_1\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"locality\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Berlin, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.6754542,\n" +
                "                  \"lng\" : 13.7611175\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.338234,\n" +
                "                  \"lng\" : 13.088346\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.52000659999999,\n" +
                "               \"lng\" : 13.404954\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.6754542,\n" +
                "                  \"lng\" : 13.7611175\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.33962959999999,\n" +
                "                  \"lng\" : 13.0911719\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJAVkDPzdOqEcRcDteW0YgIQQ\",\n" +
                "         \"types\" : [ \"locality\", \"political\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"10178\",\n" +
                "               \"short_name\" : \"10178\",\n" +
                "               \"types\" : [ \"postal_code\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"locality\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"10178 Berlin, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.528538,\n" +
                "                  \"lng\" : 13.4296049\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.5120099,\n" +
                "                  \"lng\" : 13.3940579\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.5221879,\n" +
                "               \"lng\" : 13.4093313\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.528538,\n" +
                "                  \"lng\" : 13.4296049\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.5120099,\n" +
                "                  \"lng\" : 13.3940579\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJ85n72yFOqEcRIM89lUkgIRw\",\n" +
                "         \"types\" : [ \"postal_code\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin\",\n" +
                "               \"short_name\" : \"Berlin\",\n" +
                "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Berlin, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.6754542,\n" +
                "                  \"lng\" : 13.7611175\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.338234,\n" +
                "                  \"lng\" : 13.088346\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.4938053,\n" +
                "               \"lng\" : 13.4552919\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 52.6754542,\n" +
                "                  \"lng\" : 13.7611175\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 52.33962959999999,\n" +
                "                  \"lng\" : 13.0911719\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJ8_KccStOqEcRhtFXjKWPuo0\",\n" +
                "         \"types\" : [\n" +
                "            \"administrative_area_level_1\",\n" +
                "            \"establishment\",\n" +
                "            \"point_of_interest\",\n" +
                "            \"political\"\n" +
                "         ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"Berlin Metropolitan Area\",\n" +
                "               \"short_name\" : \"Berlin Metropolitan Area\",\n" +
                "               \"types\" : [ \"political\" ]\n" +
                "            },\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Berlin Metropolitan Area, Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 53.55898,\n" +
                "                  \"lng\" : 14.7658261\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 51.3590586,\n" +
                "                  \"lng\" : 11.265727\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 52.268409,\n" +
                "               \"lng\" : 13.5287229\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 53.55898,\n" +
                "                  \"lng\" : 14.765826\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 51.3590586,\n" +
                "                  \"lng\" : 11.265727\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJZ4kamin3qEcRQ5VPQ7O8dWY\",\n" +
                "         \"types\" : [ \"political\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"address_components\" : [\n" +
                "            {\n" +
                "               \"long_name\" : \"Germany\",\n" +
                "               \"short_name\" : \"DE\",\n" +
                "               \"types\" : [ \"country\", \"political\" ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"formatted_address\" : \"Germany\",\n" +
                "         \"geometry\" : {\n" +
                "            \"bounds\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 55.0815,\n" +
                "                  \"lng\" : 15.0418962\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 47.2701115,\n" +
                "                  \"lng\" : 5.8663425\n" +
                "               }\n" +
                "            },\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 51.165691,\n" +
                "               \"lng\" : 10.451526\n" +
                "            },\n" +
                "            \"location_type\" : \"APPROXIMATE\",\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 55.05812359999999,\n" +
                "                  \"lng\" : 15.0417724\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 47.2702482,\n" +
                "                  \"lng\" : 5.8664874\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"place_id\" : \"ChIJa76xwh5ymkcRW-WRjmtd6HU\",\n" +
                "         \"types\" : [ \"country\", \"political\" ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}\n";
    }
}
