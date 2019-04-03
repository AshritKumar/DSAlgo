package practice.algo;

import java.util.Arrays;
import java.util.LinkedList;

public class MazeGrid {
	
	public static void main(String[] args) {
		// s - source, d- destinaltion, 0-block, * - proceed
		char[][] maze = new char[][]  { { '0', '*', '0', 's' }, 
										{ '*', '*', '*', '*' }, 
										{ '0', '*', '*', '0' }, 
										{ 'd', '*', '*', '*' } }; 
		findShortestPath(maze);
	}

	public static void findShortestPath(char[][] maze) {
		// create a visited array
		boolean visited[][] = new boolean[maze.length][maze[0].length]; 
		int srcX = 0, srcY = 0;
		// find the source
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				if(maze[i][j] == 's') {
					srcX = i; srcY=j;
					break;
				}
			}
		}
		boolean pe = doGridBFS(maze,visited,srcX,srcY);
		System.out.println(pe);
		
	}

	private static boolean doGridBFS(char[][] maze, boolean[][] visited, int srcX, int srcY) {
		int dist = 0, nodesInNextLayer = 0, nodesLeft = 1;
		// direction vectors for x and y for 4 directions, North(Up), East(R), South(D) and West(L)
		int rv[] = new int[] {-1, 0, 1, 0};
		int cv[] = new int[] {0, 1, 0, -1};
		// create q queue with array to hold x and y pos'n, 0th pos X and 1st pos Y
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[]{srcX, srcY});
		visited[srcX][srcY] = true;
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int x = pos[0], y = pos[1];
			// if reached end break
			if(maze[x][y] =='d') {
				System.out.println(dist);
				return true;
			}
			// else explore all the 4 directions and add them to queue
			for(int i=0; i<4; i++) {
				int newX = x + rv[i], newY = y + cv[i];
				if(isSafeMove(newX, newY, maze, visited)) {
					visited[newX][newY] = true;
					queue.add(new int[] {newX, newY});
					nodesInNextLayer++;
				}
			}
			--nodesLeft;
			if(nodesLeft == 0) {
				++dist;
				nodesLeft = nodesInNextLayer;
				nodesInNextLayer = 0;
				//System.out.println(x+" , "+y);
			}
		}
		return false;
		
	}

	private static boolean isSafeMove(int newX, int newY, char[][] maze, boolean[][] visited) {
		if(newX >=0 && newX < maze.length && newY >=0 && newY < maze[0].length && !visited[newX][newY] && maze[newX][newY] != '0')
			return true;
		return false;
	}

}
