
import java.util.*;


public class Main 
{

	
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		while(choice != 3)
		{
			System.out.println("===================");
			System.out.println("1. Encode");
			System.out.println("2. Decode");
			System.out.println("3. End");
			System.out.print("Option number ? : ");
			choice = Integer.parseInt(scan.nextLine());
			Encoder en = new Encoder();
		

			if(choice == 1)
			{
				System.out.print("ENTER PLAINTEXT : ");
				String plainText = scan.nextLine();
				System.out.println("ENCODED TEXT: " + en.encode(plainText.toUpperCase()));
			} 
			else if(choice == 2)
			{
				System.out.print("ENTER DECODEDTEXT : ");
				String decodedText = scan.nextLine();
				//System.out.println(decodedText.toUpperCase());
				System.out.println("DECODED TEXT: " + en.decode(decodedText.toUpperCase())); 
			}
			 else if(choice == 3)
			{
				scan.close();
				System.out.println("End of program");
			}
			else 
			{
				System.out.println("Invalid choice");
			}
		}
		
	} // END MAIN 


}// END CLASS MAIN

class Encoder 
{
	
	
	Scanner scan = new Scanner(System.in);
	ArrayList <Character> refTable   = new ArrayList <> ();
	ArrayList <Character> shiftTable = new ArrayList <> ();
	
	public String encode (String plainText)
	{
		populateRef(refTable);
		System.out.print("OFFSET eg(A-Z or 1-9) :");
		String str = (scan.next()).toUpperCase();
		char input = str.charAt(0);
		String encodedText = "";
		
		
		populateShift(shiftTable,input);
		encodedText = encodedText + input;
				
		for(int i = 0 ; i < plainText.length(); i++)
		{
			if(plainText.charAt(i) != ' ')
			{
				
				int offsetValue = refTable.indexOf(plainText.charAt(i));
					encodedText = encodedText + shiftTable.get(offsetValue);
			}
			else 
			{
				encodedText = encodedText + " ";
			}
		}
			
		return encodedText;
	}
	
	public String decode (String encodedText)
	{
			populateRef(refTable);
			char offset = encodedText.charAt(0);
			populateShift(shiftTable,offset);
			
			int shiftValue = refTable.indexOf(offset);
			String decodedText = "";
				
		for(int i = 1;  i < encodedText.length(); i++)
		{	
				if(encodedText.charAt(i) != ' ')
				{
					int encodedIndex = shiftTable.indexOf(encodedText.charAt(i));
					decodedText = decodedText + refTable.get(encodedIndex);
				}
				else 
				{
					decodedText = decodedText + " ";
				}
			
		} 
	
	return decodedText;
	}
	
	public void populateRef(ArrayList <Character> refTable)
	{
		for (char c = 'A'; c <= 'Z'; ++c)
            refTable.add(c);

		for (char c = '0'; c <= '9'; ++c)
            refTable.add(c);
		
		for (char c = '('; c <= '/'; ++c)
            refTable.add(c);
		
		
	}
	
	public void populateShift(ArrayList <Character> shiftTable, char input)
	{
		int offset = refTable.indexOf(input);
		
		// populate arraylist accoring to the shift in index 
		for(int i = 0; i < refTable.size() - offset; i++)
			shiftTable.add(refTable.get(i));
		
		
		// add in remaining characters 
		for(int i = refTable.size() - 1 ; i >=  refTable.size() - offset;i--)
			shiftTable.add(0,refTable.get(i));	
		
	}

} // END ENCODER CLASS