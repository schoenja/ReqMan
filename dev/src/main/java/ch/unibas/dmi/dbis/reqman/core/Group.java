package ch.unibas.dmi.dbis.reqman.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * TODO: write JavaDoc
 *
 * @author loris.sauter
 */
public class Group implements Comparable<Group> {

    private String name;
    private String projectName;
    private List<String> members;
    private String catalogueName;
    private List<Progress> progressList = new ArrayList<>();
    private List<ProgressSummary> progressSummaries = new ArrayList<>();

    private String exportFileName;

    public Group(String name, String projectName, List<String> members, String catalogueName) {

        this.name = name;
        this.projectName = projectName;
        this.members = members;
        this.catalogueName = catalogueName;
    }

    public Group() {

    }

    public double getSumForMilestone(Milestone ms, Catalogue catalogue) {
        ArrayList<Double> points = new ArrayList<>();

        getProgressByMilestoneOrdinal(ms.getOrdinal()).forEach(p -> points.add(p.getPointsSensitive(catalogue)));

        return points.stream().mapToDouble(Double::doubleValue).sum();
    }

    public List<Milestone> getMilestonesForGroup(Catalogue catalogue) {
        ArrayList<Milestone> list = new ArrayList<>();

        for (Progress p : getProgressList()) {
            Milestone ms = catalogue.getMilestoneForProgress(p);
            if (!list.contains(ms)) {
                list.add(ms);
            } else {
                // Milestone already in list.
            }
        }

        return list;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Group group = (Group) o;

        if (getName() != null ? !getName().equals(group.getName()) : group.getName() != null) {
            return false;
        }
        if (getProjectName() != null ? !getProjectName().equals(group.getProjectName()) : group.getProjectName() != null) {
            return false;
        }
        if (members != null ? !members.equals(group.members) : group.members != null) {
            return false;
        }
        if (getCatalogueName() != null ? !getCatalogueName().equals(group.getCatalogueName()) : group.getCatalogueName() != null) {
            return false;
        }
        if (progressList != null ? !progressList.equals(group.progressList) : group.progressList != null) {
            return false;
        }
        return progressSummaries != null ? progressSummaries.equals(group.progressSummaries) : group.progressSummaries == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getProjectName() != null ? getProjectName().hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (getCatalogueName() != null ? getCatalogueName().hashCode() : 0);
        result = 31 * result + (progressList != null ? progressList.hashCode() : 0);
        result = 31 * result + (progressSummaries != null ? progressSummaries.hashCode() : 0);
        return result;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public boolean addMember(String name) {
        return members.add(name);
    }

    public List<String> getMembers() {
        return new Vector<String>(members);
    }

    public boolean removeMember(String name) {
        return members.remove(name);
    }

    public boolean addProgress(Progress progress) {
        return progressList.add(progress);
    }

    public List<Progress> getProgressList() {
        return new ArrayList<>(progressList);
    }

    public void setProgressList(List<Progress> progressList) {
        this.progressList.clear();
        this.progressList.addAll(progressList);
    }

    public boolean removeProgress(Progress progress) {
        return progressList.remove(progress);
    }

    public boolean addProgressSummary(ProgressSummary progressSummary) {
        return progressSummaries.add(progressSummary);
    }

    public List<ProgressSummary> getProgressSummaries() {
        return new ArrayList<ProgressSummary>(progressSummaries);
    }

    public boolean removeProgressSummary(ProgressSummary progressSummary) {
        return progressSummaries.remove(progressSummary);
    }

    public void setProgressSummaryList(List<ProgressSummary> progressSummaryList) {
        this.progressSummaries.clear();
        this.progressSummaries.addAll(progressSummaryList);
    }

    @Override
    public int compareTo(Group o) {
        return name.compareTo(o.getName());
    }

    public ProgressSummary getProgressSummaryForMilestone(Milestone ms) {
        for (ProgressSummary ps : progressSummaries) {
            if (ps.getMilestoneOrdinal() == ms.getOrdinal()) {
                return ps;
            }
        }
        return null;
    }

    public List<Progress> getProgressByMilestoneOrdinal(int ordinal) {
        ArrayList<Progress> list = new ArrayList<>();
        for (Progress p : getProgressList()) {
            if (p.getMilestoneOrdinal() == ordinal) {
                if (!list.contains(p)) {
                    list.add(p);
                } else {
                    // Progress already in list.
                }
            }
        }
        return list;
    }

    public double getTotalSum(Catalogue catalogue) {
        ArrayList<Double> points = new ArrayList<>();
        getMilestonesForGroup(catalogue).forEach(ms -> {
            points.add(getSumForMilestone(ms, catalogue));
        });
        return points.stream().mapToDouble(Double::doubleValue).sum();
    }

    public Progress getProgressForRequirement(Requirement requirement) {
        if (requirement == null) {
            throw new IllegalArgumentException("Requirement cannot be null, if progress for it should be provided");
        }
        for (Progress p : progressList) {
            if (p.getRequirementName().equals(requirement.getName())) {
                return p;
            }
        }
        return null;
    }

    public boolean isProgressUnlocked(Catalogue catalogue, Progress progress) {
        int predecessorsAchieved = 0;
        for (String name : catalogue.getRequirementForProgress(progress).getPredecessorNames()) {
            Progress pred = getProgressForRequirement(catalogue.getRequirementByName(name));
            if (pred != null && pred.hasProgress()) {
                predecessorsAchieved++;
            }
        }
        return predecessorsAchieved == catalogue.getRequirementForProgress(progress).getPredecessorNames().size();
    }

}
