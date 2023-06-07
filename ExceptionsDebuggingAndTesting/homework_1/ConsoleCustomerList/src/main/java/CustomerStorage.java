import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private final String regexPhoneNumber = "[^0-9]";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");

        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format information for add!");
        }

        components[INDEX_PHONE] = components[INDEX_PHONE].replaceAll(regexPhoneNumber,"");

        if (!verificationPhoneNumber(components[INDEX_PHONE])) {
            throw new IllegalArgumentException("Wrong format phone number");
        }

        if (!verificationEmailAddress(components[INDEX_EMAIL])) {
            throw new IllegalArgumentException("Wrong format email address");
        }


        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public boolean verificationPhoneNumber(String phoneNumber) {


        if(phoneNumber.length() != 11){
            return false;
        }

        if (phoneNumber.charAt(0) != '7' && phoneNumber.charAt(0) != '8'){
            return false;
        }

        return true;
    }

    public boolean verificationEmailAddress(String emailAddress) {
        final String regex = "[a-zA-Z0-9.]+@[A-Za-z]+[.][a-z]+";
        return emailAddress.matches(regex);
    }


    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}