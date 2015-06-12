package at.renehollander.advancedmanager.util;

/**
 * A value trio
 *
 * @param <L> type of the left value
 * @param <M> type of the middle value
 * @param <R> type of the right value
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class Trio<L, M, R> {
    private final L left;
    private final M middle;
    private final R right;

    /**
     * Create a new Value Pair
     *
     * @param left  Left value
     * @param right Right value
     */
    public Trio(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    /**
     * Gets the left value
     *
     * @return left value
     */
    public L getLeft() {
        return left;
    }

    /**
     * Gets the middle value
     *
     * @return middle value
     */
    public M getMiddle() {
        return middle;
    }

    /**
     * Gets the right value
     *
     * @return right value
     */
    public R getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Trio{" +
                "left=" + left +
                ", middle=" + middle +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trio<?, ?, ?> trio = (Trio<?, ?, ?>) o;
        if (left != null ? !left.equals(trio.left) : trio.left != null) return false;
        if (middle != null ? !middle.equals(trio.middle) : trio.middle != null) return false;
        return !(right != null ? !right.equals(trio.right) : trio.right != null);

    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (middle != null ? middle.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}

