/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gf.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ayefou
 */
public class PrintUtils {

    public static byte[] print(MemberListPrintObject printObject, String pathReport) throws JRException, IOException {
        Map params = new HashMap();
        params.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
        List<MemberListPrintObject> printDs = new ArrayList<>();
        printDs.add(printObject);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(printDs);
        JasperPrint jasperPrint = JasperFillManager.fillReport(pathReport, params, beanCollectionDataSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }
}
