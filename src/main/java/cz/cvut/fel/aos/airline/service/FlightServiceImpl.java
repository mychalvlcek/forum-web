package cz.cvut.fel.aos.airline.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import cz.cvut.fel.aos.airline.api.data.Flight;
import cz.cvut.fel.aos.airline.entity.DestinationEntity;
import cz.cvut.fel.aos.airline.entity.FlightEntity;
import cz.cvut.fel.aos.airline.helpers.GPS;
import cz.cvut.fel.aos.airline.persistence.GenericDao;
import cz.cvut.fel.aos.airline.persistence.GenericDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Flight resource implementation
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 24.10.14
 */
public class FlightServiceImpl implements FlightService {

    private GenericDao genericDao = new GenericDaoImpl();

    @Override
    public List<Flight> find(String sort, String filter, int base, int offset, String currency) {
        if (sort == "") {
            sort = "e.id asc";
        } else {
            sort = "e." + sort.replace(":", " ");
        }
        return this.format(genericDao.getPage(sort, filter, base, offset, FlightEntity.class), currency);
    }

    @Override
    public List<Flight> getAll() {
        List<FlightEntity> entities = genericDao.getAll(FlightEntity.class);
        List<Flight> dtos = new ArrayList<Flight>();

        for (FlightEntity e : entities) {
            dtos.add(entityToDto(e));
        }
        return dtos;
    }

    @Override
    public Flight get(Long id) {
        return entityToDto(genericDao.getById(id, FlightEntity.class));
    }

    @Override
    public void delete(Long id) {
        genericDao.removeById(id, FlightEntity.class);
    }

    @Override
    public void save(Flight entity) {
//        double distance = getDistance(from, to);
//        entity.setDistance(distance);
//        entity.setPrice(distance * 10); // 100 km = 1000 Kc

        genericDao.saveOrUpdate(dtoToEntity(entity));
    }

    @Override
    public Long getCount() {
        return genericDao.getCount(FlightEntity.class);
    }

    // =========== Helpers ================

    private List<Flight> format(List<FlightEntity> entities, String currency) {
        List<Flight> dtos = new ArrayList<Flight>();
        double rate = 1d;
        if (!currency.equals("CZK")) {
            rate = getRate(currency);
        }

        for (FlightEntity c : entities) {
            Flight f = entityToDto(c);
            f.setPrice(f.getPrice() * rate);
            dtos.add(f);
        }
        return dtos;
    }

    /**
     * Get price in given currency
     * @param currency name of currency
     * @return double price
     */
     private double getRate(String currency) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://rate-exchange.appspot.com/currency")
                .queryParam("from", "CZK").queryParam("to", currency);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        InputStream is = response.getEntityInputStream();
        int ch;
        StringBuilder sb = new StringBuilder();
        try {
            while((ch = is.read())!= -1)
                sb.append((char)ch);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(sb.toString());
        if (element.isJsonObject()) {

            JsonElement rate = element.getAsJsonObject().get("rate");
            if (!rate.isJsonNull()) {
                System.out.println(rate.getAsDouble());
                return rate.getAsDouble();
            }
        }
        return 1;
    }

    /**
     * Gets distances between two points specified by GPS coordinates
     * @TODO
     * @param from GPS
     * @param to GPS
     * @return double distance
     */
    private double getDistance(GPS from, GPS to) {
        Client client = Client.create();
        WebResource webResource = client.resource("https://www.mashape.com/orfeomorello/flight");
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        InputStream is = response.getEntityInputStream();
        int ch;
        StringBuilder sb = new StringBuilder();
        try {
            while((ch = is.read())!= -1)
                sb.append((char)ch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private Flight entityToDto(FlightEntity entity) {
        Flight f = new Flight();

        if (entity != null) {
            f.setId(entity.getId());
            f.setName(entity.getName());
            f.setDateOfDeparture(entity.getDateOfDeparture());
            f.setDistance(entity.getDistance());
            f.setFrom(entity.getFrom().getId());
            f.setTo(entity.getTo().getId());
            f.setPrice(entity.getPrice());
            f.setSeats(entity.getSeats());
            f.setUrl("/flight/" + entity.getId());
        }

        return f;
    }

    private FlightEntity dtoToEntity(Flight f) {
        FlightEntity entity = new FlightEntity();

        DestinationEntity from = new DestinationEntity();
        DestinationEntity to = new DestinationEntity();

        from.setId(f.getFrom());
        to.setId(f.getTo());

        if (entity != null) {
            entity.setId(f.getId());
            entity.setName(f.getName());
            entity.setDateOfDeparture(f.getDateOfDeparture());
            entity.setDistance(f.getDistance());
            entity.setFrom(from);
            entity.setTo(to);
            entity.setPrice(f.getPrice());
            entity.setSeats(f.getSeats());
        }

        return entity;
    }
}
