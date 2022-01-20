package it.uniroma3.siw.officina.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mechanical {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private String surname;
    
    @OneToMany(mappedBy="mechanical")
    private List<Intervention> interventions = new ArrayList<Intervention>();

    public Mechanical() {
    }

    public Mechanical(Long id, String name, String surname) {
        this();
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Intervention> getInterventions() {
        return this.interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
}