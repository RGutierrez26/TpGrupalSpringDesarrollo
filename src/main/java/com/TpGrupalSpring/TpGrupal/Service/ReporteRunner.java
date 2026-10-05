package com.TpGrupalSpring.TpGrupal.Service;

import com.TpGrupalSpring.TpGrupal.DTO.Consultas.FacturaReporteDTO;
import com.TpGrupalSpring.TpGrupal.Service.ReporteFacturaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ReporteRunner {
    ReporteFacturaService reporteFacturaService;

    //inyeccion
    public ReporteRunner(ReporteFacturaService reporteFacturaService) {
        this.reporteFacturaService = reporteFacturaService;
    }

    @Bean
    public CommandLineRunner ejecutarReportes() {
        return args -> {
            List<FacturaReporteDTO> facturas = reporteFacturaService.obtenerReporteFacturas();

            // Genera reporte.pdf
            reporteFacturaService.generarPdf(facturas, "C:\\Users\\renzo\\Desktop\\UTN\\3er Año\\Desarrollo\\Java\\SpringBoot\\Practica\\reporte_facturas.pdf");

            // Genera reporte_facturas.xls (Excel lo abre perfectamente por estar separado por \t)
            reporteFacturaService.generarExcelTSV(facturas, "C:\\Users\\renzo\\Desktop\\UTN\\3er Año\\Desarrollo\\Java\\SpringBoot\\Practica\\reporte_facturas.xls");

            System.out.println(">>> Reportes PDF y Excel generados con éxito.");
        };
    }
}