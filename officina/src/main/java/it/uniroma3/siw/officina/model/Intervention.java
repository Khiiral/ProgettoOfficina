package it.uniroma3.siw.officina.model;



import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Intervention {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private LocalDate dateOfPrenotation;
    
    private String dateIntervention;
    
    @ManyToOne
    private User client;
    
    @ManyToOne
    private Mechanical mechanical;
    
    @ManyToOne
    private TypeOfIntervention typeOfIntervention;

    public Intervention() {
    }

    public Intervention(Long id, LocalDate dateOfPrenotation, String dateIntervention) {
        this.id = id;
        this.dateOfPrenotation = dateOfPrenotation;
        this.dateIntervention = dateIntervention;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfPrenotation() {
        return this.dateOfPrenotation;
    }

    public void setDateOfPrenotation(LocalDate dateOfPrenotation) {
        this.dateOfPrenotation = dateOfPrenotation;
    }

    public String getDateIntervention() {
        return this.dateIntervention;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Mechanical getMechanical() {
        return this.mechanical;
    }

    public void setMechanical(Mechanical mechanical) {
        this.mechanical = mechanical;
    }

    public TypeOfIntervention getTypeOfIntervention() {
        return this.typeOfIntervention;
    }

    public void setTypeOfIntervention(TypeOfIntervention typeOfIntervention) {
        this.typeOfIntervention = typeOfIntervention;
    }
}