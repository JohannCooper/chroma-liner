package model;

import java.util.Objects;

public class KeyboardShortcut {
    private Character shortcut;

    public KeyboardShortcut(Character shorcut) {
        this.shortcut = shorcut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyboardShortcut that = (KeyboardShortcut) o;
        return Objects.equals(shortcut, that.shortcut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(shortcut);
    }
}
