package com.sscomputing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BasicMandelbrotTest {
    MandelbrotService service = new MandelbrotImpl();

    @Before
    public void before() {
    }

    @Test
    public void basicTest() {
        Double [][] result = service.iterate(10, 2, 0.0, 0.0, 1.0, 1.0);
        for (int i=0; i < result.length; i++) {
            for (int j=0; j < result[i].length; j++) {
                System.out.print(result[j][i] + " ");
            }
            System.out.println("");
        }
    }
}
