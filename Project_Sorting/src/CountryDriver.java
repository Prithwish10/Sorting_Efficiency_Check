import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CountryDriver {

	public static void main(String args[]) {
		
		List<Double> TaxList = new ArrayList<Double>();
		List<String> countyList = new ArrayList<String>();		
		String fileName = "Sales_Tax_Rates.csv";
		
		File file = new File(fileName);
		
		//Read from the file
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.nextLine();
			while(inputStream.hasNext()) {
	
				String data = inputStream.nextLine();
				if(!data.startsWith("(")){
						
						String values[] = data.split(",");
						countyList.add(values[0]);
						TaxList.add(Double.parseDouble(values[1]));
						//System.out.println(values[0]+" " +values[1]);
				}
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(int i = 0; i < TaxRate.size(); i++)
//			System.out.println(county.get(i)+" "+TaxRate.get(i));
		
		Double taxRate[] = new Double[TaxList.size()];
		taxRate = TaxList.toArray(taxRate);
		
		String countries[] = new String[countyList.size()];
		countries = countyList.toArray(countries);
		
		System.out.println("Welcome to the sorting performance tester :");
		
		System.out.println("This program demonstrates the diiferent sorting algorithms "
				+ "and their efficiencies in sorting missouri country sales tax data !!");
		
		CountryTax countryTax[] = new CountryTax[taxRate.length];
		
		for(int i = 0; i < countryTax.length; i++)
			countryTax[i] = new CountryTax(taxRate[i], countries[i]);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Bubble Sort\n"
				+ "2. Insertion Sort\n"
				+ "3. Selection Sort\n"
				+ "4. Merge Sort\n"
				+ "5. Quick Sort\n"
				+ "6. Heap Sort\n");
		System.out.println("Which sorting Algorithm do u want to test :");
		
		int ch = sc.nextInt();
		switch(ch) {
		
		case 1:
			bubbleSort(countryTax);
			break;
		case 2:
			insertionSort(countryTax);
			break;
		case 3:
			selectionSort(countryTax);
			break;
		case 4:
			mergeSort(countryTax);
			break;
		case 5:
			quickSort(countryTax);
			break;
		case 6:
			heapSort(countryTax);
			break;
			default:
				System.out.println("Invalid Choice !!");
				break;
		}
	}
	public static void bubbleSort(CountryTax original[]) {
		
		CountryTax sorted[] = Arrays.copyOf(original, original.length);
		long startTime = System.currentTimeMillis();
		
		bSort(sorted, sorted.length);
		printArray(sorted);
		
		long endTime = System.currentTimeMillis();
		System.out.println("The sort took "+ (double)(endTime - startTime)/1000 + "seconds");
	}
	
	public static void bSort(CountryTax sorted[], int n) {
		
		CountryTax temp;
		boolean flag = false;
		
		for(int i = 0; i < n - 1; i++) {
			
			for(int j = 0; j < n - i - 1; j++) {
				
				if(sorted[j].getTax() > sorted[j + 1].getTax()) {
					
					flag = true;
					temp = sorted[j];
					sorted[j] = sorted[j + 1];
					sorted[j + 1] = temp;
				}
			}
			if(flag != true)
				break;
		}
	}
	
	public static void insertionSort(CountryTax original[]) {
		
		CountryTax sorted[] = Arrays.copyOf(original, original.length);
		long startTime = System.currentTimeMillis();
		
		inSort(sorted, sorted.length);
		printArray(sorted);
		
		long endTime = System.currentTimeMillis();
		System.out.println("The sort took "+ (double)(endTime - startTime)/1000 + "seconds");
	}
	
	public static void inSort(CountryTax sorted[], int n) {
		
		CountryTax temp;
		for(int i = 1; i < n; i++) {
			
			temp = sorted[i];
			int j = 0;
			for(j = i; j > 0 && temp.getTax() < sorted[j - 1].getTax(); j--)
				sorted[j] = sorted[j -1];
			sorted[j] = temp;
		}
	}
	
	public static void selectionSort(CountryTax original[]) {
		
		CountryTax sorted[] = Arrays.copyOf(original, original.length);
		long startTime = System.currentTimeMillis();
		
		sSort(sorted, sorted.length);
		printArray(sorted);
		
		long endTime = System.currentTimeMillis();
		System.out.println("The sort took "+ (double)(endTime - startTime)/1000 + "seconds");
	}
	
	public static void sSort(CountryTax sorted[], int n) {
		
		CountryTax temp;
		for(int i = 0; i < n - 1; i++) {
			
			int min = i;
			for(int j = i + 1; j < n; j++) {
				if(sorted[j].getTax() < sorted[min].getTax())
					min = j;
			}
			if(min != i) {
				temp = sorted[i];
				sorted[i] = sorted[min];
				sorted[min] = temp;
			}
		}
	}
	
	public static void mergeSort(CountryTax original[]) {
		
		CountryTax sorted[] = Arrays.copyOf(original, original.length);
		long startTime = System.currentTimeMillis();
		
		mSort(sorted, 0, sorted.length - 1);
		printArray(sorted);
		
		long endTime = System.currentTimeMillis();
		System.out.println("The sort took "+ (double)(endTime - startTime)/1000 + "seconds");
	}
	
	public static void mSort(CountryTax sorted[], int low, int high) {
		
		if(low < high) {
			
			int mid = (low + high)/2;
			
			mSort(sorted, low, mid);
			mSort(sorted, mid + 1, high);
			merge(sorted, low, mid, high);
		}
	}
	
	public static void merge(CountryTax sorted[], int low, int mid, int high) {
		
		int n = mid - low + 1;
		int m = high - mid;
		
		CountryTax L[] = new CountryTax[n];
		CountryTax R[] = new CountryTax[m];
		
		for(int i = 0; i < n; i++)
			L[i] = sorted[low + i];
		
		for(int i = 0; i < m; i++)
			R[i] = sorted[mid + 1 +i];
		
		int i = 0;
		int j = 0;
		int k = low;
		
		while(i < n && j < m) {
			
			if(L[i].getTax() <= R[j].getTax()) {
				sorted[k++] = L[i];
				i++;
			}
			else {
				sorted[k++] = R[j];
				j++;
			}
		}
		
		for(;i < n; i++)
			sorted[k++] = L[i];
		
		for(;j < m; j++)
			sorted[k++] = R[j];
	}
	
	public static void quickSort(CountryTax original[]) {
		
		CountryTax sorted[] = Arrays.copyOf(original, original.length);
		long startTime = System.currentTimeMillis();
		
		bSort(sorted, sorted.length);
		
		long endTime = System.currentTimeMillis();
		System.out.println("The sort took "+ (double)(endTime - startTime)/1000 + "seconds");
	}
	
	public static void heapSort(CountryTax original[]) {
		
		CountryTax sorted[] = Arrays.copyOf(original, original.length);
		long startTime = System.currentTimeMillis();
		
		hSort(sorted, sorted.length);
		printArray(sorted);
		
		long endTime = System.currentTimeMillis();
		System.out.println("The sort took "+ (double)(endTime - startTime)/1000 + "seconds");
	}
	
	public static void hSort(CountryTax sorted[], int n) {
		
		PriorityQueue<CountryTax> minHeap = new PriorityQueue<CountryTax>(
				(n1, n2) -> (int)(n1.getTax() - n2.getTax()));
		
		for(CountryTax c : sorted) {
			minHeap.add(c);
		}
		int i=0;
		while(!minHeap.isEmpty())
			sorted[i++] = minHeap.poll();
	}
	
	public static void printArray(CountryTax sorted[]) {
		
		for(CountryTax i : sorted)
			System.out.println(i.getCountry()+" has a Tax Rate of "+i.getTax());
	}
}
