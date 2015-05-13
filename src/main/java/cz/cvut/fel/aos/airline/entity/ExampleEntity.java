package cz.cvut.fel.aos.airline.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * examples
 * @Id
 * @GeneratedValue(generator="system-sequence")
 * @GenericGenerator(name="system-sequence", strategy = "sequence")
 *
 * @ManyToOne
 * private User author;
 *
 * @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})

 * @OneToMany(mappedBy="author", fetch=FetchType.LAZY)
 * @OrderBy("created DESC")
 * private List<Message> messages;
 *
 * @OneToOne(fetch=FetchType.LAZY)
 * @JoinColumn(name="ADDRESS_ID")
 */

@Entity
@Table(name = "_example")
public class ExampleEntity extends AbstractEntity {

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    public ExampleEntity() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
    }

    public ExampleEntity(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Updating timestamps before each transaction.
     */
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        updated = new Date();
        if (created == null) {
            created = new Date();
        }
    }
}
