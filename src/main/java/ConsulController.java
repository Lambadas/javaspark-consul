import static spark.Spark.get;

public class ConsulController {

    private IConsulManager consulManager;

    public ConsulController() {

        consulManager = new IConsulManagerImpl();

        get("/register", (req, res) -> {
            consulManager.registerService();

            return "is registered";
        });

        get("/", (req, res) -> "ok");

        get("/instance", (req, res) -> consulManager.getServiceName());
    }
}
