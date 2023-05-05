# Teste 1 - 2017/18

## 1.

4ª Reunião:

| 0  | 1  | 2  | 3  | 4 | 5 | 6 | 7 |
|----|----|----|----|---|---|---|---|
| -2 | -2 | -2 | -2 | 0 | 1 | 2 | 3 |

6ª Reunião:

| 0  | 1 | 2  | 3 | 4 | 5 | 6 | 7 |
|----|---|----|---|---|---|---|---|
| -3 | 0 | -3 | 2 | 0 | 1 | 2 | 3 |

7ª Reunião:

| 0  | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|----|---|---|---|---|---|---|---|
| -4 | 0 | 0 | 2 | 0 | 1 | 2 | 2 |

## 2.

```java
int solve(int N) {
	int[] f = new int[N + 1];

	f[0] = 1;

	for (int n = 0; n <= N; n++) {
		for (int i = 0; i < n; i++) {
			f[n] += f[i] * f[n - i - 1];
		}
	}

	return f[N];
}
```

Complexidade temporal: $\Theta(N^2)$, no primeiro for, são executadas $N$ vezes, cada iteração desse for executa outro for, que por sua vez faz $N$ iterações de operações de tempo constante, sendo $N$ o valor de entrada da função.
$$
\Theta(\sum_{n=0}^{N}\sum_{i=0}^{n-1} 1) = \Theta(\sum_{n=0}^{N}(n-1)) = \Theta((\sum_{n=0}^{N}n) - N) = \Theta(\frac{N(N-1)}{2}-N) = \Theta(N^2)
$$
Complexidade espacial: $\Theta(N)$, é criado um vetor memória de resultados da função recursiva, com tamanho $N$, sendo $N$ o valor de entrada da função.

## 3.

### a)

```java
List<Node> solve(DiGraph graph, List<Node> red) {
	List<Node> permutation = new ArrayList<>(red.size());
	Bag<Node> ready = new BagIn...<>(?);
	int[] inDegree = new int[graph.numNodes()];
	
	for (Node v : graph.nodes()) {
		inDegree[v] = -1;
	}

	for (Node v : red) {
		inDegree[v] = graph.inDegree(v);
		if (inDegree[v] == 0)
			ready.add(v);
	}

	while (!ready.isEmpty()) {
		Node node = ready.remove();
		permutation.add(node);
		for (Node v : graph.outAdjacentNodes(node)) {
			inDegree[v]--;
			if (inDegree[v] == 0)
				ready.add(v);
		}
	}

	return permutation;
}
```

### b)

Implementaria o grafo com um vetor de listas ligadas de sucessores de cada vertice, e um vetor de inteiros para o grau de antecessores de cada vertice.

### c)

De acordo com a implementação utilizada para o grafo, a complexidade temporal seria $\Theta(|V| + |A|)$, sendo que o pior caso encontra-se no while, onde se pode acabar por percorrer todos os vertices e todas as arestas. Cada operação intermediária é constante.

## 4.

$$
man
$$
