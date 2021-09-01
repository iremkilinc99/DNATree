public class DNATree<E> extends LinkedTree<E>{
    public DNATree() {}

    public void insert(String sequence) {
        if(root == null) {
            Position<E> position = addRoot((E) "");
            char startSequenceChar = sequence.charAt(0);

            addChildren(position);

            if(startSequenceChar == 'A') set(A(position),(E) sequence);
            if(startSequenceChar == 'C')  set(C(position),(E) sequence);
            if(startSequenceChar == 'G')  set(G(position),(E) sequence);
            if(startSequenceChar == 'T')  set(T(position),(E) sequence);

            System.out.println("Insertion of (" + sequence +  ") was successful");
        }else{

            Position<E> found = search(sequence, 0);
            if(found != null) {
                System.out.println("Sequence (" + sequence + ") exist in tree. No need to insert.");
                return;
            }
           insertAux(root, sequence, 0);
        }
    }

    private void insertAux(Position<E> position, String sequence, int charIndex) {
        if (isExternal(position) && position.getElement() == null) {
            validate(position).setElement((E) sequence);
            System.out.println("Insertion of (" + sequence + ") was successful. Insertion was at level " + charIndex);
            return;
        }

        E leafSequence = position.getElement();
        if (isExternal(position) && leafSequence.toString().length() != 1 && sequence.length() != 1) {
            if (leafSequence.toString().length() > sequence.length() && (leafSequence.toString().substring(0, sequence.length()).equals(sequence)) ||
                    (leafSequence.toString().length() < sequence.length() && sequence.substring(0, leafSequence.toString().length()).equals(leafSequence.toString()))) {
                insertAuxContain(position, sequence, leafSequence.toString());
                return;
            }
        }

        if (isExternal(position)) {
            Position<E> newPosition = createNode((E) "");
            Position<E> parent = parent(position);
            addChildren(newPosition);

            if (sequence.length() <= charIndex) set(endSequence(newPosition), (E) sequence);
            else if (sequence.charAt(charIndex) == 'A' && sequence.length() > charIndex)
                set(A(newPosition), (E) sequence);
            else if (sequence.charAt(charIndex) == 'C' && sequence.length() > charIndex)
                set(C(newPosition), (E) sequence);
            else if (sequence.charAt(charIndex) == 'G' && sequence.length() > charIndex)
                set(G(newPosition), (E) sequence);
            else if (sequence.charAt(charIndex) == 'T' && sequence.length() > charIndex)
                set(T(newPosition), (E) sequence);

            if (leafSequence != null) {
                if (leafSequence.toString().length() > charIndex && leafSequence.toString().charAt(charIndex) == 'A')
                    set(A(newPosition), leafSequence);
                else if (leafSequence.toString().length() > charIndex && leafSequence.toString().charAt(charIndex) == 'C')
                    set(C(newPosition), leafSequence);
                else if (leafSequence.toString().length() > charIndex && leafSequence.toString().charAt(charIndex) == 'G')
                    set(G(newPosition), leafSequence);
                else if (leafSequence.toString().length() > charIndex && leafSequence.toString().charAt(charIndex) == 'T')
                    set(T(newPosition), leafSequence);
                else
                    set(endSequence(newPosition), leafSequence);
            }

            if (A(parent) == position) {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setA(node);
                node.setParent(nodeParent);
            }

            if (C(parent) == position) {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setC(node);
                node.setParent(nodeParent);
            }

            if (G(parent) == position) {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setG(node);
                node.setParent(nodeParent);
            }

            if (T(parent) == position) {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setT(node);
                node.setParent(nodeParent);
            }

            System.out.println("Insertion of (" + sequence + ") was successful. Insertion was at level " + (charIndex + 1));
            return;
        }

        if(sequence.charAt(charIndex) == 'A') {
               insertAux(A(position), sequence, ++charIndex);

        }else if(sequence.charAt(charIndex) == 'C') {
                insertAux(C(position), sequence, ++charIndex);

        }else if(sequence.charAt(charIndex) == 'G') {
                insertAux(G(position), sequence, ++charIndex);

        }else if(sequence.charAt(charIndex) == 'T') {
                insertAux(T(position), sequence, ++charIndex);
        }

}

    private void insertAuxContain(Position<E> position, String sequence, String leaf) {
        int differentCharIndex = 0;

        for (int i = 0; i < Math.min(leaf.length(), sequence.length()); i++){
            if(leaf.charAt(i) != sequence.charAt(i)){
                differentCharIndex = i;
                break;
            }
        }
        if(differentCharIndex == 0) differentCharIndex = leaf.length();
        int leafDepth = depth(position);
        int numberOfCreatedNodes = 0;

        Position<E> parent = parent(position);
        Position<E> newPosition = createNode((E) "");
        addChildren(newPosition);

        if (A(parent) == position) {
            Node<E> nodeParent = validate(parent);
            Node<E> node = validate(newPosition);
            nodeParent.setA(node);
            node.setParent(nodeParent);
        }

        if (C(parent) == position) {
            Node<E> nodeParent = validate(parent);
            Node<E> node = validate(newPosition);
            nodeParent.setC(node);
            node.setParent(nodeParent);
        }

        if (G(parent) == position) {
            Node<E> nodeParent = validate(parent);
            Node<E> node = validate(newPosition);
            nodeParent.setG(node);
            node.setParent(nodeParent);
        }

        if (T(parent) == position) {
            Node<E> nodeParent = validate(parent);
            Node<E> node = validate(newPosition);
            nodeParent.setT(node);
            node.setParent(nodeParent);
        }

        parent = newPosition;
        newPosition = createNode((E) "");
        addChildren(newPosition);

        while (numberOfCreatedNodes <= leaf.length()-leafDepth) {
            if(sequence.charAt(leafDepth) == 'A') {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setA(node);
                node.setParent(nodeParent);
                leafDepth++;
            }

            else if(sequence.charAt(leafDepth) == 'C') {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setC(node);
                node.setParent(nodeParent);
                leafDepth++;
            }

            else if(sequence.charAt(leafDepth) == 'G') {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setG(node);
                node.setParent(nodeParent);
                leafDepth++;
            }

            else if(sequence.charAt(leafDepth) == 'T') {
                Node<E> nodeParent = validate(parent);
                Node<E> node = validate(newPosition);
                nodeParent.setT(node);
                node.setParent(nodeParent);
                leafDepth++;
            }
            numberOfCreatedNodes++;
            parent = newPosition;
            if(numberOfCreatedNodes <= leaf.length()-leafDepth) {
                newPosition = createNode((E) "");
                addChildren(newPosition);
            }
        }

        if(sequence.length() <= leafDepth) set(endSequence(newPosition),(E) sequence);
        else if(sequence.charAt(leafDepth) == 'A' && sequence.length() > leafDepth) set(A(newPosition),(E) sequence);
        else if(sequence.charAt(leafDepth) == 'C' && sequence.length() > leafDepth)  set(C(newPosition),(E) sequence);
        else if(sequence.charAt(leafDepth) == 'G' && sequence.length() > leafDepth)  set(G(newPosition),(E) sequence);
        else if(sequence.charAt(leafDepth) == 'T' && sequence.length() > leafDepth)  set(T(newPosition),(E) sequence);

        if(leaf != null) {
            if (leaf.length() > leafDepth && leaf.charAt(leafDepth) == 'A')
                set(A(newPosition), (E) leaf);
            else if (leaf.length() > leafDepth && leaf.charAt(leafDepth) == 'C')
                set(C(newPosition), (E) leaf);
            else if (leaf.length() > leafDepth && leaf.charAt(leafDepth) == 'G')
                set(G(newPosition), (E) leaf);
            else if (leaf.length() > leafDepth && leaf.charAt(leafDepth) == 'T')
                set(T(newPosition), (E) leaf);
            else
                set(endSequence(newPosition), (E) leaf);
        }
        System.out.println("Insertion of (" + sequence + ") was successful. Insertion was at level " + (leafDepth+1));
        return;
    }

    public void remove(String sequence) {
        Position<E> position = search(sequence, 0);

        if(position == null) {
            System.out.println("Sequence is not in the tree.");
            return;
        }

        Position<E> parent = parent(position);

        if(numChildren(parent) == 1){
            if(A(parent) == position) {
                Node<E> nodeParent = validate(parent(parent));
                Node<E> node = validate(position);
                Node<E> newNodeA = validate(createNode(null));
                nodeParent.setA(newNodeA);
                newNodeA.setParent(nodeParent);
                node.setParent(node); //defunct
            }

            if(C(parent) == position) {
                Node<E> nodeParent = validate(parent(parent));
                Node<E> node = validate(position);
                Node<E> newNodeC = validate(createNode(null));
                nodeParent.setC(newNodeC);
                newNodeC.setParent(nodeParent);
                node.setParent(node); //defunct
            }

            if(G(parent) == position) {
                Node<E> nodeParent = validate(parent(parent));
                Node<E> node = validate(position);
                Node<E> newNodeG = validate(createNode(null));
                nodeParent.setG(newNodeG);
                newNodeG.setParent(nodeParent);
                node.setParent(node); //defunct
            }

            if(T(parent) == position) {
                Node<E> nodeParent = validate(parent(parent));
                Node<E> node = validate(position);
                Node<E> newNodeT = validate(createNode(null));
                nodeParent.setT(newNodeT);
                newNodeT.setParent(nodeParent);
                node.setParent(node); //defunct
            }
        }
        else
           validate(position).setElement(null);
    }

    public void display(int flag){
        displayAux(root(), flag);
    }

    private void displayAux(Position<E> pos, int flag) {
        if (pos == root) System.out.println("ROOT");

        else if (isInternal(pos))
            System.out.println(".".repeat(depth(pos)) + " I");
        else {
            System.out.print(".".repeat(depth(pos)) + (pos.getElement() != null ? " " + pos.getElement() : " E"));
             if(flag != 0  & pos.getElement() != null)  System.out.print(" " +pos.getElement().toString().length());
             System.out.println();
        }

        for (Position<E> c : children(pos))
            displayAux(c, flag);
    }

    public Position<E> search(String sequence, int type) {
        Position<E> position;
        if(type == 0)
            if(sequence.contains("$"))
                position = searchAux(root(), sequence.substring(0,sequence.length()-1), 0, 0);
            else
            position = searchAux(root(), sequence, 0, 0);
        else
            position = searchPrefix(root(), sequence,0,0);

        return position;
    }

    private Position<E> searchAux(Position<E> position, String sequence, int charIndex, int numberOfNodesVisited){
        Position<E> found = null;
        numberOfNodesVisited++;

        if(position != null) {
            if (isExternal(position) && position.getElement() != null && !position.getElement().toString().equals(sequence)) return null;
            else if (isExternal(position) && position.getElement() != null) {
                System.out.println(position.getElement() + ". Number of nodes visited: " + numberOfNodesVisited);
                 return position;
            }else if(position.getElement() == null)
                return null;
            else if(endSequence(position).getElement() != null && endSequence(position).getElement().toString().equals(sequence))
                return endSequence(position);
        }

        if(sequence.charAt(charIndex) == 'A')
            found = searchAux(A(position), sequence, ++charIndex, numberOfNodesVisited);

        else  if(sequence.charAt(charIndex) == 'C')
            found = searchAux(C(position), sequence, ++charIndex, numberOfNodesVisited);

        else if(sequence.charAt(charIndex) == 'G')
            found = searchAux(G(position), sequence, ++charIndex, numberOfNodesVisited);

        else if(sequence.charAt(charIndex) == 'T')
            found = searchAux(T(position), sequence, ++charIndex, numberOfNodesVisited);

        return found;
    }


    private Position<E> searchPrefix(Position<E> position, String sequence, int charIndex, int numberOfNodesVisited){ // total mi olmalı visited node sayısı yoksa// her birine ulasmak icin mi
        Position<E> found = null;
        numberOfNodesVisited++;

        if(position != null) {
            if (isExternal(position) && position.getElement() != null
                    && (position.getElement().toString().substring(0, sequence.length()).equals(sequence) //prefix kontrolu
                    || position.getElement().toString().equals(sequence))) {
                System.out.println(sequence + " is prefix of " + position.getElement() + ". Number of nodes visited: " + numberOfNodesVisited);
                return position;
            } else if (position.getElement() == null) {
                return null;
            }
        }

        if(charIndex < sequence.length()) {
            if (sequence.charAt(charIndex) == 'A')
                found = searchPrefix(A(position), sequence, ++charIndex, numberOfNodesVisited);

            else if (sequence.charAt(charIndex) == 'C')
                found = searchPrefix(C(position), sequence, ++charIndex, numberOfNodesVisited);

            else if (sequence.charAt(charIndex) == 'G')
                found = searchPrefix(G(position), sequence, ++charIndex, numberOfNodesVisited);

            else if (sequence.charAt(charIndex) == 'T')
                found = searchPrefix(T(position), sequence, ++charIndex, numberOfNodesVisited);
        }else{
                found = searchPrefix(A(position), sequence, charIndex, numberOfNodesVisited);
                found = searchPrefix(C(position), sequence, charIndex, numberOfNodesVisited);
                found = searchPrefix(G(position), sequence, charIndex, numberOfNodesVisited);
                found = searchPrefix(T(position), sequence, charIndex, numberOfNodesVisited);
                found = searchPrefix(endSequence(position), sequence, charIndex, numberOfNodesVisited);
        }

        return found;
    }

    private void addChildren(Position<E> position) {
        addA(position, null);
        addC(position, null);
        addG(position, null);
        addT(position, null);
        addEnd(position,null);
    }
}
