public abstract class AbstractTree<E> implements Tree<E> {

  @Override
  public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }

  @Override
  public boolean isExternal(Position<E> p) {
    if(p.getElement() == null) return true;
    return numChildren(p) == 0;
  }

  @Override
  public boolean isRoot(Position<E> p) { return p == root(); }

  @Override
  public int numChildren(Position<E> p) {
   return 0;
  }

  public int depth(Position<E> p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }
}
