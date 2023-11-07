package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element){
		if (element.compareTo(_element) < 0) {
			_left = _left.insert(element);
		} else if (element.compareTo(_element) >= 0) {
			_right = _right.insert(element);
		}

		return this;
	}

	// TODO: findMin
	@Override
	public T findMin() {
		BST<T> current = this;
		while (!current.getLeft().isEmpty()) {
			current = current.getLeft();
		}
		return current.getElement();
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(_element) < 0) {
			_left = _left.remove(element);
		} else if (element.compareTo(_element) > 0) {
			_right = _right.remove(element);
		} else {
			if (_left.isEmpty()) {
				return _right;
			} else if (_right.isEmpty()) {
				return _left;
			} else {
				_element = _right.findMin();
				_right = _right.remove(_element);
			}
		}

		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element + " ");
		_left.printPreOrderTraversal();
		_right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element + " ");
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
