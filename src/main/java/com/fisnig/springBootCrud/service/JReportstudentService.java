package com.fisnig.springBootCrud.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;



import com.fisnig.springBootCrud.model.Customer;
import com.fisnig.springBootCrud.model.Teacher;
import com.fisnig.springBootCrud.repository.CustomerRepository;
import com.fisnig.springBootCrud.repository.TeacherRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class JReportstudentService {
 
    @Autowired
    private CustomerRepository customerrepository;
 
 
    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
        List<Customer> customer = (List<Customer>) customerrepository.findAll();
        //Get file and compile it
        File file = ResourceUtils.getFile("classpath:students.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customer);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Simplifying Tech");
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}
