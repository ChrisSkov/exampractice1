/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "hobbies")
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<User> user;
    private String name;
    private String description;

    public Hobby()
    {
    }

    public List<User> getUser()
    {
        return user;
    }

    public void setUser(List<User> user)
    {
        this.user = user;
    }

 

    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

 

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hobby))
        {
            return false;
        }
        Hobby other = (Hobby) object;
        return this.id == other.id;
    }

    @Override
    public String toString()
    {
        return "entities.NewEntity[ id=" + id + " ]";
    }

}
