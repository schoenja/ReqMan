package ch.unibas.dmi.dbis.reqman.ui.editor;

import ch.unibas.dmi.dbis.reqman.data.Milestone;
import ch.unibas.dmi.dbis.reqman.ui.common.ModifiableListHandler;
import ch.unibas.dmi.dbis.reqman.ui.common.ModifiableListView;
import ch.unibas.dmi.dbis.reqman.ui.event.CUDEvent;
import ch.unibas.dmi.dbis.reqman.ui.event.TargetEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * TODO: Write JavaDoc
 *
 * @author loris.sauter
 */
public class MilestonesListView extends ModifiableListView<Milestone> implements ModifiableListHandler<Milestone> {
    private EditorHandler handler;

    public MilestonesListView(EditorHandler handler) {
        super("Milestones");
        this.handler = handler;
        addHandler(this);
        listView.setCellFactory((ListView<Milestone> l) -> new ch.unibas.dmi.dbis.reqman.ui.editor.MilestoneCell());
        listView.setOnMouseClicked(this::handleModifyRequest);
        listView.setTooltip(new Tooltip("Double-click on Milestone to modify"));

    }

    @Override
    public void onRemove(RemoveEvent<Milestone> event) {
        CUDEvent evt = CUDEvent.generateDeletionEvent(event, TargetEntity.MILESTONE, event.getSelectedIndex(), event.getSelected());
        handler.handleDeletion(evt);
    }

    @Override
    public void onAdd(AddEvent<Milestone> event) {
        CUDEvent evt = CUDEvent.generateCreationEvent(event, TargetEntity.MILESTONE);
        handler.handleCreation(evt);
    }

    public Milestone getSelectedMS() {
        return listView.getSelectionModel().getSelectedItem();
    }

    void setMilestones(ObservableList<Milestone> observableList) {
        setItems(observableList);
    }

    private void handleModifyRequest(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Milestone ms = listView.getSelectionModel().getSelectedItem();
            if (ms != null) {
                CUDEvent mod = CUDEvent.generateModificationEvent(new ActionEvent(mouseEvent.getSource(), mouseEvent.getTarget()), TargetEntity.MILESTONE, ms);
                handler.handleModification(mod);
            }
        }
    }

    public static class MilestoneCell extends ListCell<Milestone> {

        @Override
        public void updateItem(Milestone item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setText(item.getName() + " (" + item.getOrdinal() + ")");
            } else {
                setText("");
            }
        }
    }
}
