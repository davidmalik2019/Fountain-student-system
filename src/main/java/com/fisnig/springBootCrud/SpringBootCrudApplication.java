package com.fisnig.springBootCrud;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fisnig.springBootCrud.service.JReportcourseService;
import com.fisnig.springBootCrud.service.JReportinstructorService;
import com.fisnig.springBootCrud.service.JReportstudentService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
@RestController
public class SpringBootCrudApplication {

	@Autowired
    private JReportcourseService jreportcourseService;
	@Autowired
    private JReportinstructorService jreportinstructorService;
	
	@Autowired
    private JReportstudentService jreportstudentService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}
	 @GetMapping("/jasperpdf/course/export")
	    public void createOrderPDF(HttpServletResponse response) throws IOException, JRException {
	       response.setContentType("application/pdf");
	       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	       String currentDateTime = dateFormatter.format(new Date());
	  
	       String headerKey = "Content-Disposition";
	       String headerValue = "attachment; filename=Courses_" + currentDateTime + ".pdf";
	       response.setHeader(headerKey, headerValue);
	  
	       jreportcourseService.exportJasperReport(response);
	    }
	 @GetMapping("/jasperpdf/instructors/export")
	    public void createinstructorsPDF(HttpServletResponse response) throws IOException, JRException {
	       response.setContentType("application/pdf");
	       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	       String currentDateTime = dateFormatter.format(new Date());
	  
	       String headerKey = "Content-Disposition";
	       String headerValue = "attachment; filename=Instructors" + currentDateTime + ".pdf";
	       response.setHeader(headerKey, headerValue);
	  
	       jreportinstructorService.exportJasperReport(response);
	    }
	 @GetMapping("/jasperpdf/student/export")
	    public void createstudentPDF(HttpServletResponse response) throws IOException, JRException {
	       response.setContentType("application/pdf");
	       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	       String currentDateTime = dateFormatter.format(new Date());
	  
	       String headerKey = "Content-Disposition";
	       String headerValue = "attachment; filename=Students" + currentDateTime + ".pdf";
	       response.setHeader(headerKey, headerValue);
	  
	       jreportstudentService.exportJasperReport(response);
	    }
	}
	   
