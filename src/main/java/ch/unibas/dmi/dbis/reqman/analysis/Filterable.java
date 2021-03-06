package ch.unibas.dmi.dbis.reqman.analysis;

import ch.unibas.dmi.dbis.reqman.data.Milestone;
import org.jetbrains.annotations.NotNull;

/**
 * A Filterable can handle the application of a filter.
 * E.g. a Filterable has some sort of list / set to be filtered.
 *
 * @author loris.sauter
 */
public interface Filterable {
  
  void applyFilter(@NotNull Filter filter);
  
  void applyActiveMilestone(@NotNull Milestone ps);
  
  void clearFilter();
}
