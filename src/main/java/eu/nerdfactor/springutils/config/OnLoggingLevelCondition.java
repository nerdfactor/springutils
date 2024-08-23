package eu.nerdfactor.springutils.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnLoggingLevelCondition implements Condition {

	/**
	 * Check if a logging level is configured for the specified property.
	 *
	 * @param context  The context in which the condition is evaluated. Provides
	 *                 access to the bean factory and other contextual
	 *                 information.
	 * @param metadata Metadata about the annotated type or method that is used
	 *                 to extract the configuration property name.
	 * @return True if the logging level is configured and not OFF.
	 */
	@Override
	public boolean matches(@NotNull ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {
		String propertyName = this.getConditionalLoglevelPropertyName(metadata);
		if (propertyName == null || propertyName.isEmpty()) {
			return false;
		}
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		if (beanFactory == null) {
			return false;
		}
		LoggingSystem loggingSystem = beanFactory.getBean(LoggingSystem.class);

		LoggerConfiguration loggerConfig = loggingSystem.getLoggerConfiguration(propertyName);

		return loggerConfig != null && loggerConfig.getEffectiveLevel() != null && !loggerConfig.getEffectiveLevel().equals(LogLevel.OFF);
	}

	/**
	 * Get the name of the conditional property.
	 *
	 * @param metadata The {@link AnnotatedTypeMetadata} to receive the
	 *                 annotation.
	 * @return The name of the property or null.
	 */
	@SuppressWarnings({"java:S2259", "DataFlowIssue"})
	protected @Nullable String getConditionalLoglevelPropertyName(@NotNull AnnotatedTypeMetadata metadata) {
		try {
			Class<?> cls = (Class<?>) metadata.getAnnotationAttributes(ConditionalOnLoggingLevel.class.getName()).get("value");
			return cls.getName();
		} catch (NullPointerException e) {
			return null;
		}
	}
}
