package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.service.GeneratingInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class GeneratingInvoiceController {
    @Autowired
    GeneratingInvoiceService generatingInvoiceService;

    @GetMapping("/generateinvoice/{booking_id}")
    public void generateInvoice(@PathVariable Long booking_id,HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = invoice.pdf";

        response.setHeader(headerKey,headerValue);
        generatingInvoiceService.export(response,booking_id);

    }
}
