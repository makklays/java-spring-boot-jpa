package com.techmatrix18.service.impl;

import com.techmatrix18.service.SOAPBarcoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import static org.testng.Assert.assertEquals;

public class SOAPBarcoServiceImplTest {

    private static SOAPBarcoService barcoService;

    @BeforeAll
    public static void setup() throws MalformedURLException, URISyntaxException {
        SOAPBarcoServiceImpl service = new SOAPBarcoServiceImpl();
        SOAPBarcoService barcoService = service.getSOAPBarcoServiceImplPort();
    }

    @Test
    public void givenCountryService_whenCountryIndia_thenCapitalIsNewDelhi() {
        assertEquals("1905", barcoService.findByTitle("TUAPSE").getYear());
    }

    @Test
    public void givenCountryService_whenCountryFrance_thenPopulationCorrect() {
        assertEquals(500000, barcoService.findByTitle("EAGLE VENTURA").getWeight());
    }

    @Test
    public void givenCountryService_whenCountryUSA_thenCurrencyUSD() {
        assertEquals("8000000", barcoService.findByTitle("ЛЕТУЧИЙ ГОЛЛАНДЕЦ").getSpeedometer());
    }
}

