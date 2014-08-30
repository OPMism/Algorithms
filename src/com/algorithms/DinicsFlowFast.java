package com.algorithms;

import java.util.Arrays;

public class DinicsFlowFast {

	public int V;
	public int E;
	public static final int MAXV = 5003;
	public static final int MAXE = 60003;
	public int mVertex[];
	public long mCapacity[];
	public int mNext[];
	public int mLast[];
	public int mDist[];
	public int mQueue[];
	public int mNow[];
	public int edgeCount;
	public int S;
	public int T;
	public long mFlow;

	public DinicsFlowFast(int V, int E) {
		this.V = V;
		this.E = E;
		mVertex = new int[MAXE];
		mCapacity = new long[MAXE];
		mNext = new int[MAXE];
		mLast = new int[MAXV];
		mDist = new int[MAXV];
		mQueue = new int[MAXV];
		mNow = new int[MAXV];
	}

	public void initialize() {
		edgeCount = 0;
		Arrays.fill(mLast, -1);
		Arrays.fill(mNext, -1);
		mFlow = 0;
	}

	public void addEdge(int u, int v, long capacity, long reverseCapacity) {
		mVertex[edgeCount] = v;
		mCapacity[edgeCount] = capacity;
		mNext[edgeCount] = mLast[u];
		mLast[u] = edgeCount++;

		mVertex[edgeCount] = u;
		mCapacity[edgeCount] = reverseCapacity;
		mNext[edgeCount] = mLast[v];
		mLast[v] = edgeCount++;
	}

	/**
	 * Computes paths (level graph) backtracking from destination to source
	 * 
	 * @return
	 */
	public boolean dinicBFS() {

		int a, b;
		int u, edge;

		Arrays.fill(mDist, -1);
		a = b = 0;

		mQueue[0] = T;
		mDist[T] = 0;

		while (a <= b) {
			u = mQueue[a++];
			for (edge = mLast[u]; edge >= 0; edge = mNext[edge]) {
				if (mCapacity[edge ^ 1] > 0 && mDist[mVertex[edge]] == -1) {
					mQueue[++b] = mVertex[edge];
					mDist[mVertex[edge]] = mDist[u] + 1;
				}
			}
		}

		return mDist[S] >= 0;
	}

	public long dinicDFS(int u, long flow) {

		long delta;

		if (u == T)
			return flow;

		for (int edge = mNow[u]; edge >= 0; edge = mNext[edge], mNow[u] = edge) {
			if (mCapacity[edge] > 0 && mDist[u] == mDist[mVertex[edge]] + 1) {
				delta = dinicDFS(mVertex[edge], Math.min(flow, mCapacity[edge]));
				if (delta > 0) {
					mCapacity[edge] -= delta;
					mCapacity[edge ^ 1] += delta;
					return delta;
				}
			}
		}

		return 0;
	}

	//	 /** Computes paths (level graph) from source to destination
//	 *
//	 * @return
//	 */
//	 public boolean dinicBFS() {
//	
//	 int a, b;
//	 int u, edge;
//	
//	 Arrays.fill(mDist, -1);
//	 a = b = 0;
//	
//	 mQueue[0] = S;
//	 mDist[S] = 0;
//	
//	 while (a <= b) {
//	 u = mQueue[a++];
//	 for (edge = mLast[u]; edge >= 0; edge = mNext[edge]) {
//	 if (mCapacity[edge] > 0 && mDist[mVertex[edge]] == -1) {
//	 mQueue[++b] = mVertex[edge];
//	 mDist[mVertex[edge]] = mDist[u] + 1;
//	 }
//	 }
//	 }
//	
//	 return mDist[T] >= 0;
//	 }
//	
//	 public long dinicDFS(int u, long flow) {
//	
//	 long delta;
//	
//	 if (u == T)
//	 return flow;
//	
//	 for (int edge = mNow[u]; edge >= 0; edge = mNext[edge], mNow[u] = edge) {
//	 //Paths from source to dest
//	 if (mCapacity[edge] > 0 && mDist[u] == mDist[mVertex[edge]] - 1) {
//	
//	 delta = dinicDFS(mVertex[edge], Math.min(flow, mCapacity[edge]));
//	 if (delta > 0) {
//	 mCapacity[edge] -= delta;
//	 mCapacity[edge ^ 1] += delta;
//	 return delta;
//	 }
//	 }
//	 }
//	
	// return 0;
	// }

	public void computeMaxFlow(int S, int T) {
		long delta;

		this.S = S;
		this.T = T;

		while (dinicBFS()) {
			// System.out.println("BFS");
			for (int i = 0; i < MAXV; i++)
				mNow[i] = mLast[i];
			while (true) {
				delta = dinicDFS(S, Long.MAX_VALUE);
				if (delta == 0)
					break;
				mFlow += delta;
				// System.out.println("DFS");
			}
		}

	}

	public long maxFlow() {
		return mFlow;
	}

	public static void main(String[] args) {

		DinicsFlowFast mFlow = new DinicsFlowFast(6, 10);
		mFlow.initialize();

		mFlow.addEdge(0, 1, 10, 0);
		mFlow.addEdge(0, 2, 5, 0);
		mFlow.addEdge(1, 3, 9, 0);
		mFlow.addEdge(1, 4, 4, 0);
		mFlow.addEdge(2, 1, 4, 0);
		mFlow.addEdge(2, 4, 8, 0);
		mFlow.addEdge(3, 4, 15, 0);
		mFlow.addEdge(3, 5, 10, 0);
		mFlow.addEdge(4, 5, 10, 0);

		mFlow.computeMaxFlow(0, 5);

		System.out.println("Maxflow = " + mFlow.maxFlow());
	}

}
