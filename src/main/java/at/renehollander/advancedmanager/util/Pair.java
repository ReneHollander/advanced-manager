package at.renehollander.advancedmanager.util;

/**
 * A value pair
 *
 * @param <L> type of the left value
 * @param <R> type of the right value
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public class Pair<L, R> {
    private final L left;
    private final R right;

    /**
     * Create a new Value Pair
     *
     * @param left  Left value
     * @param right Right value
     */
    public Pair(L left, R right) {
        this.left = left;
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
     * Gets the right value
     *
     * @return right value
     */
    public R getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return !(left != null ? !left.equals(pair.left) : pair.left != null) && !(right != null ? !right.equals(pair.right) : pair.right != null);

    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
