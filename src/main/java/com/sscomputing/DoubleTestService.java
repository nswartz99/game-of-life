package com.sscomputing;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://sscomputing.com")
public interface DoubleTestService {
    @WebMethod
    String sayHello(String s);
    @WebMethod
    Double[][] iterate(Double[][] g);
}
