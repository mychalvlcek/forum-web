package cz.cvut.forum.service;

import com.sun.jersey.api.client.Client;

public abstract class AbstractDataAccessService {
    private static Client client;
    public static final String API_URL = "http://localhost:8080/";

    public static Client getClient() {
        if (client == null) {
            client = Client.create();
        }

        return client;
    }

}