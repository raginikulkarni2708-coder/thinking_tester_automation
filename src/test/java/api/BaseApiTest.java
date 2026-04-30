package api;

import com.framework_Page.Utilities.ApiClient;
import org.testng.annotations.BeforeMethod;

public class BaseApiTest {

    protected ApiClient apiClient;

    @BeforeMethod
    public void setupApi() {
        apiClient = new ApiClient();
        apiClient.login("youruser@test.com", "YourPassword123");
    }



}
