/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.peserta.service;

import aplikasi.peserta.domain.Peserta;
import java.util.List;

/**
 *
 * @author Divisi-TI
 */
public interface PesertaService {
    public Peserta findPesertaByNoKartu(String noKartu);
    public List<Peserta> findListPesertaByNip(String nip);
}
