package ch.supsi.webapp.web.control;

import org.json.JSONObject;
import org.json.JSONArray;
import ch.supsi.webapp.web.service.BlogpostService;
import ch.supsi.webapp.web.model.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BlogpostController {

    @Autowired
    private BlogpostService blogpostService;

    @RequestMapping(value = "/blogposts", method = RequestMethod.GET)
    public ResponseEntity<List<Blogpost>> getBlogposts() {
        return new ResponseEntity<>(blogpostService.getBlogposts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/blogposts", method = RequestMethod.POST)
    public ResponseEntity<? extends Object> postBlogpost(@RequestBody Blogpost bPost) {
        if (bPost.getUtente() == null || bPost.getText() == null || bPost.getTitle() == null) {
            return new ResponseEntity<String>("Bad Request, author,title e text devono essere tutti specificati", HttpStatus.BAD_REQUEST);
        }
        bPost.setDate(new Date());
        blogpostService.saveBlogpost(bPost);
        return new ResponseEntity<Blogpost>(bPost, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Blogpost> getResource(@PathVariable int id) {
        Blogpost blg = blogpostService.getBlogpostByID(id);
        if (blg == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blg, HttpStatus.OK);
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<? extends Object> putResource(@PathVariable int id, @RequestBody Blogpost bPost) {
        Blogpost blg = blogpostService.getBlogpostByID(id);
        if (blg == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        blogpostService.modifica(bPost,id);
        return new ResponseEntity<>(blogpostService.getBlogpostByID(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<? extends Object> deleteResource(@PathVariable int id) {
        Blogpost blg = blogpostService.getBlogpostByID(id);
        if (blg == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        blogpostService.elimina(id);
        return new ResponseEntity<>("{\n\"success\": true\n}", HttpStatus.OK);
    }

    @GetMapping("/blogpost/search")
    public ResponseEntity<String> searchBlogpost(@RequestParam("q") String query) {
        List<Blogpost> allBlogposts = blogpostService.getBlogposts();
        List<Blogpost> list = new ArrayList<>();
        for(Blogpost b : allBlogposts){
            if(b.getText().toLowerCase().contains(query.toLowerCase()) || b.getTitle().toLowerCase().contains(query.toLowerCase()))
                list.add(b);
        }
        //create json array
        JSONArray jsonArray = new JSONArray();

        //create and add al the json objects
        for(Blogpost b : list){
            JSONObject obj = new JSONObject();
            obj.put("id", b.getId());
            obj.put("title", b.getTitle());
            if(b.getText().length() <= 4)
                obj.put("text", b.getText());
            else
                obj.put("text", b.getText().substring(0,4)+"...");
            obj.put("author", b.getUtente().getUsername());
            obj.put("category", b.getCategoria().getIdCategoria());
            obj.put("date", b.getDate());
            jsonArray.put(obj);
        }
        return new ResponseEntity<>(jsonArray.toString(), HttpStatus.OK);
    }
}
