/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.peserta.rest.springmvc;

import aplikasi.peserta.domain.Peserta;
import aplikasi.peserta.service.PesertaService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Student-03
 */
@Controller
@RequestMapping("/peserta")
public class PesertaController {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:pstContext-impl.xml");
    private static PesertaService service = (PesertaService) ctx.getBean("pesertaServiceDao");
    
    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex, HttpServletRequest request) {
        return ClassUtils.getShortName(ex.getClass());
    }
    
    @RequestMapping(value = "/{noKartu}/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Peserta> getPesertaByNoKartu(@PathVariable String noKartu, @PathVariable int id) {
        ResponseEntity<Peserta> entity = null;

        try {
            Peserta p = service.findPesertaByNoKartu(noKartu, id);
            if (p != null) {
                entity = new ResponseEntity<Peserta>(p, HttpStatus.OK);                
            } else {
                entity = new ResponseEntity<Peserta>(HttpStatus.NOT_FOUND);
                return entity;                
            }
        } catch (Exception e) {
            entity = new ResponseEntity<Peserta>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping(value = "/nip/{nip}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Peserta>> getPesertaByNip(@PathVariable String nip) {
        ResponseEntity<List<Peserta>> entity = null;

        try {
            List<Peserta> p = service.findListPesertaByNip(nip);
            if (p != null) {
                entity = new ResponseEntity<List<Peserta>>(p, HttpStatus.OK);                
            } else {
                entity = new ResponseEntity<List<Peserta>>(HttpStatus.NOT_FOUND);
                return entity;                
            }
        } catch (Exception e) {
            entity = new ResponseEntity<List<Peserta>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }    

}
