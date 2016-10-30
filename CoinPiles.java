import java.util.*;

public class CoinPiles {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		int testNum = scanner.nextInt();
		for(int j = 0; j<testNum; j++){
			int len = scanner.nextInt();
			int k = scanner.nextInt();
			int[] test = new int[len];
			for(int i = 0; i<len; i++){
				test[i] = scanner.nextInt();
			}
			int rm = remove(test, len, k);
			System.out.println(rm);
		}
		scanner.close();

	}

	private static int remove(int[] test, int len, int k){
		int output = 0;
		int score = 0;
		int location;
		Arrays.sort(test);
		int[] sum = new int[len];
		sum[0] = test[0];
		for (int i = 1; i<len; i++){
			sum[i] = sum[i-1] + test[i];
		}
		location = find(test, test[0]+k, 0, len-1);
		score = (sum[len-1] - sum[location]-(len-1-location)*(test[0]+k));
		output = score;
		for (int i = 1; i<len; i++){
			location = find(test, test[i]+k, 0, len-1);
			score = (sum[len-1] - sum[location]-(len-1-location)*(test[i]+k)) + sum[i-1];
			if (output > score){
				output = score;
			}
		}
		return output;
	}
	
	private static int find(int[] test, int a, int start, int end){
		if (end - start >= 1){
			int mid = start + (end - start)/2;
			if (test[mid]>a)
				return find(test, a, start, mid-1);
			else
				return find(test, a, mid+1, end);
		}
		else{
			if (test[start] <= a)
				return start;
			else
				return start - 1;
		}
	}

}
