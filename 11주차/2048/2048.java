import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                answer = Math.max(answer, num);
            }
        }

        rotate(map, 5);

        System.out.println(answer);
        br.close();
    }

    static void moveLeft(int[][] orgMap, int[][] map, int count){
        for(int i=0; i<N; i++){
            for(int j=0; j<N-1; j++){
                if(map[i][j] != 0 && orgMap[i][j] == map[i][j]){
                    int prev = j;
                    j++;
                    while (j<N && map[i][j] == 0){
                        j++;
                    }
                    if(j<N && map[i][j] == map[i][prev]){
                        map[i][prev] += map[i][j];
                        map[i][j] = 0;
                        answer = Math.max(answer, map[i][prev]);
                    }
                    j--;
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 0){
                    int k = j+1;
                    while(k<N && map[i][k] == 0){
                        k++;
                    }
                    if(k<N && map[i][k] != 0){
                        map[i][j] = map[i][k];
                        map[i][k] = 0;
                    }
                }
            }
        }
        rotate(map, count);
    }

    static void moveRight(int[][] orgMap, int[][] map, int count){
        for(int i=0; i<N; i++){
            for(int j=N-1; j>=0; j--){
                if(map[i][j] != 0 && orgMap[i][j] == map[i][j]){
                    int prev = j;
                    j--;
                    while (j>=0 && map[i][j] == 0){
                        j--;
                    }
                    if(j>=0 && map[i][j] == map[i][prev]){
                        map[i][prev] += map[i][j];
                        map[i][j] = 0;
                        answer = Math.max(answer, map[i][prev]);
                    }
                    j++;
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=N-1; j>=0; j--){
                if(map[i][j] == 0){
                    int k = j-1;
                    while(k>=0 && map[i][k] == 0){
                        k--;
                    }
                    if(k>=0 && map[i][k] != 0){
                        map[i][j] = map[i][k];
                        map[i][k] = 0;
                    }
                }
            }
        }
        rotate(map, count);
    }

    static void moveUp(int[][] orgMap, int[][] map, int count){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[j][i] != 0 && orgMap[j][i] == map[j][i]){
                    int prev = j;
                    j++;
                    while (j<N && map[j][i] == 0){
                        j++;
                    }
                    if(j<N && map[j][i] == map[prev][i]){
                        map[prev][i] += map[j][i];
                        map[j][i] = 0;
                        answer = Math.max(answer, map[prev][i]);
                    }
                    j--;
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[j][i] == 0){
                    int k = j+1;
                    while(k<N && map[k][i] == 0){
                        k++;
                    }
                    if(k<N && map[k][i] != 0){
                        map[j][i] = map[k][i];
                        map[k][i] = 0;
                    }
                }
            }
        }
        rotate(map, count);
    }

    static void moveDown(int[][] orgMap, int[][] map, int count){
        for(int i=0; i<N; i++){
            for(int j=N-1; j>=0; j--){
                if(map[j][i] != 0 && orgMap[j][i] == map[j][i]){
                    int prev = j;
                    j--;
                    while (j>=0 && map[j][i] == 0){
                        j--;
                    }
                    if(j>=0 && map[j][i] == map[prev][i]){
                        map[prev][i] += map[j][i];
                        map[j][i] = 0;
                        answer = Math.max(answer, map[prev][i]);
                    }
                    j++;
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=N-1; j>=0; j--){
                if(map[j][i] == 0){
                    int k = j-1;
                    while(k>=0 && map[k][i] == 0){
                        k--;
                    }
                    if(k>=0 && map[k][i] != 0){
                        map[j][i] = map[k][i];
                        map[k][i] = 0;
                    }
                }
            }
        }
        rotate(map, count);
    }

    static int[][] copyArr(int[][] map){
        int[][] newMap = new int[N][N];
        for(int i=0; i<N; i++){
            System.arraycopy(map[i], 0, newMap[i], 0, map[i].length);
        }
        return newMap;
    }

    static void rotate(int[][] orgMap, int count){
        if(count>0){
            moveLeft(orgMap, copyArr(orgMap), count-1);
            moveRight(orgMap, copyArr(orgMap), count-1);
            moveUp(orgMap, copyArr(orgMap), count-1);
            moveDown(orgMap, copyArr(orgMap), count-1);
        }
    }
}
