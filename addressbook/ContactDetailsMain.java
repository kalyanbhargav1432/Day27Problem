package addressbook;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;

public class ContactDetailsMain {

	public static Hashtable<Integer, ArrayList<AddContact>> hashTable = new Hashtable<Integer, ArrayList<AddContact>>();
	static InputScanner inputScanner = new InputScanner();
	static AddContact addContact = new AddContact();

	private static char inputCharater() {
		Scanner scanCharater = new Scanner(System.in);
		char charaterInput = scanCharater.next().charAt(0);
		return charaterInput;
	}

	public static void main(String[] args) {
		System.out.println("enter the address book limit:");
		int addressBookLimit = inputScanner.inputInteger();
		for (int i = 1; i <= addressBookLimit; i++) {
			System.out.println("enter name of addressbook:");
			String addressBookName = inputScanner.inputString();
			System.out.println("Addres book name is :" + addressBookName);
			ArrayList<AddContact> addDetails = new <AddContact>ArrayList();
			while (true) {
				AddContact addPersonDetails = new AddContact();
				System.out.printf(
						"Input the Charater A to add Details E to Edit details D to delete details\n Any charater to Ignore");
				char charater = inputCharater();
				if (charater == 'A' || charater == 'E' || charater == 'D' || charater == 'a' || charater == 'e'
						|| charater == 'd') {
					switch (charater) {
					case 'A':
						addPersonDetails.setContactDetails();
						boolean checkDuplicate = addPersonDetails.checkDuplicate(addDetails, addPersonDetails);
						if (!checkDuplicate)
							addDetails.add(addPersonDetails);
						else
							System.out.println("Dont enter duplicate entry");
						break;
					case 'E':
						System.out.println("Enter the name to edit");
						addPersonDetails.editDetails(addDetails);
						break;
					case 'D':
						addPersonDetails.deleteDetails(addDetails);
						break;
					default:
						System.out.println("you have not perform any operation");
						break;
					}

				} else {
					System.out.println("Details are uptodate");
					break;
				}
			}
			hashTable.put(i, addDetails);
		}
		for (int i = 1; i <= hashTable.size(); i++) {
			System.out.println("address book " + i);
			System.out.println(hashTable.get(i));
		}
		System.out.println("Reading AddressBook from File");
        AddContact.readFromFile();
        System.out.println();
        System.out.println("AddressBooks Sorted based on city :");
        AddContact.sort(hashTable,SortOption.CITY);
        System.out.println("AddressBooks Sorted based on state :");
        AddContact.sort(hashTable,SortOption.STATE);
        System.out.println("AddressBooks Sorted based on Zip :");
        AddContact.sort(hashTable,SortOption.ZIP);
	}
}