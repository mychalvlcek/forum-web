package cz.cvut.fel.aos.airline.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import cz.cvut.fel.aos.airline.api.data.Destination;
import cz.cvut.fel.aos.airline.entity.DestinationEntity;
import cz.cvut.fel.aos.airline.helpers.GPS;
import cz.cvut.fel.aos.airline.persistence.GenericDao;
import cz.cvut.fel.aos.airline.persistence.GenericDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Destination resource
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 21.10.14
 */
public class DestinationServiceImpl implements DestinationService {

    private GenericDao genericDao = new GenericDaoImpl();

    @Override
    public List<Destination> getAll() {
        return this.format(genericDao.getAll(DestinationEntity.class));
    }

    @Override
    public List<Destination> find(String sort, String filter, int base, int offset) {
        if (sort == "") {
            sort = "e.id asc";
        } else {
            sort = "e." + sort.replace(":", " ");
        }
        return this.format(genericDao.getPage(sort, filter, base, offset, DestinationEntity.class));
    }

    @Override
    public Destination get(Long id) {
        return entityToDestination(genericDao.getById(id, DestinationEntity.class));
    }

    @Override
    public void delete(Long id) {
        genericDao.removeById(id, DestinationEntity.class);
    }


    @Override
    public void save(Destination destination) {
        GPS gps = getGps(destination.getName());
        if (gps != null) {
            destination.setLat(gps.lat);
            destination.setLon(gps.lng);
        }
        genericDao.saveOrUpdate(destinationToEntity(destination));
    }


    public Long getCount() {
        return genericDao.getCount(DestinationEntity.class);
    }

    // =========== Helpers ================

    private List<Destination> format(List<DestinationEntity> destinations) {
        List<Destination> destinationsDtos = new ArrayList<Destination>();

        for (DestinationEntity c : destinations) {
            destinationsDtos.add(entityToDestination(c));
        }
        return destinationsDtos;
    }

    private Destination entityToDestination(DestinationEntity entity) {
        Destination destination = new Destination();

        if (entity != null) {
            destination.setId(entity.getId());
            destination.setName(entity.getName());
            destination.setLat(entity.getLat());
            destination.setUrl("/destination/" + entity.getId());
            destination.setLon(entity.getLon());
        }

        return destination;
    }

    private DestinationEntity destinationToEntity(Destination destination) {
        DestinationEntity entity = new DestinationEntity();

        if (destination != null) {
            entity.setId(destination.getId());
            entity.setName(destination.getName());
            entity.setLat(destination.getLat());
            entity.setLon(destination.getLon());
        }

        return entity;
    }

    /**
     * Get GPS coordinates from The Google Geocoding API.
     * @param destination name of the destination to search
     * @return GPS
     */
    private GPS getGps(String destination) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://maps.googleapis.com/maps/api/geocode/json")
                .queryParam("address", destination).queryParam("sensor", "false");
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

            JsonObject location = element.getAsJsonObject()
                    .get("results").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("geometry").getAsJsonObject().get("location").getAsJsonObject();
            return new GPS(location.get("lat").getAsDouble(), location.get("lng").getAsDouble());
        }
        return null;
    }

}
