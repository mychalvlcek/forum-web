package cz.cvut.fel.aos.airline.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import cz.cvut.fel.aos.airline.api.data.Destination;
import cz.cvut.fel.aos.airline.service.DestinationService;
import cz.cvut.fel.aos.airline.service.DestinationServiceImpl;

import java.util.List;

/**
 * Destination resource is class which provides methods for handling CRUD events on destination entities.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 21.10.14
 */
@Path(value = "destination")
public class DestinationResource {

    private final DestinationService service;

	/**
	 * Construct a resource with Destination service.
	 */
    public DestinationResource() {
        this.service = new DestinationServiceImpl();
    }

    /**
     * Returns all destinations based on filter.
     * @param filter filtering (e.g.: "dateOfDepartureFrom=2013-02-27T02:04:46+01:00,dateOfDepartureTo=2013-02-27T03:04:46+01:00")
     * @param order ordering (e.g.: "name:desc")
     * @param base paging param (e.g.: 10)
     * @param offset paging param (e.g.: 10)
     * @return List of destinations
     */
    @GET
    @Path("/")
    // @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDestinations(@HeaderParam("X-Filter") String filter,
                                    @HeaderParam("X-Order") String order,
                                    @HeaderParam("X-Base") String base,
                                    @HeaderParam("X-Offset") String offset
                                    ) {
        List result = null;
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

        result = service.find(order, filter, Integer.valueOf(baseParam), Integer.valueOf(offsetParam));

        Response.ResponseBuilder rb = Response.status(Status.OK);
        rb.header("X-Count-records", service.getCount());
        rb.entity(result);
        return  rb.build();
    }

    /**
     * Save new destination
     * @param destination Destination
     * @return response message
     */
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed("admin")
    public Response saveDestination(Destination destination) {
        service.save(destination);
        return Response.status(Status.OK).entity("Successfully saved").type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Update existing destination
     * @param id of edited entity
     * @param destination Destination
     * @return response message
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed("admin")
    public Response updateDestination(@PathParam("id") Long id, Destination destination) {
        destination.setId(id);
        service.save(destination);
        return Response.status(Status.OK).entity("Successfully updated").type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Retrieve destination specified by id
     * @param id of entity
     * @return destination
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Destination getDestination(@PathParam("id") Long id) {
        return service.get(id);
    }

    /**
     * Remove destination
     * @param id of entity
     * @return message response
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response deleteDestination(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.OK).entity("Successfully deleted").type(MediaType.APPLICATION_JSON).build();
    }

}
