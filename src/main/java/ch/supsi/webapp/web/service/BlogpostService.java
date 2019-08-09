package ch.supsi.webapp.web.service;

import ch.supsi.webapp.web.repository.BlogpostRepository;
import ch.supsi.webapp.web.repository.CategoriaRepository;
import ch.supsi.webapp.web.repository.RuoloRepository;
import ch.supsi.webapp.web.repository.UtenteRepository;
import ch.supsi.webapp.web.model.Blogpost;
import ch.supsi.webapp.web.model.Categoria;
import ch.supsi.webapp.web.model.Ruolo;
import ch.supsi.webapp.web.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogpostService {
    @Autowired
    private BlogpostRepository blogpostRepository;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        //salvo il ruolo
        Ruolo ruolo = new Ruolo("ROLE_ADMIN");
        ruoloRepository.save(ruolo);

        //salvo utente admin
        Utente user = new Utente("admin","admin",ruolo, "Matteo", "Bresciani");
        postUser(user);

        //salvo le categorie
        categoriaRepository.save(new Categoria("Post Non Gestionale"));
        categoriaRepository.save(new Categoria("Swiss Post"));
        categoriaRepository.save(new Categoria("Scienza Post"));
        categoriaRepository.save(new Categoria("Cultura Post"));
        categoriaRepository.save(new Categoria("Sport Post"));
        Categoria nonGest = categoriaRepository.save(new Categoria("Post Non Gestionale"));

        //salvo 1 blogpost solo in caso non ce ne siano
        if(blogpostRepository.findAll().size()==0) {
            Blogpost blg = new Blogpost();
            blg.setUtente(utenteRepository.findById("admin").get());
            blg.setCategoria(nonGest);
            blg.setTitle("Il gestionale che dorme all'ombra di un pino");
            blg.setText("non è nè sdraiato, nè supino.");
            blg.setDate(new Date());
            blogpostRepository.save(blg);
        }
    }

    //operazioni sui blogpost

    public List<Blogpost> getBlogposts() {
        return blogpostRepository.findAll();
    }

    public Blogpost getBlogpostByID(int id) {
        Optional<Blogpost> opt = blogpostRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        return null;
    }

    public boolean exists(int id){
        return blogpostRepository.existsById(id);
    }

    public Blogpost saveBlogpost(Blogpost bPost) {
        return blogpostRepository.save(bPost);
    }

    public Blogpost modifica(Blogpost bPost, int id) {
        return blogpostRepository.save(bPost);
    }

    public boolean elimina(int id) {
        blogpostRepository.deleteById(id);
        if(exists(id))
            return true;
        return false;
    }

    //operazioni su categoria

    public List<Categoria> getCategories() {
        return categoriaRepository.findAll();
    }

    public List<Blogpost> searchByCategory(String categoria) {
        return blogpostRepository.findByCategoria(categoria);
        //findTop5ByCategoryNameIgnoreCaseOrderByDateDesc(String category);
    }


    //operazioni su utente

    public List<Utente> getAuthors() {
        return utenteRepository.findAll();
    }

    public Utente findUserByUsername(String username) {
        List<Utente> list = utenteRepository.findAll();
        for (Utente u : list) {
            if (u.getUsername().equals(username))
                return u;
        }
        return null;
    }

    public void postUser(Utente utente) {
        ruoloRepository.save(utente.getRuolo());

        String passEnc = encoder.encode(utente.getPassword());
        utente.setPassword(passEnc);

        utenteRepository.save(utente);
    }

    //operazioni su ruolo

    public List<Ruolo> getRoles(){
        return ruoloRepository.findAll();
    }
}
