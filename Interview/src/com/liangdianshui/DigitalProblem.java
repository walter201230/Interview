package com.liangdianshui;

/**
 * <p>鬼谷子问徒[经典] 孙膑，庞涓都是鬼谷子的徒弟。一天鬼谷子出了这道题目： 他从2到99中选出两个不同的整数，把积告诉孙膑，把和告诉庞涓；
 * 庞涓说：我虽然不能确定这两个数是什么，但是我肯定你也不知道这两个数是什么。 
 * 孙膑说：我本来的确不知道，但是听你这么一说，我现在能够确定这两个数字了。
 * 庞涓说：既然你这么说，我现在也知道这两个数字是什么了。 请问这两个数字是什么？为什么？
 * </p>
 * 
 * @author Administrator
 *
 */
public class DigitalProblem {

	public static void main(String[] args) {
		for (int n = 6; n < 200; ++n) // 穷举和的可能，最大不超过200
		{
			// 其和能同时满足条件1和3者即为结果
			if (p1(n) && p3(n)) {
				// 找出对应解
				for (int t = 2; t * 2 < n; ++t) {
					if (p2(t * (n - t))) // 分拆结果符合条件2就输出
						System.out.println("(" + t + "," + (n - t) + ")");
				}
			}
		}
	}

	/**
	 * 是否是唯一分解
	 * 
	 * @param n
	 * @param nMax
	 *            最大值为100
	 * @return
	 */
	public static boolean isOnlySolve(int n, int nMax) {
		int nRet = 0; // 记录满足本条件数
		for (int i = 2; i * i < n; ++i) {
			if (n % i == 0 && n / i < nMax)
				if (++nRet > 1) {
					return false;
				}
		}
		return true;
	}

	/**
	 * sum的任意和的分拆之积不可能有唯一分解，否则对方可能猜出
	 * <p>
	 * 我虽然不能确定这两个数是什么，但是我肯定你也不知道这两个数是什么。
	 * </p>
	 * 
	 * @param sum
	 * @return
	 */
	public static boolean p1(int sum) {
		if (sum < 6)
			return false;
		for (int t = (sum - 1) / 2; t > 1; --t) {
			if (isOnlySolve(t * (sum - t), 100))
				return false;
		}
		return true;
	}

	/**
	 * 只有一种积的分拆满足 p1
	 * <p>
	 * 我本来的确不知道，但是听你这么一说，我现在能够确定这两个数字了
	 * </p>
	 * 
	 * @param times2
	 * @return
	 */
	public static boolean p2(int times2) {
		int nRet = 0; // 记录满足本条件数
		for (int nd = 2; nd * nd < times2; ++nd) {
			if (times2 % nd == 0 && p1(nd + times2 / nd))
				if (++nRet > 1)
					return false;
		}
		return true;
	}

	/**
	 * 只有一种和的分拆满足 p2
	 * <p>
	 * 既然你这么说，我现在也知道这两个数字是什么了
	 * </p>
	 * 
	 * @param sum
	 * @return
	 */
	public static boolean p3(int sum) {
		int nRet = 0; // 记录满足本条件数
		for (int t = (sum - 1) / 2; t > 1; --t) {
			if (p2(t * (sum - t)))
				if (++nRet > 1)
					return false;
		}
		return true;
	}

}
