package com.liangdianshui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 网易2017春招笔试(调整队形)a
 * 
 * @author 两点水
 *
 */
public class AdjustFormation {

	public static void main(String[] args) {
		// 控制台输入
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		sc.close();

		int n = s.length();
		if (n <= 0 || n > 50) {
			throw new RuntimeException("the length is too long");
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == 'B') {
				list.add(i);
			}
		}

		int bSize = list.size();
		int indexSum = 0;
		for (int val : list) {
			indexSum += val;
		}
		
		int left = indexSum - bSize * (bSize - 1) / 2;
		int right = bSize * (n - 1) - indexSum - bSize * (bSize - 1) / 2;
		// 移左 或者 移右 ，选择最少的
		System.out.println(Math.min(left, right));
	}
}
