package experiment.tutorial4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectSimpleMap implements ProjectLookupService {
    private Map<String, Project> projects;

    public ProjectSimpleMap() {
        projects = new HashMap<>();
        addProject(new Project("Project 1", "E-commerce Web Application", "img/project1.png", "The goal of this project was to do an E-commerce Web Application where the user would be able to create and connect to an account as well as purchase items. The administrator interface offered functionalities such as product management (listing, addition and deletion), moderator administration (addition, removal and right assignment). The project structure involved a design phase with a detailed class diagram and an implementation phase using MySQL database. We used Java Persistence API and Hibernate.", "I worked on both front end and back end with the jsp pages, the servlets and the connection to our database.", "I learned a lot on Java Persistence, Hibernate and in general on making a web application.", "MySQL database, Java Persistence API, Hibernate, Java Database Connectivity"));

        addProject(new Project("Project 2", "Java Game", "img/map.png", "This project consisted of creating a sliding puzzle in different shapes and levels. We implemented a map that provided access to different levels. The user could save their progress in the game, and they had to solve the first level to unlock the other ones. Additionally, we developed an algorithm that could solve the puzzle and show each steps, in case the user needed assistance.", "With the help of one of my teammates, we worked on the algorithm to solve the puzzle. I also helped designing our game.", "I learned a lot on the different types of algorithms and their efficiency.", "Java, JavaFX"));
    }

    @Override
    public Project findProject(String projectId) {
        if (projectId != null) {
            return projects.get(projectId);
        } else {
            return null;
        }
    }

    private void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public void saveProject(Project project) {
        if (project != null && project.getId() != null) {
            projects.put(project.getId(), project);
        }
    }
}
