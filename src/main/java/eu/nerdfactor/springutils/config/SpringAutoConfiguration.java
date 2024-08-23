package eu.nerdfactor.springutils.config;

import eu.nerdfactor.springutils.PropertyLogging;
import eu.nerdfactor.springutils.ViewUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Provide auto configuration for utils.
 */
@Configuration
@AutoConfiguration
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

	/**
	 * Provide a new Bean of {@link PropertyLogging}.
	 *
	 * @return A new {@link PropertyLogging}.
	 */
	@Bean(name = "propertyLogging")
	@ConditionalOnLoggingLevel(PropertyLogging.class)
	@ConditionalOnMissingBean(PropertyLogging.class)
	public @NotNull PropertyLogging getPropertyLogging() {
		return new PropertyLogging();
	}
}
