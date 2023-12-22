package eu.nerdfactor.springutils;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Utility methods used in Thymeleaf view templates.
 */
@SuppressWarnings("unused")
@Service
public class ViewUtil {

	@Autowired
	HttpServletRequest request;

	/**
	 * The current request URI.
	 *
	 * @return The current request URI.
	 */
	public @NotNull String requestURI() {
		return this.requestURI("");
	}

	/**
	 * The current request URI with a provided suffix.
	 *
	 * @param url The suffix that will be added to the current URI.
	 * @return The current request URI with a suffix.
	 */
	public @NotNull String requestURI(@NotNull String url) {
		return request.getRequestURI() + url;
	}

	public @NotNull String sortBy(@NotNull String name, @NotNull Pageable pageable) {
		return this.sortBy(name, pageable, false);
	}

	public @NotNull String sortBy(@NotNull String name, @NotNull Pageable pageable, boolean toggle) {
		Sort.Direction direction = Sort.Direction.ASC;
		Sort.Order order = pageable.getSort().getOrderFor(name);
		if (null != order) {
			if (toggle && pageable.getSort().isSorted()) {
				direction = (order.isAscending()) ? Sort.Direction.DESC : Sort.Direction.ASC;
			} else {
				direction = (order.isAscending()) ? Sort.Direction.ASC : Sort.Direction.DESC;
			}

		}
		return this.sortBy(name, direction);
	}

	public @NotNull String sortBy(@NotNull String name, @NotNull Sort.Direction direction) {
		return name + "," + direction.name().toLowerCase();
	}

	public @NotNull String sort(@NotNull Pageable pageable) {
		return pageable.getSort().get()
				.map(x -> x.getProperty() + "," + x.getDirection().name().toLowerCase())
				.collect(Collectors.joining("&"));
	}

	public @NotNull String classappend(@NotNull String value, @NotNull String conditions) {
		return this.classappend(value, conditions, "");
	}

	public @NotNull String classappend(@NotNull String value, @Nullable String conditions, @NotNull String def) {
		if (conditions != null && !conditions.isEmpty()) {
			String[] cons = conditions.split(",");
			for (int i = 0; i < cons.length; i += 2) {
				if (cons[i].equals(value)) {
					return cons[i + 1];
				}
			}
		}
		return def;
	}

	public @NotNull String getUpdateUrl(@NotNull String start, boolean condition, @NotNull String onTrue, @NotNull String onFalse) {
		return start + ((condition) ? onTrue : onFalse);
	}

	/**
	 * Check if the current user of the request has any of the provided roles.
	 *
	 * @param roles The roles to check.
	 * @return true if the user has any of the roles.
	 */
	public boolean hasAnyRole(@NotNull String... roles) {
		for (String string : roles) {
			boolean result = this.request.isUserInRole(string);
			if (result) {
				return true;
			}
		}
		return false;
	}

	public @NotNull String status(@NotNull String status) {
		return this.status(status, "badge-");
	}

	public @NotNull String status(@NotNull String status, @NotNull String prefix) {
		return prefix.toLowerCase() + status.toLowerCase();
	}

}
