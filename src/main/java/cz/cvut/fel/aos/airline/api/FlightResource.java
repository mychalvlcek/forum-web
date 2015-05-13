package cz.cvut.fel.aos.airline.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import cz.cvut.fel.aos.airline.api.data.Flight;
import cz.cvut.fel.aos.airline.service.FlightService;
import cz.cvut.fel.aos.airline.service.FlightServiceImpl;

import java.util.List;

/**
 * Flight resource is class which provides methods for handling CRUD events on flight entities.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 24.10.14
 */
@Path(value = "flight")
public class FlightResource {

    private final FlightService service;


	/**
	 * Construct a resource with Flight service.
	 */
    public FlightResource() {
        this.service = new FlightServiceImpl();
    }

    /**
     * Returns all destinations based on given filter.
     * @param filter filtering (e.g.: "dateOfDepartureFrom=2013-02-27T02:04:46+01:00,dateOfDepartureTo=2013-02-27T03:04:46+01:00")
     * @param order ordering (e.g.: "name:desc")
     * @param base paging param (e.g.: 10)
     * @param offset paging param (e.g.: 10)
     * @return List of destinations
     */
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll(@HeaderParam("X-Filter") String filter,
                                    @HeaderParam("X-Order") String order,
                                    @HeaderParam("X-Base") String base,
                                    @HeaderParam("X-Offset") String offset,
                                    @HeaderParam("X-Currency") String currency
    ) {
        int baseParam = service.getCount().intValue();
        int offsetParam = 0;
        if (base != null) {
            baseParam = Integer.valueOf(base);
        }
        if (offset != null) {
            offsetParam = Integer.valueOf(offset);
        }
        if (order == null) {
            order = "";
        }
        if (currency == null) {
            currency = "CZK";
        }

        List<Flight> result = service.find(order, filter, Integer.valueOf(baseParam), Integer.valueOf(offsetParam), currency);

        Response.ResponseBuilder rb = Response.ok(result);
        rb.header("X-Count-records", service.getCount());
        return  rb.build();
    }

    /**
     * Save flight entity.
     * @param entity Flight
     * @return
     */
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed("admin")
    public Response save(Flight entity) {
        service.save(entity);
        return Response.status(Status.OK).entity("Successfully saved").type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Update single flight entity.
     * @param id of flight
     * @param entity Flight
     * @return
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed("admin")
    public Response update(@PathParam("id") Long id, Flight entity) {
        entity.setId(id);
        service.save(entity);
        return Response.status(Status.OK).entity("Successfully updated").type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Return single flight by id.
     * @param id of flight
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Flight get(@PathParam("id") Long id) {
        return service.get(id);
    }

    /**
     * Remove flight by id.
     * @param id of flight
     * @return
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.OK).entity("Successfully deleted").type(MediaType.APPLICATION_JSON).build();
    }

}
