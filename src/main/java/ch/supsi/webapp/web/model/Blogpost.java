package ch.supsi.webapp.web.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BLOGPOST")
public class Blogpost {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "username")
    private Utente utente;
    private Date date;

    public Blogpost() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }
}
