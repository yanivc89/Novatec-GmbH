package microservices;

import javax.inject.Inject;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;

public class DepartmentMicroProfileConfig {

	@Inject
    Config config;
	
	public String getAllProperties() {
		
        StringBuilder out = new StringBuilder();
        
        out.append("<br><h2>ConfigSources</h2>");
        out.append("<ul>");
        for (ConfigSource source : config.getConfigSources()) {
            out.append("<li>" + source.getName() + " - " + source.getOrdinal() + "</li>");
        }
        out.append("</ul>");

        out.append("<h2>Properties</h2>");
        out.append("<ul>");
        for (String name : config.getPropertyNames()) {
            out.append(String.format("<li>%s = %s</li>\n", name, config.getValue(name, String.class)));
        }
        out.append("</ul>");
        
        return out.toString();
    }
}
