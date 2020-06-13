package P1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MagicSquare {
	public static boolean generateMagicSquare(int n) {
		if (n < 0 || n % 2 == 0)
			return false; // 若n为偶数或者负数，返回错误
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File file = new File("src/P1/6.txt");
			fw = new FileWriter(file, true);
			if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();  //清空文件中原本拥有的内容
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				pw.print(magic[i][j] + "\t");
			pw.println();
			pw.flush();
		}
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	static boolean isLegalMagicSquare(String fileName) {
		String path = "src/P1/" + fileName;
		FileReader fr;
		try {
			fr = new FileReader(path);
			BufferedReader bf = new BufferedReader(fr);

			String str;
			int sum1 = 0;
			int sum2;
			String[] arr = null;
			int[][] darr;
			int i;
			int count = 0;
			if ((str = bf.readLine()) != null) {
				if (str.indexOf(" ")>=0)
					{
					System.out.println("分隔符错误");
					return false;  //若分隔符错误，则返回错误
					}
				sum1 = 0;
				arr = str.split("\t"); // 把一行数据按照空格分开
				for (String a : arr) {
					sum1++;
				}
			}
			for (i = 0; i < sum1; i++) {
				if (arr[i].indexOf(".") >= 0) // 若矩阵中存在小数，则返回错误
					{
					System.out.println("存在小数");
					return false;
					}
				if (arr[i].indexOf("-") >= 0) // 若矩阵中存在负数，则返回错误
					{
					System.out.println("存在负数");
					return false;
					}
			}
			darr = new int[sum1][sum1]; // 给二维数组申请内存
			i = 0;
			for (String a : arr) {
				darr[count][i] = Integer.parseInt(a); // 第一行赋值
				i++;
			}
			count++;
			while ((str = bf.readLine()) != null) {
				if (str.indexOf(" ")>=0)
					{
					System.out.println("分隔符错误");
					return false;  //若分隔符错误，则返回错误
					}
				arr = str.split("\t"); // 把一行数据按照空格分开
				sum2 = 0;
				for (String a : arr) {
					sum2++;
				}
				if (sum1 != sum2) {
					System.out.println("行列个数不同");
					return false; // 两行的数字个数若不相同，则返回错误
				}
				sum1 = sum2;
				for (i = 0; i < sum1; i++) {
					if (arr[i].indexOf(".") >= 0) // 若矩阵中存在小数，则返回错误
						{
						System.out.println("存在小数");
						return false;
						}
					if (arr[i].indexOf("-") >= 0) // 若矩阵中存在负数，则返回错误
						{
						System.out.println("存在负数");
						return false;
						}
				}
				i = 0;
				for (String a : arr) {
					darr[count][i] = Integer.parseInt(a);
					i++;
				}
				count++;
			}
			bf.close();
			fr.close();
			if (count != i) {
				System.out.println("行列个数不同");
				return false; // 矩阵行数和列数不相等，则返回错误
			}
			int temp1 = 0;
			int temp2 = 0;
			int temp3 = 0;
			for (i = 0; i < sum1; i++) {
				temp1 += darr[0][i];
				temp2 += darr[i][0]; // 以第一行第一列作为基准
			}
			for (count = 1; count < sum1; count++) {
				for (i = 0; i < sum1; i++) {
					temp3 += darr[count][i]; // 看每行数字之和是否相同
				}
				if (temp3 != temp1) {
					System.out.println("数字之和不同");
					return false;
				}
				temp3 = 0;
			}
			for (count = 1; count < sum1; count++) {
				for (i = 0; i < sum1; i++) {
					temp3 += darr[i][count]; // 看每列数字之和是否相同
				}
				if (temp3 != temp2) {
					System.out.println("数字之和不同");
					return false;
				}
				temp3 = 0;
			}
			if (temp1 != temp2)
				{
				System.out.println("数字之和不同");
				return false; // 若行之和与列之和不相等，返回错误
				}
			for (i = 0; i < sum1; i++) {
				temp3 += darr[i][i]; // 检查对角线之和是否相同
			}
			if (temp3 != temp1)
				{
				System.out.println("数字之和不同");
				return false;
				}
			temp3 = 0;
			for (i = 0; i < sum1; i++) {
				temp3 += darr[sum1 - 1 - i][i]; // 检查对角线之和是否相同
			}
			if (temp3 != temp1)
				{
				System.out.println("数字之和不同");
				return false;
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		MagicSquare magic = new MagicSquare();
		for (int i = 1; i < 6; i++) {
			System.out.print(i+":");
			if (MagicSquare.isLegalMagicSquare(String.valueOf(i) + ".txt")) // 循环将五个文本文件名输入进去
				System.out.println("Yes"); // 若得到true，输出yes
			else {
				System.out.println("No"); // 否则输出no
			}
		}
		boolean is = MagicSquare.generateMagicSquare(5);
		if (is == false)
			System.out.println(is);
		if (MagicSquare.isLegalMagicSquare("6.txt"))
			System.out.println("6:Yes");
		else
			System.out.println("6:No");
	}

}
