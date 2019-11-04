package com.gmail.maxsvynarchuk.presentation.command;

import com.gmail.maxsvynarchuk.presentation.util.RedirectType;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * <p>
 * Class that carries information about Command executing result
 * so {@code FrontController} could know where and how redirect further
 *
 * @author Maksym Svynarchuk
 * @see com.gmail.maxsvynarchuk.presentation.FrontController
 * @see Command
 */
public class CommandResult {
    private final RedirectType redirectType;
    private final String pagePath;

    private CommandResult(RedirectType redirectType, String pagePath) {
        this.redirectType = redirectType;
        this.pagePath = pagePath;
    }

    public static CommandResult of(RedirectType redirectType, String pagePath) {
        return new CommandResult(Objects.requireNonNull(redirectType), Objects.requireNonNull(pagePath));
    }

    public static CommandResult redirect(String pagePath) {
        return new CommandResult(RedirectType.REDIRECT, Objects.requireNonNull(pagePath));
    }

    public static CommandResult forward(String pagePath) {
        return new CommandResult(RedirectType.FORWARD, Objects.requireNonNull(pagePath));
    }

    public RedirectType getRedirectType() {
        return redirectType;
    }

    public String getPagePath() {
        return pagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return redirectType == that.redirectType &&
                Objects.equals(pagePath, that.pagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirectType, pagePath);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CommandResult.class.getSimpleName() + "[", "]")
                .add("redirectType=" + redirectType)
                .add("pagePath='" + pagePath + "'")
                .toString();
    }
}