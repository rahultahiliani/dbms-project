import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class a1 {
	
	public static class metadata {
		int fields;
		ArrayList<String> fieldName;
		ArrayList<String> type;
		ArrayList<Integer> size;
		
		public metadata() {
			BufferedReader reader;
			fieldName = new ArrayList<String>();
			type = new ArrayList<String>();
			size = new ArrayList<Integer>();
			System.out.println();
			
			try {
				reader = new BufferedReader(new FileReader("metadata.txt"));
				String row = reader.readLine();
				
				while(row != null) {				
					String[] csv = row.split(",");
					fieldName.add(csv[0]);
					type.add(csv[1]);
					size.add(Integer.parseInt(csv[2]));
					
					System.out.println("Field: " + csv[0] + ", type: " + csv[1] + ", size: " + csv[2]);
					
					row = reader.readLine();
				}
				
				System.out.println();
				System.out.println("Finish reading data description file....");
				reader.close();
				fields = fieldName.size();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		metadata M = new metadata();
		
		BufferedReader reader;
		String field = args[0];
		float max = Float.NEGATIVE_INFINITY;
		
		try {
			reader = new BufferedReader(new FileReader("db.data"));
			System.out.println();
			System.out.println("The data file contains these records:");
			System.out.println();
			
			String row = reader.readLine();
			
			while(row!=null) {
				
				for(int i=0; i<M.fields; i++) {
					//System.out.println(M.size.get(i));
					String value = row.substring(0, M.size.get(i));
					//System.out.println(value);
					
					if(M.type.get(i).equals("F")) {
						value = value.strip();
						float val = Float.parseFloat(value);
						System.out.print(val + " ");
						
						if(M.fieldName.get(i).equals(field)) {
							if(val>max)
								max = val;
						}
					}
					else if(M.type.get(i).equals("I")) {
						value = value.strip();
						int val = Integer.parseInt(value);
						System.out.print(val + " ");
						
						if(M.fieldName.get(i).equals(field)) {
							if(val>max)
								max = val;
						}
					}
					else {
						String val = value.strip();
						System.out.print(val + " ");
						
						if(M.fieldName.get(i).equals(field)) {
							max = -1;
						}
					}
					
					row = row.substring(M.size.get(i));
				}
				
				row = reader.readLine();
				System.out.println();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Find max value in the field " + field);
		if(max==Float.NEGATIVE_INFINITY) {
			System.out.println("--- Error: field name not found.");
		}
		else {
			System.out.println("Max = " + max);
		}
	}
}
