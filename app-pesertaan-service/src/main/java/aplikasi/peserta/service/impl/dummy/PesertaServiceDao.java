/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.peserta.service.impl.dummy;

import aplikasi.peserta.domain.Peserta;
import aplikasi.peserta.service.PesertaService;
import aplikasi.peserta.service.impl.error.CustomSQLErrorCodesTranslator;
import aplikasi.refer.domain.User;
import aplikasi.refer.service.UserService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Divisi-TI
 */
@Repository("pesertaServiceDao")
@Transactional
public class PesertaServiceDao implements PesertaService {

    private JdbcTemplate jdbcTemplate = null;
    private SimpleJdbcTemplate simpleJdbcTemplate = null;
    
    private static ApplicationContext ctxUser = new ClassPathXmlApplicationContext("classpath:refContext-impl.xml");
    private static UserService serviceUser = (UserService) ctxUser.getBean("userServiceDao");

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);

        // create a custom translator and set the DataSource for the default translation lookup 
        CustomSQLErrorCodesTranslator tr = new CustomSQLErrorCodesTranslator();
        tr.setDataSource(dataSource);
        this.jdbcTemplate.setExceptionTranslator(tr);
//        this.simpleJdbcTemplate.setExceptionTranslator(tr);
    }

    @Override
    public Peserta findPesertaByNoKartu(String noKartu) {
        
        String sql = "select a.nokapst, a.nmpst, a.pisapst, a.tgllhrpst, a.jkpst from datnkapst a where a.nokapst=?";
        Peserta pst = simpleJdbcTemplate.queryForObject(sql, new PesertaRowMapper(), noKartu);            
        return pst;
    }

    @Override
    public List<Peserta> findListPesertaByNip(String nip) {
        if (nip.equalsIgnoreCase("") || nip == null)
                return null;
        
        String sql = "select a.nokapst, a.nmpst, a.pisapst, a.tgllhrpst, a.jkpst from datnkapst a where a.dpsnip1=?";
        List<Peserta> pst = simpleJdbcTemplate.query(sql, new PesertaRowMapper(), nip);
        return pst;
    }

    @Override
    public Peserta findPesertaByNoKartu(String noKartu, int id) {
        
        User user = serviceUser.findUserById(id);
        if (user == null) {
            return null;
        } else {
            return findPesertaByNoKartu(noKartu);            
        }
    }

    private class PesertaRowMapper implements RowMapper<Peserta> {

        @Override
        public Peserta mapRow(ResultSet rs, int arg1) throws SQLException {
            Peserta p = new Peserta();
            p.setNokaBarcode(rs.getString("nokapst"));
            p.setNama(rs.getString("nmpst"));
            p.setTanggalLahir(rs.getDate("tgllhrpst"));
            p.setSex(rs.getString("jkpst"));
            p.setPisa(rs.getString("pisapst"));
            return p;
        }
    }
}
