package com.sscomputing;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://sscomputing.com")
public interface MandelbrotService {
    @WebMethod
    String sayHello(String s);
    @WebMethod
    String iterate(int count, int dim, Double xStart, Double yStart, Double xEnd, Double yEnd);
}
