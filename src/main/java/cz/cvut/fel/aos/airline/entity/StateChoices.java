package cz.cvut.fel.aos.airline.entity;

/**
 * StateChoices.
 *
 * For correct functionality new DB items (reservation) must be inserted throw program (not by SQL script)
 *
 * @author Michal Vlček<mychalvlcek@gmail.com>
 * @date 26.10.14
 */
public enum StateChoices {
    NEW,
    PAID,
    CANCELED
}
