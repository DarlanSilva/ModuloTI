package br.com.senac.moduloTI.Entity;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 *
 * @author Darlan Silva
 */
@Component
public class FilterRel {

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    //@Temporal(TemporalType.DATE)
    private LocalDate  dtInicio;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    //@Temporal(TemporalType.DATE)
    private LocalDate  dtFinal;

    public FilterRel() {
    }

    public FilterRel(LocalDate  dtInicio, LocalDate  dtFinal) {
        this.dtInicio = dtInicio;
        this.dtFinal = dtFinal;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate  dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(LocalDate  dtFinal) {
        this.dtFinal = dtFinal;
    }
    
    

   
}
