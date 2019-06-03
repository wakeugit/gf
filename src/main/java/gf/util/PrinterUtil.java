package gf.util;

import javafx.print.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class PrinterUtil {
    // Create the JobStatus Label
    public String jobStatus = "";

    public static <T> void printSetup(TableView<T> tableView, Stage owner)
    {
        TableView<T> copyTableView = tableView;
        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        Group pane = new Group();
        //double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        //double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();

        HBox hBox = new HBox(tableView);

        //node.getTransforms().add(new Scale(scaleX, scaleY));

        hBox.setPrefSize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
        tableView.setPrefSize(pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight());
        pane.getChildren().addAll(hBox);
        pane.getStylesheets().add("main.css");

        if (job != null && job.showPrintDialog(owner))
        {
            boolean success = job.printPage(pageLayout, pane);
            if (success){
                job.endJob();
            }
        }
    }


}
