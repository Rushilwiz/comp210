package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateLeft() {
        if (isEmpty() || _right == null) {
            return this;
        }

        AVLTree<T> newRoot = _right;
        AVLTree<T> temp = newRoot._left;

        newRoot._left = this;
        _right = temp;

        updateHeight();
        newRoot.updateHeight();

        return newRoot;
    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
    private AVLTree<T> rotateRight() {
        if (isEmpty() || _left == null) {
            return this;
        }
        AVLTree<T> newRoot = _left;
        AVLTree<T> temp = newRoot._right;

        newRoot._right = this;
        _left = temp;

        updateHeight();
        newRoot.updateHeight();

        return newRoot;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (isEmpty()) {
            _value = element;
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            _height = 0;
            _size = 1;
            return this;
        }

        int compareResult = element.compareTo(_value);

        if (compareResult < 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (compareResult > 0) {
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            // Duplicate values are not allowed in this AVL tree implementation
            return this;
        }

        updateHeight();
        _size++;

        int balance = getBalance();

        if (balance > 1) {
            if (_left.getBalance() < 0) {
                _left = _left.rotateLeft();
            }
            return rotateRight();
        }

        else if (balance < -1) {
            if (_right.getBalance() > 0) {
                _right = _right.rotateRight();
            }
            return rotateLeft();
        }

        return this;
    }


    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (isEmpty()) {
            return this;
        }

        int compareResult = element.compareTo(_value);

        if (compareResult < 0) {
            _left = (AVLTree<T>) _left.remove(element);
        } else if (compareResult > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        } else {
            // Node with only one child or no child
            if (_left.isEmpty() || _right.isEmpty()) {
                AVLTree<T> temp = _left.isEmpty() ? _right : _left;
                if (temp.isEmpty()) {
                    return new AVLTree<>();
                } else {
                    return temp;
                }
            } else {
                _value = _right.findMin();
                _right = (AVLTree<T>) _right.remove(_value);
            }
        }

        updateHeight();
        _size--;

        int balance = getBalance();

        if (balance > 1) {
            if (_left.getBalance() < 0) {
                _left = _left.rotateLeft();
            }
            return rotateRight();
        }

        else if (balance < -1) {
            if (_right.getBalance() > 0) {
                _right = _right.rotateRight();
            }
            return rotateLeft();
        }

        return this;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_left == null || _left.isEmpty()) {
            return _value;
        } else {
            return _left.findMin();
        }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // Check if _right is not null before calling isEmpty on it
        if (_right == null || _right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }

        if (_value.compareTo(element) > 0) {
            return _left.contains(element);
        } else if (_value.compareTo(element) < 0) {
            return _right.contains(element);
        } else {
            return true;
        }
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
        return _right;
    }

    private void updateHeight() {
        int leftHeight = (_left != null) ? _left.height() : -1;
        int rightHeight = (_right != null) ? _right.height() : -1;
        _height = 1 + Math.max(leftHeight, rightHeight);
    }

    private int getBalance() {
        if (isEmpty()) {
            return 0;
        }

        int leftHeight = _left != null ? _left.height() : -1;
        int rightHeight = _right != null ? _right.height() : -1;

        return leftHeight - rightHeight;
    }

    public String toString() {
        if (isEmpty()) {
            return "";
        }

        String result = "";

        if (_left != null) {
            result += _left.toString();
        }

        result += _value + " ";

        if (_right != null) {
            result += _right.toString();
        }

        return result;
    }

    /**
     * Checks if the tree is balanced.
     * 
     * @return true if the tree is balanced, false otherwise.
     */
    public boolean isBalanced() {
        if (isEmpty()) {
            return true;
        }

        int balance = getBalance();
        if (Math.abs(balance) > 1) {
            return false;
        } else {
            boolean leftBalanced = _left == null || _left.isBalanced();
            boolean rightBalanced = _right == null || _right.isBalanced();
            return leftBalanced && rightBalanced;
        }
    }

}
