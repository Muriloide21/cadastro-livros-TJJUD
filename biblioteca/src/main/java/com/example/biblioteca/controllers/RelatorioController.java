/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.AutorLivroAssuntoDTO;
import com.example.biblioteca.models.AutorLivroAssuntoModel;
import com.example.biblioteca.repository.AutorLivroAssuntoRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Murilo Schirmer
 */
@Controller
public class RelatorioController {

    @Autowired
    private AutorLivroAssuntoRepository autorLivroAssuntoRepository;

    @GetMapping("/relatorios/gerar-pdf")
    public void gerarRelatorioPdf(HttpServletResponse response) throws JRException, IOException {
        // Recupera os dados da view
        List<AutorLivroAssuntoModel> listaLivrosAutoresAssuntos = (List<AutorLivroAssuntoModel>) autorLivroAssuntoRepository.findLivroAutorAssunto();

        InputStream reportStream = getClass().getClassLoader().getResourceAsStream("reports/livro_relatorio.jasper");

        // Prepara os parâmetros
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatório de Livros, Autores e Assuntos");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaLivrosAutoresAssuntos);
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parametros, dataSource);

        // Configura a resposta HTTP para gerar o PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");

        // Exporta o relatório para PDF
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        exporter.exportReport();
    }
}
