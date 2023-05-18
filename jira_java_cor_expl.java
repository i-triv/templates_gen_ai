import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;

public class JiraIssueCreator {
    
    public static void main(String[] args) {
        final String username = "your-jira-username";
        final String password = "your-jira-password";
        final URI serverUri = URI.create("https://your-jira-server-url.com");

        // Create Jira rest client
        final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(serverUri, username, password);

        // Get a list of all projects
        Iterable<BasicProject> allProjects = restClient.getProjectClient().getAllProjects().claim();
        for (BasicProject project : allProjects) {
            System.out.println("Project Key: " + project.getKey());
            System.out.println("Project Name: " + project.getName());
        }

        // Use the issue builder to create a new issue
        IssueInputBuilder issueBuilder = new IssueInputBuilder("TEST", 1L, "Test issue")
                .setDescription("This is a test issue created by the Jira API")
                .setAssigneeName("username");

        // Send the issue creation request to Jira
        restClient.getIssueClient().createIssue(issueBuilder.build()).claim();

        // Close the Jira rest client
        restClient.close();
    }
}
