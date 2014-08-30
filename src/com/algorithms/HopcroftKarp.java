package com.algorithms;

import java.util.Arrays;

public class HopcroftKarp {

	public int mHead[];
	public int mPrev[];
	public int mLast[];
	public int mDist[];
	public int mQueue[];
	public int mMatching[];
	public boolean mVisited[];
	public boolean mUsed[];
	public int mEdgeCount;
	public int V1, V2, E;

	/**
	 * Adds edges to a bipartite graph. Divides it into two sets of vertices. V
	 * = V1 U V2, where V1 and V2 are disjoint
	 * 
	 * @param V1
	 * @param V2
	 */
	public HopcroftKarp(int V1, int V2, int E) {
		this.V1 = V1;
		this.V2 = V2;
		this.E = E;
		mQueue = new int[E + 5];
		mHead = new int[E + 5];
		mPrev = new int[E + 5];

		mLast = new int[V1 + 5];
		mUsed = new boolean[V1 + 5];
		mVisited = new boolean[V1 + 5];
		mDist = new int[V1 + 5];
		
		mMatching = new int[V2 + 5];
		initialize();
	}

	public void initialize() {
		mEdgeCount = 0;
		Arrays.fill(mLast, -1);
		Arrays.fill(mMatching, -1);
	}

	public void addEdge(int u, int v) {
		mHead[mEdgeCount] = v;
		mPrev[mEdgeCount] = mLast[u];
		mLast[u] = mEdgeCount++;
	}

	public void bfs() {
		int sizeQ = 0;
		int v;
		int u1;
		int u2;

		Arrays.fill(mDist, -1);

		for (int u = 0; u < V1; u++) {
			if (!mUsed[u]) {
				mQueue[sizeQ++] = u;
				mDist[u] = 0;
			}
		}

		for (int i = 0; i < sizeQ; i++) {
			u1 = mQueue[i];

			for (int edge = mLast[u1]; edge >= 0; edge = mPrev[edge]) {
				v = mHead[edge];
				u2 = mMatching[v];

				if (u2 >= 0 && mDist[u2] == -1) {
					mDist[u2] = mDist[u1] + 1;
					mQueue[sizeQ++] = u2;
				}
			}
		}

	}

	public boolean dfs(int u1) {

		int u2;
		int v;

		mVisited[u1] = true;

		for (int edge = mLast[u1]; edge >= 0; edge = mPrev[edge]) {

			v = mHead[edge];
			u2 = mMatching[v];

			if (u2 < 0
					|| (!mVisited[u2] && mDist[u2] == mDist[u1] + 1 && dfs(u2))) {
				mMatching[v] = u1;
				mUsed[u1] = true;
				return true;
			}
		}

		return false;
	}

	public int maxMatching() {
		int df = 0;

		for (int res = 0;;) {
			bfs();

			Arrays.fill(mVisited, false);
			df = 0;
			
			for (int u = 0; u < V1; u++) {
				if (!mUsed[u] && dfs(u)) {
					df++;
				}
			}

			if (df == 0)
				return res;
			
			res += df;
		}

	}

	public static void main(String[] args) {
		int maxMatching;
		
		HopcroftKarp mHopcroftKarp;

		mHopcroftKarp = new HopcroftKarp(2, 2, 3);
		
		mHopcroftKarp.addEdge(0, 0);
		mHopcroftKarp.addEdge(0, 1);
		mHopcroftKarp.addEdge(1, 1);
		
		maxMatching = mHopcroftKarp.maxMatching();
		
		System.out.println("Max matching = " + maxMatching);

	}
}
