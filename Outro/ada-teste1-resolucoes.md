# 2019 - Teste 1

## 1.

### a)

| 7 | 0 | 4 | 3 | 9 | 1 | 5 | 6 | 8 | 2 |
|---|---|---|---|---|---|---|---|---|---|

### b)

| 3 |
|---|

## 2.

### a)

```java
int solve(int[] X, int[] Y) {
	// Init values
	int current, previous;

	// Base case 1 (i = 0)
	previous = 0;

	// Base case 2 (i = 1)
	current = Math.abs(X[0] - Y[0]);

	// Remaining cases (i >= 2)
	for (int i = 2; i <= X.length + 1; i++) {
		current = Math.min(
			current + Math.abs(X[i - 1] - Y[i - 1]),
			previous + Math.abs(X[i - 1] - Y[i - 2]) + Math.abs(X[i - 2] - Y[i - 1])
		);
		previous = current;
	}

	return current;
}
```

### b)

A complexidade espacial deste algoritmo é $\Theta(1)$, sendo que é independente do tamanho dos valores de entrada, o espaço usado para fazer calculos intermédios é constante (usa apenas duas variáveis auxiliares).

### c)

A complexidade temporal deste algoritmo é $\Theta(n)$, onde $n$ é o comprimento dos valores de entrada, sendo que é preciso fazer $n + 1$ iterações, e cada iteração tem complexidade constante.

## 3.

### a)

```java
int meanCohesionCoef(UndiGraph graph) {
	boolean[] processed = new boolean[graph.numNodes()];
	Queue<Node> ready = new QueueIn...<>(?);

	double cohesionCoefTotal = 0
	int subGraphCount = 0;

	for (Node root : graph.nodes()) {
		if (processed[root]) continue;
		
		int n = 0, m = 0;
		ready.enqueue(root);
		
		while (!ready.isEmpty()) {
			Node node = ready.dequeue();
			m++;
			
			for (Node adj : graph.adjacentNodes(node)) {
				if (processed[adj]) continue;
				ready.push(adj);
				n++;
			}
			
			processed[node] = true;
		}

		cohesionCoefTotal += 2.0 * n / (m * (m - 1));
		subGraphCount++;
	}

	return cohesionCoefTotal / subGraphCount;
}
```

### b)

Para guardar os nós basta usar um inteiro, e para guardar as arestas usaria um vetor de listas ligadas de tamanho igual ao numero de nós, cada posição desse vetor corresponte a um nó no grafo, cuja lista contém os seus nós adjacentes.

```java
int numNodes = 13;
LinkedList<Integer>[] adjacentEdges = new List[numNodes];

adjacentEdges[0] = 1 -> 7 -> 8;
adjacentEdges[1] = 0 -> 7 -> 8;
adjacentEdges[2] = 3 -> 9 -> 10;
adjacentEdges[3] = 2 -> 4;
...
```

### c)

No pior caso, a complexidade temporal deste algoritmo é $\Theta(|V| + |A|)$, onde (V, A) é o grafo de entrada, porque o algoritmo efetua um percurso em largura, num grafo implementado com listas ligadas de adjacencias. As instruções adicionais para calcular o coeficiente médio têm todas complexidade constante. Em particular, o grau de um vertice é o tamanho da sua lista de adjacencias.

## 4.

### a)
$$
C_{O,V}(i, j) = \left\{
\begin{array}{ll}
	(1, 0) && \text{if } i = j \land E_i = \text{T} \\
	(0, 1) && \text{if } i = j \land E_i = \text{F} \\
	\displaystyle\sum\limits_{k=i+1}^{j}{L(O_k, C{O,V}(i, k - 1), C_{O,V}(k, j))} && \text{if } i < j
\end{array} 
\right.
$$
$$
L(o, (a, b), (x, y)) = \left\{
\begin{array}{ll}
	(ax, ay + bx + by) && \text{if } o = \text{and} \\
	(ax + ay + bx, by) && \text{if } o = \text{or} \\
	(ay + bx, ax + by) && \text{if } o = \text{xor}
\end{array} 
\right.
$$

### b)

$C_{O,V}(0, k)$