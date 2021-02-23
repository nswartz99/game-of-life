package com.sscomputing;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class GameOfLifeClient implements GameOfLifeService {
    private GameOfLifeService gameOfLifeService;

    public GameOfLifeClient(final URL wsdlUrl) {
        System.out.println("Get QName");
        QName serviceName = new QName("http://sscomputing.com/game-of-life/GameOfLife", "GameOfLifeService");

        System.out.println("Get Service:" + wsdlUrl.toString() + "," + serviceName);
        Service service = Service.create(wsdlUrl, serviceName);
        System.out.println("Get Port");
        gameOfLifeService = service.getPort(GameOfLifeService.class);
        assert (gameOfLifeService != null);
        System.out.println("Got it");
    }

    /**
     * Default constructor
     *
     * @param url The URL to the Hello World WSDL endpoint.
     * @throws MalformedURLException if the WSDL url is malformed.
     */
    public GameOfLifeClient(final String url) throws MalformedURLException {
        this(new URL(url));
    }

    @Override
    public String sayHello() {
        return gameOfLifeService.sayHello();
    }

    @Override
    public Boolean[][] iterate(Boolean[][] g) {
        return gameOfLifeService.iterate(g);
    }

    @Override
    public Boolean[][] sayBooleanArray() {
        return gameOfLifeService.sayBooleanArray();
    }

    @Override
    public String iterateCompact(String g) {
        return gameOfLifeService.iterateCompact(g);
    }

}
