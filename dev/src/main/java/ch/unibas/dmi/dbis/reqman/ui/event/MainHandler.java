package ch.unibas.dmi.dbis.reqman.ui.event;

import ch.unibas.dmi.dbis.reqman.core.Milestone;
import ch.unibas.dmi.dbis.reqman.ui.MenuHandler;
import ch.unibas.dmi.dbis.reqman.ui.editor.EditorHandler;
import ch.unibas.dmi.dbis.reqman.ui.evaluator.EvaluatorHandler;
import javafx.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO: Write JavaDoc
 *
 * @author loris.sauter
 */
public class MainHandler implements MenuHandler {

    private static final Logger LOGGER = LogManager.getLogger(MainHandler.class);

    private static MainHandler instance = null;
    public static MainHandler getInstance(EvaluatorHandler evaluatorHandler, EditorHandler editorHandler){
        if(instance == null){
            instance = new MainHandler(evaluatorHandler,editorHandler);
        }
        return instance;
    }

    private final EvaluatorHandler evaluatorHandler;
    private final EditorHandler editorHandler;

    private MainHandler(EvaluatorHandler evaluatorHandler, EditorHandler editorHandler) {
        this.evaluatorHandler = evaluatorHandler;
        this.editorHandler = editorHandler;
    }

    @Override
    public void handleNewCatalogue(ActionEvent event) {
        // TODO ensure correct mode
        editorHandler.handle(CUDEvent.generateCreationEvent(event, TargetEntity.CATALOGUE));
        LOGGER.fatal("IMPLEMENT change view");
    }

    @Override
    public void handleNewGroup(ActionEvent event) {
        // TODO ensure correct mode
        evaluatorHandler.handle(CUDEvent.generateCreationEvent(event, TargetEntity.GROUP));
    }

    @Override
    public void handleOpenCat(ActionEvent event) {
        evaluatorHandler.handleOpenCatalogue(event);
    }

    @Override
    public void handleOpenGroups(ActionEvent event) {
        // TODO ensure correct mode
        evaluatorHandler.handleOpenGroups(event);
    }

    @Override
    public void handleSaveCat(ActionEvent event) {
        // TODO ensure correct mode ?
        editorHandler.saveCatalogue();
    }

    @Override
    public void handleSaveGroup(ActionEvent event) {
        // TODO ensure correct mode
        evaluatorHandler.handleSaveGroup(event);
    }

    @Override
    public void handleSaveCatAs(ActionEvent event) {
        // TODO ensure correct mode ?
        editorHandler.saveAsCatalogue();
    }

    @Override
    public void handleSaveGroupAs(ActionEvent event) {
        // TODO ensure correct mode
        evaluatorHandler.handleSaveGroupAs(event);
    }

    @Override
    public void handleExportCat(ActionEvent event) {
        // TODO ensure correct mode
        editorHandler.handleExportCatalogue(event);
    }

    @Override
    public void handleExportGroups(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleExportGroup(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleQuit(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleNewReq(ActionEvent event) {
        // TODO ensure correct mode
        editorHandler.handle(CUDEvent.generateCreationEvent(event, TargetEntity.REQUIREMENT));
    }

    @Override
    public void handleNewMS(ActionEvent event) {
        // TODO ensure correct mode
        editorHandler.handle(CUDEvent.generateCreationEvent(event, TargetEntity.MILESTONE));
    }

    @Override
    public void handleModCat(ActionEvent event) {
        // TODO ensure correct mode ?
        editorHandler.handle(CUDEvent.generateModificationEvent(event, TargetEntity.CATALOGUE, null));// By design, can be null
    }

    @Override
    public void handleModReq(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleModMS(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleModGroup(ActionEvent event) {
        // TODO ensure correct mode
        evaluatorHandler.handle(CUDEvent.generateModificationEvent(event, TargetEntity.GROUP, null));
    }

    @Override
    public void handleShowOverview(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleShowEditor(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void handleShowEvaluator(ActionEvent event) {
        // TODO ensure correct mode
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void resetGlobalMilestoneChoice() {
        // TODO ensure correct mode
        evaluatorHandler.resetGlobalMilestoneChoice();
    }

    @Override
    public void setGlobalMilestoneChoice(Milestone ms) {
        // TODO ensure correct mode
        evaluatorHandler.setGlobalMilestoneChoice(ms);
    }
}
