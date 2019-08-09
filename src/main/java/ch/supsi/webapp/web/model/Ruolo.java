package ch.supsi.webapp.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ruolo")
public class Ruolo {
    @Id
    private String idRuolo;
    @OneToMany(mappedBy = "ruolo")
    private List<Utente> utenteList;

    public Ruolo(String ruolo) {
        idRuolo = ruolo;
    }

    public Ruolo(){}

    public String getRuolo() {
        return idRuolo;
    }

    public void setRuolo(String ruolo) {
        idRuolo = ruolo;
    }

    public void setUtenteList(List<Utente> utenteList) {
        this.utenteList = utenteList;
    }
}
