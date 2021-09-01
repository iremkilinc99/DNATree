public class LinkedTree<E> extends AbstractFiveWayTree<E> {

  protected static class Node<E> implements Position<E> {
    private E element;
    private Node<E> parent;
    private Node<E> A;
    private Node<E> C;
    private Node<E> G;
    private Node<E> T;
    private Node<E> end;

    public Node(E e, Node<E> above, Node<E> A, Node<E> C, Node<E> G, Node<E> T, Node<E> endSeq) {
      element = e;
      parent = above;
      this.A = A;
      this.C = C;
      this.G = G;
      this.T = T;
      end = endSeq;
    }

    // accessor methods
    public E getElement() { return element; }
    public Node<E> getParent() { return parent; }
    public Node<E> getA() {return A;}
    public Node<E> getC() {return C;}
    public Node<E> getG() {return G;}
    public Node<E> getT() {return T;}
    public Node<E> getEnd() {return end;}

    public void setElement(E e) { element = e; }
    public void setParent(Node<E> parentNode) { parent = parentNode; }
    public void setA(Node<E> a) {A = a;}
    public void setC(Node<E> c) {C = c;}
    public void setG(Node<E> g) {G = g;}
    public void setT(Node<E> t) {T = t;}
    public void setEnd(Node<E> end) {this.end = end;}

  } //----------- end of nested Node class -----------


  protected Node<E> createNode(E e, Node<E> above, Node<E> A, Node<E> C, Node<E> G, Node<E> T, Node<E> endSeq) {
    return new Node<E>(e, above, A, C, G, T, endSeq);
  }

  protected Node<E> root = null;     // root of the tree

  private int size = 0;              // number of nodes in the tree

  public LinkedTree() { }      // constructs an empty binary tree

  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  @Override
  public Position<E> root() {
    return root;
  }

  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getParent();
  }

  @Override
  public Position<E> A(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getA();
  }

  @Override
  public Position<E> C(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getC();
  }

  @Override
  public Position<E> G(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getG();
  }

  @Override
  public Position<E> T(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getT();
  }

  @Override
  public Position<E> endSequence(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getEnd();
  }

  public Position<E> addRoot(E e) throws IllegalStateException {
    if (root != null) throw new IllegalStateException("Tree is not empty");
    root = createNode(e, null, null, null, null,null, null);
    size = 1;
    return root;
  }

  public Position<E> makeRoot(Position<E> p) {
    Node<E> node = validate(p);
    root = node;
    return root;
  }

  public Position<E> createNode(E e) throws IllegalStateException {
    Position<E> node = createNode(e,null, null, null, null,null, null);
    return node;
  }

  public Position<E> addA(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getA() != null)  throw new IllegalArgumentException("p already has A child");
    Node<E> child = createNode(e, parent, null, null, null, null, null);
    parent.setA(child);
    size++;
    return child;
  }

  public Position<E> addC(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getC() != null)  throw new IllegalArgumentException("p already has A child");
    Node<E> child = createNode(e, parent, null, null, null, null, null);
    parent.setC(child);
    size++;
    return child;
  }

  public Position<E> addG(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getG() != null)  throw new IllegalArgumentException("p already has A child");
    Node<E> child = createNode(e, parent, null, null, null, null, null);
    parent.setG(child);
    size++;
    return child;
  }

  public Position<E> addT(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getT() != null)  throw new IllegalArgumentException("p already has A child");
    Node<E> child = createNode(e, parent, null, null, null, null, null);
    parent.setT(child);
    size++;
    return child;
  }

  public Position<E> addEnd(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getEnd() != null)  throw new IllegalArgumentException("p already has A child");
    Node<E> child = createNode(e, parent, null, null, null, null, null);
    parent.setEnd(child);
    size++;
    return child;
  }

  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }
}
