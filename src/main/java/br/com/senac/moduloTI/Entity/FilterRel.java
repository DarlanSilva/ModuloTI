package br.com.senac.moduloTI.Entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 *
 * @author Darlan Silva
 */
@Component
public class FilterRel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Temporal(TemporalType.DATE)
    private Date  dtInicio;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Temporal(TemporalType.DATE)
    private Date  dtFinal;

    public FilterRel() {
    }

    public FilterRel(Date  dtInicio, Date  dtFinal) {
        this.dtInicio = dtInicio;
        this.dtFinal = dtFinal;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date  dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date  dtFinal) {
        this.dtFinal = dtFinal;
    }
    
    

   
}
