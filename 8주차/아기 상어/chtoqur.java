import java.io.*;
import java.util.*;

class Node {
  int x;
  int y;
  int dis;

  public Node(int x, int y, int dist) {
    this.x = x;
    this.y = y;
    this.dis = dist;
  }
}

public class chtoqur {
  static int N;
  static int[][] space;
  static boolean[][] visited;
  static int sharkSize = 2;
  static int eatCount = 0;
  static int time = 0;
  static int[] dx = { -1, 0, 0, 1 };
  static int[] dy = { 0, -1, 1, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    space = new int[N][N];

    int sharkX = 0, sharkY = 0;

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        space[i][j] = Integer.parseInt(st.nextToken());
        if (space[i][j] == 9) {
          sharkX = i;
          sharkY = j;
          space[i][j] = 0; // 상어 위치
        }
      }
    }

    while (true) {
      Node target = bfs(sharkX, sharkY);
      if (target == null)
        break;

      // 상어 이동
      time += target.dis;
      sharkX = target.x;
      sharkY = target.y;

      // 물고기 먹기
      space[sharkX][sharkY] = 0;
      eatCount++;
      if (eatCount == sharkSize) {
        sharkSize++;
        eatCount = 0;
      }
    }

    System.out.println(time);
  }

  static Node bfs(int sx, int sy) {
    visited = new boolean[N][N];
    Queue<Node> q = new LinkedList<>();
    q.offer(new Node(sx, sy, 0));
    visited[sx][sy] = true;

    List<Node> fishes = new ArrayList<>();
    int minDist = Integer.MAX_VALUE;

    while (!q.isEmpty()) {
      Node cur = q.poll();

      // 현재 거리보다 더 먼 거리: break
      if (cur.dis > minDist)
        break;

      // 먹을 수 있는 물고기
      if (space[cur.x][cur.y] > 0 && space[cur.x][cur.y] < sharkSize) {
        fishes.add(cur);
        minDist = cur.dis;
        continue;
      }

      for (int d = 0; d < 4; d++) {
        int nx = cur.x + dx[d];
        int ny = cur.y + dy[d];
        if (nx < 0 || ny < 0 || nx >= N || ny >= N)
          continue;
        if (!visited[nx][ny] && space[nx][ny] <= sharkSize) {
          visited[nx][ny] = true;
          q.offer(new Node(nx, ny, cur.dis + 1));
        }
      }
    }

    if (fishes.isEmpty())
      return null;

    // 가장 위 + 왼쪽 기준
    fishes.sort((a, b) -> {
      if (a.x != b.x)
        return a.x - b.x;
      return a.y - b.y;
    });

    return fishes.get(0);
  }
}