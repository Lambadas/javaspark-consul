import com.orbitz.consul.Consul;


public class IConsulWrapperImpl implements IConsulWrapper {

    @Override
    public Consul build() {

        return Consul.builder().withAclToken(Constants.ACL_TOKEN).build();
    }
}
