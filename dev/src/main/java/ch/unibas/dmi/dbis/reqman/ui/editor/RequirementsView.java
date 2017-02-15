package ch.unibas.dmi.dbis.reqman.ui.editor;

import ch.unibas.dmi.dbis.reqman.core.Requirement;
import ch.unibas.dmi.dbis.reqman.ui.common.ModifiableListController;
import ch.unibas.dmi.dbis.reqman.ui.common.ModifiableListHandler;
import ch.unibas.dmi.dbis.reqman.ui.common.ModifiableListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * TODO: write JavaDoc
 *
 * @author loris.sauter
 */
public class RequirementsView extends ModifiableListView<Requirement> implements ModifiableListHandler<Requirement>{

    private EditorController controller;

    public RequirementsView(EditorController controller){
        super("Requirements");
        this.controller = controller;
        listView.setItems(controller.getObservableRequirements() );
        addHandler(this);
        listView.setCellFactory((ListView<Requirement> l) -> new RequirementCell());
    }

    @Override
    public void onRemove(RemoveEvent<Requirement> event) {
        controller.requestRemoveRequirement(event);
    }

    @Override
    public void onAdd(AddEvent<Requirement> event) {
        controller.requestAddRequirement(event);

    }

    static class RequirementCell extends ListCell<Requirement> {

        @Override
        public void updateItem(Requirement item, boolean empty){
            super.updateItem(item, empty);
            if(!empty){
                setText(item.getName() );
            }
        }
    }
}
