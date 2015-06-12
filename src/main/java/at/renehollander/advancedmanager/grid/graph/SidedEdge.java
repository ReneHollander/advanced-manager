package at.renehollander.advancedmanager.grid.graph;

import net.minecraft.util.EnumFacing;
import org.jgrapht.graph.DefaultEdge;

public class SidedEdge<V> extends DefaultEdge {

    private V v1;
    private V v2;
    private EnumFacing v1side;
    private EnumFacing v2side;

    public SidedEdge(V v1, V v2, EnumFacing v1side, EnumFacing v2side) {
        this.v1 = v1;
        this.v2 = v2;
        this.v1side = v1side;
        this.v2side = v2side;
    }

    /**
     * Getter for property 'v1'.
     *
     * @return Value for property 'v1'.
     */
    public V getV1() {
        return v1;
    }

    /**
     * Getter for property 'v2'.
     *
     * @return Value for property 'v2'.
     */
    public V getV2() {
        return v2;
    }

    /**
     * Getter for property 'v1side'.
     *
     * @return Value for property 'v1side'.
     */
    public EnumFacing getV1side() {
        return v1side;
    }

    /**
     * Getter for property 'v2side'.
     *
     * @return Value for property 'v2side'.
     */
    public EnumFacing getV2side() {
        return v2side;
    }

    @Override
    public String toString() {
        return getV1() + ":" + getV1side() + "<-->" + getV2side() + ":" + getV2();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SidedEdge<?> sidedEdge = (SidedEdge<?>) o;

        if (v1 != null ? !v1.equals(sidedEdge.v1) : sidedEdge.v1 != null) return false;
        if (v2 != null ? !v2.equals(sidedEdge.v2) : sidedEdge.v2 != null) return false;
        if (v1side != sidedEdge.v1side) return false;
        return v2side == sidedEdge.v2side;

    }

    @Override
    public int hashCode() {
        int result = v1 != null ? v1.hashCode() : 0;
        result = 31 * result + (v2 != null ? v2.hashCode() : 0);
        result = 31 * result + (v1side != null ? v1side.hashCode() : 0);
        result = 31 * result + (v2side != null ? v2side.hashCode() : 0);
        return result;
    }
}
