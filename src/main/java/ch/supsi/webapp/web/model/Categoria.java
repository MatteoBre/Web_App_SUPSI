package ch.supsi.webapp.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categoria")
public class Categoria {
    @Id
    private String idCategoria;
    @OneToMany(mappedBy = "categoria")
    private List<Blogpost> blogpostList;
    public Categoria(){}

    public Categoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdCategoria() {
        return idCategoria;
    }
}
