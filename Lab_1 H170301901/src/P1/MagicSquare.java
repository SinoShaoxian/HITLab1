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
			return false; // ��nΪż�����߸��������ش���
		FileWriter fw = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			File file = new File("src/P1/6.txt");
			fw = new FileWriter(file, true);
			if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();  //����ļ���ԭ��ӵ�е�����
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
					System.out.println("�ָ�������");
					return false;  //���ָ��������򷵻ش���
					}
				sum1 = 0;
				arr = str.split("\t"); // ��һ�����ݰ��տո�ֿ�
				for (String a : arr) {
					sum1++;
				}
			}
			for (i = 0; i < sum1; i++) {
				if (arr[i].indexOf(".") >= 0) // �������д���С�����򷵻ش���
					{
					System.out.println("����С��");
					return false;
					}
				if (arr[i].indexOf("-") >= 0) // �������д��ڸ������򷵻ش���
					{
					System.out.println("���ڸ���");
					return false;
					}
			}
			darr = new int[sum1][sum1]; // ����ά���������ڴ�
			i = 0;
			for (String a : arr) {
				darr[count][i] = Integer.parseInt(a); // ��һ�и�ֵ
				i++;
			}
			count++;
			while ((str = bf.readLine()) != null) {
				if (str.indexOf(" ")>=0)
					{
					System.out.println("�ָ�������");
					return false;  //���ָ��������򷵻ش���
					}
				arr = str.split("\t"); // ��һ�����ݰ��տո�ֿ�
				sum2 = 0;
				for (String a : arr) {
					sum2++;
				}
				if (sum1 != sum2) {
					System.out.println("���и�����ͬ");
					return false; // ���е����ָ���������ͬ���򷵻ش���
				}
				sum1 = sum2;
				for (i = 0; i < sum1; i++) {
					if (arr[i].indexOf(".") >= 0) // �������д���С�����򷵻ش���
						{
						System.out.println("����С��");
						return false;
						}
					if (arr[i].indexOf("-") >= 0) // �������д��ڸ������򷵻ش���
						{
						System.out.println("���ڸ���");
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
				System.out.println("���и�����ͬ");
				return false; // ������������������ȣ��򷵻ش���
			}
			int temp1 = 0;
			int temp2 = 0;
			int temp3 = 0;
			for (i = 0; i < sum1; i++) {
				temp1 += darr[0][i];
				temp2 += darr[i][0]; // �Ե�һ�е�һ����Ϊ��׼
			}
			for (count = 1; count < sum1; count++) {
				for (i = 0; i < sum1; i++) {
					temp3 += darr[count][i]; // ��ÿ������֮���Ƿ���ͬ
				}
				if (temp3 != temp1) {
					System.out.println("����֮�Ͳ�ͬ");
					return false;
				}
				temp3 = 0;
			}
			for (count = 1; count < sum1; count++) {
				for (i = 0; i < sum1; i++) {
					temp3 += darr[i][count]; // ��ÿ������֮���Ƿ���ͬ
				}
				if (temp3 != temp2) {
					System.out.println("����֮�Ͳ�ͬ");
					return false;
				}
				temp3 = 0;
			}
			if (temp1 != temp2)
				{
				System.out.println("����֮�Ͳ�ͬ");
				return false; // ����֮������֮�Ͳ���ȣ����ش���
				}
			for (i = 0; i < sum1; i++) {
				temp3 += darr[i][i]; // ���Խ���֮���Ƿ���ͬ
			}
			if (temp3 != temp1)
				{
				System.out.println("����֮�Ͳ�ͬ");
				return false;
				}
			temp3 = 0;
			for (i = 0; i < sum1; i++) {
				temp3 += darr[sum1 - 1 - i][i]; // ���Խ���֮���Ƿ���ͬ
			}
			if (temp3 != temp1)
				{
				System.out.println("����֮�Ͳ�ͬ");
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
			if (MagicSquare.isLegalMagicSquare(String.valueOf(i) + ".txt")) // ѭ��������ı��ļ��������ȥ
				System.out.println("Yes"); // ���õ�true�����yes
			else {
				System.out.println("No"); // �������no
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
