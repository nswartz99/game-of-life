package com.sscomputing;

import javax.jws.WebService;

@WebService(serviceName = "DoubleTestService", portName = "DoubleTest", name = "DoubleTest", endpointInterface = "com.sscomputing.DoubleTestService",
    targetNamespace = "http://sscomputing.com")
public class DoubleTestImpl implements DoubleTestService {

    @Override
    public String sayHello(String s) {
        return "Hello Double:" + s;
    }

    @Override
    public Double[][] iterate(Double[][] g) {
        Double[][] d = new Double[2][2];
        d[0][0] = 3.14;
        d[1][1] = 2.1878;
        d[1][0] = Math.sqrt(2);
        return d;
    }
    
}
