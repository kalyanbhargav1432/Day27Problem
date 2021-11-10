package addressbook;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.FileReader;
import java.io.FileWriter;

public class AddContact extends PersonDetail {
	ArrayList<AddContact> addContactDetails;
	InputScanner inputScanner = new InputScanner();
	ContactDetailsMain contact = new ContactDetailsMain();

	public void setContactDetails() {
		System.out.println("enter the First Name");
		setFirstName(inputScanner.inputString());
		System.out.println("enter the Last Name");
		setLastName(inputScanner.inputString());
		System.out.println("enter the Address Name");
		setAddress(inputScanner.inputString());
		System.out.println("enter the city Name");
		setCity(inputScanner.inputString());
		System.out.println("enter the State Name");
		setState(inputScanner.inputString());
		System.out.println("enter the Phone Number");
		setPhoneNumber(inputScanner.inputString());
		System.out.println("enter the Zip");
		setZip(inputScanner.inputString());
		System.out.println("enter the Email");
		setEmail(inputScanner.inputString());
	}

	public ArrayList<AddContact> getContactDetails() {
		getFirstName();
		getLastName();
		getAddress();
		getCity();
		getState();
		getPhoneNumber();
		getZip();
		getEmail();
		return addContactDetails;
	}

	public void editDetails(ArrayList<AddContact> contactDetails) {
		String name = inputScanner.inputString();
		System.out.println(contactDetails.size());
		for (int i = 0; i < contactDetails.size(); i++) {
			if (contactDetails.get(i).getFirstName().equals(name)) {
				System.out.printf(
						"enter the number to update contact details 1. First Name 2. to update Last Name 3. to phone number 4. to pincode 5. to address 6. to email 7. to state 8.city");
				int updateChoice = inputScanner.inputInteger();
				inputScanner.inputInteger();

				switch (updateChoice) {
				case 1:
					System.out.println("enter the name to update");
					String fName = inputScanner.inputString();
					contactDetails.get(i).setFirstName(fName);
					break;
				case 2:
					System.out.println("enter the Last name to update");
					String lName = inputScanner.inputString();
					contactDetails.get(i).setLastName(lName);
					break;
				case 3:
					System.out.println("enter the Phone Number to Update");
					String phNumber = inputScanner.inputString();
					contactDetails.get(i).setPhoneNumber(phNumber);
					break;
				case 4:
					System.out.println("enter the Pincode to Update");
					String pinC = inputScanner.inputString();
					contactDetails.get(i).setZip(pinC);
					break;
				case 5:
					System.out.println("enter the Address to Update");
					String address = inputScanner.inputString();
					contactDetails.get(i).setAddress(address);
					break;
				case 6:
					System.out.println("enter the Email to Update");
					String email = inputScanner.inputString();
					contactDetails.get(i).setEmail(email);
					break;
				case 7:
					System.out.println("enter the State to Update");
					String state = inputScanner.inputString();
					contactDetails.get(i).setState(state);
					break;
				case 8:
					System.out.println("enter the city to Update");
					String city = inputScanner.inputString();
					contactDetails.get(i).setCity(city);
					break;
				default:
					System.out.println("you have not update any details");
					break;
				}

			} else
				System.out.println("not match any details");
		}
	}

	public void deleteDetails(ArrayList<AddContact> contactDetails) {
		System.out.println("enter the name");
		String name = inputScanner.inputString();
		for (int i = 0; i < contactDetails.size(); i++) {
			if (contactDetails.get(i).getFirstName().equals(name)) {
				contactDetails.remove(i);
			} else
				System.out.println("not match any details");
		}
	}

	public void print(ArrayList<AddContact> addContactDetails) {
		for (AddContact s : addContactDetails) {
			System.out.println((s));
		}

	}

	public boolean checkDuplicate(ArrayList<AddContact> addContactDetails2, AddContact addPersonDetails) {

		boolean check = false;
		for (AddContact contact : addContactDetails2) {
			if (addPersonDetails.getFirstName().equals(contact.getFirstName()))
				check = true;
		}
		return check;
	}

	public static void writeToFile(Hashtable<Integer, ArrayList<AddContact>> hashTable) {
		try {
			FileWriter fileWriter = new FileWriter("C:/Users/eclipse-workspace/readwritefileio.txt");
			String stream = String.valueOf(hashTable);
			fileWriter.write(stream);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readFromFile() {
		try {
			FileReader fileReader = new FileReader("C:/Users/eclipse-workspace/readwritefileio.txt");
			int i;
			while ((i = fileReader.read()) != -1) {
				System.out.print((char) i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sort(Hashtable<Integer, ArrayList<AddContact>> hashTable, SortOption sortOptions) {
		for (int i = 1; i <= hashTable.size(); i++) {
			hashTable.get(i).stream().sorted(sortOptions.comparator).forEach(System.out::println);
		}
	}

	public static void search(Hashtable<Integer, ArrayList<AddContact>> hashTable) {
		InputScanner inputScanner = new InputScanner();
		long count = 0;
		long countForState = 0;
		System.out.println("enter the city  name");
		String city = inputScanner.inputString();
		System.out.println("Enter state name");
		String state = inputScanner.inputString();
		for (int i = 1; i <= ContactDetailsMain.hashTable.size(); i++) {

			List<AddContact> cityList = ContactDetailsMain.hashTable.get(i).stream()
					.filter(c -> c.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
			Predicate<AddContact> predicate = c -> c.getCity().contains(city);
			count = cityList.stream().filter(predicate).count();
			System.out.println("Count for city: " + count);
			System.out.println("List for city" + cityList);

			List<AddContact> stateList = ContactDetailsMain.hashTable.get(i).stream()
					.filter(s -> s.getState().equalsIgnoreCase(state)).collect(Collectors.toList());
			Predicate<AddContact> predicateForState = s -> s.getState().contains(state);
			countForState = cityList.stream().filter(predicateForState).count();
			System.out.println("Count for state: " + countForState);
			System.out.println("List for state" + stateList);

		}
		System.out.println("Count for city: " + count);
		System.out.println("Count for state: " + countForState);

	}
}