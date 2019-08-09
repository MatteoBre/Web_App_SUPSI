package ch.supsi.webapp.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Utente")
public class Utente {
    @Id
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "idRuolo")
    private Ruolo ruolo;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "utente")
    private List<Blogpost> blogpostList;

    public Utente(String username, String password, Ruolo ruolo, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Utente(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
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
}
