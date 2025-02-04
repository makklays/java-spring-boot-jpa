package com.techmatrix18.service.impl;

import com.techmatrix18.service.SOAPBarcoService;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;

@WebServiceClient(name = "SOAPBarcoServiceImpl",
        wsdlLocation = "http://localhost:8888/ws/barco?wsdl")
public class SOAPBarcoServiceImpl extends Service {
    public SOAPBarcoServiceImpl() {
        super(QName("http://server.ws.soap.baeldung.com/", "SOAPBarcoServiceImpl"));
    }
    @WebEndpoint(name = "SOAPBarcoServiceImplPort")
    public SOAPBarcoService getSOAPBarcoServiceImplPort() {
        return super.getPort(SOAPBarcoService.class);
    }
}

