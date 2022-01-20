package it.uniroma3.siw.officina.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TypeOfIntervention {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private String description;
    
    private Long cost;
    
    @OneToMany(mappedBy="typeOfIntervention")
    private List<Intervention> interventions = new ArrayList<Intervention>();

    public TypeOfIntervention() {
    }

    public TypeOfIntervention(Long id, String name, String description, Long cost) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return this.cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public List<Intervention> getInterventions() {
        return this.interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
}