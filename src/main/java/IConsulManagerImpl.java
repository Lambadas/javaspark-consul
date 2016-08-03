import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class IConsulManagerImpl implements IConsulManager {

    private IConsulWrapper consulWrapper;

    public IConsulManagerImpl() {

        consulWrapper = new IConsulWrapperImpl();
    }

    @Override
    public void registerService() {

        try {
            consulWrapper.build().agentClient().register(Constants.PORT, new URL(Constants.Url), Constants.TTL, Constants.SERVICE_NAME, Constants.SERVICE_NAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getServiceName() {

        String result = null;
        HealthClient healthClient = consulWrapper.build().healthClient();

        List<ServiceHealth> services = healthClient.getHealthyServiceInstances(Constants.SERVICE_NAME).getResponse();

        if (!services.isEmpty()) {

            ServiceHealth service = services.get(0);
            result = service.getService().getService();
        }

        return result;
    }
}
