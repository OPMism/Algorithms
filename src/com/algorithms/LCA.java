package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.topcoder.com/tc?d1=tutorials&d2=lowestCommonAncestor&module=Static
 * 
 * @author sultan.of.swing
 *
 */

public class LCA {
	public static void main(String[] args) {

	}
}

/**
 * LCA using square root decomposition
 * 
 * @author sultan.of.swing
 *
 */

class SqrtLCA {
	public int mAncestor[];
	public int mLevel[];
	public int mTree[]; // Stores the parent of each node in the tree
	public int N;
	public int mHeight;
	public boolean mMarked[];
	public ArrayList<Integer> mAdj[];

	public SqrtLCA(ArrayList<Integer> adj[], int N) {
		int nr;
		this.N = N;
		mAncestor = new int[N];
		mLevel = new int[N];
		mTree = new int[N];

		mAdj = adj;
		mHeight = 0;

		// Root tree with node 0 as root
		rootTree(0, adj, N);

		for (int i = 0; i < N; i++) {
			mAncestor[i] = -1;
		}

		nr = (int) Math.sqrt(mHeight);
		preprocess(nr);
	}

	public void rootTree(int root, ArrayList<Integer> adj[], int N) {
		mMarked = new boolean[N];

		mTree[root] = -1;
		mLevel[root] = 0;
		dfs(root, adj, 1);
	}

	public void dfs(int root, ArrayList<Integer> adj[], int height) {

		mMarked[root] = true;
		mHeight = Math.max(height, mHeight);

		for (int node : adj[root]) {
			if (!mMarked[node]) {
				mTree[node] = root;
				mLevel[node] = height;
				dfs(node, adj, height + 1);
			}
		}
	}

	public void preprocess(int nr) {
		Arrays.fill(mMarked, false);
		dfs(0, nr);
	}

	public void dfs(int node, int nr) {

		mMarked[node] = true;

		if (mLevel[node] < nr)
			mAncestor[node] = 0;
		else {
			if (mLevel[node] % nr == 0) {
				mAncestor[node] = mTree[node];
			} else {
				mAncestor[node] = mAncestor[mTree[node]];
			}
		}

		for (int u : mAdj[node]) {
			if (!mMarked[u])
				dfs(u, nr);
		}

	}

	public int lca(int u, int v) {

		while (mAncestor[u] != mAncestor[v]) {
			if (mLevel[u] > mLevel[v])
				u = mAncestor[u];
			else
				v = mAncestor[v];
		}

		while (u != v) {
			if (mLevel[u] > mLevel[v])
				u = mTree[u];
			else
				v = mTree[v];
		}

		return u;
	}

	public void printAll() {
		int i;

		System.out.println("Tree:");
		for (i = 0; i < N; i++) {
			System.out.println("Node: " + i + ", Parent: " + mTree[i]);
		}
		System.out.println("\nLevels:");
		for (i = 0; i < N; i++) {
			System.out.println("Node: " + i + ", Level: " + mLevel[i]);
		}
		System.out.println("\nAncestors:");
		for (i = 0; i < N; i++) {
			System.out.println("Node: " + i + ", Ancestors: " + mAncestor[i]);
		}
	}

	public static SqrtLCA mSqrtLCA;

	public static void main(String[] args) {
		int N = 13;
		int lca;
		ArrayList<Integer> adj[];
		adj = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		add(adj, 0, 1);
		add(adj, 0, 2);
		add(adj, 0, 3);
		add(adj, 2, 4);
		add(adj, 2, 5);
		add(adj, 2, 6);
		add(adj, 5, 7);
		add(adj, 5, 8);
		add(adj, 6, 9);
		add(adj, 6, 10);
		add(adj, 9, 11);
		add(adj, 9, 12);

		mSqrtLCA = new SqrtLCA(adj, N);
		mSqrtLCA.printAll();

		lca = mSqrtLCA.lca(0, 0);
		System.out.println("\nLCA: " + lca);

		testCase1();

	}

	static void testCase1() {
		int N = 20;
		int lca;
		SqrtLCA mSqrtLCA;
		ArrayList<Integer> adj[];
		adj = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		add(adj, 0, 1);
		add(adj, 0, 3);
		add(adj, 0, 4);
		add(adj, 1, 2);
		add(adj, 1, 8);
		add(adj, 1, 5);
		add(adj, 4, 6);
		add(adj, 4, 7);
		add(adj, 5, 9);
		add(adj, 5, 13);
		add(adj, 9, 15);
		add(adj, 9, 12);
		add(adj, 13, 16);
		add(adj, 12, 10);
		add(adj, 10, 14);
		add(adj, 10, 11);
		add(adj, 11, 17);
		add(adj, 17, 18);
		add(adj, 18, 19);

		mSqrtLCA = new SqrtLCA(adj, N);
		mSqrtLCA.printAll();

		System.out.println("\nLCA: ");
		lca = mSqrtLCA.lca(6, 10);
		System.out.println(lca);
	}

	static void add(ArrayList<Integer> adj[], int u, int v) {
		adj[u].add(v);
		adj[v].add(u);
	}

}

class LogLCA {
	public int mTree[];
	public int mLevel[];
	public int mLCA[][];
	public boolean mMarked[];
	public ArrayList<Integer> mAdj[];
	public int mN;
	public int mLog;

	public LogLCA(int N, ArrayList<Integer> adj[]) {
		mN = N;
		mTree = new int[N];
		mLevel = new int[N];
		mLog = 32 - Integer.numberOfLeadingZeros(N - 1);
		mAdj = adj;
		mLCA = new int[N][mLog];
		mMarked = new boolean[N];
		rootTree(0);
		preprocess();
	}

	public void rootTree(int root) {
		mTree[root] = -1;
		mLevel[root] = 0;
		dfs(root, 1);
	}

	public void dfs(int node, int level) {
		mMarked[node] = true;
		for (int u : mAdj[node]) {
			if (!mMarked[u]) {
				mTree[u] = node;
				mLevel[u] = level;
				dfs(u, level + 1);
			}
		}
	}

	public void preprocess() {

		for (int i = 0; i < mLog; i++)
			for (int j = 0; j < mN; j++)
				mLCA[j][i] = -1;

		for (int i = 0; i < mLog; i++) {
			for (int j = 0; j < mN; j++) {
				if (i == 0) {
					mLCA[j][0] = mTree[j];
				} else if (mLCA[j][i - 1] != -1) {
					mLCA[j][i] = mLCA[mLCA[j][i - 1]][i - 1];
				}
			}
		}
	}

	public int lca(int u, int v) {
		int i;
		int log;

		if (mLevel[u] < mLevel[v]) {
			u ^= v;
			v ^= u;
			u ^= v;
		}

		for (log = 0; (1 << log) <= mLevel[u]; log++)
			;
		log--;

		for (i = log; i >= 0; i--) {
			if (mLevel[u] - (1 << i) >= mLevel[v]) {
				u = mLCA[u][i];
			}
		}

		if (u == v)
			return u;

		for (i = log; i >= 0; i--) {
			if (mLCA[u][i] != -1 && mLCA[u][i] != mLCA[v][i]) {
				u = mLCA[u][i];
				v = mLCA[v][i];
			}
		}

		return mTree[u];
	}
	
	public int LCA(int u, int v) {
		int i;
		int log;
		
		if (mLevel[u] < mLevel[v]) {
			u ^= v;
			v ^= u;
			u ^= v;
		}
		
		for (log = 0; (1 << log) <= mLevel[u]; log++);
		log--;
		
		for (i = log; i >= 0; i--) {
			if (mLevel[u] - (1 << i) >= mLevel[v]) {
				u = mLCA[u][i];
			}
		}
		
		if (u == v)
			return u;
		
		for (i = log; i >= 0; i--) {
			if (mLCA[u][i] != -1 && mLCA[u][i] != mLCA[v][i]) {
				u = mLCA[u][i];
				v = mLCA[v][i];
			}
		}
		
		return mTree[u];
	}

	public void printAll() {

		System.out.println("Levels:");
		System.out.printf("%12s", "Nodes:");
		for (int i = 0; i < mN; i++)
			System.out.printf("%4d", i);
		System.out.println();
		System.out.printf("%12s", "Level:");
		for (int i = 0; i < mN; i++)
			System.out.printf("%4d", mLevel[i]);

		System.out.println();
		System.out.println("Preprocessed array:");

		System.out.printf("%12s", "Nodes:");
		for (int i = 0; i < mN; i++)
			System.out.printf("%4d", i);

		// System.out.printf("%s", i);
		System.out.println();

		for (int i = 0; i < mLog; i++) {
			System.out.printf("%12s", "mLCA[..][" + i + "]:");
			for (int j = 0; j < mN; j++) {
				System.out.printf("%4d", mLCA[j][i]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		testCase();
	}

	static void testCase() {
		int N = 13;
		int lca;
		ArrayList<Integer> adj[];
		adj = new ArrayList[N];
		LogLCA mLogLCA;

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		add(adj, 0, 1);
		add(adj, 0, 2);
		add(adj, 0, 3);
		add(adj, 2, 4);
		add(adj, 2, 5);
		add(adj, 2, 6);
		add(adj, 5, 7);
		add(adj, 5, 8);
		add(adj, 6, 9);
		add(adj, 6, 10);
		add(adj, 9, 11);
		add(adj, 9, 12);

		mLogLCA = new LogLCA(N, adj);
		mLogLCA.printAll();

		lca = mLogLCA.lca(11, 6);
		System.out.println("\nLCA: " + lca);
	}

	static void add(ArrayList<Integer> adj[], int u, int v) {
		adj[u].add(v);
		adj[v].add(u);
	}
}