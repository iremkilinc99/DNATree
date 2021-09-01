import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFiveWayTree<E> extends AbstractTree<E>
                                             implements FiveWayTree<E> {
  @Override
  public Position<E> sibling(Position<E> p) {
    Position<E> parent = parent(p);
    if (parent == null) return null;

    if (p == A(parent))
      return A(parent);

    if (p == C(parent))
      return C(parent);

    if (p == G(parent))
      return G(parent);

    if (p == T(parent))
      return T(parent);
    else
      return endSequence(parent);
  }

  @Override
  public int numChildren(Position<E> p) {
    int count = 0;
    if (A(p) != null && A(p).getElement() != null)
      count++;
    if (C(p) != null && C(p).getElement() != null)
      count++;
    if (G(p) != null && G(p).getElement() != null)
      count++;
    if (T(p) != null && T(p).getElement() != null)
      count++;
    if (endSequence(p) != null && endSequence(p).getElement() != null)
      count++;

    return count;
  }

  public List<Position<E>> children(Position<E> p) {
    List<Position<E>> snapshot = new ArrayList<>(5);
    if (A(p) != null)
      snapshot.add(A(p));

    if (C(p) != null)
      snapshot.add(C(p));

    if (G(p) != null)
      snapshot.add(G(p));

    if (T(p) != null)
      snapshot.add(T(p));

    if (endSequence(p) != null)
      snapshot.add(endSequence(p));

    return snapshot;
  }
}
