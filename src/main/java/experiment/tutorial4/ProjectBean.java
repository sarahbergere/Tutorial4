package experiment.tutorial4;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class ProjectBean {
    private Project project;
    private static ProjectLookupService lookupService = new ProjectSimpleMap(); //To get all of the project : useful for the index page
    private String projectId;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        this.projectId = params.get("p"); //getting the id of the project depending on the link

        if (projectId != null) {
            project = lookupService.findProject(projectId);
        }else{
            project = lookupService.findProject("Project 1"); // in case there is an issue with the parameter the default project is the first one
        }
    }

    public List<Project> getAllProjects() {
        return lookupService.getAllProjects();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project currentProject) {
        this.project= currentProject;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public boolean validateProject() {
        return project != null && project.getId() != null
                && project.getTitle() != null && !project.getTitle().isEmpty()
                && project.getDescription() != null && !project.getDescription().isEmpty()
                && project.getImage() != null && !project.getImage().isEmpty()
                && project.getMyPart() != null && !project.getMyPart().isEmpty()
                && project.getLearned() != null && !project.getLearned().isEmpty()
                && project.getTechnologies() != null && !project.getTechnologies().isEmpty();
    }

    public String saveProject() throws ServletException, IOException {
        if (validateProject()) {
            lookupService.saveProject(project);
            return "project?faces-redirect=true&p=" + projectId;  //redirecting to the page where the informations will be displayed
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please fill all fields. "));
            return "editProject?faces-redirect=true&p=" + projectId;
        }
    }
}
