/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.peserta.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Student-03
 */
@XmlRootElement
public class Peserta {
    private String nokaBarcode;
    private String nama;
    private String pisa;
    private String sex;
    private Date tanggalLahir;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    /**
     * @return the nokaBarcode
     */
    public String getNokaBarcode() {
        return nokaBarcode;
    }

    /**
     * @param nokaBarcode the nokaBarcode to set
     */
    public void setNokaBarcode(String nokaBarcode) {
        this.nokaBarcode = nokaBarcode;
    }

    /**
     * @return the pisa
     */
    public String getPisa() {
        return pisa;
    }

    /**
     * @param pisa the pisa to set
     */
    public void setPisa(String pisa) {
        this.pisa = pisa;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
}
