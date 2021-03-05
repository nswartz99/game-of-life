package com.sscomputing;

import javax.jws.WebService;

@WebService(serviceName = "MandelbrotService", portName = "Mandelbrot", name = "Mandelbrot", endpointInterface = "com.sscomputing.IntegerService",
targetNamespace = "http://sscomputing.com")
public class MandelbrotImpl implements MandelbrotService {
    
        @Override
        public String sayHello(String s) {
            return "Hello Mandelbrot:" + s;
        }
    
        @Override
        public Double[][] iterate(int count, int dim, Double xStart, Double yStart, Double xEnd, Double yEnd) {
            Double domain = xEnd - xStart;
            Double range = yEnd - yStart;
            Double[][] result = new Double[dim+1][dim+1];
            for (int i=0; i < result.length; i++) {
                for (int j=0; j < result[i].length; j++) {
                    Double ii = 0.0;
                    Double jj = 0.0;
                    for (int k=0; k < count; k++) {
                        System.out.print(" i:" + ii + " j:" + jj + " cX:" + i*domain/dim + " cY:" + j*range/dim + "//");
                        Double iiSave = ii;
                        ii = ii*ii - jj*jj + xStart + i*domain/dim;
                        jj = 2*iiSave*jj + yStart + j*range/dim;
                    }
                    result[i][j] = Math.sqrt(ii*ii + jj*jj);
                    System.out.print("\n" + i+","+j + " i:" + ii + " j:" + jj + " cX:" + i*domain/dim + " cY:" + j*range/dim);
                    System.out.println(" res:" + result[i][j]);
                }
            }
            return result;
        }

}
