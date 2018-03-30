package bean;

import java.util.List;

public class Contact {
    private String name;
    private String email;
    private List<String> address;

    Contact(String initNmae, String initEmail, List<String> initAddress) {
        name = initNmae;
        email = initEmail;
        address = initAddress;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getAddress(int i) { return address.get(i); }

}
