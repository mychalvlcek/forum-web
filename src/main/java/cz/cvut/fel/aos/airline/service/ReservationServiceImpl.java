package cz.cvut.fel.aos.airline.service;

import cz.cvut.fel.aos.airline.api.data.Reservation;
import cz.cvut.fel.aos.airline.entity.FlightEntity;
import cz.cvut.fel.aos.airline.entity.ReservationEntity;
import cz.cvut.fel.aos.airline.entity.StateChoices;
import cz.cvut.fel.aos.airline.persistence.GenericDao;
import cz.cvut.fel.aos.airline.persistence.GenericDaoImpl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ReservationService implementation
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 26.10.14
 */
public class ReservationServiceImpl implements ReservationService {
    private GenericDao genericDao = new GenericDaoImpl();

    @Override
    public List<Reservation> find(String sort, String filter, int base, int offset) {
        if (sort == "") {
            sort = "e.id asc";
        } else {
            sort = "e." + sort.replace(":", " ");
        }
        return this.format(genericDao.getPage(sort, filter, base, offset, ReservationEntity.class));
    }

    @Override
    public List<Reservation> getAll() {
        List<ReservationEntity> entities = genericDao.getAll(ReservationEntity.class);
        List<Reservation> dtos = new ArrayList<Reservation>();

        for (ReservationEntity e : entities) {
            dtos.add(entityToDto(e));
        }
        return dtos;
    }

    @Override
    public Reservation get(Long id) {
        return entityToDto(genericDao.getById(id, ReservationEntity.class));
    }

    @Override
    public void delete(Long id) {
        genericDao.removeById(id, ReservationEntity.class);
    }

    @Override
    public void save(Reservation entity) {
        // check if is free
        // SELECT seats FROM flight WHERE id = entity.getFlight()
        // SELECT COUNT(*) FROM reservations WHERE state IN ('NEW', 'PAID') AND flight = entity.getFlight()
        // if (seats + entity.getSeats() > count) throw err else continue

        entity.setState(StateChoices.NEW);
        entity.setCreated(new Date());
        entity.setPassword(generatePassword());
        genericDao.saveOrUpdate(dtoToEntity(entity));
    }

    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    @Override
    public void cancel(Long id) throws Exception {
        // check state
        ReservationEntity r = genericDao.getById(id, ReservationEntity.class);
        if (r.getState().equals(StateChoices.NEW)) {
            r.setState(StateChoices.CANCELED);
        } else {
            throw new Exception("Only NEW reservation can be canceled");
        }

        genericDao.saveOrUpdate(r);
    }

    @Override
    public void pay(Long id) throws Exception {
        // check state
        ReservationEntity r = genericDao.getById(id, ReservationEntity.class);
        if (r.getState().equals(StateChoices.NEW)) {
            r.setState(StateChoices.PAID);
        } else {
            throw new Exception("Can't pay \"" + r.getState().name() + "\" reservation");
        }

        genericDao.saveOrUpdate(r);
    }

    @Override
    public Long getCount() {
        return genericDao.getCount(ReservationEntity.class);
    }

    // =========== Helpers ================

    private List<Reservation> format(List<ReservationEntity> entities) {
        List<Reservation> dtos = new ArrayList<Reservation>();

        for (ReservationEntity c : entities) {
            dtos.add(entityToDto(c));
        }
        return dtos;
    }

    private Reservation entityToDto(ReservationEntity entity) {
        Reservation r = new Reservation();

        if (entity != null) {
            r.setId(entity.getId());
            r.setCreated(entity.getCreated());
            r.setSeats(entity.getSeats());
            r.setPassword(entity.getPassword());
            r.setState(entity.getState());
            r.setUrl("/reservation/" + entity.getId());
            r.setFlight(entity.getFlight().getId());
        }

        return r;
    }

    private ReservationEntity dtoToEntity(Reservation r) {
        ReservationEntity entity = new ReservationEntity();

        FlightEntity flight = new FlightEntity();

        flight.setId(r.getFlight());

        if (entity != null) {
            entity.setId(r.getId());
            entity.setCreated(r.getCreated());
            entity.setSeats(r.getSeats());
            entity.setPassword(r.getPassword());
            entity.setState(r.getState());
            entity.setFlight(flight);
        }

        return entity;
    }
}
