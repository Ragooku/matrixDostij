package m5;

import java.util.*;

public class m5 {
    
    public static int[][] buildAdjacencyMatrix(int[][] edges, int n) {
        int[][] adjacencyMatrix = new int[n][n];

        for (int[] edge : edges) {
            int from = edge[0] - 1;  // Переключаемся на индекс с 0
            int to = edge[1] - 1;    // Переключаемся на индекс с 0
            if (from >= n || to >= n || from < 0 || to < 0) {
                System.out.println("Ошибка: Номера вершин должны быть в пределах от 1 до " + n);
                return null;
            }
            adjacencyMatrix[from][to] = 1;
        }

        return adjacencyMatrix;
    }

    // Метод для вычисления матрицы достижимости с помощью алгоритма Флойда-Уоршалла
    public static int[][] buildReachabilityMatrix(int[][] adjacencyMatrix, int n) {
        if (adjacencyMatrix == null) return null;

        int[][] reachabilityMatrix = new int[n][n];

        // Инициализируем матрицу достижимости копией матрицы смежности
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reachabilityMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }

        // Применяем алгоритм Флойда-Уоршалла для нахождения всех путей
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    reachabilityMatrix[i][j] = reachabilityMatrix[i][j] != 0 || 
                                               (reachabilityMatrix[i][k] != 0 && reachabilityMatrix[k][j] != 0) ? 1 : 0;
                }
            }
        }

        return reachabilityMatrix;
    }

    // Метод для вывода матрицы
    public static void printMatrix(int[][] matrix, int n) {
        if (matrix == null) return;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод количества вершин и ребер
        System.out.println("Введите количество вершин:");
        int n = scanner.nextInt();

        System.out.println("Введите количество ребер:");
        int m = scanner.nextInt();

        // Ввод ребер
        int[][] edges = new int[m][2];
        System.out.println("Введите ребра (формат: from to):");
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            edges[i][0] = from;
            edges[i][1] = to;
        }

        // Построение матрицы смежности
        int[][] adjacencyMatrix = buildAdjacencyMatrix(edges, n);
        System.out.println("Матрица смежности:");
        printMatrix(adjacencyMatrix, n);

        // Построение матрицы достижимости
        int[][] reachabilityMatrix = buildReachabilityMatrix(adjacencyMatrix, n);
        System.out.println("Матрица достижимости:");
        printMatrix(reachabilityMatrix, n);

        scanner.close();
    }
}
