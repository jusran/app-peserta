package aplikasi.peserta.service.impl.demo;

import aplikasi.peserta.domain.Peserta;
import aplikasi.peserta.service.PesertaService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringJdbcDemo {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:pstContext-impl.xml");
    PesertaService pstDaoSpring = (PesertaService) ctx.getBean("pesertaServiceDao");

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringJdbcDemo j = new SpringJdbcDemo();
        j.checkPeserta();

    }
    
    public void checkPeserta() {
        Peserta p = pstDaoSpring.findPesertaByNoKartu("0000000008638");

        String format = new String();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dtFormat = new Date();

        try {
            System.out.println("tgllhr : " + p.getTanggalLahir());
            dtFormat = p.getTanggalLahir();
            format = sdf.format(dtFormat);
        } catch (Exception e) {
        }
        
        System.out.println("noka : " + p.getNokaBarcode());
        System.out.println("Nama : " + p.getNama());
        System.out.println("Tanggal lahir : " + p.getTanggalLahir());
    }
}
