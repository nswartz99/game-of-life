package com.sscomputing;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BasicMandelbrotTest {
    MandelbrotService service = new MandelbrotImpl();

    @Before
    public void before() {
    }

    @Test
    public void basicTest() {
        String ret  = service.iterate(10, 2, 0.0, 0.0, 1.0, 1.0);
        Double [][] result = new Double[3][3];
        String[] rows = ret.split(",");
        for (int i=0; i < rows.length; i++) {
            String cols[] = rows[i].split(" ");
            for (int j=0; j < cols.length; j++) {
                result[i][j] = Double.parseDouble(cols[j]);
            }
        }
        for (int i=0; i < result.length; i++) {
            for (int j=0; j < result[i].length; j++) {
                logNonl(result[j][i] + " ");
                if (j == 0 && i == 2) assertIsApproxmiatelyEqual(j + "," + i, result[j][i], Math.sqrt(2.0));
                if (j > 0) assertTrue("Value at " + j + "," + i + " is too small:" + result[j][i], result[j][i] > 1.0E15);
            }
            log("");
        }
    }
    private void logNonl(String s) {
        System.out.print(s);
    }
    private void log(String s) {
        System.out.println(s);
    }
    private void assertIsApproxmiatelyEqual(String mesg, Double a, Double b) {
        assertTrue(mesg + " expected " + a + " was " + b, Math.abs(a - b) < 1E-15);
    }
}
