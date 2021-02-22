package com.sscomputing;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BasicIT {
    private static final String APP_NAME = "game-of-life";
    private static final String WSDL_PATH = "GameOfLifeService?wsdl";

    @ArquillianResource
    private URL deploymentUrl;

    private GameOfLifeService client;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, APP_NAME + ".war").addPackage(GameOfLifeService.class.getPackage());
    }

    @Before
    public void setup() {
        System.out.println("Deployment URL is " + deploymentUrl.toString());
        try {
            client = new GameOfLifeClient(new URL(deploymentUrl, WSDL_PATH));
        } catch (MalformedURLException e) {
            System.err.println("Bad URL Exception caught");
            e.printStackTrace();
        }
    }

    @Test
    public void testHello() {
        System.out.println("[Client] Requesting the WebService to say Hello.");

        final String response = client.sayHello();
        assertEquals("Hello Game", response);
        System.out.println("[WebService] " + response);
    }
    @Test
    public void testIterate() {
        System.out.println("[Client] Requesting the WebService to Iterate.");

        Boolean[][] g = new Boolean[3][3];
        g[0][1] = true; g[1][1] = true; g[2][1] = true; 
        final Boolean[][] response = client.iterate(g);
        System.out.println("[WebService] Result:\n" + response);

    }
}
