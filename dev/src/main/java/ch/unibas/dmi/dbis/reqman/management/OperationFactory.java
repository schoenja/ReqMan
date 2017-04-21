package ch.unibas.dmi.dbis.reqman.management;

import ch.unibas.dmi.dbis.reqman.core.Catalogue;
import ch.unibas.dmi.dbis.reqman.ui.StatusBar;

import java.io.File;
import java.util.function.Consumer;

/**
 * TODO: write JavaDoc
 *
 * @author loris.sauter
 */
public class OperationFactory {

    private OperationFactory(){
        // Factory class - no constructor needed
    }

    private static StatusBar statusBar = null;
    public static void registerStatusBar(StatusBar bar){
        statusBar = bar;
    }

    public static CheckedAsynchronousOperation<Catalogue> createLoadCatalogueOperation(File catFile, Consumer<Catalogue> processor){
        OpenCatalogueTask task = new OpenCatalogueTask(catFile);
        CheckedAsynchronousOperation<Catalogue> operation = createOperationForTask(task, true);
        operation.addProcessor(processor);
        return operation;
    }

    public static CheckedAsynchronousOperation<Boolean> createSaveCatalogueOperation(Catalogue catalogue, File catFile){
        SaveCatalogueTask task = new SaveCatalogueTask(catalogue, catFile);
        return createOperationForTask(task, true);
    }

    public static CheckedAsynchronousOperation<Boolean> createExportCatalogueOperation(Catalogue cat, File file){
        ExportCatalogueTask task = new ExportCatalogueTask(cat, file);
        return createOperationForTask(task, true);
    }

    private static <T> CheckedAsynchronousOperation<T> createOperationForTask(ManagementTask<T> task, boolean deamon){
        CheckedAsynchronousOperation<T> op = new CheckedAsynchronousOperation<T>(task, deamon);
        bindStatusBar(op);
        return op;
    }



    private static <T> void bindStatusBar(CheckedAsynchronousOperation<T> operation){
        if(statusBar != null){
            operation.setStatusBar(statusBar);
        }
    }
}
