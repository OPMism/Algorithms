package com.algorithms;

import java.util.ArrayList;

public class HLD {
	
	public int mChainHead[];
	public int mChainNumber[];
	public int mChainSize[];
	public int mChainPos[];
	public int mTreeNodes[];
	public int mChainNum;
	public ArrayList<Integer> mAdj[];
	public int mN;

	public HLD(int nodes, ArrayList<Integer> adj[]) {
		mN = nodes;
		mChainHead = new int[nodes];
		mChainNumber = new int[nodes];
		mChainSize = new int[nodes];
		mChainPos = new int[nodes];
		mTreeNodes = new int[nodes];
		mChainNum = 0;
		
		for (int i = 0; i < nodes; i++) {
			mChainHead[i] = -1;
			mAdj[i] = new ArrayList<>();
		}
	}
	
	public void addNode(int parent, int child) {
		mTreeNodes[child] = parent;
		mAdj[parent].add(child);
	}
	
	public void buildHLD(int curNode) {
		int i, subSize, subNode;
		int node;
		
		if (mChainHead[mChainNum] == -1) {
			mChainHead[mChainNum] = curNode;
		}
		
		mChainNumber[curNode] = mChainNum;
		mChainPos[curNode] = mChainSize[mChainNum];
		mChainSize[mChainNum]++;
		
		subSize = -1;
		subNode = -1;
		
		for (i = 0; i < mAdj[curNode].size(); i++) {
			node = mAdj[i].get(i);
			if (mAdj[node].size() > subSize) {
				subNode = i;
				subSize = mAdj[node].size();
			}
		}
		
		if (subNode != -1)
			buildHLD(subNode);
		
		for (i = 0; i < mAdj[curNode].size(); i++) {
			if (i != subNode) {
				mChainNum++;
				buildHLD(mAdj[curNode].get(i));
			}
		}
		
	}

}
