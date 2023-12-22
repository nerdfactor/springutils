package eu.nerdfactor.springutils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Utility methods for Spring Messages.
 */
@SuppressWarnings("unused")
@Slf4j
public class MessageUtil {


	/**
	 * Different Message levels.
	 */
	public enum MessageType {
		DANGER, SUCCESS, INFO, WARNING
	}

	public static @NotNull RedirectAttributes error(@NotNull String msg, @NotNull RedirectAttributes redirect) {
		return message(msg, MessageType.DANGER, redirect);
	}

	public static @NotNull RedirectAttributes success(@NotNull String msg, @NotNull RedirectAttributes redirect) {
		return message(msg, MessageType.SUCCESS, redirect);
	}

	public static @NotNull RedirectAttributes info(@NotNull String msg, @NotNull RedirectAttributes redirect) {
		return message(msg, MessageType.INFO, redirect);
	}

	public static @NotNull RedirectAttributes warning(@NotNull String msg, @NotNull RedirectAttributes redirect) {
		return message(msg, MessageType.WARNING, redirect);
	}

	/**
	 * Include a message in the flash attribute of a {@link RedirectAttributes}.
	 *
	 * @param msg      The message.
	 * @param type     The {@link MessageType}.
	 * @param redirect The {@link RedirectAttributes}.
	 * @return The {@link RedirectAttributes} containing the message.
	 */
	public static @NotNull RedirectAttributes message(@NotNull String msg, @NotNull MessageType type, @NotNull RedirectAttributes redirect) {
		log.debug("{}: {}", type.name().toLowerCase(), msg);
		redirect.addFlashAttribute("msg", msg);
		redirect.addFlashAttribute("msgType", type.name().toLowerCase());
		return redirect;
	}

	public static @NotNull Model error(@NotNull String msg, @NotNull Model model) {
		return message(msg, MessageType.DANGER, model);
	}

	public static @NotNull Model success(@NotNull String msg, @NotNull Model model) {
		return message(msg, MessageType.SUCCESS, model);
	}

	public static @NotNull Model info(@NotNull String msg, @NotNull Model model) {
		return message(msg, MessageType.INFO, model);
	}

	public static @NotNull Model warning(@NotNull String msg, @NotNull Model model) {
		return message(msg, MessageType.WARNING, model);
	}

	/**
	 * Includes a message in the attributes of a {@link Model}.
	 *
	 * @param msg   The message.
	 * @param type  The {@link MessageType}.
	 * @param model The {@link Model}.
	 * @return The {@link Model} containing the message.
	 */
	public static @NotNull Model message(@NotNull String msg, @NotNull MessageType type, @NotNull Model model) {
		log.debug("{}: {}", type.name().toLowerCase(), msg);
		model.addAttribute("msg", msg);
		model.addAttribute("msgType", type.name().toLowerCase());
		return model;
	}
}
