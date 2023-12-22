package eu.nerdfactor.springutils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Log used properties as part of the application startup.
 *
 * @see <a href="https://gist.github.com/sandor-nemeth/f6d2899b714e017266cb9cce66bc719d" />
 */
@Slf4j
@Component
@SuppressWarnings("unused")
public class PropertyLogging {

	@SuppressWarnings("rawtypes")
	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) throws UnknownHostException {
		boolean useHttps = false;
		final Environment env = event.getApplicationContext().getEnvironment();
		log.info("============== Configuration ==============");
		log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
		final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
		List<String> props = StreamSupport.stream(sources.spliterator(), false)
				.filter(ps -> ps instanceof EnumerablePropertySource && (ps.getName().toLowerCase().contains(".properties") || ps.getName().toLowerCase().contains(".yaml")))
				.map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
				.flatMap(Arrays::stream)
				.distinct().toList();
		for (String prop : props) {
			if (!prop.toLowerCase().startsWith("//") && !prop.toLowerCase().startsWith("#")) {
				useHttps = useHttps || prop.toLowerCase().contains("server.ssl");
				String value = env.getProperty(prop);
				if (value != null && (prop.toLowerCase().contains("credentials") || prop.toLowerCase().contains("pass") || prop.toLowerCase().contains("token"))) {
					value = "************";
				}
				log.info("{}: {}", prop, value);
			}
		}
		log.info("===========================================");
		String port = env.getProperty("local.server.port");
		log.info("http" + ((useHttps) ? "s" : "") + "://" + InetAddress.getLocalHost().getHostName() + ":" + port);
		log.info("http" + ((useHttps) ? "s" : "") + "://" + InetAddress.getLocalHost().getHostAddress() + ":" + port);
		log.info("http" + ((useHttps) ? "s" : "") + "://" + InetAddress.getLoopbackAddress().getHostName() + ":" + port);
		log.info("===========================================");
	}
}
