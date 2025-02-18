package com.techmatrix18.service.impl;

import com.techmatrix18.service.SOAPBarcoService;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import javax.xml.namespace.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@WebServiceClient(name = "SOAPBarcoServiceImpl", wsdlLocation = "http://localhost:8888/soap-wsdl/barco.wsdl?wsdl")
public class SOAPBarcoServiceImpl extends Service {
    public SOAPBarcoServiceImpl() throws URISyntaxException, MalformedURLException {
        super(new URI("http://localhost:8888/soap-wsdl/barco.wsdl?wsdl").toURL(), new QName("http://server.ws.soap.techmatrix18.com/", "SOAPBarcoServiceImpl"));
    }

    @WebEndpoint(name = "SOAPBarcoServiceImplPort")
    public SOAPBarcoService getSOAPBarcoServiceImplPort() {
        return super.getPort(new QName("http://server.ws.soap.techmatrix18.com/", "SOAPBarcoServiceImpl"), SOAPBarcoService.class);
    }
}

