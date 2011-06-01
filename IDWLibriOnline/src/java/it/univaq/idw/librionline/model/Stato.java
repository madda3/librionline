/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author giacomolm
 */
public interface Stato {

    boolean equals(Object object);

    Integer getId();

    String getStato();

    @XmlTransient
    Collection<Volume> getVolumeCollection();

    int hashCode();

    void setId(Integer id);

    void setStato(String stato);

    void setVolumeCollection(Collection<Volume> volumeCollection);

    String toString();
    
}
