public interface FiveWayTree<E> extends Tree<E> {

  Position<E> A(Position<E> p) throws IllegalArgumentException;
  Position<E> C(Position<E> p) throws IllegalArgumentException;
  Position<E> G(Position<E> p) throws IllegalArgumentException;
  Position<E> T(Position<E> p) throws IllegalArgumentException;
  Position<E> endSequence(Position<E> p) throws IllegalArgumentException;
  Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
