package com.liangdianshui;

import java.util.Scanner;


/**
 * <p>
 *   有任意种水果，每种水果个数也是任意的，两人轮流从中取出水果，规则如下：
 *   1）每一次应取走至少一个水果；每一次只能取走一种水果的一个或者全部
 *   2）如果谁取到最后一个水果就胜
 *	  给定水果种类N和每种水果的个数M1，M2，…Mn，算出谁取胜。
 *   （题目的隐含条件是两个人足够聪明，聪明到为了取胜尽可能利用规则）
 * </p>
 * 
 * @author liangdianshui
 *
 */
public class TakeTheFruit {
	private static final String EXIT = "q";

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String input;
		int[] fruitNums;

		do {
			System.out.println("假设 A 和 B 两个人，A 先取水果");
			System.out.println("请输入每种水果的个数（空格或回车分隔）：");
			System.out.println("输入 Q 或  q 退出");

			if (EXIT.equalsIgnoreCase(input = scanner.nextLine())) {
				System.out.println("Exit");
				break;
			}

			input = input.trim();
			if (input.length() != 0) {
				fruitNums = initFruitNums(input);
				boolean isWin = takeTheFruitGame(fruitNums, fruitNums.length);
				if(isWin){
					System.out.println("A 赢");
				}else{
					System.out.println("B 赢");
				}
				System.out.println("--------------------------------------------");
			}
		} while (true);
	}

	/**
	 * 初始化每种水果的个数
	 * 
	 * @param input
	 * @return
	 */
	private static int[] initFruitNums(String input) {
		String[] nums = input.split("\\s+");
		int[] fruitNums = new int[nums.length];
		int num;
		for (int i = 0; i < nums.length; i++) {
			num = Integer.parseInt(nums[i]);
			if (num <= 0) {
				throw new IllegalArgumentException("水果数量不能为 0 或负数：" + num);
			}

			fruitNums[i] = num;
		}

		return fruitNums;
	}

	/**
	 * 递归法
	 * 
	 * @param fruitNums
	 * @param numOfTypes
	 * @return
	 */
	private static boolean takeTheFruitGame(int[] fruitNums, int numOfTypes) {
		
		//当水果种类为1的时候，必胜
		if (numOfTypes == 1) {
			return true;
		}

		// 当水果种类为2的时候
		if (numOfTypes == 2) {
			return sumOfTwoFruitNums(fruitNums) % 2 == 1;
		}

		// 当水果种类大于等于3的时候
		int num;
		for (int i = 0; i < fruitNums.length; i++) {
			num = fruitNums[i];
			if (num == 0)
				continue;

			fruitNums[i] = 0;
			if (!takeTheFruitGame(fruitNums, numOfTypes - 1)) {
				fruitNums[i] = num;
				return true;
			}
			if (num > 1) {
				fruitNums[i] = num - 1;
				if (!takeTheFruitGame(fruitNums, numOfTypes)) {
					fruitNums[i] = num;
					return true;
				}
			}

			fruitNums[i] = num;
		}

		return false;
	}

	/**
	 * <p>
	 * 	通过结论直接输出结果
	 *  N 为奇数，A 必胜
	 *  N 为偶数，如果 M 为奇数，A 必胜；如果 M 为偶数，A 必败
	 * </p>
	 * @param fruitNums
	 * @return
	 */
	private static boolean takeTheFruitGame2(int[] fruitNums) {
		if (fruitNums.length % 2 == 1) {
			return true;
		}

		return sumOfFruitNums(fruitNums) % 2 == 1;
	}

	private static int sumOfTwoFruitNums(int[] fruitNums) {
		int num1 = 0;
		int num2 = 0;

		for (int num : fruitNums) {
			if (num > 0) {
				if (num1 == 0) {
					num1 = num;
				} else {
					num2 = num;
					break;
				}
			}
		}

		return num1 + num2;
	}

	private static int sumOfFruitNums(int[] fruitNums) {
		int sum = 0;

		for (int num : fruitNums) {
			sum += num;
		}

		return sum;
	}
}
