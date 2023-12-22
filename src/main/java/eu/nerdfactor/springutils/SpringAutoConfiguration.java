package eu.nerdfactor.springutils;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Provide auto configuration for utils.
 */
@Configuration
@ComponentScan(basePackages = {"eu.nerdfactor.springutils"})
public class SpringAutoConfiguration {

	/**
	 * Provide a new Bean of {@link ViewUtil}.
	 *
	 * @return A new {@link ViewUtil}.
	 */
	@Bean(name = "viewUtil")
	@ConditionalOnMissingBean(ViewUtil.class)
	public @NotNull ViewUtil getViewUtil() {
		return new ViewUtil();
	}
}
