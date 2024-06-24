package experiment.tutorial4;

import java.util.List;

public interface ProjectLookupService {
    public Project findProject(String projectId);
    public List<Project> getAllProjects();
    public void saveProject(Project project);
}
