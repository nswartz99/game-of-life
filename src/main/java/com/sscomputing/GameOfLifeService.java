package com.sscomputing;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://sscomputing.com")
public interface GameOfLifeService {
    @WebMethod
    String sayHello();
    @WebMethod
    Boolean[][] iterate(Boolean[][] g);
    @WebMethod
    String iterateCompact(String g);
    @WebMethod
    Boolean[][] sayBooleanArray();
}
