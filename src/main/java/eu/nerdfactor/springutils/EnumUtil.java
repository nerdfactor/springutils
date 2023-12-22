package eu.nerdfactor.springutils;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Utility methods for Enums.
 */
@SuppressWarnings("unused")
public class EnumUtil {

	/**
	 * Get a String Array of all names in an Enum.
	 *
	 * @param e The Enum containing the desired names.
	 * @return A String[] of names in the Enum.
	 */
	public static @NotNull String[] getNames(@NotNull Class<? extends Enum<?>> e) {
		return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
