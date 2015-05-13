package cz.cvut.fel.aos.airline.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import cz.cvut.fel.aos.airline.api.data.Payment;
import cz.cvut.fel.aos.airline.api.data.Reservation;
import cz.cvut.fel.aos.airline.service.ReservationService;
import cz.cvut.fel.aos.airline.service.ReservationServiceImpl;

import java.util.List;

/**
 * Reservation resource is class which provides methods for handling CRUD events on reservation entities.
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 26.10.14
 */
@Path(value = "reservation")
public class ReservationResource {

    private final ReservationService service;


	/**
	 * Construct a resource with Reservation service.
	 */
    public ReservationResource() {
        this.service = new ReservationServiceImpl();
    }

    /**
     * Gets all destinations.
     * XML nefunkncni:
     *  - http://stackoverflow.com/questions/15618061/a-message-body-writer-for-java-class-java-util-arraylist-and-mime-media-type-t
     *  - http://stackoverflow.com/questions/11771830/jax-rs-how-to-automatically-serialize-a-collection-when-returning-a-response-ob
     *
     * @param filter filtering (e.g.: "dateOfDepartureFrom=2013-02-27T02:04:46+01:00,dateOfDepartureTo=2013-02-27T03:04:46+01:00")
     * @param order ordering (e.g.: "name:desc")
     * @param base paging param (e.g.: 10)
     * @param offset paging param (e.g.: 10)
     * @return List of destinations
     */
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed({"admin", "manager"})
    public Response getAll(@HeaderParam("X-Filter") String filter,
                           @HeaderParam("X-Order") String order,
                           @HeaderParam("X-Base") String base,
                           @HeaderParam("X-Offset") String offset
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

        List<Reservation> result = service.find(order, filter, Integer.valueOf(baseParam), Integer.valueOf(offsetParam));

        Response.ResponseBuilder rb = Response.ok(result);
        rb.header("X-Count-records", service.getCount());
        return  rb.build();
    }

    /**
     * Save new reservation
     * @param entity reservation
     * @return
     */
    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed({"admin", "manager"})
    public Response save(Reservation entity) {
        service.save(entity);
        return Response.status(Status.OK).entity("Successfully saved").type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Update single reservation by id.
     * Requires "X-Password" header to access
     * @param password
     * @param id
     * @return
     */
    @PUT
    @Path("{id}")
    @RolesAllowed({"admin", "manager"})
    public Response update(@HeaderParam("X-Password") String password, @PathParam("id") Long id) {
        try {
            Reservation reservation = service.get(id);
            if (reservation.getPassword().equals(password)) {
                service.cancel(id);
                return Response.status(Status.OK).entity("Successfully canceled").type(MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Method pays the reservation specified by the given id.
     * Requires "X-Password" header to access
     * @param id reservation id
     * @param payment payment which pays for the reservation
     * @return response
     */
    @POST
    @Path("{id}/payment")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed({"admin", "manager"})
    public Response pay(@PathParam("id") Long id, Payment payment) {
        System.out.println(payment.getBankAccount());
        try {
            service.pay(id);
            return Response.status(Status.OK).entity("Successfully paid").type(MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Return single reservation by id
     * @param password
     * @param id of reservation
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed({"admin", "manager"})
    public Response get(@HeaderParam("X-Password") String password, @PathParam("id") Long id) {
        Reservation reservation = service.get(id);
        if (reservation.getPassword().equals(password)) {
            return Response.status(Response.Status.OK).entity(reservation).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    /**
     * Remove reservation by id.
     * @param id of reservation
     * @return
     */
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "manager"})
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.OK).entity("Successfully deleted").type(MediaType.APPLICATION_JSON).build();
    }

}
