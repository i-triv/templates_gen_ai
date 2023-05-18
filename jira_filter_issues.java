import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class JiraConnector {
    public static void main(String[] args) {
        String jiraUrl = "https://yourjira.com";
        String username = "yourUsername";
        String password = "yourPassword";
        String filterId = "yourFilterId"; // this is the ID of the filter you want to use

        try {
            // create a new JIRA Rest client
            AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            URI jiraServerUri = new URI(jiraUrl);
            JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, username, password);

            // execute the JIRA search
            SearchResult searchResult = restClient.getSearchClient().searchJql("filter=" + filterId).get();

            // print the results
            searchResult.getIssues().forEach(issue -> System.out.println(issue.getSummary()));

            // close the client
            restClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
