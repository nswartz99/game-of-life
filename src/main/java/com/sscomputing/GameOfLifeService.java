package com.sscomputing;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://sscomputing.com/game-of-life/GameOfLife")
public interface GameOfLifeService {
    @WebMethod
    String sayHello();
}
