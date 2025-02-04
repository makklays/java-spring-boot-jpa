package com.techmatrix18.service;

import com.techmatrix18.model.Barco;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name = "SOAPBarcoService", targetNamespace = "http://server.ws.soap.baeldung.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SOAPBarcoService {
    @WebMethod
    @WebResult(partName = "return")
    public Barco findByTitle(@WebParam(name = "arg0") String arg0);
}

